package craft_faq;
import java.sql.*;

import javax.sql.*;//DataSource
import javax.naming.*;//lookup

import craft_noti.notiDTO;

import java.util.*;//List, ArrayList

//DAO : DB처리(비지니스 로직)
public class faqDAO {
	Connection con=null;
	Statement stmt=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	String sql="";
	
	//싱글톤 객체 사용(메모리 절약 효과)
	private static faqDAO dao=new faqDAO();//객체생성
	
	private faqDAO(){}//생성자 , private하면 외부에서 접근 못함 
	
	public static faqDAO getInstance(){
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
	public void insertBoard(faqDTO dto) throws Exception{
		int faq_num=dto.getFaq_num();
		int faq_ref=dto.getFaq_ref();
		int faq_re_step=dto.getFaq_re_step();
		int faq_re_level=dto.getFaq_re_level();

		
		int faq_number=0;//글 그룹 처리 하기 위한 변수
		try{
			//처리 
			//커넥션 얻기
			con=getCon();

			//글쓰기
			sql="insert into craft_faq(faq_writer, faq_subject, faq_pw, faq_regdate, faq_content, faq_ip) "+
					"values(?,?,?,NOW(),?,?)";

			pstmt=con.prepareStatement(sql);//생성시 인자가 들어간다.

			pstmt.setString(1, dto.getFaq_writer());
			pstmt.setString(2, dto.getFaq_subject());
			pstmt.setString(3, dto.getFaq_pw());
			//regdate는 NOW()로 들어감
			pstmt.setString(4, dto.getFaq_content());
			pstmt.setString(5, dto.getFaq_ip());

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
			pstmt=con.prepareStatement("select count(*) from craft_faq");//생성시 인자 들어간다
			
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
	// 리스트
	//---------------------
	public List getList(int faq_startRow, int faq_pageSize) throws Exception{
		//System.out.println("startRow:"+startRow);
		//System.out.println("pageSize:"+pageSize);
		List<faqDTO> list=null;//변수
        try{
        	//처리
        	con=getCon();
        	sql="select * from craft_faq order by faq_num desc limit ?,?";
        	//                                                          시작,갯수
        	//limit 시작 위치는  0부터 할 것
        	
        	pstmt=con.prepareStatement(sql);//생성시 인자 들어간다 
        	
        	pstmt.setInt(1,faq_startRow-1);//?값채우기 ,0부터 시작
        	pstmt.setInt(2,faq_pageSize);//?값채우기 
        	
        	rs=pstmt.executeQuery();//쿼리 수행 
        	
        	if(rs.next()){
        		list=new ArrayList<faqDTO>();
        		do{
        			faqDTO dto=new faqDTO();//객체 생성
        			
        			dto.setFaq_num(rs.getInt("faq_num"));
					dto.setFaq_writer(rs.getString("faq_writer"));
					dto.setFaq_subject(rs.getString("faq_subject"));
					dto.setFaq_pw(rs.getString("faq_pw"));
					dto.setFaq_regdate(rs.getDate("faq_regdate"));//★Date로 받아줬으므로 getDate사용한다 						
					dto.setFaq_readcount(rs.getInt("faq_readcount"));//조회수				
					dto.setFaq_content(rs.getString("faq_content"));
					dto.setFaq_ip(rs.getString("faq_ip"));
        			
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
	public faqDTO getBoard(int faq_num) throws Exception{
		faqDTO dto=null;
		try{
			//처리
			con=getCon();//커넥션 얻기
			
			//조회수 증가
			sql="update craft_faq set faq_readcount=faq_readcount+1 where faq_num=?";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, faq_num);
			pstmt.executeUpdate();//쿼리 수행
			
			//글 내용 보기
			pstmt=con.prepareStatement("select * from craft_faq where faq_num=?");
			pstmt.setInt(1, faq_num);
			rs=pstmt.executeQuery();//쿼리 수행
			
			if(rs.next()){
				//rs내용을 dto에 넣고 dto를 클라이언트로 보낸다.
				dto=new faqDTO();//객체생성
				dto.setFaq_num(rs.getInt("faq_num"));
				dto.setFaq_writer(rs.getString("faq_writer"));
				dto.setFaq_subject(rs.getString("faq_subject"));
				dto.setFaq_pw(rs.getString("faq_pw"));
				dto.setFaq_regdate(rs.getDate("faq_regdate"));					
				dto.setFaq_readcount(rs.getInt("faq_readcount"));				
				dto.setFaq_content(rs.getString("faq_content"));
				dto.setFaq_ip(rs.getString("faq_ip"));

				
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
	public faqDTO getUpdate(int faq_num) throws Exception{
		faqDTO dto=null;
		
		try{
			
			con=getCon();//커넥션 얻기
			//글 내용 보기
			pstmt=con.prepareStatement("select * from craft_faq where faq_num=?");
			pstmt.setInt(1, faq_num);
			rs=pstmt.executeQuery();//쿼리 수행
			
			if(rs.next()){
				//rs내용을 dto에 넣고 dto를 클라이언트로 보낸다.
				dto=new faqDTO();//객체생성
				dto.setFaq_num(rs.getInt("faq_num"));
				dto.setFaq_writer(rs.getString("faq_writer"));
				dto.setFaq_subject(rs.getString("faq_subject"));
				dto.setFaq_pw(rs.getString("faq_pw"));
				dto.setFaq_regdate(rs.getDate("faq_regdate"));			
				dto.setFaq_readcount(rs.getInt("faq_readcount"));			
				dto.setFaq_content(rs.getString("faq_content"));
				dto.setFaq_ip(rs.getString("faq_ip"));
			}//if end
			
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
	public int updateBoard(faqDTO dto) throws Exception{
		String dbfaq_pw="";
		int x=-1;
		try{
			//처리
			con=getCon();
			pstmt=con.prepareStatement("select faq_pw from craft_faq where faq_num=?");
			pstmt.setInt(1, dto.getFaq_num());//?값 채우기 
			rs=pstmt.executeQuery();//쿼리 수행 
			
			if(rs.next()){
				dbfaq_pw=rs.getString("faq_pw");
				String faq_pw=dto.getFaq_pw();
				
				if(dbfaq_pw.equals(faq_pw)){//암호가 일치하면 글 수정
				  sql="update craft_faq set faq_writer=?,faq_subject=?,faq_content=? where faq_num=?";
				  pstmt=con.prepareStatement(sql);//생성시 인자 들어간다 
				  
				  pstmt.setString(1, dto.getFaq_writer());
				  pstmt.setString(2, dto.getFaq_subject());
				  pstmt.setString(3, dto.getFaq_content());
				  pstmt.setInt(4, dto.getFaq_num());
				  
				  pstmt.executeUpdate();//쿼리 수행
				  x=1;//정상적인 수정
				}else if(!dbfaq_pw.equals(faq_pw)){
					//암호가 틀릴때 
					x=0;
				}else if(faq_pw==""){
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
	public int deleteBoard(int faq_num,String faq_pw) throws Exception{
		String dbfaq_pw="";
		int x=-1;
		
		try{
			//처리
			con=getCon();
			pstmt=con.prepareStatement("select faq_pw from craft_faq where faq_num=?");
			pstmt.setInt(1, faq_num);
			
			rs=pstmt.executeQuery();//쿼리 수행 
			if(rs.next()){
				dbfaq_pw=rs.getString("faq_pw");
				if(dbfaq_pw.equals(faq_pw)){//암호가 일치하면 글 삭제 
					pstmt=con.prepareStatement("delete from craft_faq where faq_num=?");
					
					pstmt.setInt(1, faq_num);
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
