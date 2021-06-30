package action.craft_board;
import javax.servlet.http.*;

import command.CommandAction;//�������̵� - Ŭ������ ����� ���� ���� ������Ѵ�.
import craft_board.BoardDAO;

import java.util.*;//List,ArrayList

//�������̽� ��� �޾� ���� Ŭ���� �۾��ϴ� ��
//1. action�� jspó�� ������ action�� �Ѵ�.
//2. DAO�޼��� ȣ���Ͽ� ������� ��´�.
public class ListAction implements CommandAction{

	@Override     //��ûó���ϴ� ���̶� �޼��尡 requestPro()�̴�.
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//jsp�� ó������, DAO�޼��� ȣ��
		String craft_pageNum=request.getParameter("craft_pageNum");
		if(craft_pageNum==null){
			craft_pageNum="1";
		}//if end
		
		int pageSize=10;//�� ������ �� �� ����
		int currentPage=Integer.parseInt(craft_pageNum);
		
		int startRow=(currentPage-1)*pageSize+1;//�� �������� ���� �۹�ȣ
		int endRow=currentPage*pageSize;//���������� ������ �۹�ȣ
		
		int count=0;//�� �۰��� ���� ����
		int number=0;//�� ��ȣ ó���ϱ� ���� ����
		int pageBlock=10;//���� ������ ��
		
		List boardList=null;
		BoardDAO dao=BoardDAO.getInstance();//dao��ü���
		count=dao.getBoardCount();//��ü �۰��� ���
		
		if(count>0){//���� ������ 
			boardList=dao.getList(startRow, pageSize);//dao�޼��� ȣ���ϰ� ��� �޴´�
		}else{//���� ������
			boardList=Collections.EMPTY_LIST;//����ִٴ� ��
		}//else end
		
		number=count-(currentPage-1)*pageSize;//����� �۹�ȣ ����
		
		int pageCount=count/pageSize+(count%pageSize==0?0:1);//�� ������ ��
		//                 ��                       �Ǵٸ� ���ڵ� ��(31�� ��, �������� 4���� ������)
		int startPage=(currentPage/10)*10+1;//����������
		//                
		int endPage=startPage+pageBlock-1;
		//             1+10-1=10 end������
		
		//JSP���� ����ϵ��� request,setAttribute("key",value) �۾��� �Ѵ�
		request.setAttribute("craft_pageNum", craft_pageNum);
		request.setAttribute("currentPage", currentPage);
		
		request.setAttribute("startRow", startRow);
		request.setAttribute("endRow", endRow);

		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);

		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		
		request.setAttribute("boardList", boardList);
		
		
	return "/craft_board/list.jsp";//��(/board/list.jsp)�� ���� ==> ��Ʈ�ѷ��� ���� ==> ��� �Ѿ��
	}//requestPro() end
}//class end

