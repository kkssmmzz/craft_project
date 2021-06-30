package action.craft_board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import command.CommandAction;

//인터페이스를 상속 받아서 구현하는 클래스
public class DeleteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		String craft_pageNum=request.getParameter("craft_pageNum");
		int craft_num=Integer.parseInt(request.getParameter("craft_num"));
		
		//해당 뷰에서 사용할 속성들
		request.setAttribute("craft_pageNum", craft_pageNum);
		request.setAttribute("craft_num", new Integer(craft_num));
		
		return "/craft_board/deleteForm.jsp";//뷰리턴
	}//requestPro()-end

}//class end



