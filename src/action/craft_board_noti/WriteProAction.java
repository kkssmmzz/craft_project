package action.craft_board_noti;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import command.CommandAction;
import craft_noti.*;

public class WriteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("UTF-8");
		notiDTO dto=new notiDTO();//��ü����
		
		//Ŭ���̾�Ʈ�� ������ �����͸� �޾Ƽ� dto�� ����
		dto.setNoti_num(Integer.parseInt(request.getParameter("noti_num")));
		dto.setNoti_writer(request.getParameter("noti_writer"));
		dto.setNoti_subject(request.getParameter("noti_subject"));	
		dto.setNoti_pw(request.getParameter("noti_pw"));
		dto.setNoti_content(request.getParameter("noti_content"));
		dto.setNoti_ip(request.getRemoteAddr());
		
		notiDAO dao=notiDAO.getInstance();//dao��ü ���
		dao.insertBoard(dto);//dao�޼��� ȣ��, dto �μ��� �ѱ��.
		return "/craft_board_noti/writePro.jsp";
	}//requestPro() end

}//class end
