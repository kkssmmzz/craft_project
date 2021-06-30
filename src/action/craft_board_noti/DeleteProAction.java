package action.craft_board_noti;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import craft_noti.*;
import command.CommandAction;

public class DeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		String pageNum=request.getParameter("pageNum");
		int noti_num=Integer.parseInt(request.getParameter("noti_num"));
		String noti_pw=request.getParameter("noti_pw");
		
		notiDAO dao=notiDAO.getInstance();//dao ��ü ���
		int check=dao.deleteBoard(noti_num, noti_pw);//dao �޼��� ȣ��, num�� pw�� �μ��� ������.
		//x=1;//���������� ����
		//x=0;//���� ����, ��ȣ Ʋ��
		
		//�ش� �信�� ����� �Ӽ� ����
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", check);
		
		return "/craft_board_noti/deletePro.jsp";//�丮��
	}

}//class end
