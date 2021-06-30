package craft_item;
import java.sql.*;
import javax.sql.*;//DataSource
import javax.naming.*;//lookup
import java.util.*;

public class ItemDAO {
	//싱글톤 객체 사용(객체 하나만 사용) : 메모리 절약 효과
	private static ItemDAO dao=new ItemDAO();//객체생성
	
	private ItemDAO(){}
	//생성자 외부에서 객체 생성 못하게 하기 위해 private사용 => 그래서 getInstance()를 사용하는것
	
	//JSP에서 사용할 메서드(JSP에서 ItemDAO.getInstance() 와 같이 사용함)
	public static ItemDAO getInstance(){
		return dao;
	}
	
	//--------------------------------------------------------
	//  커넥션 풀 사용
	//--------------------------------------------------------
	private Connection getConnection() throws Exception{
		Context ct=new InitialContext();
		DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		
		return ds.getConnection();
	}//getCon() end
	
	//--------------------------------------------------------
	//  1. 상품 목록 리스트
	//--------------------------------------------------------
	public List<ItemDTO> getGoodList(int startItem, int pageSize) throws Exception{
		String sql="";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		List<ItemDTO> list=new ArrayList<ItemDTO>();//객체생성
		
		try {
			//실행문
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
			System.out.println("getGoodList() 예외 :"+ex1);
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
	//  2. 상품 상세보기
	//--------------------------------------------------------
	public ItemDTO getDetail(int craft_pro_no) throws Exception{
		String sql="";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ItemDTO dto=new ItemDTO();
		
		try {
			//실행문
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
			System.out.println("getDetail 예외 :"+ex1);
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
	//  3. 상품 갯수확인
	//--------------------------------------------------------
	public int getItemCount() throws Exception{
		int cnt=-1;
		String sql="";
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try {
			//실행문
			con=getConnection();
			sql="select count(*) from craft_item";
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			
			if(rs.next()){
				cnt=rs.getInt(1);
			}//if end
			
		} catch (Exception ex1) {
			System.out.println("getItemCount() 예외 :"+ex1);
		} finally {
			try{
				if(stmt!=null){stmt.close();}
				if(con!=null){con.close();}
			} catch (Exception ex2){}
		}//finally end
		
		return cnt;
	}//getItemCount() end
}//class end
