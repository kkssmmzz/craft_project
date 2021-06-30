package action.craft_board_noti;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import craft_noti.*;
import command.CommandAction;

public class ContentAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int noti_num=Integer.parseInt(request.getParameter("noti_num"));//파라미터로 넘어올때는 문자열로 넘어오므로 정수로 바꿔준다.
		String pageNum=request.getParameter("pageNum");
		
		notiDAO dao=notiDAO.getInstance();//dao객체 얻기
		notiDTO dto=dao.getBoard(noti_num);//dao메서드 호출, num을 인수로 넘긴다.

		//해당 뷰에서 사용할 속성 설정
		request.setAttribute("noti_num", new Integer(noti_num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("dto", dto);
		
		String noti_content=dto.getNoti_content();
		noti_content=noti_content.replace("\n", "<br>");
		request.setAttribute("noti_content", noti_content);
		
		return "/craft_board_noti/content.jsp";
	}//requestPro() end

}//class end
