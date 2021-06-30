package action.craft_board_noti;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import craft_noti.*;
import command.CommandAction;

public class UpdateFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		int noti_num=Integer.parseInt(request.getParameter("noti_num"));
		String pageNum=request.getParameter("pageNum");
		
		notiDAO dao=notiDAO.getInstance();//dao 객체 얻기
		notiDTO dto=dao.getUpdate(noti_num);//dao메서드 호출, num을 인자로 넘긴다.
				
		request.setAttribute("dto", dto);		
		request.setAttribute("pageNum", pageNum);		
		request.setAttribute("noti_num", new Integer(noti_num));		
	
		return "/craft_board_noti/updateForm.jsp";//뷰 리턴
	}//requestPro() end

}//class end
