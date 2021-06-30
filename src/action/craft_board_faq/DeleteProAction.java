package action.craft_board_faq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import craft_faq.faqDAO;

public class DeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		
		String faq_pageNum=request.getParameter("faq_pageNum");
		int faq_num=Integer.parseInt(request.getParameter("faq_num"));
		String faq_pw=request.getParameter("faq_pw");
		
		faqDAO dao=faqDAO.getInstance();//dao��ü��� 
		int faq_check=dao.deleteBoard(faq_num, faq_pw);//dao�޼��� ȣ��,num,pw�� �μ��� �ѱ�� 
		//x=1;//���������� ���� 
		//x=0;//���� ����,��ȣƲ��
		
		//�ش� �信�� �����  �Ӽ� ����
		request.setAttribute("faq_pageNum", faq_pageNum);
		request.setAttribute("faq_check", faq_check);
		
		return "/craft_board_faq/deletePro.jsp";//�丮��
	}//requestPro()-end

}//class-end
