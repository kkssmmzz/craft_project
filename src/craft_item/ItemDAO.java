package craft_item;
import java.sql.*;
import javax.sql.*;//DataSource
import javax.naming.*;//lookup
import java.util.*;

public class ItemDAO {
	//�̱��� ��ü ���(��ü �ϳ��� ���) : �޸� ���� ȿ��
	private static ItemDAO dao=new ItemDAO();//��ü����
	
	private ItemDAO(){}
	//������ �ܺο��� ��ü ���� ���ϰ� �ϱ� ���� private��� => �׷��� getInstance()�� ����ϴ°�
	
	//JSP���� ����� �޼���(JSP���� ItemDAO.getInstance() �� ���� �����)
	public static ItemDAO getInstance(){
		return dao;
	}
	
	//--------------------------------------------------------
	//  Ŀ�ؼ� Ǯ ���
	//--------------------------------------------------------
	private Connection getConnection() throws Exception{
		Context ct=new InitialContext();
		DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		
		return ds.getConnection();
	}//getCon() end
	
	//--------------------------------------------------------
	//  1. ��ǰ ��� ����Ʈ
	//--------------------------------------------------------
	public List<ItemDTO> getGoodList(int startItem, int pageSize) throws Exception{
		String sql="";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		List<ItemDTO> list=new ArrayList<ItemDTO>();//��ü����
		
		try {
			//���๮
			con=getConnection();
			sql="select * from craft_item order by craft_pro_no asc limit ?,?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startItem);
			pstmt.setInt(2, pageSize);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				ItemDTO dto=new ItemDTO();
				
				dto.setCraft_pro_no(rs.getInt("craft_pro_no"));
				dto.setCraft_name(rs.getString("craft_name"));
				dto.setCraft_detail(rs.getString("craft_detail"));
				dto.setCraft_regdate(rs.getDate("craft_regdate"));
				dto.setCraft_image(rs.getString("craft_image"));
				
				list.add(dto);
			}//if end
			
		} catch (Exception ex1) {
			System.out.println("getGoodList() ���� :"+ex1);
		} finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			} catch (Exception ex2){}
		}//finally end
		
		return list;
	}//getGoodList() end
	
	//--------------------------------------------------------
	//  2. ��ǰ �󼼺���
	//--------------------------------------------------------
	public ItemDTO getDetail(int craft_pro_no) throws Exception{
		String sql="";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ItemDTO dto=new ItemDTO();
		
		try {
			//���๮
			con=getConnection();
			sql="select * from craft_item where craft_pro_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, craft_pro_no);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				dto.setCraft_pro_no(rs.getInt("craft_pro_no"));
				dto.setCraft_name(rs.getString("craft_name"));
				dto.setCraft_detail(rs.getString("craft_detail"));
				dto.setCraft_regdate(rs.getDate("craft_regdate"));
				dto.setCraft_image(rs.getString("craft_image"));
				
			}//if end
		} catch (Exception ex1) {
			System.out.println("getDetail ���� :"+ex1);
		} finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			} catch (Exception ex2){}
		}//finally end
		
		return dto;
	}//getDetail() end
	
	//--------------------------------------------------------
	//  3. ��ǰ ����Ȯ��
	//--------------------------------------------------------
	public int getItemCount() throws Exception{
		int cnt=-1;
		String sql="";
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try {
			//���๮
			con=getConnection();
			sql="select count(*) from craft_item";
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			
			if(rs.next()){
				cnt=rs.getInt(1);
			}//if end
			
		} catch (Exception ex1) {
			System.out.println("getItemCount() ���� :"+ex1);
		} finally {
			try{
				if(stmt!=null){stmt.close();}
				if(con!=null){con.close();}
			} catch (Exception ex2){}
		}//finally end
		
		return cnt;
	}//getItemCount() end
}//class end
