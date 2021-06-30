package action.craft_board_faq;
import javax.servlet.http.*;

import command.CommandAction;//�������̵� - Ŭ������ ����� ���� ���� ������Ѵ�.
import craft_faq.*;

import java.util.*;//List,ArrayList

//�������̽� ��� �޾� ���� Ŭ���� �۾��ϴ� ��
//1. action�� jspó�� ������ action�� �Ѵ�.
//2. DAO�޼��� ȣ���Ͽ� ������� ��´�.
public class ListAction implements CommandAction{

	@Override     //��ûó���ϴ� ���̶� �޼��尡 requestPro()�̴�.
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//jsp�� ó������, DAO�޼��� ȣ��
		String faq_pageNum=request.getParameter("faq_pageNum");
		if(faq_pageNum==null){
			faq_pageNum="1";
		}//if end
		
		int faq_pageSize=10;//�� ������ �� �� ����
		int faq_currentPage=Integer.parseInt(faq_pageNum);
		
		int faq_startRow=(faq_currentPage-1)*faq_pageSize+1;//�� �������� ���� �۹�ȣ
		int faq_endRow=faq_currentPage*faq_pageSize;//���������� ������ �۹�ȣ
		
		int faq_count=0;//�� �۰��� ���� ����
		int faq_number=0;//�� ��ȣ ó���ϱ� ���� ����
		int faq_pageBlock=10;//���� ������ ��
		
		List faq_boardList=null;
		faqDAO faq_dao=faqDAO.getInstance();//dao��ü���
		faq_count=faq_dao.getBoardCount();//��ü �۰��� ���
		
		if(faq_count>0){//���� ������ 
			faq_boardList=faq_dao.getList(faq_startRow, faq_pageSize);//dao�޼��� ȣ���ϰ� ��� �޴´�
		}else{//���� ������
			faq_boardList=Collections.EMPTY_LIST;//����ִٴ� ��
		}//else end
		
		faq_number=faq_count-(faq_currentPage-1)*faq_pageSize;//����� �۹�ȣ ����
		
		int faq_pageCount=faq_count/faq_pageSize+(faq_count%faq_pageSize==0?0:1);//�� ������ ��
		//                 ��                       �Ǵٸ� ���ڵ� ��(31�� ��, �������� 4���� ������)
		int faq_startPage=(faq_currentPage/10)*10+1;//����������
		//                
		int faq_endPage=faq_startPage+faq_pageBlock-1;
		//             1+10-1=10 end������
		
		//if(endPage>pageCount){//��������
			//endPage=pageCount;
		//}//if end		
		
		//JSP���� ����ϵ��� request,setAttribute("key",value) �۾��� �Ѵ�
		request.setAttribute("faq_pageNum", faq_pageNum);
		request.setAttribute("faq_currentPage", faq_currentPage);
		
		request.setAttribute("faq_startRow", faq_startRow);
		request.setAttribute("faq_endRow", faq_endRow);

		request.setAttribute("faq_pageBlock", faq_pageBlock);
		request.setAttribute("faq_pageCount", faq_pageCount);
		request.setAttribute("faq_startPage", faq_startPage);
		request.setAttribute("faq_endPage", faq_endPage);

		request.setAttribute("faq_count", new Integer(faq_count));
		request.setAttribute("faq_pageSize", new Integer(faq_pageSize));
		request.setAttribute("faq_number", new Integer(faq_number));
		
		request.setAttribute("faq_boardList", faq_boardList);
		
		
	return "/craft_board_faq/list.jsp";//��(/board/list.jsp)�� ���� ==> ��Ʈ�ѷ��� ���� ==> ��� �Ѿ��
	}//requestPro() end
}//class end

