package action.craft_board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import craft_board.BoardDAO;

public class DeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		
		String craft_pageNum=request.getParameter("craft_pageNum");
		int craft_num=Integer.parseInt(request.getParameter("craft_num"));
		String craft_pw=request.getParameter("craft_pw");
		
		BoardDAO dao=BoardDAO.getInstance();//dao��ü��� 
		int check=dao.deleteBoard(craft_num, craft_pw);//dao�޼��� ȣ��,craft_num,craft_pw�� �μ��� �ѱ�� 
		//x=1;//���������� ���� 
		//x=0;//���� ����,��ȣƲ��
		
		//�ش� �信�� �����  �Ӽ� ����
		request.setAttribute("craft_pageNum", craft_pageNum);
		request.setAttribute("check", check);
		
		return "/craft_board/deletePro.jsp";//�丮��
	}//requestPro()-end

}//class-end
