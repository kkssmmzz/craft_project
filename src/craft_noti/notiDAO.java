package craft_noti;
import java.sql.*;

import javax.sql.*;//DataSource
import javax.naming.*;//lookup
import craft_noti.*;

import java.util.*;//List, ArrayList

public class notiDAO {

	//==============
	// DB 연결 작업
	//==============
	Connection con=null;
	Statement stmt=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;

	String sql="";

	//싱글톤 객체 사용 (메모리 절약 효과)
	private static notiDAO dao=new notiDAO();//객체생성

	private notiDAO(){};//생성자

	public static notiDAO getInstance(){
		return dao;
	}

	//커넥션 연결하기
	private Connection getCon() throws Exception{
		Context ct=new InitialContext();
		DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");

		return ds.getConnection();
	}

	//====================
	// 글쓰기 insertBoard()
	//====================
	public void insertBoard(notiDTO dto) throws Exception{
		int noti_num=dto.getNoti_num();

		try {
			//커넥션 얻기
			con=getCon();

			//글쓰기
			sql="insert into craft_notice(noti_writer, noti_subject, noti_pw, noti_regdate, noti_content, noti_ip) "+
					"values(?,?,?,NOW(),?,?)";

			pstmt=con.prepareStatement(sql);//생성시 인자가 들어간다.

			pstmt.setString(1, dto.getNoti_writer());
			pstmt.setString(2, dto.getNoti_subject());
			pstmt.setString(3, dto.getNoti_pw());
			//regdate는 NOW()로 들어감
			pstmt.setString(4, dto.getNoti_content());
			pstmt.setString(5, dto.getNoti_ip());

			pstmt.executeUpdate();

		} catch (Exception ex1) {
			System.out.println("insertBoard() 예외 : "+ex1);
		}finally{
			try {
				if(pstmt != null){pstmt.close();}
				if(con != null){con.close();}
			} catch (Exception ex2) {}
		}//finally end
	}//insertBoard() end
	
	//=====================
	//글 갯수 getBoardCount()
	//=====================
	public int getBoardCount() throws Exception{
		int cnt=-1;//초기화
		
		try {
			//처리문
			con=getCon();//커넥션 얻기
			pstmt=con.prepareStatement("select count(*) from craft_notice");//생성시 인자가 들어간다.
			
			rs=pstmt.executeQuery();//쿼리 수행
			
			if(rs.next()){
				cnt=rs.getInt(1);//1은 필드 번호
			}
			
		} catch (Exception ex1) {
			System.out.println("getBoardCount() 예외 : "+ex1);
		}finally{
			try {
				if(rs != null){rs.close();}
				if(pstmt != null){pstmt.close();}
				if(con != null){con.close();}
			} catch (Exception ex2) {}
		}//finally end

		return cnt;
	}//getBoardCount() end
	
	//===============
	//글목록 getList()
	//===============
		public List getList(int startRow, int pageSize) throws Exception{
			List<notiDTO> list=null;//변수
			
			try {
				//처리문
				con=getCon();//커넥션 얻기
				sql="select * from craft_notice order by noti_num desc limit ?,?";
				//                                                      limit 시작위치,갯수 (몇 개 가져올지 설정)
				//                                                            0부터
				
				pstmt=con.prepareStatement(sql);//생성시 인자가 들어간다.
				
				pstmt.setInt(1, startRow-1);//?값 채우기
				pstmt.setInt(2, pageSize);//?값 채우기
				
				rs=pstmt.executeQuery();//쿼리 수행
				
				if(rs.next()){
					list=new ArrayList<notiDTO>();
					do{
						notiDTO dto=new notiDTO();//객체 생성
						
						dto.setNoti_num(rs.getInt("noti_num"));
						dto.setNoti_writer(rs.getString("noti_writer"));
						dto.setNoti_subject(rs.getString("noti_subject"));
						dto.setNoti_pw(rs.getString("noti_pw"));
						dto.setNoti_regdate(rs.getDate("noti_regdate"));//★Date로 받아줬으므로 getDate사용한다 						
						dto.setNoti_readcount(rs.getInt("noti_readcount"));//조회수				
						dto.setNoti_content(rs.getString("noti_content"));
						dto.setNoti_ip(rs.getString("noti_ip"));
						
						list.add(dto);
					}while(rs.next());
										
				}//if end
			} catch (Exception ex1) {
				System.out.println("getList() 예외 : "+ex1);
			}finally{
				try {
					if(rs != null){rs.close();}
					if(pstmt != null){pstmt.close();}
					if(con != null){con.close();}
				} catch (Exception ex2) {}
			}//finally end
			
			return list;
		}//getList() end
		
		//===================
		//글 내용 보기 getBoard()
		//===================
		public notiDTO getBoard(int noti_num) throws Exception{
			notiDTO dto=null;
			try {
				//처리문
				con=getCon();//커넥션 얻기
				
				//조회수 증가
				sql="update craft_notice set noti_readcount=noti_readcount+1 where noti_num=?";
				pstmt=con.prepareStatement(sql);
				
				pstmt.setInt(1, noti_num);
				pstmt.executeUpdate();//쿼리 수행
				
				//글 내용 보기
				pstmt=con.prepareStatement("select * from craft_notice where noti_num=?");
				pstmt.setInt(1, noti_num);
				rs=pstmt.executeQuery();//쿼리 수행
				
				if(rs.next()){
					//rs내용을 dto에 넣고 dto를 클라이언트로 보낸다.
					dto=new notiDTO();//객체생성
					dto.setNoti_num(rs.getInt("noti_num"));
					dto.setNoti_writer(rs.getString("noti_writer"));
					dto.setNoti_subject(rs.getString("noti_subject"));
					dto.setNoti_pw(rs.getString("noti_pw"));
					dto.setNoti_regdate(rs.getDate("noti_regdate"));					
					dto.setNoti_readcount(rs.getInt("noti_readcount"));				
					dto.setNoti_content(rs.getString("noti_content"));
					dto.setNoti_ip(rs.getString("noti_ip"));
				}//if end
			
			}catch (Exception ex1) {
				System.out.println("getBoard() 예외 : "+ex1);
			}finally{
				try {
					if(rs != null){rs.close();}
					if(pstmt != null){pstmt.close();}
					if(con != null){con.close();}
				} catch (Exception ex2) {}
			}//finally end
			
			return dto;
		}//getBoard() end
		
		//===================================
		//글수정 클라이언트로 보낼 데이터 getUpdate()
		//===================================
		public notiDTO getUpdate(int noti_num) throws Exception{
			
			notiDTO dto=null;
			
			try{
				
			con=getCon();//커넥션 얻기
			//글 내용 보기
			pstmt=con.prepareStatement("select * from craft_notice where noti_num=?");
			pstmt.setInt(1, noti_num);
			rs=pstmt.executeQuery();//쿼리 수행
			
			if(rs.next()){
				//rs내용을 dto에 넣고 dto를 클라이언트로 보낸다.
				dto=new notiDTO();//객체생성
				dto.setNoti_num(rs.getInt("noti_num"));
				dto.setNoti_writer(rs.getString("noti_writer"));
				dto.setNoti_subject(rs.getString("noti_subject"));
				dto.setNoti_pw(rs.getString("noti_pw"));
				dto.setNoti_regdate(rs.getDate("noti_regdate"));			
				dto.setNoti_readcount(rs.getInt("noti_readcount"));			
				dto.setNoti_content(rs.getString("noti_content"));
				dto.setNoti_ip(rs.getString("noti_ip"));
			}//if end
		
		}catch (Exception ex1) {
			System.out.println("getBoard() 예외 : "+ex1);
		}finally{
			try {
				if(rs != null){rs.close();}
				if(pstmt != null){pstmt.close();}
				if(con != null){con.close();}
			} catch (Exception ex2) {}
		}//finally end
		
		return dto;
		}//getUpdate() end
		
		//======================
		//DB 글수정 updateBoard()
		//======================
		public int updateBoard(notiDTO dto) throws Exception{
			String dbPw="";
			int x=-1;
			
			try{
				//처리
				con=getCon();
				pstmt=con.prepareStatement("select noti_pw from craft_notice where noti_num=?");
				pstmt.setInt(1, dto.getNoti_num());//?값 채우기
				rs=pstmt.executeQuery();//쿼리 수행
				
				if(rs.next()){
					dbPw=rs.getString("noti_pw");
					String noti_pw=dto.getNoti_pw();
					
					if(dbPw.equals(noti_pw)){//암호가 일치하면 글 수정
						sql="update craft_notice set noti_writer=?,noti_subject=?,noti_content=? where noti_num=?";
						pstmt=con.prepareStatement(sql);//생성시 인자가 들어간다.
						
						pstmt.setString(1, dto.getNoti_writer());
						pstmt.setString(2, dto.getNoti_subject());
						pstmt.setString(3, dto.getNoti_content());
						pstmt.setInt(4, dto.getNoti_num());
						
						pstmt.executeUpdate();//쿼리수행
						x=1;//정상적으로 수정
						
					}else{//암호가 틀릴 때
						x=0;
					}
				}//if end
				
			}catch (Exception ex1) {
				System.out.println("updateBoard() 예외 : "+ex1);
			}finally{
				try {
					if(rs != null){rs.close();}
					if(pstmt != null){pstmt.close();}
					if(con != null){con.close();}
				} catch (Exception ex2) {}
			}//finally end
			
			return x;
		}//updateBoard() end
		
		//===================
		// 글삭제 deleteBoard()
		//===================
		public int deleteBoard(int noti_num, String noti_pw) throws Exception{
			String dbPw="";
			int x=-1;
			
			try {
				//처리문
				con=getCon();//커넥션 얻기
				pstmt=con.prepareStatement("select noti_pw from craft_notice where noti_num=?");
				pstmt.setInt(1, noti_num);
				
				rs=pstmt.executeQuery();//쿼리 수행
				if(rs.next()){
					dbPw=rs.getString("noti_pw");
					if(dbPw.equals(noti_pw)){//암호가 일치하면 글 삭제
						pstmt=con.prepareStatement("delete from craft_notice where noti_num=?");
						
						pstmt.setInt(1, noti_num);
						pstmt.executeUpdate();//쿼리 수행
						x=1;//정상적으로 삭제
					}else{//암호가 일치하지 않으면
						x=0;//삭제 실패
					}//else end
				}//if end
				
			} catch (Exception ex1) {
				System.out.println("deleteBoard() 예외 : "+ex1);
			}finally{
				try {
					if(rs != null){rs.close();}
					if(pstmt != null){pstmt.close();}
					if(con != null){con.close();}
				} catch (Exception ex2) {}
			}//finally end
			
			return x;
		}//deleteBoard() end
}//class end
