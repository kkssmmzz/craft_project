package action.craft_board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import craft_board.*;
import command.CommandAction;

public class ContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int craft_num=Integer.parseInt(request.getParameter("craft_num"));
		String craft_pageNum=request.getParameter("craft_pageNum");
		
		BoardDAO dao=BoardDAO.getInstance();//dao객체 얻기
		BoardDTO dto=dao.getBoard(craft_num);//dto메서드 호출, num을 인수로 넘긴다
		
		//해당 뷰에서 사용할 속성 설정
		request.setAttribute("craft_num", new Integer(craft_num));
		request.setAttribute("craft_pageNum", new Integer(craft_pageNum));
		request.setAttribute("dto", dto);
		
		String craft_content=dto.getCraft_content();
		craft_content=craft_content.replace("\n", "<br>");
		request.setAttribute("craft_content", craft_content);
		
		int craft_ref=dto.getCraft_ref();
		int craft_re_step=dto.getCraft_re_step();
		int craft_re_level=dto.getCraft_re_level();
		
		request.setAttribute("craft_ref", craft_ref);
		request.setAttribute("craft_re_step", craft_re_step);
		request.setAttribute("craft_re_level", craft_re_level);
		
		return "/craft_board/content.jsp";// 뷰를 리턴
	}//requestPro() end

}//class end
