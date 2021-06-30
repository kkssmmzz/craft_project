package action.craft_member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import craft_member.*;
public class InputProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		MemberDTO dto=new MemberDTO();//객체 생성
		
		//클라이언트가 보내준 데이터를 dto에 저장
		String craft_id=request.getParameter("craft_id");
		dto.setCraft_id(request.getParameter("craft_id"));
		dto.setCraft_pw(request.getParameter("craft_pw"));
		dto.setCraft_name(request.getParameter("craft_name"));
		//이메일
		String craft_email1=request.getParameter("craft_email1");
		String craft_email2=request.getParameter("craft_email2");
		String craft_email=craft_email1+craft_email2;
		dto.setCraft_email(craft_email);//dto에 담음
		//전화
		String craft_tel1=request.getParameter("craft_tel1");
		String craft_tel2=request.getParameter("craft_tel2");
		String craft_tel3=request.getParameter("craft_tel3");
		String craft_tel=craft_tel1+craft_tel2+craft_tel3;
		dto.setCraft_tel(craft_tel);//dto에 담음
		dto.setCraft_zipcode(request.getParameter("craft_zipcode"));
		dto.setCraft_addr(request.getParameter("craft_addr"));
		dto.setCraft_addr2(request.getParameter("craft_addr2"));
		
		MemberDAO dao=MemberDAO.getInstance();//dao객체얻기
		dao.insertMember(dto);//dao메서드 호출, dto를 인수로 넘긴다
		
		request.setAttribute("craft_id", craft_id);
		
		return "/craft_member/inputPro.jsp";//뷰를 리턴
	}//method-end

}//class end
