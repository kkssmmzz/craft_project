package craft_board;
import java.sql.*;
import javax.sql.*;//DataSource
import javax.naming.*;//lookup
import java.util.*;//List, ArrayList

//DAO : DBó��(�����Ͻ� ����)
public class BoardDAO {
	Connection con=null;
	Statement stmt=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	String sql="";
	
	//�̱��� ��ü ���(�޸� ���� ȿ��)
	private static BoardDAO dao=new BoardDAO();//��ü����
	
	private BoardDAO(){}//������ , private�ϸ� �ܺο��� ���� ���� 
	
	public static BoardDAO getInstance(){
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
	public void insertBoard(BoardDTO dto) throws Exception{
		int craft_num=dto.getCraft_num();
		int craft_ref=dto.getCraft_ref();
		int craft_re_step=dto.getCraft_re_step();
		int craft_re_level=dto.getCraft_re_level();
		
		int number=0;//�� �׷� ó�� �ϱ� ���� ����
		try{
			//ó�� 
			con=getCon();//Ŀ�ؼ� ���
			
			//�ִ� �۹�ȣ ��� 
			pstmt=con.prepareStatement("select max(craft_num) from craft_board");
			rs=pstmt.executeQuery();//���� ����
			
			if(rs.next()){//���� ���� �ϸ� 
				number=rs.getInt(1)+1;//�ִ�۹�ȣ+1,  �� �׷쿡 ����Ϸ���
				//rs.getString("id");//�ʵ� �̸�
				//rs.getString(1);//�ʵ��ȣ
				
			}else{//���� ������
				number=1;//ó�� ��  ref=number;
			}//else end---
			
			if(craft_num!=0){//����̸�
				//��� �������� ��ġ Ȯ�� 
				sql="update craft_board set craft_re_step=craft_re_step+1 where craft_ref=? and craft_re_step>?";
				pstmt=con.prepareStatement(sql);//������ ���� ���� 
				
				pstmt.setInt(1, craft_ref);//?�� ä��� 
				pstmt.setInt(2, craft_re_step);
				
				pstmt.executeUpdate();//���� ���� 
				
				craft_re_step=craft_re_step+1;
				craft_re_level=craft_re_level+1;
				
				
			}else{//�����̸�, ù��° ���̸�
				craft_ref=number;
				craft_re_step=0;
				craft_re_level=0;
			}//else -end
			
			//�۾���
			sql="insert into craft_board(craft_writer, craft_subject, craft_pw, craft_regdate, "+
			"craft_ref, craft_re_step, craft_re_level, craft_content, craft_ip) "+
			"values(?,?,?,NOW(),?,?,?,?,?)";
			
			pstmt=con.prepareStatement(sql);//������ ���� ���� 
			
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
	//  �� ����
	//---------------------
	public int getBoardCount() throws Exception{
		int cnt=-1;//�ʱ�ȭ
		
		try{
			//ó��
			con=getCon();//Ŀ�ؼ� ���
			pstmt=con.prepareStatement("select count(*) from craft_board");//������ ���� ����
			
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
	//����Ʈ
	//---------------------
	public List getList(int startRow, int pageSize) throws Exception{
		//System.out.println("startRow:"+startRow);
		//System.out.println("pageSize:"+pageSize);
		List<BoardDTO> list=null;//����
        try{
        	//ó��
        	con=getCon();
        	sql="select * from craft_board order by craft_ref desc, craft_re_step asc limit ?,?";
        	//                                                          ����,����
        	//limit ���� ��ġ��  0���� �� ��
        	
        	pstmt=con.prepareStatement(sql);//������ ���� ���� 
        	
        	pstmt.setInt(1,startRow-1);//?��ä��� ,0���� ����
        	pstmt.setInt(2,pageSize);//?��ä��� 
        	
        	rs=pstmt.executeQuery();//���� ���� 
        	
        	if(rs.next()){
        		list=new ArrayList<BoardDTO>();
        		do{
        			BoardDTO dto=new BoardDTO();//��ü ����
        			
        			dto.setCraft_num(rs.getInt("craft_num"));
        			dto.setCraft_writer(rs.getString("craft_writer"));
        			dto.setCraft_subject(rs.getString("craft_subject"));
        			dto.setCraft_pw(rs.getString("craft_pw"));
        			dto.setCraft_regdate(rs.getDate("craft_regdate"));//*****
        			
        			dto.setCraft_readcount(rs.getInt("craft_readcount"));//��Ƚ�� 
        			dto.setCraft_ref(rs.getInt("craft_ref"));//�� �׷�
        			dto.setCraft_re_step(rs.getInt("craft_re_step"));//�� ����
        			dto.setCraft_re_level(rs.getInt("craft_re_level"));//�� ����
        			
        			dto.setCraft_content(rs.getString("craft_content"));
        			dto.setCraft_ip(rs.getString("craft_ip"));
        			
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
	//�۳��뺸��
	//-----------------------
	public BoardDTO getBoard(int num) throws Exception{
		BoardDTO dto=null;
		try{
			//ó��
			con=getCon();
			
			//��Ƚ�� ����
			sql="update craft_board set craft_readcount=craft_readcount+1 where craft_num=?";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			pstmt.executeUpdate();//���� ���� 
			//-----------------------------
			
			//�� ���뺸��
			pstmt=con.prepareStatement("select * from craft_board where craft_num=?");
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();//���� ����
			
			if(rs.next()){
				//rs������ dto�ְ� dto�� Ŭ���̾�Ʈ�� ������ 
				dto=new BoardDTO();//��ü����
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
	public BoardDTO getUpdate(int num) throws Exception{
		BoardDTO dto=null;
		
		try{
		con=getCon();
		//�� ���뺸��
		pstmt=con.prepareStatement("select * from craft_board where craft_num=?");
		pstmt.setInt(1, num);
		rs=pstmt.executeQuery();//���� ����
		
		if(rs.next()){
			//rs������ dto�ְ� dto�� Ŭ���̾�Ʈ�� ������ 
			dto=new BoardDTO();//��ü����
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
	public int updateBoard(BoardDTO dto) throws Exception{
		String dbPw="";
		int x=-1;
		try{
			//ó��
			con=getCon();
			pstmt=con.prepareStatement("select craft_pw from craft_board where craft_num=?");
			pstmt.setInt(1, dto.getCraft_num());//?�� ä��� 
			rs=pstmt.executeQuery();//���� ���� 
			
			if(rs.next()){
				dbPw=rs.getString("craft_pw");
				String craft_pw=dto.getCraft_pw();
				
				if(dbPw.equals(craft_pw)){//��ȣ�� ��ġ�ϸ� �� ����
				  sql="update craft_board set craft_writer=?, craft_subject=?, craft_content=? where craft_num=?";
				  pstmt=con.prepareStatement(sql);//������ ���� ���� 
				  
				  pstmt.setString(1, dto.getCraft_writer());
				  pstmt.setString(2, dto.getCraft_subject());
				  pstmt.setString(3, dto.getCraft_content());
				  pstmt.setInt(4, dto.getCraft_num());
				  
				  pstmt.executeUpdate();//���� ����
				  x=1;//�������� ����
				}else{
					//��ȣ�� Ʋ���� 
					x=0;
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
	public int deleteBoard(int craft_num,String craft_pw) throws Exception{
		String dbPw="";
		int x=-1;
		
		try{
			//ó��
			con=getCon();
			pstmt=con.prepareStatement("select craft_pw from craft_board where craft_num=?");
			pstmt.setInt(1, craft_num);
			
			rs=pstmt.executeQuery();//���� ���� 
			if(rs.next()){
				dbPw=rs.getString("craft_pw");
				if(dbPw.equals(craft_pw)){//��ȣ�� ��ġ�ϸ� �� ���� 
					pstmt=con.prepareStatement("delete from craft_board where craft_num=?");
					
					pstmt.setInt(1, craft_num);
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
