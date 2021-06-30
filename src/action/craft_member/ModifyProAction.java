package action.craft_member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import craft_member.*;

public class ModifyProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		String craft_id=request.getParameter("craft_id");
		
		//클라이언트 보내준 데이터를 dto에 저장
		MemberDTO dto=new MemberDTO();
		dto.setCraft_id(craft_id);
		dto.setCraft_pw(request.getParameter("craft_pw"));
		dto.setCraft_name(request.getParameter("craft_name"));
		
		String craft_email=(request.getParameter("craft_email1")+request.getParameter("craft_email2"));

		dto.setCraft_email(craft_email);
		String craft_tel=request.getParameter("craft_tel1");
		craft_tel+=request.getParameter("craft_tel2");
		craft_tel+=request.getParameter("craft_tel3");		
		dto.setCraft_tel(craft_tel);
		
		dto.setCraft_zipcode(request.getParameter("craft_zipcode"));
		dto.setCraft_addr(request.getParameter("craft_addr"));
		dto.setCraft_addr2(request.getParameter("craft_addr2"));
		
		MemberDAO dao=MemberDAO.getInstance();//dao 객체 얻기
		dao.updateMember(dto);//dao메서드 호출 dto를 인수로 넘긴다
		
		request.setAttribute("craft_id", craft_id);
		
		return "/craft_member/modifyPro.jsp";//뷰리턴
	}

}
