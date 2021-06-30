package action.craft_board_faq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import craft_faq.*;

public class ContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int faq_num=Integer.parseInt(request.getParameter("faq_num"));
		String faq_pageNum=request.getParameter("faq_pageNum");
		
		faqDAO dao=faqDAO.getInstance();//dao객체 얻기
		faqDTO dto=dao.getBoard(faq_num);//dto메서드 호출, faq_num을 인수로 넘긴다
		
		//해당 뷰에서 사용할 속성 설정
		request.setAttribute("faq_num", new Integer(faq_num));
		request.setAttribute("faq_pageNum", new Integer(faq_pageNum));
		request.setAttribute("dto", dto);
		
		String faq_content=dto.getFaq_content();
		faq_content=faq_content.replace("\n", "<br>");
		request.setAttribute("faq_content", faq_content);
		
		int faq_ref=dto.getFaq_ref();
		int faq_re_step=dto.getFaq_re_step();
		int faq_re_level=dto.getFaq_re_level();
		
		request.setAttribute("faq_ref", faq_ref);
		request.setAttribute("faq_re_step", faq_re_step);
		request.setAttribute("faq_re_level", faq_re_level);
		
		return "/craft_board_faq/content.jsp";// 뷰를 리턴
	}//requestPro() end

}//class end
