package action.craft_board_noti;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import craft_noti.*;
import command.CommandAction;

public class ContentAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int noti_num=Integer.parseInt(request.getParameter("noti_num"));//�Ķ���ͷ� �Ѿ�ö��� ���ڿ��� �Ѿ���Ƿ� ������ �ٲ��ش�.
		String pageNum=request.getParameter("pageNum");
		
		notiDAO dao=notiDAO.getInstance();//dao��ü ���
		notiDTO dto=dao.getBoard(noti_num);//dao�޼��� ȣ��, num�� �μ��� �ѱ��.

		//�ش� �信�� ����� �Ӽ� ����
		request.setAttribute("noti_num", new Integer(noti_num));
		request.setAttribute("pageNum", new Integer(pageNum));
		request.setAttribute("dto", dto);
		
		String noti_content=dto.getNoti_content();
		noti_content=noti_content.replace("\n", "<br>");
		request.setAttribute("noti_content", noti_content);
		
		return "/craft_board_noti/content.jsp";
	}//requestPro() end

}//class end
