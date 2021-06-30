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
		
		BoardDTO dto=new BoardDTO();//��ü����
		
		//Ŭ���̾�Ʈ�� ������ �����͸� �޾Ƽ� dto�� ����
		dto.setCraft_num(Integer.parseInt(request.getParameter("craft_num")));
		dto.setCraft_writer(request.getParameter("craft_writer"));
		dto.setCraft_subject(request.getParameter("craft_subject"));
		dto.setCraft_content(request.getParameter("craft_content"));
		dto.setCraft_pw(request.getParameter("craft_pw"));
		
		BoardDAO dao=BoardDAO.getInstance();//dao��ü ���
		int x=dao.updateBoard(dto);//dao�޼��� ȣ��, dto�� �μ��� �ѱ��.		
			//x=1;//�������� ����
			//x=0;//��ȣƲ����
		
		request.setAttribute("check", x);
		request.setAttribute("craft_pageNum", craft_pageNum);
		
		return "/craft_board/updatePro.jsp";
	}//requestPro() end

}//class end
