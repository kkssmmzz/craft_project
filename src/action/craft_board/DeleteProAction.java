package action.craft_board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import craft_board.BoardDAO;

public class DeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		
		String craft_pageNum=request.getParameter("craft_pageNum");
		int craft_num=Integer.parseInt(request.getParameter("craft_num"));
		String craft_pw=request.getParameter("craft_pw");
		
		BoardDAO dao=BoardDAO.getInstance();//dao객체얻기 
		int check=dao.deleteBoard(craft_num, craft_pw);//dao메서드 호출,craft_num,craft_pw를 인수로 넘긴다 
		//x=1;//정상적으로 삭제 
		//x=0;//삭제 실패,암호틀림
		
		//해다 뷰에서 사용할  속성 지정
		request.setAttribute("craft_pageNum", craft_pageNum);
		request.setAttribute("check", check);
		
		return "/craft_board/deletePro.jsp";//뷰리턴
	}//requestPro()-end

}//class-end
