package action.craft_board_faq;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import craft_faq.*;

//Action : jsp����,  DAO�޼��� ȣ��, �並 ����
public class WriteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		faqDTO faq_dto=new faqDTO();//��ü����
		
		//Ŭ���̾�Ʈ�� ������ ������ �޾Ƽ� dto�� ����
		faq_dto.setFaq_num(Integer.parseInt(request.getParameter("faq_num")));
		faq_dto.setFaq_writer(request.getParameter("faq_writer"));
		faq_dto.setFaq_subject(request.getParameter("faq_subject"));
		
		faq_dto.setFaq_ref(Integer.parseInt(request.getParameter("faq_ref")));
		faq_dto.setFaq_re_step(Integer.parseInt(request.getParameter("faq_re_step")));
		faq_dto.setFaq_re_level(Integer.parseInt(request.getParameter("faq_re_level")));
		
		faq_dto.setFaq_pw(request.getParameter("faq_pw"));
		faq_dto.setFaq_content(request.getParameter("faq_content"));
		faq_dto.setFaq_ip(request.getRemoteAddr());//IP
		
		faqDAO faq_dao=faqDAO.getInstance();//dao��ü ��� 
		faq_dao.insertBoard(faq_dto);//dao�޼��� ȣ�� , dto�� �μ��� �ѱ�� 
		
		return "/craft_board_faq/writePro.jsp";//�� ����
	}//requestPro() -end
  
}//WriteProAction end
