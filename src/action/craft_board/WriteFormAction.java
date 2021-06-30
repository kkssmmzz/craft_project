package action.craft_board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

//인터페이스 구현
public class WriteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//원글,답글
		int craft_num=0, craft_ref=1, craft_re_step=0, craft_re_level=0;//변수 선언
				
		if(request.getParameter("craft_num")!=null){//답글이면
			craft_num=Integer.parseInt(request.getParameter("craft_num"));
			craft_ref=Integer.parseInt(request.getParameter("craft_ref"));
			craft_re_step=Integer.parseInt(request.getParameter("craft_re_step"));
			craft_re_level=Integer.parseInt(request.getParameter("craft_re_level"));
		}//if end
		
		//해당뷰(jsp)에서 사용할 속성 설정
		request.setAttribute("craft_num", new Integer(craft_num));
		request.setAttribute("craft_ref", new Integer(craft_ref));
		request.setAttribute("craft_re_step", new Integer(craft_re_step));
		request.setAttribute("craft_re_level", new Integer(craft_re_level));
		
		
		return "/craft_board/writeForm.jsp";//뷰 리턴,컨트롤러로 넘긴다 
	}//requestPro() end

}//class end
