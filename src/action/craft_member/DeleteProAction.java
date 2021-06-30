package action.craft_member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import craft_member.*;
public class DeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		String craft_id=request.getParameter("craft_id");
		String craft_pw=request.getParameter("craft_pw");
		
		MemberDAO dao=MemberDAO.getInstance();//dao��ü ���
		int check=dao.deleteMember(craft_id, craft_pw);//dao�޼��� ȣ��
		
		request.setAttribute("check", check);
		//x=1; ��������
		//x=-1; ��ȣ�� Ʋ��
		//x=0; ���̵� ����
		
		
		return "/craft_member/deletePro.jsp";
	}

}
