package action.craft_board_noti;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class WriteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//원글
		int noti_num=0;//변수 선언
		
		if(request.getParameter("noti_num")!=null){
			noti_num=Integer.parseInt(request.getParameter("noti_num"));
		}//if end
		
		request.setAttribute("noti_num", new Integer(noti_num));

		return "/craft_board_noti/writeForm.jsp";
	}//requestPro() end

}//class end
