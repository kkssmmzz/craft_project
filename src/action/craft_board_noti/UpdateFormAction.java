package action.craft_board_noti;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import craft_noti.*;
import command.CommandAction;

public class UpdateFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		int noti_num=Integer.parseInt(request.getParameter("noti_num"));
		String pageNum=request.getParameter("pageNum");
		
		notiDAO dao=notiDAO.getInstance();//dao ��ü ���
		notiDTO dto=dao.getUpdate(noti_num);//dao�޼��� ȣ��, num�� ���ڷ� �ѱ��.
				
		request.setAttribute("dto", dto);		
		request.setAttribute("pageNum", pageNum);		
		request.setAttribute("noti_num", new Integer(noti_num));		
	
		return "/craft_board_noti/updateForm.jsp";//�� ����
	}//requestPro() end

}//class end
