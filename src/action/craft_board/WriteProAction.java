package action.craft_board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import craft_board.*;
import command.CommandAction;

//Action : jsp����,  DAO�޼��� ȣ��, �並 ����
public class WriteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		BoardDTO dto=new BoardDTO();//��ü����
		
		//Ŭ���̾�Ʈ�� ������ ������ �޾Ƽ� dto�� ����
		dto.setCraft_num(Integer.parseInt(request.getParameter("craft_num")));
		dto.setCraft_writer(request.getParameter("craft_writer"));
		dto.setCraft_subject(request.getParameter("craft_subject"));
		
		dto.setCraft_ref(Integer.parseInt(request.getParameter("craft_ref")));
		dto.setCraft_re_step(Integer.parseInt(request.getParameter("craft_re_step")));
		dto.setCraft_re_level(Integer.parseInt(request.getParameter("craft_re_level")));
		
		dto.setCraft_pw(request.getParameter("craft_pw"));
		dto.setCraft_content(request.getParameter("craft_content"));
		dto.setCraft_ip(request.getRemoteAddr());//IP
		
		BoardDAO dao=BoardDAO.getInstance();//dao��ü ��� 
		dao.insertBoard(dto);//dao�޼��� ȣ�� , dto�� �μ��� �ѱ�� 
		
		return "/craft_board/writePro.jsp";//�� ����
	}//requestPro() -end
  
}//WriteProAction end
