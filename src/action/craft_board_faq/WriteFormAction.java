package action.craft_board_faq;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

//인터페이스 구현
public class WriteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//원글,답글
		int faq_num=0, faq_ref=1, faq_re_step=0, faq_re_level=0;//변수 선언
				
		if(request.getParameter("faq_num")!=null){//답글이면
			faq_num=Integer.parseInt(request.getParameter("faq_num"));
			faq_ref=Integer.parseInt(request.getParameter("faq_ref"));
			faq_re_step=Integer.parseInt(request.getParameter("faq_re_step"));
			faq_re_level=Integer.parseInt(request.getParameter("faq_re_level"));
		}//if end
		
		//해당뷰(jsp)에서 사용할 속성 설정
		request.setAttribute("faq_num", new Integer(faq_num));
		request.setAttribute("faq_ref", new Integer(faq_ref));
		request.setAttribute("faq_re_step", new Integer(faq_re_step));
		request.setAttribute("faq_re_level", new Integer(faq_re_level));
		
		
		return "/craft_board_faq/writeForm.jsp";//뷰 리턴,컨트롤러로 넘긴다 
	}//requestPro() end

}//class end
