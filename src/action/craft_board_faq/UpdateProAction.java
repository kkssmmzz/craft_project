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
		
		faqDTO faq_dto=new faqDTO();//객체생성
		
		//클라이언트가 보내준 데이터를 받아서 dto에 저장
		faq_dto.setFaq_num(Integer.parseInt(request.getParameter("faq_num")));
		faq_dto.setFaq_writer(request.getParameter("faq_writer"));
		faq_dto.setFaq_subject(request.getParameter("faq_subject"));
		faq_dto.setFaq_content(request.getParameter("faq_content"));
		faq_dto.setFaq_pw(request.getParameter("faq_pw"));
		
		faqDAO faq_dao=faqDAO.getInstance();//dao객체 얻기
		int x=faq_dao.updateBoard(faq_dto);//dao메서드 호출, dto를 인수로 넘긴다.		
			//x=1;//정상적인 수정
			//x=0;//암호틀릴때
		
		request.setAttribute("faq_check", x);
		request.setAttribute("faq_pageNum", faq_pageNum);
		
		return "/craft_board_faq/updatePro.jsp";
	}//requestPro() end

}//class end
