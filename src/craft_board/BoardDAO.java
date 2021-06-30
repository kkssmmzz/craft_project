package craft_board;
import java.sql.*;
import javax.sql.*;//DataSource
import javax.naming.*;//lookup
import java.util.*;//List, ArrayList

//DAO : DB처리(비지니스 로직)
public class BoardDAO {
	Connection con=null;
	Statement stmt=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	String sql="";
	
	//싱글톤 객체 사용(메모리 절약 효과)
	private static BoardDAO dao=new BoardDAO();//객체생성
	
	private BoardDAO(){}//생성자 , private하면 외부에서 접근 못함 
	
	public static BoardDAO getInstance(){
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
	public void insertBoard(BoardDTO dto) throws Exception{
		int craft_num=dto.getCraft_num();
		int craft_ref=dto.getCraft_ref();
		int craft_re_step=dto.getCraft_re_step();
		int craft_re_level=dto.getCraft_re_level();
		
		int number=0;//글 그룹 처리 하기 위한 변수
		try{
			//처리 
			con=getCon();//커넥션 얻기
			
			//최대 글번호 얻기 
			pstmt=con.prepareStatement("select max(craft_num) from craft_board");
			rs=pstmt.executeQuery();//쿼리 수행
			
			if(rs.next()){//글이 존재 하면 
				number=rs.getInt(1)+1;//최대글번호+1,  글 그룹에 사용하려고
				//rs.getString("id");//필드 이름
				//rs.getString(1);//필드번호
				
			}else{//글이 없을때
				number=1;//처음 글  ref=number;
			}//else end---
			
			if(craft_num!=0){//답글이면
				//답글 끼워넣을 위치 확보 
				sql="update craft_board set craft_re_step=craft_re_step+1 where craft_ref=? and craft_re_step>?";
				pstmt=con.prepareStatement(sql);//생성시 인자 들어간다 
				
				pstmt.setInt(1, craft_ref);//?값 채우기 
				pstmt.setInt(2, craft_re_step);
				
				pstmt.executeUpdate();//쿼리 수행 
				
				craft_re_step=craft_re_step+1;
				craft_re_level=craft_re_level+1;
				
				
			}else{//원글이면, 첫번째 글이면
				craft_ref=number;
				craft_re_step=0;
				craft_re_level=0;
			}//else -end
			
			//글쓰기
			sql="insert into craft_board(craft_writer, craft_subject, craft_pw, craft_regdate, "+
			"craft_ref, craft_re_step, craft_re_level, craft_content, craft_ip) "+
			"values(?,?,?,NOW(),?,?,?,?,?)";
			
			pstmt=con.prepareStatement(sql);//생성지 인자 들어간다 
			
			pstmt.setString(1, dto.getCraft_writer());
			pstmt.setString(2, dto.getCraft_subject());
			pstmt.setString(3, dto.getCraft_pw());
			
			pstmt.setInt(4, craft_ref);//ref
			pstmt.setInt(5, craft_re_step);
			pstmt.setInt(6, craft_re_level);
			
			pstmt.setString(7, dto.getCraft_content());
			pstmt.setString(8, dto.getCraft_ip());
			
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
	//  글 갯수
	//---------------------
	public int getBoardCount() throws Exception{
		int cnt=-1;//초기화
		
		try{
			//처리
			con=getCon();//커넥션 얻기
			pstmt=con.prepareStatement("select count(*) from craft_board");//생성시 인자 들어간다
			
			rs=pstmt.executeQuery();//쿼리 수행 
			
			if(rs.next()){
				cnt=rs.getInt(1);//1은 필드 번호, 글 갯수 
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
	//리스트
	//---------------------
	public List getList(int startRow, int pageSize) throws Exception{
		//System.out.println("startRow:"+startRow);
		//System.out.println("pageSize:"+pageSize);
		List<BoardDTO> list=null;//변수
        try{
        	//처리
        	con=getCon();
        	sql="select * from craft_board order by craft_ref desc, craft_re_step asc limit ?,?";
        	//                                                          시작,갯수
        	//limit 시작 위치는  0부터 할 것
        	
        	pstmt=con.prepareStatement(sql);//생성시 인자 들어간다 
        	
        	pstmt.setInt(1,startRow-1);//?값채우기 ,0부터 시작
        	pstmt.setInt(2,pageSize);//?값채우기 
        	
        	rs=pstmt.executeQuery();//쿼리 수행 
        	
        	if(rs.next()){
        		list=new ArrayList<BoardDTO>();
        		do{
        			BoardDTO dto=new BoardDTO();//객체 생성
        			
        			dto.setCraft_num(rs.getInt("craft_num"));
        			dto.setCraft_writer(rs.getString("craft_writer"));
        			dto.setCraft_subject(rs.getString("craft_subject"));
        			dto.setCraft_pw(rs.getString("craft_pw"));
        			dto.setCraft_regdate(rs.getDate("craft_regdate"));//*****
        			
        			dto.setCraft_readcount(rs.getInt("craft_readcount"));//조횟수 
        			dto.setCraft_ref(rs.getInt("craft_ref"));//글 그룹
        			dto.setCraft_re_step(rs.getInt("craft_re_step"));//글 순서
        			dto.setCraft_re_level(rs.getInt("craft_re_level"));//글 깊이
        			
        			dto.setCraft_content(rs.getString("craft_content"));
        			dto.setCraft_ip(rs.getString("craft_ip"));
        			
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
	//글내용보기
	//-----------------------
	public BoardDTO getBoard(int num) throws Exception{
		BoardDTO dto=null;
		try{
			//처리
			con=getCon();
			
			//조횟수 증가
			sql="update craft_board set craft_readcount=craft_readcount+1 where craft_num=?";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			pstmt.executeUpdate();//쿼리 수행 
			//-----------------------------
			
			//글 내용보기
			pstmt=con.prepareStatement("select * from craft_board where craft_num=?");
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();//쿼리 수행
			
			if(rs.next()){
				//rs내용을 dto넣고 dto를 클라이언트로 보낸다 
				dto=new BoardDTO();//객체생성
				dto.setCraft_num(rs.getInt("craft_num"));
				dto.setCraft_writer(rs.getString("craft_writer"));
				dto.setCraft_subject(rs.getString("craft_subject"));
				dto.setCraft_pw(rs.getString("craft_pw"));
				dto.setCraft_regdate(rs.getDate("craft_regdate"));
				
				dto.setCraft_readcount(rs.getInt("craft_readcount"));
				dto.setCraft_ref(rs.getInt("craft_ref"));
				dto.setCraft_re_step(rs.getInt("craft_re_step"));
				dto.setCraft_re_level(rs.getInt("craft_re_level"));
				
				dto.setCraft_content(rs.getString("craft_content"));
				dto.setCraft_ip(rs.getString("craft_ip"));
				
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
	public BoardDTO getUpdate(int num) throws Exception{
		BoardDTO dto=null;
		
		try{
		con=getCon();
		//글 내용보기
		pstmt=con.prepareStatement("select * from craft_board where craft_num=?");
		pstmt.setInt(1, num);
		rs=pstmt.executeQuery();//쿼리 수행
		
		if(rs.next()){
			//rs내용을 dto넣고 dto를 클라이언트로 보낸다 
			dto=new BoardDTO();//객체생성
			dto.setCraft_num(rs.getInt("craft_num"));
			dto.setCraft_writer(rs.getString("craft_writer"));
			dto.setCraft_subject(rs.getString("craft_subject"));
			dto.setCraft_pw(rs.getString("craft_pw"));
			dto.setCraft_regdate(rs.getDate("craft_regdate"));
			
			dto.setCraft_readcount(rs.getInt("craft_readcount"));
			dto.setCraft_ref(rs.getInt("craft_ref"));
			dto.setCraft_re_step(rs.getInt("craft_re_step"));
			dto.setCraft_re_level(rs.getInt("craft_re_level"));
			
			dto.setCraft_content(rs.getString("craft_content"));
			dto.setCraft_ip(rs.getString("craft_ip"));
			
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
	public int updateBoard(BoardDTO dto) throws Exception{
		String dbPw="";
		int x=-1;
		try{
			//처리
			con=getCon();
			pstmt=con.prepareStatement("select craft_pw from craft_board where craft_num=?");
			pstmt.setInt(1, dto.getCraft_num());//?값 채우기 
			rs=pstmt.executeQuery();//쿼리 수행 
			
			if(rs.next()){
				dbPw=rs.getString("craft_pw");
				String craft_pw=dto.getCraft_pw();
				
				if(dbPw.equals(craft_pw)){//암호가 일치하면 글 수정
				  sql="update craft_board set craft_writer=?, craft_subject=?, craft_content=? where craft_num=?";
				  pstmt=con.prepareStatement(sql);//생성시 인자 들어간다 
				  
				  pstmt.setString(1, dto.getCraft_writer());
				  pstmt.setString(2, dto.getCraft_subject());
				  pstmt.setString(3, dto.getCraft_content());
				  pstmt.setInt(4, dto.getCraft_num());
				  
				  pstmt.executeUpdate();//쿼리 수행
				  x=1;//정상적인 수정
				}else{
					//암호가 틀릴때 
					x=0;
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
	public int deleteBoard(int craft_num,String craft_pw) throws Exception{
		String dbPw="";
		int x=-1;
		
		try{
			//처리
			con=getCon();
			pstmt=con.prepareStatement("select craft_pw from craft_board where craft_num=?");
			pstmt.setInt(1, craft_num);
			
			rs=pstmt.executeQuery();//쿼리 수행 
			if(rs.next()){
				dbPw=rs.getString("craft_pw");
				if(dbPw.equals(craft_pw)){//암호가 일치하면 글 삭제 
					pstmt=con.prepareStatement("delete from craft_board where craft_num=?");
					
					pstmt.setInt(1, craft_num);
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
