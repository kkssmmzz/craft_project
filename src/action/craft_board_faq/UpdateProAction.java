package action.craft_board_faq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import craft_faq.*;
public class UpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		
		request.setCharacterEncoding("UTF-8");
		String faq_pageNum=request.getParameter("faq_pageNum");
		
		faqDTO faq_dto=new faqDTO();//��ü����
		
		//Ŭ���̾�Ʈ�� ������ �����͸� �޾Ƽ� dto�� ����
		faq_dto.setFaq_num(Integer.parseInt(request.getParameter("faq_num")));
		faq_dto.setFaq_writer(request.getParameter("faq_writer"));
		faq_dto.setFaq_subject(request.getParameter("faq_subject"));
		faq_dto.setFaq_content(request.getParameter("faq_content"));
		faq_dto.setFaq_pw(request.getParameter("faq_pw"));
		
		faqDAO faq_dao=faqDAO.getInstance();//dao��ü ���
		int x=faq_dao.updateBoard(faq_dto);//dao�޼��� ȣ��, dto�� �μ��� �ѱ��.		
			//x=1;//�������� ����
			//x=0;//��ȣƲ����
		
		request.setAttribute("faq_check", x);
		request.setAttribute("faq_pageNum", faq_pageNum);
		
		return "/craft_board_faq/updatePro.jsp";
	}//requestPro() end

}//class end
