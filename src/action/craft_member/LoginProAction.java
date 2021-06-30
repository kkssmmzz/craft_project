package action.craft_member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import craft_member.*;
public class LoginProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		String craft_id=request.getParameter("craft_id");
		String craft_pw=request.getParameter("craft_pw");
		
		MemberDAO dao=MemberDAO.getInstance();//dao��ü���
		int check=dao.userCheck(craft_id, craft_pw);//dao�޼��� ȣ��, id,pw�� �μ��� �ѱ��
		//x=1 ���� ����
		//x=0 ��ȣ Ʋ��
		//x=-1 ���� id
		
		request.setAttribute("check", check);
		request.setAttribute("craft_id", craft_id);
		request.setAttribute("memId", craft_id);
		
		return "/craft_member/loginPro.jsp";//�丮��
	}//requestPro() end

}
