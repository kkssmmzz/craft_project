package action.craft_member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import craft_member.*;

public class ModifyFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		String craft_id=request.getParameter("craft_id");
		MemberDAO dao=MemberDAO.getInstance();//dao객체 얻기
		MemberDTO dto=dao.getMember(craft_id);//dao메서드 호출, id를 인수로 넘긴다
		
		String craft_email=dto.getCraft_email();
		int idx=craft_email.indexOf("@");
		System.out.println(idx);
		
		String craft_email1=craft_email.substring(0,idx);
		String craft_email2=craft_email.substring(idx);

		
		String craft_tel=dto.getCraft_tel();
		String craft_tel1=craft_tel.substring(0,3);
		String craft_tel2=craft_tel.substring(3,7);
		String craft_tel3=craft_tel.substring(7);
				
		request.setAttribute("dto", dto);
		
		request.setAttribute("craft_email1", craft_email1);
		request.setAttribute("craft_email2", craft_email2);
		
		request.setAttribute("craft_tel1", craft_tel1);
		request.setAttribute("craft_tel2", craft_tel2);
		request.setAttribute("craft_tel3", craft_tel3);
		//request.setAttribute("craft_id", craft_id);
		
		return "/craft_member/modifyForm.jsp";//뷰 리턴
	}
	
}
