package craft_member;
import java.sql.*;
import javax.sql.*;//DataSource
import javax.naming.*;//lookup
import java.util.*;


//DAO : 비즈니스로직(DB처리기능)
public class MemberDAO {
	
	//싱글톤 객체 사용(객체 하나만 사용) : 메모리 절약 효과
	private static MemberDAO instance=new MemberDAO();
	
	private MemberDAO(){}//외부에서 객체 생성 못하게 하기 위해 private사용
	
	//JSP에서 사용할 메서드 (JSP에서 MemberDAO.jetInstance() 이렇게 사용)
	public static MemberDAO getInstance(){
		return instance;
	}
	
	//-------------------------------------
	//	커넥션 풀 사용
	//-------------------------------------
	private Connection getConnection() throws Exception{
		Context ct=new InitialContext();
		DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		
		return ds.getConnection();
	}//connection() end
	
	//-------------------------------------
	//	ID 중복 체크
	//-------------------------------------
	public int confirmID(String craft_id) throws Exception{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		int x=-1;
		try{
			con=getConnection();//커넥션 얻기
			pstmt=con.prepareStatement("select craft_id from craft_member where craft_id=?");
			pstmt.setString(1, craft_id); //?값 체우기
			rs=pstmt.executeQuery();//쿼리 수행
			
			if(rs.next()){
				x=1;//사용중인 ID
			}else{
				x=-1;//사용 가능한 ID
			}
		}catch(Exception ex1){
			System.out.println("confirmid() 예외 :"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally end
		
		return x;
	}//confirmID() end
	//-------------------------------------
	//	회원가입 insert
	//-------------------------------------
	public void insertMember(MemberDTO dto) throws Exception{
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=getConnection();//커넥션 얻기
			pstmt=con.prepareStatement("insert into craft_member values(?,?,?,?,?,?,?,?,NOW())");
			
			pstmt.setString(1, dto.getCraft_id());
			pstmt.setString(2, dto.getCraft_pw());
			pstmt.setString(3, dto.getCraft_name());
			pstmt.setString(4, dto.getCraft_email());
			pstmt.setString(5, dto.getCraft_tel());
			pstmt.setString(6, dto.getCraft_zipcode());
			pstmt.setString(7, dto.getCraft_addr());
			pstmt.setString(8, dto.getCraft_addr2());
			
			pstmt.executeUpdate();//쿼리 수행
			
			
		}catch(Exception ex1){
			System.out.println("insertMember() 예외 :" + ex1);
		}finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally end
	}//insertMember() end
	
	//---------------------------------
	//로그인 인증
	//---------------------------------
	public int userCheck(String craft_id,String craft_pw) throws Exception{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String dbPw="";
		int x=-1;
		try{
			con=getConnection();//커넥션얻기
			pstmt=con.prepareStatement("select * from craft_member where craft_id=?");
			pstmt.setString(1, craft_id);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				dbPw=rs.getString("craft_pw");
				if(craft_pw.equals(dbPw)){//입력암호와 DB암호가 일치하면
					//인증 성공
					x=1;
					
				}else{
					x=0;//암호 틀림
				}
			
			}else{
				x=-1;//없는 ID
			}
		}catch(Exception ex1){
			System.out.println("userCheck() 예외 :"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally end
		return x;
	}//usercheck() end
	
	//---------------------------
	//회원정보를 클라이언트에게 보냄 (수정하기위해)
	//---------------------------
	public MemberDTO getMember(String craft_id) throws Exception{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		MemberDTO dto=null;
		
		try{
			con=getConnection();//커넥션 얻기
			pstmt=con.prepareStatement("select * from craft_member where craft_id=?");
			pstmt.setString(1, craft_id);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				dto=new MemberDTO();
				dto.setCraft_id(rs.getString("craft_id"));
				dto.setCraft_pw(rs.getString("craft_pw"));
				dto.setCraft_name(rs.getString("craft_name"));
				dto.setCraft_email(rs.getString("craft_email"));
				dto.setCraft_tel(rs.getString("craft_tel"));
				dto.setCraft_zipcode(rs.getString("craft_zipcode"));
				dto.setCraft_addr(rs.getString("craft_addr"));
				dto.setCraft_addr2(rs.getString("craft_addr2"));
				
			}//while end
		}catch(Exception ex1){
			System.out.println("getMember() 예외:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally end
		return dto;
	}//getMember() end
	
	//-------------------------------
	//	DB정보 수정
	//-------------------------------
	public void updateMember(MemberDTO dto) throws Exception{
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try{
			//처리
			con=getConnection();
			String sql="update craft_member set craft_pw=? , craft_name=? , craft_email=? , craft_tel=? , "+
			" craft_zipcode=? , craft_addr=? , craft_addr2=? where craft_id=?";
			
			pstmt=con.prepareStatement(sql);//생성시 인자 들어간다
			
			pstmt.setString(1, dto.getCraft_pw());
			pstmt.setString(2, dto.getCraft_name());
			pstmt.setString(3, dto.getCraft_email());
			pstmt.setString(4, dto.getCraft_tel());
			pstmt.setString(5, dto.getCraft_zipcode());
			pstmt.setString(6, dto.getCraft_addr());
			pstmt.setString(7, dto.getCraft_addr2());
			pstmt.setString(8, dto.getCraft_id());
			pstmt.executeUpdate();//쿼리수행
			
		}catch(Exception ex1){
			System.out.println("updateMember() 예외:"+ex1);
		}finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){
				
			}
		}//finally end
	}//updateMember() end
	
	//----------------------------------------
	//	회원탈퇴
	//----------------------------------------
	public int deleteMember(String craft_id, String craft_pw) throws Exception{
		
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;
		ResultSet rs=null;
		
		String dbPw="";
		int x=-1;
		try{
			//처리
			con=getConnection();//커넥션 얻기
			pstmt=con.prepareStatement("select craft_pw from craft_member where craft_id=?");
			pstmt.setString(1, craft_id);
			rs=pstmt.executeQuery();//쿼리수행, 결과 받기
			
			if(rs.next()){//id가 존재할때
				dbPw=rs.getString("craft_pw");
				if(craft_pw.equals(dbPw)){//입력한 암호와 DB의 암호가 일치하면 삭제
					
					pstmt2=con.prepareStatement("delete from craft_member where craft_id=?");
					pstmt2.setString(1, craft_id);//?값 채우기
					pstmt2.executeUpdate();//쿼리 수행, 삭제
					x=1;//삭제 성공
				}else{//암호가 일치하지 않을떄
					
				}
			}else{//id가 없을때
				x=0;//id없을때
			}
			
		}catch(Exception ex1){
			System.out.println("deleteMember() 예외:"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(pstmt2!=null){pstmt2.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}	
			
		}//finally end
		return x;
	}//deleteMember() end
	
	
}//class end
