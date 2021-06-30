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
		
		MemberDAO dao=MemberDAO.getInstance();//dao객체얻기
		int check=dao.userCheck(craft_id, craft_pw);//dao메서드 호출, id,pw를 인수로 넘긴다
		//x=1 인증 성공
		//x=0 암호 틀림
		//x=-1 없는 id
		
		request.setAttribute("check", check);
		request.setAttribute("craft_id", craft_id);
		request.setAttribute("memId", craft_id);
		
		return "/craft_member/loginPro.jsp";//뷰리턴
	}//requestPro() end

}
