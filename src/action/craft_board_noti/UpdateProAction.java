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
		notiDTO dto= new notiDTO();//dto��ü ����
		
		//Ŭ���̾�Ʈ�� ������ �����͸� �޾Ƽ� dto�� ����
		dto.setNoti_num(Integer.parseInt(request.getParameter("noti_num")));
		dto.setNoti_writer(request.getParameter("noti_writer"));
		dto.setNoti_subject(request.getParameter("noti_subject"));
		dto.setNoti_content(request.getParameter("noti_content"));
		dto.setNoti_pw(request.getParameter("noti_pw"));
		
		notiDAO dao=notiDAO.getInstance();
		int x=dao.updateBoard(dto);//dao�޼��� ȣ��, dto�� �μ��� �ѱ��.
		//x=1;//�������� ����
		//x=0;//��ȣ�� Ʋ�� ��
		
		request.setAttribute("check", x);
		request.setAttribute("pageNum", pageNum);
		
		return "/craft_board_noti/updatePro.jsp";

	}//requestPro() end

}//class end
