package action.craft_board_noti;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import craft_noti.*;
import command.CommandAction;

public class UpdateProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("UTF-8");
		String pageNum=request.getParameter("pageNum");
		notiDTO dto= new notiDTO();//dto객체 생성
		
		//클라이언트가 보내준 데이터를 받아서 dto에 저장
		dto.setNoti_num(Integer.parseInt(request.getParameter("noti_num")));
		dto.setNoti_writer(request.getParameter("noti_writer"));
		dto.setNoti_subject(request.getParameter("noti_subject"));
		dto.setNoti_content(request.getParameter("noti_content"));
		dto.setNoti_pw(request.getParameter("noti_pw"));
		
		notiDAO dao=notiDAO.getInstance();
		int x=dao.updateBoard(dto);//dao메서드 호출, dto를 인수로 넘긴다.
		//x=1;//정상적인 수정
		//x=0;//암호가 틀릴 때
		
		request.setAttribute("check", x);
		request.setAttribute("pageNum", pageNum);
		
		return "/craft_board_noti/updatePro.jsp";

	}//requestPro() end

}//class end
