package action.craft_board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import command.CommandAction;

//�������̽��� ��� �޾Ƽ� �����ϴ� Ŭ����
public class DeleteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		String craft_pageNum=request.getParameter("craft_pageNum");
		int craft_num=Integer.parseInt(request.getParameter("craft_num"));
		
		//�ش� �信�� ����� �Ӽ���
		request.setAttribute("craft_pageNum", craft_pageNum);
		request.setAttribute("craft_num", new Integer(craft_num));
		
		return "/craft_board/deleteForm.jsp";//�丮��
	}//requestPro()-end

}//class end



