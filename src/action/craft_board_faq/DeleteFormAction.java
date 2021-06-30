package action.craft_board_faq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import command.CommandAction;

//인터페이스를 상속 받아서 구현하는 클래스
public class DeleteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		String faq_pageNum=request.getParameter("faq_pageNum");
		int faq_num=Integer.parseInt(request.getParameter("faq_num"));
		
		//해당 뷰에서 사용할 속성들
		request.setAttribute("faq_pageNum", faq_pageNum);
		request.setAttribute("faq_num", new Integer(faq_num));
		
		return "/craft_board_faq/deleteForm.jsp";//뷰리턴
	}//requestPro()-end

}//class end



