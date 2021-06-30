package craft_qna;
import java.sql.*;
import javax.sql.*;//DataSource
import javax.naming.*;//lookup
import java.util.*;//List, ArrayList

//DAO : DBó��(�����Ͻ� ����)
public class QnaDAO {
	Connection con=null;
	Statement stmt=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	String sql="";
	
	//�̱��� ��ü ���(�޸� ���� ȿ��)
	private static QnaDAO dao=new QnaDAO();//��ü����
	
	private QnaDAO(){}//������ , private�ϸ� �ܺο��� ���� ���� 
	
	public static QnaDAO getInstance(){
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
	public void insertBoard(QnaDTO dto) throws Exception{
		int qna_num=dto.getQna_num();
		int qna_ref=dto.getQna_ref();
		int qna_re_step=dto.getQna_re_step();
		int qna_re_level=dto.getQna_re_level();

		
		int qna_number=0;//�� �׷� ó�� �ϱ� ���� ����
		try{
			//ó�� 
			con=getCon();//Ŀ�ؼ� ���
			
			//�ִ� �۹�ȣ ��� 
			pstmt=con.prepareStatement("select max(qna_num) from craft_qna");
			rs=pstmt.executeQuery();//���� ����
			
			if(rs.next()){//���� ���� �ϸ� 
				qna_number=rs.getInt(1)+1;//�ִ�۹�ȣ+1,  �� �׷쿡 ����Ϸ���
				//rs.getString("id");//�ʵ� �̸�
				//rs.getString(1);//�ʵ��ȣ
				
			}else{//���� ������
				qna_number=1;//ó�� ��  qna_ref=qna_number;
			}//else end---
			
			if(qna_num!=0){//����̸�
				
				//��� �������� ��ġ Ȯ�� 
				sql="update craft_qna set qna_re_step=qna_re_step+1 where qna_ref=? and qna_re_step>?";
				pstmt=con.prepareStatement(sql);//������ ���� ���� 
				
				pstmt.setInt(1, qna_ref);//?�� ä��� 
				pstmt.setInt(2, qna_re_step);
				
				pstmt.executeUpdate();//���� ���� 
				
				qna_re_step=qna_re_step+1;
				qna_re_level=qna_re_level+1;
				
			}else{//�����̸�, ù��° ���̸�
				qna_ref=qna_number;
				qna_re_step=0;
				qna_re_level=0;
			}//else -end
			
			//�۾���
			sql="insert into craft_qna(qna_writer,qna_subject,qna_pw,qna_regdate,"+
			"qna_ref,qna_re_step,qna_re_level,qna_content,qna_ip) "+
			"values(?,?,?,NOW(),?,?,?,?,?)";
			
			pstmt=con.prepareStatement(sql);//������ ���� ���� 
			
			pstmt.setString(1, dto.getQna_writer());
			pstmt.setString(2, dto.getQna_subject());
			pstmt.setString(3, dto.getQna_pw());
			
			pstmt.setInt(4, qna_ref);//qna_ref
			pstmt.setInt(5, qna_re_step);
			pstmt.setInt(6, qna_re_level);
			
			pstmt.setString(7, dto.getQna_content());
			pstmt.setString(8, dto.getQna_ip());
			
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
			pstmt=con.prepareStatement("select count(*) from craft_qna");//������ ���� ����
			
			rs=pstmt.executeQuery();//���� ���� 
			
			if(rs.next()){
				cnt=rs.getInt(1);//1�� �ʵ� ��ȣ, �� ���� 
				System.out.println("cnt:"+cnt);
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
	public List getList(int qna_startRow, int qna_pageSize) throws Exception{
		//System.out.println("startRow:"+startRow);
		//System.out.println("pageSize:"+pageSize);
		List<QnaDTO> list=null;//����
        try{
        	//ó��
        	con=getCon();
        	sql="select * from craft_qna order by qna_ref desc,qna_re_step asc limit ?,?";
        	//                                                          ����,����
        	//limit ���� ��ġ��  0���� �� ��
        	
        	pstmt=con.prepareStatement(sql);//������ ���� ���� 
        	
        	pstmt.setInt(1,qna_startRow-1);//?��ä��� ,0���� ����
        	pstmt.setInt(2,qna_pageSize);//?��ä��� 
        	
        	rs=pstmt.executeQuery();//���� ���� 
        	
        	if(rs.next()){
        		list=new ArrayList<QnaDTO>();
        		do{
        			QnaDTO dto=new QnaDTO();//��ü ����
        			
        			dto.setQna_num(rs.getInt("qna_num"));
        			dto.setQna_writer(rs.getString("qna_writer"));
        			dto.setQna_subject(rs.getString("qna_subject"));
        			dto.setQna_pw(rs.getString("qna_pw"));
        			dto.setQna_regdate(rs.getDate("qna_regdate"));//*****
        			
        			dto.setQna_readcount(rs.getInt("qna_readcount"));//��Ƚ�� 
        			dto.setQna_ref(rs.getInt("qna_ref"));//�� �׷�
        			dto.setQna_re_step(rs.getInt("qna_re_step"));//�� ����
        			dto.setQna_re_level(rs.getInt("qna_re_level"));//�� ����
        			
        			dto.setQna_content(rs.getString("qna_content"));
        			dto.setQna_ip(rs.getString("qna_ip"));
        			
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
	public QnaDTO getBoard(int qna_num) throws Exception{
		QnaDTO dto=null;
		try{
			//ó��
			con=getCon();
			
			//��Ƚ�� ����
			sql="update craft_qna set qna_readcount=qna_readcount+1 where qna_num=?";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, qna_num);
			pstmt.executeUpdate();//���� ���� 
			//-----------------------------
			
			//�� ���뺸��
			pstmt=con.prepareStatement("select * from craft_qna where qna_num=?");
			pstmt.setInt(1, qna_num);
			rs=pstmt.executeQuery();//���� ����
			
			if(rs.next()){
				//rs������ dto�ְ� dto�� Ŭ���̾�Ʈ�� ������ 
				dto=new QnaDTO();//��ü����
				dto.setQna_num(rs.getInt("qna_num"));
				dto.setQna_writer(rs.getString("qna_writer"));
				dto.setQna_subject(rs.getString("qna_subject"));
				dto.setQna_pw(rs.getString("qna_pw"));
				dto.setQna_regdate(rs.getDate("qna_regdate"));
				
				dto.setQna_readcount(rs.getInt("qna_readcount"));
				dto.setQna_ref(rs.getInt("qna_ref"));
				dto.setQna_re_step(rs.getInt("qna_re_step"));
				dto.setQna_re_level(rs.getInt("qna_re_level"));
				
				dto.setQna_content(rs.getString("qna_content"));
				dto.setQna_ip(rs.getString("qna_ip"));
				
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
	public QnaDTO getUpdate(int qna_num) throws Exception{
		QnaDTO dto=null;
		
		try{
		con=getCon();
		//�� ���뺸��
		pstmt=con.prepareStatement("select * from craft_qna where qna_num=?");
		pstmt.setInt(1, qna_num);
		rs=pstmt.executeQuery();//���� ����
		
		if(rs.next()){
			//rs������ dto�ְ� dto�� Ŭ���̾�Ʈ�� ������ 
			dto=new QnaDTO();//��ü����
			dto.setQna_num(rs.getInt("qna_num"));
			dto.setQna_writer(rs.getString("qna_writer"));
			dto.setQna_subject(rs.getString("qna_subject"));
			dto.setQna_pw(rs.getString("qna_pw"));
			dto.setQna_regdate(rs.getDate("qna_regdate"));
			
			dto.setQna_readcount(rs.getInt("qna_readcount"));
			dto.setQna_ref(rs.getInt("qna_ref"));
			dto.setQna_re_step(rs.getInt("qna_re_step"));
			dto.setQna_re_level(rs.getInt("qna_re_level"));
			
			dto.setQna_content(rs.getString("qna_content"));
			dto.setQna_ip(rs.getString("qna_ip"));
			
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
	public int updateBoard(QnaDTO dto) throws Exception{
		String dbqna_pw="";
		int x=-1;
		try{
			//ó��
			con=getCon();
			pstmt=con.prepareStatement("select qna_pw from craft_qna where qna_num=?");
			pstmt.setInt(1, dto.getQna_num());//?�� ä��� 
			rs=pstmt.executeQuery();//���� ���� 
			
			if(rs.next()){
				dbqna_pw=rs.getString("qna_pw");
				String qna_pw=dto.getQna_pw();
				
				if(dbqna_pw.equals(qna_pw)){//��ȣ�� ��ġ�ϸ� �� ����
				  sql="update craft_qna set qna_writer=?,qna_subject=?,qna_content=? where qna_num=?";
				  pstmt=con.prepareStatement(sql);//������ ���� ���� 
				  
				  pstmt.setString(1, dto.getQna_writer());
				  pstmt.setString(2, dto.getQna_subject());
				  pstmt.setString(3, dto.getQna_content());
				  pstmt.setInt(4, dto.getQna_num());
				  
				  pstmt.executeUpdate();//���� ����
				  x=1;//�������� ����
				}else if(!dbqna_pw.equals(qna_pw)){
					//��ȣ�� Ʋ���� 
					x=0;
				}else if(qna_pw==""){
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
	public int deleteBoard(int qna_num,String qna_pw) throws Exception{
		String dbqna_pw="";
		int x=-1;
		
		try{
			//ó��
			con=getCon();
			pstmt=con.prepareStatement("select qna_pw from craft_qna where qna_num=?");
			pstmt.setInt(1, qna_num);
			
			rs=pstmt.executeQuery();//���� ���� 
			if(rs.next()){
				dbqna_pw=rs.getString("qna_pw");
				if(dbqna_pw.equals(qna_pw)){//��ȣ�� ��ġ�ϸ� �� ���� 
					pstmt=con.prepareStatement("delete from craft_qna where qna_num=?");
					
					pstmt.setInt(1, qna_num);
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
