package action.craft_board_noti;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import craft_noti.*;
import command.CommandAction;

public class DeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		String pageNum=request.getParameter("pageNum");
		int noti_num=Integer.parseInt(request.getParameter("noti_num"));
		String noti_pw=request.getParameter("noti_pw");
		
		notiDAO dao=notiDAO.getInstance();//dao 객체 얻기
		int check=dao.deleteBoard(noti_num, noti_pw);//dao 메서드 호출, num과 pw를 인수로 보낸다.
		//x=1;//정상적으로 삭제
		//x=0;//삭제 실패, 암호 틀림
		
		//해당 뷰에서 사용할 속성 지정
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", check);
		
		return "/craft_board_noti/deletePro.jsp";//뷰리턴
	}

}//class end
