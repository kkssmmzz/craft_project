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
		
		MemberDAO dao=MemberDAO.getInstance();//dao객체 얻기
		int check=dao.deleteMember(craft_id, craft_pw);//dao메서드 호출
		
		request.setAttribute("check", check);
		//x=1; 삭제성공
		//x=-1; 암호가 틀림
		//x=0; 아이디가 없음
		
		
		return "/craft_member/deletePro.jsp";
	}

}
