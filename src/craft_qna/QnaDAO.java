package craft_qna;
import java.sql.*;
import javax.sql.*;//DataSource
import javax.naming.*;//lookup
import java.util.*;//List, ArrayList

//DAO : DB처리(비지니스 로직)
public class QnaDAO {
	Connection con=null;
	Statement stmt=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	String sql="";
	
	//싱글톤 객체 사용(메모리 절약 효과)
	private static QnaDAO dao=new QnaDAO();//객체생성
	
	private QnaDAO(){}//생성자 , private하면 외부에서 접근 못함 
	
	public static QnaDAO getInstance(){
		return dao;
	}
	
	//커넥션 연결하기 
	private Connection getCon() throws Exception{
		Context ct=new InitialContext();
		DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		
		return ds.getConnection();
	}
	
	//-------------------------
	//  원글쓰기, 답글 쓰기 
	//-------------------------
	public void insertBoard(QnaDTO dto) throws Exception{
		int qna_num=dto.getQna_num();
		int qna_ref=dto.getQna_ref();
		int qna_re_step=dto.getQna_re_step();
		int qna_re_level=dto.getQna_re_level();

		
		int qna_number=0;//글 그룹 처리 하기 위한 변수
		try{
			//처리 
			con=getCon();//커넥션 얻기
			
			//최대 글번호 얻기 
			pstmt=con.prepareStatement("select max(qna_num) from craft_qna");
			rs=pstmt.executeQuery();//쿼리 수행
			
			if(rs.next()){//글이 존재 하면 
				qna_number=rs.getInt(1)+1;//최대글번호+1,  글 그룹에 사용하려고
				//rs.getString("id");//필드 이름
				//rs.getString(1);//필드번호
				
			}else{//글이 없을때
				qna_number=1;//처음 글  qna_ref=qna_number;
			}//else end---
			
			if(qna_num!=0){//답글이면
				
				//답글 끼워넣을 위치 확보 
				sql="update craft_qna set qna_re_step=qna_re_step+1 where qna_ref=? and qna_re_step>?";
				pstmt=con.prepareStatement(sql);//생성시 인자 들어간다 
				
				pstmt.setInt(1, qna_ref);//?값 채우기 
				pstmt.setInt(2, qna_re_step);
				
				pstmt.executeUpdate();//쿼리 수행 
				
				qna_re_step=qna_re_step+1;
				qna_re_level=qna_re_level+1;
				
			}else{//원글이면, 첫번째 글이면
				qna_ref=qna_number;
				qna_re_step=0;
				qna_re_level=0;
			}//else -end
			
			//글쓰기
			sql="insert into craft_qna(qna_writer,qna_subject,qna_pw,qna_regdate,"+
			"qna_ref,qna_re_step,qna_re_level,qna_content,qna_ip) "+
			"values(?,?,?,NOW(),?,?,?,?,?)";
			
			pstmt=con.prepareStatement(sql);//생성지 인자 들어간다 
			
			pstmt.setString(1, dto.getQna_writer());
			pstmt.setString(2, dto.getQna_subject());
			pstmt.setString(3, dto.getQna_pw());
			
			pstmt.setInt(4, qna_ref);//qna_ref
			pstmt.setInt(5, qna_re_step);
			pstmt.setInt(6, qna_re_level);
			
			pstmt.setString(7, dto.getQna_content());
			pstmt.setString(8, dto.getQna_ip());
			
			pstmt.executeUpdate();
			
		}catch(Exception ex1){
			System.out.println("insertBoard() 예외 :"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(stmt!=null){stmt.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		
		
	}//insertBoard() end
	//---------------------
	// 글 갯수
	//---------------------
	public int getBoardCount() throws Exception{
		int cnt=-1;//초기화
		
		try{
			//처리
			con=getCon();//커넥션 얻기
			pstmt=con.prepareStatement("select count(*) from craft_qna");//생성시 인자 들어간다
			
			rs=pstmt.executeQuery();//쿼리 수행 
			
			if(rs.next()){
				cnt=rs.getInt(1);//1은 필드 번호, 글 갯수 
				System.out.println("cnt:"+cnt);
			}
		}catch(Exception ex1){
			System.out.println("getBoardCount() 예외 :"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally end
		
		return cnt;
	}// getBoardCount()
	
	//---------------------
	// 리스트
	//---------------------
	public List getList(int qna_startRow, int qna_pageSize) throws Exception{
		//System.out.println("startRow:"+startRow);
		//System.out.println("pageSize:"+pageSize);
		List<QnaDTO> list=null;//변수
        try{
        	//처리
        	con=getCon();
        	sql="select * from craft_qna order by qna_ref desc,qna_re_step asc limit ?,?";
        	//                                                          시작,갯수
        	//limit 시작 위치는  0부터 할 것
        	
        	pstmt=con.prepareStatement(sql);//생성시 인자 들어간다 
        	
        	pstmt.setInt(1,qna_startRow-1);//?값채우기 ,0부터 시작
        	pstmt.setInt(2,qna_pageSize);//?값채우기 
        	
        	rs=pstmt.executeQuery();//쿼리 수행 
        	
        	if(rs.next()){
        		list=new ArrayList<QnaDTO>();
        		do{
        			QnaDTO dto=new QnaDTO();//객체 생성
        			
        			dto.setQna_num(rs.getInt("qna_num"));
        			dto.setQna_writer(rs.getString("qna_writer"));
        			dto.setQna_subject(rs.getString("qna_subject"));
        			dto.setQna_pw(rs.getString("qna_pw"));
        			dto.setQna_regdate(rs.getDate("qna_regdate"));//*****
        			
        			dto.setQna_readcount(rs.getInt("qna_readcount"));//조횟수 
        			dto.setQna_ref(rs.getInt("qna_ref"));//글 그룹
        			dto.setQna_re_step(rs.getInt("qna_re_step"));//글 순서
        			dto.setQna_re_level(rs.getInt("qna_re_level"));//글 깊이
        			
        			dto.setQna_content(rs.getString("qna_content"));
        			dto.setQna_ip(rs.getString("qna_ip"));
        			
        			list.add(dto);/////////******** 스레드 기능이 없고
        			//vec.add(dto); 스레드 기능이 있다 (채팅에서 사용)
        			
        		}while(rs.next());
        	}//if
        	
        }catch(Exception ex1){
        	System.out.println("getList() 예외 :"+ex1);
        }finally{
        	try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
        }//finally-end
		
		return list;
	}//getList() - end
	
	//-----------------------
	// 글내용보기
	//-----------------------
	public QnaDTO getBoard(int qna_num) throws Exception{
		QnaDTO dto=null;
		try{
			//처리
			con=getCon();
			
			//조횟수 증가
			sql="update craft_qna set qna_readcount=qna_readcount+1 where qna_num=?";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, qna_num);
			pstmt.executeUpdate();//쿼리 수행 
			//-----------------------------
			
			//글 내용보기
			pstmt=con.prepareStatement("select * from craft_qna where qna_num=?");
			pstmt.setInt(1, qna_num);
			rs=pstmt.executeQuery();//쿼리 수행
			
			if(rs.next()){
				//rs내용을 dto넣고 dto를 클라이언트로 보낸다 
				dto=new QnaDTO();//객체생성
				dto.setQna_num(rs.getInt("qna_num"));
				dto.setQna_writer(rs.getString("qna_writer"));
				dto.setQna_subject(rs.getString("qna_subject"));
				dto.setQna_pw(rs.getString("qna_pw"));
				dto.setQna_regdate(rs.getDate("qna_regdate"));
				
				dto.setQna_readcount(rs.getInt("qna_readcount"));
				dto.setQna_ref(rs.getInt("qna_ref"));
				dto.setQna_re_step(rs.getInt("qna_re_step"));
				dto.setQna_re_level(rs.getInt("qna_re_level"));
				
				dto.setQna_content(rs.getString("qna_content"));
				dto.setQna_ip(rs.getString("qna_ip"));
				
			}//if-end
		}catch(Exception ex1){
        	System.out.println("getBoard() 예외 :"+ex1);
        }finally{
        	try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
        }//finally-end
		
		return dto;
	}//getBoard() end
	
	//-----------------------------
	// 글수정 클라이언트로 보낼 데이터 
	//-----------------------------
	public QnaDTO getUpdate(int qna_num) throws Exception{
		QnaDTO dto=null;
		
		try{
		con=getCon();
		//글 내용보기
		pstmt=con.prepareStatement("select * from craft_qna where qna_num=?");
		pstmt.setInt(1, qna_num);
		rs=pstmt.executeQuery();//쿼리 수행
		
		if(rs.next()){
			//rs내용을 dto넣고 dto를 클라이언트로 보낸다 
			dto=new QnaDTO();//객체생성
			dto.setQna_num(rs.getInt("qna_num"));
			dto.setQna_writer(rs.getString("qna_writer"));
			dto.setQna_subject(rs.getString("qna_subject"));
			dto.setQna_pw(rs.getString("qna_pw"));
			dto.setQna_regdate(rs.getDate("qna_regdate"));
			
			dto.setQna_readcount(rs.getInt("qna_readcount"));
			dto.setQna_ref(rs.getInt("qna_ref"));
			dto.setQna_re_step(rs.getInt("qna_re_step"));
			dto.setQna_re_level(rs.getInt("qna_re_level"));
			
			dto.setQna_content(rs.getString("qna_content"));
			dto.setQna_ip(rs.getString("qna_ip"));
			
		}//if-end
	}catch(Exception ex1){
    	System.out.println("getBoard() 예외 :"+ex1);
    }finally{
    	try{
			if(rs!=null){rs.close();}
			if(pstmt!=null){pstmt.close();}
			if(con!=null){con.close();}
		}catch(Exception ex2){}
    }//finally-end
	
	return dto;
	}//getUpdate() end
	
	//-------------------
	//DB 글수정
	//-------------------
	public int updateBoard(QnaDTO dto) throws Exception{
		String dbqna_pw="";
		int x=-1;
		try{
			//처리
			con=getCon();
			pstmt=con.prepareStatement("select qna_pw from craft_qna where qna_num=?");
			pstmt.setInt(1, dto.getQna_num());//?값 채우기 
			rs=pstmt.executeQuery();//쿼리 수행 
			
			if(rs.next()){
				dbqna_pw=rs.getString("qna_pw");
				String qna_pw=dto.getQna_pw();
				
				if(dbqna_pw.equals(qna_pw)){//암호가 일치하면 글 수정
				  sql="update craft_qna set qna_writer=?,qna_subject=?,qna_content=? where qna_num=?";
				  pstmt=con.prepareStatement(sql);//생성시 인자 들어간다 
				  
				  pstmt.setString(1, dto.getQna_writer());
				  pstmt.setString(2, dto.getQna_subject());
				  pstmt.setString(3, dto.getQna_content());
				  pstmt.setInt(4, dto.getQna_num());
				  
				  pstmt.executeUpdate();//쿼리 수행
				  x=1;//정상적인 수정
				}else if(!dbqna_pw.equals(qna_pw)){
					//암호가 틀릴때 
					x=0;
				}else if(qna_pw==""){
					x=2;
				}
			}//if
			
			
		}catch(Exception ex1){
	    	System.out.println("updateBoard() 예외 :"+ex1);
	    }finally{
	    	try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
	    }//finally-end
		
		return x;
	}//updateBoard() end
	
	//--------------------
	// 글삭제
	//--------------------
	public int deleteBoard(int qna_num,String qna_pw) throws Exception{
		String dbqna_pw="";
		int x=-1;
		
		try{
			//처리
			con=getCon();
			pstmt=con.prepareStatement("select qna_pw from craft_qna where qna_num=?");
			pstmt.setInt(1, qna_num);
			
			rs=pstmt.executeQuery();//쿼리 수행 
			if(rs.next()){
				dbqna_pw=rs.getString("qna_pw");
				if(dbqna_pw.equals(qna_pw)){//암호가 일치하면 글 삭제 
					pstmt=con.prepareStatement("delete from craft_qna where qna_num=?");
					
					pstmt.setInt(1, qna_num);
					pstmt.executeUpdate();//쿼리 수행
					x=1;//정상적으로 삭제 
					
				}else{//암호가 일치하지 않으면
					x=0;//삭제 실패
				}
			}
			
		}catch(Exception ex1){
			System.out.println("deleteBoard() 예외 :"+ex1);
		}finally{
	    	try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
	    }//finally-end
		
		return x;
	}//deleteBoard() -end
	
}//class end
