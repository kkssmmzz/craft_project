package action.craft_board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import craft_board.*;
import command.CommandAction;

//Action : jsp로직,  DAO메서드 호출, 뷰를 리턴
public class WriteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		BoardDTO dto=new BoardDTO();//객체생성
		
		//클라이언트가 보내준 데이터 받아서 dto에 저장
		dto.setCraft_num(Integer.parseInt(request.getParameter("craft_num")));
		dto.setCraft_writer(request.getParameter("craft_writer"));
		dto.setCraft_subject(request.getParameter("craft_subject"));
		
		dto.setCraft_ref(Integer.parseInt(request.getParameter("craft_ref")));
		dto.setCraft_re_step(Integer.parseInt(request.getParameter("craft_re_step")));
		dto.setCraft_re_level(Integer.parseInt(request.getParameter("craft_re_level")));
		
		dto.setCraft_pw(request.getParameter("craft_pw"));
		dto.setCraft_content(request.getParameter("craft_content"));
		dto.setCraft_ip(request.getRemoteAddr());//IP
		
		BoardDAO dao=BoardDAO.getInstance();//dao객체 얻기 
		dao.insertBoard(dto);//dao메서드 호출 , dto를 인수로 넘긴다 
		
		return "/craft_board/writePro.jsp";//뷰 리턴
	}//requestPro() -end
  
}//WriteProAction end
