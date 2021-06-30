package action.craft_board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import craft_board.*;
import command.CommandAction;

public class UpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int craft_num=Integer.parseInt(request.getParameter("craft_num"));
		String craft_pageNum=request.getParameter("craft_pageNum");
		
		BoardDAO dao=BoardDAO.getInstance();//dao��ü ���
		BoardDTO dto=dao.getUpdate(craft_num);//dao�޼��� ȣ�� craft_num�� ���ڷ� �ѱ��
		
		request.setAttribute("dto", dto);
		request.setAttribute("craft_pageNum", craft_pageNum);
		request.setAttribute("craft_num", new Integer(craft_num));
		
		return "/craft_board/updateForm.jsp";//�丮��
	}//requestPro() end

}//class end
