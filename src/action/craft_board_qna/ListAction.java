package action.craft_board_qna;
import javax.servlet.http.*;

import command.CommandAction;//�������̵� - Ŭ������ ����� ���� ���� ������Ѵ�.
import craft_qna.*;

import java.util.*;//List,ArrayList

//�������̽� ��� �޾� ���� Ŭ���� �۾��ϴ� ��
//1. action�� jspó�� ������ action�� �Ѵ�.
//2. DAO�޼��� ȣ���Ͽ� ������� ��´�.
public class ListAction implements CommandAction{

	@Override     //��ûó���ϴ� ���̶� �޼��尡 requestPro()�̴�.
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//jsp�� ó������, DAO�޼��� ȣ��
		String qna_pageNum=request.getParameter("qna_pageNum");
		if(qna_pageNum==null){
			qna_pageNum="1";
		}//if end
		
		int qna_pageSize=10;//�� ������ �� �� ����
		int qna_currentPage=Integer.parseInt(qna_pageNum);
		
		int qna_startRow=(qna_currentPage-1)*qna_pageSize+1;//�� �������� ���� �۹�ȣ
		int qna_endRow=qna_currentPage*qna_pageSize;//���������� ������ �۹�ȣ
		
		int qna_count=0;//�� �۰��� ���� ����
		int qna_number=0;//�� ��ȣ ó���ϱ� ���� ����
		int qna_pageBlock=10;//���� ������ ��
		
		List qna_boardList=null;
		QnaDAO qna_dao=QnaDAO.getInstance();//dao��ü���
		qna_count=qna_dao.getBoardCount();//��ü �۰��� ���
		
		if(qna_count>0){//���� ������ 
			qna_boardList=qna_dao.getList(qna_startRow, qna_pageSize);//dao�޼��� ȣ���ϰ� ��� �޴´�
		}else{//���� ������
			qna_boardList=Collections.EMPTY_LIST;//����ִٴ� ��
		}//else end
		
		qna_number=qna_count-(qna_currentPage-1)*qna_pageSize;//����� �۹�ȣ ����
		
		int qna_pageCount=qna_count/qna_pageSize+(qna_count%qna_pageSize==0?0:1);//�� ������ ��
		//                 ��                       �Ǵٸ� ���ڵ� ��(31�� ��, �������� 4���� ������)
		int qna_startPage=(qna_currentPage/10)*10+1;//����������
		//                
		int qna_endPage=qna_startPage+qna_pageBlock-1;
		//             1+10-1=10 end������
		
		//if(endPage>pageCount){//��������
			//endPage=pageCount;
		//}//if end		
		
		//JSP���� ����ϵ��� request,setAttribute("key",value) �۾��� �Ѵ�
		request.setAttribute("qna_pageNum", qna_pageNum);
		request.setAttribute("qna_currentPage", qna_currentPage);
		
		request.setAttribute("qna_startRow", qna_startRow);
		request.setAttribute("qna_endRow", qna_endRow);

		request.setAttribute("qna_pageBlock", qna_pageBlock);
		request.setAttribute("qna_pageCount", qna_pageCount);
		request.setAttribute("qna_startPage", qna_startPage);
		request.setAttribute("qna_endPage", qna_endPage);

		request.setAttribute("qna_count", new Integer(qna_count));
		request.setAttribute("qna_pageSize", new Integer(qna_pageSize));
		request.setAttribute("qna_number", new Integer(qna_number));
		
		request.setAttribute("qna_boardList", qna_boardList);
		
		
	return "/craft_board_qna/list.jsp";//��(/board/list.jsp)�� ���� ==> ��Ʈ�ѷ��� ���� ==> ��� �Ѿ��
	}//requestPro() end
}//class end

