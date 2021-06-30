package action.craft_board_faq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import command.CommandAction;

//�������̽��� ��� �޾Ƽ� �����ϴ� Ŭ����
public class DeleteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		String faq_pageNum=request.getParameter("faq_pageNum");
		int faq_num=Integer.parseInt(request.getParameter("faq_num"));
		
		//�ش� �信�� ����� �Ӽ���
		request.setAttribute("faq_pageNum", faq_pageNum);
		request.setAttribute("faq_num", new Integer(faq_num));
		
		return "/craft_board_faq/deleteForm.jsp";//�丮��
	}//requestPro()-end

}//class end



