package craft_faq;
import java.sql.*;

import javax.sql.*;//DataSource
import javax.naming.*;//lookup

import craft_noti.notiDTO;

import java.util.*;//List, ArrayList

//DAO : DBó��(�����Ͻ� ����)
public class faqDAO {
	Connection con=null;
	Statement stmt=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	String sql="";
	
	//�̱��� ��ü ���(�޸� ���� ȿ��)
	private static faqDAO dao=new faqDAO();//��ü����
	
	private faqDAO(){}//������ , private�ϸ� �ܺο��� ���� ���� 
	
	public static faqDAO getInstance(){
		return dao;
	}
	
	//Ŀ�ؼ� �����ϱ� 
	private Connection getCon() throws Exception{
		Context ct=new InitialContext();
		DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		
		return ds.getConnection();
	}
	
	//-------------------------
	//  ���۾���, ��� ���� 
	//-------------------------
	public void insertBoard(faqDTO dto) throws Exception{
		int faq_num=dto.getFaq_num();
		int faq_ref=dto.getFaq_ref();
		int faq_re_step=dto.getFaq_re_step();
		int faq_re_level=dto.getFaq_re_level();

		
		int faq_number=0;//�� �׷� ó�� �ϱ� ���� ����
		try{
			//ó�� 
			//Ŀ�ؼ� ���
			con=getCon();

			//�۾���
			sql="insert into craft_faq(faq_writer, faq_subject, faq_pw, faq_regdate, faq_content, faq_ip) "+
					"values(?,?,?,NOW(),?,?)";

			pstmt=con.prepareStatement(sql);//������ ���ڰ� ����.

			pstmt.setString(1, dto.getFaq_writer());
			pstmt.setString(2, dto.getFaq_subject());
			pstmt.setString(3, dto.getFaq_pw());
			//regdate�� NOW()�� ��
			pstmt.setString(4, dto.getFaq_content());
			pstmt.setString(5, dto.getFaq_ip());

			pstmt.executeUpdate();
			
		}catch(Exception ex1){
			System.out.println("insertBoard() ���� :"+ex1);
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
	// �� ����
	//---------------------
	public int getBoardCount() throws Exception{
		int cnt=-1;//�ʱ�ȭ
		
		try{
			//ó��
			con=getCon();//Ŀ�ؼ� ���
			pstmt=con.prepareStatement("select count(*) from craft_faq");//������ ���� ����
			
			rs=pstmt.executeQuery();//���� ���� 
			
			if(rs.next()){
				cnt=rs.getInt(1);//1�� �ʵ� ��ȣ, �� ���� 
			}
		}catch(Exception ex1){
			System.out.println("getBoardCount() ���� :"+ex1);
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
	// ����Ʈ
	//---------------------
	public List getList(int faq_startRow, int faq_pageSize) throws Exception{
		//System.out.println("startRow:"+startRow);
		//System.out.println("pageSize:"+pageSize);
		List<faqDTO> list=null;//����
        try{
        	//ó��
        	con=getCon();
        	sql="select * from craft_faq order by faq_num desc limit ?,?";
        	//                                                          ����,����
        	//limit ���� ��ġ��  0���� �� ��
        	
        	pstmt=con.prepareStatement(sql);//������ ���� ���� 
        	
        	pstmt.setInt(1,faq_startRow-1);//?��ä��� ,0���� ����
        	pstmt.setInt(2,faq_pageSize);//?��ä��� 
        	
        	rs=pstmt.executeQuery();//���� ���� 
        	
        	if(rs.next()){
        		list=new ArrayList<faqDTO>();
        		do{
        			faqDTO dto=new faqDTO();//��ü ����
        			
        			dto.setFaq_num(rs.getInt("faq_num"));
					dto.setFaq_writer(rs.getString("faq_writer"));
					dto.setFaq_subject(rs.getString("faq_subject"));
					dto.setFaq_pw(rs.getString("faq_pw"));
					dto.setFaq_regdate(rs.getDate("faq_regdate"));//��Date�� �޾������Ƿ� getDate����Ѵ� 						
					dto.setFaq_readcount(rs.getInt("faq_readcount"));//��ȸ��				
					dto.setFaq_content(rs.getString("faq_content"));
					dto.setFaq_ip(rs.getString("faq_ip"));
        			
        			list.add(dto);/////////******** ������ ����� ����
        			//vec.add(dto); ������ ����� �ִ� (ä�ÿ��� ���)
        			
        		}while(rs.next());
        	}//if
        	
        }catch(Exception ex1){
        	System.out.println("getList() ���� :"+ex1);
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
	// �۳��뺸��
	//-----------------------
	public faqDTO getBoard(int faq_num) throws Exception{
		faqDTO dto=null;
		try{
			//ó��
			con=getCon();//Ŀ�ؼ� ���
			
			//��ȸ�� ����
			sql="update craft_faq set faq_readcount=faq_readcount+1 where faq_num=?";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, faq_num);
			pstmt.executeUpdate();//���� ����
			
			//�� ���� ����
			pstmt=con.prepareStatement("select * from craft_faq where faq_num=?");
			pstmt.setInt(1, faq_num);
			rs=pstmt.executeQuery();//���� ����
			
			if(rs.next()){
				//rs������ dto�� �ְ� dto�� Ŭ���̾�Ʈ�� ������.
				dto=new faqDTO();//��ü����
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
        	System.out.println("getBoard() ���� :"+ex1);
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
	// �ۼ��� Ŭ���̾�Ʈ�� ���� ������ 
	//-----------------------------
	public faqDTO getUpdate(int faq_num) throws Exception{
		faqDTO dto=null;
		
		try{
			
			con=getCon();//Ŀ�ؼ� ���
			//�� ���� ����
			pstmt=con.prepareStatement("select * from craft_faq where faq_num=?");
			pstmt.setInt(1, faq_num);
			rs=pstmt.executeQuery();//���� ����
			
			if(rs.next()){
				//rs������ dto�� �ְ� dto�� Ŭ���̾�Ʈ�� ������.
				dto=new faqDTO();//��ü����
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
    	System.out.println("getBoard() ���� :"+ex1);
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
	//DB �ۼ���
	//-------------------
	public int updateBoard(faqDTO dto) throws Exception{
		String dbfaq_pw="";
		int x=-1;
		try{
			//ó��
			con=getCon();
			pstmt=con.prepareStatement("select faq_pw from craft_faq where faq_num=?");
			pstmt.setInt(1, dto.getFaq_num());//?�� ä��� 
			rs=pstmt.executeQuery();//���� ���� 
			
			if(rs.next()){
				dbfaq_pw=rs.getString("faq_pw");
				String faq_pw=dto.getFaq_pw();
				
				if(dbfaq_pw.equals(faq_pw)){//��ȣ�� ��ġ�ϸ� �� ����
				  sql="update craft_faq set faq_writer=?,faq_subject=?,faq_content=? where faq_num=?";
				  pstmt=con.prepareStatement(sql);//������ ���� ���� 
				  
				  pstmt.setString(1, dto.getFaq_writer());
				  pstmt.setString(2, dto.getFaq_subject());
				  pstmt.setString(3, dto.getFaq_content());
				  pstmt.setInt(4, dto.getFaq_num());
				  
				  pstmt.executeUpdate();//���� ����
				  x=1;//�������� ����
				}else if(!dbfaq_pw.equals(faq_pw)){
					//��ȣ�� Ʋ���� 
					x=0;
				}else if(faq_pw==""){
					x=2;
				}
			}//if
			
			
		}catch(Exception ex1){
	    	System.out.println("updateBoard() ���� :"+ex1);
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
	// �ۻ���
	//--------------------
	public int deleteBoard(int faq_num,String faq_pw) throws Exception{
		String dbfaq_pw="";
		int x=-1;
		
		try{
			//ó��
			con=getCon();
			pstmt=con.prepareStatement("select faq_pw from craft_faq where faq_num=?");
			pstmt.setInt(1, faq_num);
			
			rs=pstmt.executeQuery();//���� ���� 
			if(rs.next()){
				dbfaq_pw=rs.getString("faq_pw");
				if(dbfaq_pw.equals(faq_pw)){//��ȣ�� ��ġ�ϸ� �� ���� 
					pstmt=con.prepareStatement("delete from craft_faq where faq_num=?");
					
					pstmt.setInt(1, faq_num);
					pstmt.executeUpdate();//���� ����
					x=1;//���������� ���� 
					
				}else{//��ȣ�� ��ġ���� ������
					x=0;//���� ����
				}
			}
			
		}catch(Exception ex1){
			System.out.println("deleteBoard() ���� :"+ex1);
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
