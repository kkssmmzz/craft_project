package action.craft_board_noti;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class DeleteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		String pageNum=request.getParameter("pageNum");
		int noti_num=Integer.parseInt(request.getParameter("noti_num"));
		
		//해당 뷰에서 사용할 속성들
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("noti_num", new Integer(noti_num));
		
		return "/craft_board_noti/deleteForm.jsp";//뷰리턴
	}//requestPro() end

}//class end
