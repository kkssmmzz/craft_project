package action.craft_board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import craft_board.*;
import command.CommandAction;

public class UpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		
		request.setCharacterEncoding("UTF-8");
		String craft_pageNum=request.getParameter("craft_pageNum");
		
		BoardDTO dto=new BoardDTO();//객체생성
		
		//클라이언트가 보내준 데이터를 받아서 dto에 저장
		dto.setCraft_num(Integer.parseInt(request.getParameter("craft_num")));
		dto.setCraft_writer(request.getParameter("craft_writer"));
		dto.setCraft_subject(request.getParameter("craft_subject"));
		dto.setCraft_content(request.getParameter("craft_content"));
		dto.setCraft_pw(request.getParameter("craft_pw"));
		
		BoardDAO dao=BoardDAO.getInstance();//dao객체 얻기
		int x=dao.updateBoard(dto);//dao메서드 호출, dto를 인수로 넘긴다.		
			//x=1;//정상적인 수정
			//x=0;//암호틀릴때
		
		request.setAttribute("check", x);
		request.setAttribute("craft_pageNum", craft_pageNum);
		
		return "/craft_board/updatePro.jsp";
	}//requestPro() end

}//class end
