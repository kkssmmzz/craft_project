package craft_noti;
import java.sql.*;

import javax.sql.*;//DataSource
import javax.naming.*;//lookup
import craft_noti.*;

import java.util.*;//List, ArrayList

public class notiDAO {

	//==============
	// DB ���� �۾�
	//==============
	Connection con=null;
	Statement stmt=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;

	String sql="";

	//�̱��� ��ü ��� (�޸� ���� ȿ��)
	private static notiDAO dao=new notiDAO();//��ü����

	private notiDAO(){};//������

	public static notiDAO getInstance(){
		return dao;
	}

	//Ŀ�ؼ� �����ϱ�
	private Connection getCon() throws Exception{
		Context ct=new InitialContext();
		DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");

		return ds.getConnection();
	}

	//====================
	// �۾��� insertBoard()
	//====================
	public void insertBoard(notiDTO dto) throws Exception{
		int noti_num=dto.getNoti_num();

		try {
			//Ŀ�ؼ� ���
			con=getCon();

			//�۾���
			sql="insert into craft_notice(noti_writer, noti_subject, noti_pw, noti_regdate, noti_content, noti_ip) "+
					"values(?,?,?,NOW(),?,?)";

			pstmt=con.prepareStatement(sql);//������ ���ڰ� ����.

			pstmt.setString(1, dto.getNoti_writer());
			pstmt.setString(2, dto.getNoti_subject());
			pstmt.setString(3, dto.getNoti_pw());
			//regdate�� NOW()�� ��
			pstmt.setString(4, dto.getNoti_content());
			pstmt.setString(5, dto.getNoti_ip());

			pstmt.executeUpdate();

		} catch (Exception ex1) {
			System.out.println("insertBoard() ���� : "+ex1);
		}finally{
			try {
				if(pstmt != null){pstmt.close();}
				if(con != null){con.close();}
			} catch (Exception ex2) {}
		}//finally end
	}//insertBoard() end
	
	//=====================
	//�� ���� getBoardCount()
	//=====================
	public int getBoardCount() throws Exception{
		int cnt=-1;//�ʱ�ȭ
		
		try {
			//ó����
			con=getCon();//Ŀ�ؼ� ���
			pstmt=con.prepareStatement("select count(*) from craft_notice");//������ ���ڰ� ����.
			
			rs=pstmt.executeQuery();//���� ����
			
			if(rs.next()){
				cnt=rs.getInt(1);//1�� �ʵ� ��ȣ
			}
			
		} catch (Exception ex1) {
			System.out.println("getBoardCount() ���� : "+ex1);
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
	//�۸�� getList()
	//===============
		public List getList(int startRow, int pageSize) throws Exception{
			List<notiDTO> list=null;//����
			
			try {
				//ó����
				con=getCon();//Ŀ�ؼ� ���
				sql="select * from craft_notice order by noti_num desc limit ?,?";
				//                                                      limit ������ġ,���� (�� �� �������� ����)
				//                                                            0����
				
				pstmt=con.prepareStatement(sql);//������ ���ڰ� ����.
				
				pstmt.setInt(1, startRow-1);//?�� ä���
				pstmt.setInt(2, pageSize);//?�� ä���
				
				rs=pstmt.executeQuery();//���� ����
				
				if(rs.next()){
					list=new ArrayList<notiDTO>();
					do{
						notiDTO dto=new notiDTO();//��ü ����
						
						dto.setNoti_num(rs.getInt("noti_num"));
						dto.setNoti_writer(rs.getString("noti_writer"));
						dto.setNoti_subject(rs.getString("noti_subject"));
						dto.setNoti_pw(rs.getString("noti_pw"));
						dto.setNoti_regdate(rs.getDate("noti_regdate"));//��Date�� �޾������Ƿ� getDate����Ѵ� 						
						dto.setNoti_readcount(rs.getInt("noti_readcount"));//��ȸ��				
						dto.setNoti_content(rs.getString("noti_content"));
						dto.setNoti_ip(rs.getString("noti_ip"));
						
						list.add(dto);
					}while(rs.next());
										
				}//if end
			} catch (Exception ex1) {
				System.out.println("getList() ���� : "+ex1);
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
		//�� ���� ���� getBoard()
		//===================
		public notiDTO getBoard(int noti_num) throws Exception{
			notiDTO dto=null;
			try {
				//ó����
				con=getCon();//Ŀ�ؼ� ���
				
				//��ȸ�� ����
				sql="update craft_notice set noti_readcount=noti_readcount+1 where noti_num=?";
				pstmt=con.prepareStatement(sql);
				
				pstmt.setInt(1, noti_num);
				pstmt.executeUpdate();//���� ����
				
				//�� ���� ����
				pstmt=con.prepareStatement("select * from craft_notice where noti_num=?");
				pstmt.setInt(1, noti_num);
				rs=pstmt.executeQuery();//���� ����
				
				if(rs.next()){
					//rs������ dto�� �ְ� dto�� Ŭ���̾�Ʈ�� ������.
					dto=new notiDTO();//��ü����
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
				System.out.println("getBoard() ���� : "+ex1);
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
		//�ۼ��� Ŭ���̾�Ʈ�� ���� ������ getUpdate()
		//===================================
		public notiDTO getUpdate(int noti_num) throws Exception{
			
			notiDTO dto=null;
			
			try{
				
			con=getCon();//Ŀ�ؼ� ���
			//�� ���� ����
			pstmt=con.prepareStatement("select * from craft_notice where noti_num=?");
			pstmt.setInt(1, noti_num);
			rs=pstmt.executeQuery();//���� ����
			
			if(rs.next()){
				//rs������ dto�� �ְ� dto�� Ŭ���̾�Ʈ�� ������.
				dto=new notiDTO();//��ü����
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
			System.out.println("getBoard() ���� : "+ex1);
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
		//DB �ۼ��� updateBoard()
		//======================
		public int updateBoard(notiDTO dto) throws Exception{
			String dbPw="";
			int x=-1;
			
			try{
				//ó��
				con=getCon();
				pstmt=con.prepareStatement("select noti_pw from craft_notice where noti_num=?");
				pstmt.setInt(1, dto.getNoti_num());//?�� ä���
				rs=pstmt.executeQuery();//���� ����
				
				if(rs.next()){
					dbPw=rs.getString("noti_pw");
					String noti_pw=dto.getNoti_pw();
					
					if(dbPw.equals(noti_pw)){//��ȣ�� ��ġ�ϸ� �� ����
						sql="update craft_notice set noti_writer=?,noti_subject=?,noti_content=? where noti_num=?";
						pstmt=con.prepareStatement(sql);//������ ���ڰ� ����.
						
						pstmt.setString(1, dto.getNoti_writer());
						pstmt.setString(2, dto.getNoti_subject());
						pstmt.setString(3, dto.getNoti_content());
						pstmt.setInt(4, dto.getNoti_num());
						
						pstmt.executeUpdate();//��������
						x=1;//���������� ����
						
					}else{//��ȣ�� Ʋ�� ��
						x=0;
					}
				}//if end
				
			}catch (Exception ex1) {
				System.out.println("updateBoard() ���� : "+ex1);
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
		// �ۻ��� deleteBoard()
		//===================
		public int deleteBoard(int noti_num, String noti_pw) throws Exception{
			String dbPw="";
			int x=-1;
			
			try {
				//ó����
				con=getCon();//Ŀ�ؼ� ���
				pstmt=con.prepareStatement("select noti_pw from craft_notice where noti_num=?");
				pstmt.setInt(1, noti_num);
				
				rs=pstmt.executeQuery();//���� ����
				if(rs.next()){
					dbPw=rs.getString("noti_pw");
					if(dbPw.equals(noti_pw)){//��ȣ�� ��ġ�ϸ� �� ����
						pstmt=con.prepareStatement("delete from craft_notice where noti_num=?");
						
						pstmt.setInt(1, noti_num);
						pstmt.executeUpdate();//���� ����
						x=1;//���������� ����
					}else{//��ȣ�� ��ġ���� ������
						x=0;//���� ����
					}//else end
				}//if end
				
			} catch (Exception ex1) {
				System.out.println("deleteBoard() ���� : "+ex1);
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
