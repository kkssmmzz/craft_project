package craft_member;
import java.sql.*;
import javax.sql.*;//DataSource
import javax.naming.*;//lookup
import java.util.*;


//DAO : ����Ͻ�����(DBó�����)
public class MemberDAO {
	
	//�̱��� ��ü ���(��ü �ϳ��� ���) : �޸� ���� ȿ��
	private static MemberDAO instance=new MemberDAO();
	
	private MemberDAO(){}//�ܺο��� ��ü ���� ���ϰ� �ϱ� ���� private���
	
	//JSP���� ����� �޼��� (JSP���� MemberDAO.jetInstance() �̷��� ���)
	public static MemberDAO getInstance(){
		return instance;
	}
	
	//-------------------------------------
	//	Ŀ�ؼ� Ǯ ���
	//-------------------------------------
	private Connection getConnection() throws Exception{
		Context ct=new InitialContext();
		DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		
		return ds.getConnection();
	}//connection() end
	
	//-------------------------------------
	//	ID �ߺ� üũ
	//-------------------------------------
	public int confirmID(String craft_id) throws Exception{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		int x=-1;
		try{
			con=getConnection();//Ŀ�ؼ� ���
			pstmt=con.prepareStatement("select craft_id from craft_member where craft_id=?");
			pstmt.setString(1, craft_id); //?�� ü���
			rs=pstmt.executeQuery();//���� ����
			
			if(rs.next()){
				x=1;//������� ID
			}else{
				x=-1;//��� ������ ID
			}
		}catch(Exception ex1){
			System.out.println("confirmid() ���� :"+ex1);
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
	//	ȸ������ insert
	//-------------------------------------
	public void insertMember(MemberDTO dto) throws Exception{
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=getConnection();//Ŀ�ؼ� ���
			pstmt=con.prepareStatement("insert into craft_member values(?,?,?,?,?,?,?,?,NOW())");
			
			pstmt.setString(1, dto.getCraft_id());
			pstmt.setString(2, dto.getCraft_pw());
			pstmt.setString(3, dto.getCraft_name());
			pstmt.setString(4, dto.getCraft_email());
			pstmt.setString(5, dto.getCraft_tel());
			pstmt.setString(6, dto.getCraft_zipcode());
			pstmt.setString(7, dto.getCraft_addr());
			pstmt.setString(8, dto.getCraft_addr2());
			
			pstmt.executeUpdate();//���� ����
			
			
		}catch(Exception ex1){
			System.out.println("insertMember() ���� :" + ex1);
		}finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally end
	}//insertMember() end
	
	//---------------------------------
	//�α��� ����
	//---------------------------------
	public int userCheck(String craft_id,String craft_pw) throws Exception{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String dbPw="";
		int x=-1;
		try{
			con=getConnection();//Ŀ�ؼǾ��
			pstmt=con.prepareStatement("select * from craft_member where craft_id=?");
			pstmt.setString(1, craft_id);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				dbPw=rs.getString("craft_pw");
				if(craft_pw.equals(dbPw)){//�Է¾�ȣ�� DB��ȣ�� ��ġ�ϸ�
					//���� ����
					x=1;
					
				}else{
					x=0;//��ȣ Ʋ��
				}
			
			}else{
				x=-1;//���� ID
			}
		}catch(Exception ex1){
			System.out.println("userCheck() ���� :"+ex1);
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
	//ȸ�������� Ŭ���̾�Ʈ���� ���� (�����ϱ�����)
	//---------------------------
	public MemberDTO getMember(String craft_id) throws Exception{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		MemberDTO dto=null;
		
		try{
			con=getConnection();//Ŀ�ؼ� ���
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
			System.out.println("getMember() ����:"+ex1);
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
	//	DB���� ����
	//-------------------------------
	public void updateMember(MemberDTO dto) throws Exception{
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try{
			//ó��
			con=getConnection();
			String sql="update craft_member set craft_pw=? , craft_name=? , craft_email=? , craft_tel=? , "+
			" craft_zipcode=? , craft_addr=? , craft_addr2=? where craft_id=?";
			
			pstmt=con.prepareStatement(sql);//������ ���� ����
			
			pstmt.setString(1, dto.getCraft_pw());
			pstmt.setString(2, dto.getCraft_name());
			pstmt.setString(3, dto.getCraft_email());
			pstmt.setString(4, dto.getCraft_tel());
			pstmt.setString(5, dto.getCraft_zipcode());
			pstmt.setString(6, dto.getCraft_addr());
			pstmt.setString(7, dto.getCraft_addr2());
			pstmt.setString(8, dto.getCraft_id());
			pstmt.executeUpdate();//��������
			
		}catch(Exception ex1){
			System.out.println("updateMember() ����:"+ex1);
		}finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){
				
			}
		}//finally end
	}//updateMember() end
	
	//----------------------------------------
	//	ȸ��Ż��
	//----------------------------------------
	public int deleteMember(String craft_id, String craft_pw) throws Exception{
		
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;
		ResultSet rs=null;
		
		String dbPw="";
		int x=-1;
		try{
			//ó��
			con=getConnection();//Ŀ�ؼ� ���
			pstmt=con.prepareStatement("select craft_pw from craft_member where craft_id=?");
			pstmt.setString(1, craft_id);
			rs=pstmt.executeQuery();//��������, ��� �ޱ�
			
			if(rs.next()){//id�� �����Ҷ�
				dbPw=rs.getString("craft_pw");
				if(craft_pw.equals(dbPw)){//�Է��� ��ȣ�� DB�� ��ȣ�� ��ġ�ϸ� ����
					
					pstmt2=con.prepareStatement("delete from craft_member where craft_id=?");
					pstmt2.setString(1, craft_id);//?�� ä���
					pstmt2.executeUpdate();//���� ����, ����
					x=1;//���� ����
				}else{//��ȣ�� ��ġ���� ������
					
				}
			}else{//id�� ������
				x=0;//id������
			}
			
		}catch(Exception ex1){
			System.out.println("deleteMember() ����:"+ex1);
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
