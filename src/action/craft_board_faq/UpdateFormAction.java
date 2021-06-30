package action.craft_board_faq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import craft_faq.*;

public class UpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int faq_num=Integer.parseInt(request.getParameter("faq_num"));
		String faq_pageNum=request.getParameter("faq_pageNum");
		
		faqDAO faq_dao=faqDAO.getInstance();//dao��ü ���
		faqDTO faq_dto=faq_dao.getUpdate(faq_num);//dao�޼��� ȣ�� num�� ���ڷ� �ѱ��
		
		request.setAttribute("faq_dto", faq_dto);
		request.setAttribute("faq_pageNum", faq_pageNum);
		request.setAttribute("faq_num", new Integer(faq_num));
		
		return "/craft_board_faq/updateForm.jsp";//�丮��
	}//requestPro() end

}//class end
