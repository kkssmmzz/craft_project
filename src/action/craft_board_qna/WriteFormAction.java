package action.craft_board_qna;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

//인터페이스 구현
public class WriteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//원글,답글
		int qna_num=0, qna_ref=1, qna_re_step=0, qna_re_level=0;//변수 선언
				
		if(request.getParameter("qna_num")!=null){//답글이면
			qna_num=Integer.parseInt(request.getParameter("qna_num"));
			qna_ref=Integer.parseInt(request.getParameter("qna_ref"));
			qna_re_step=Integer.parseInt(request.getParameter("qna_re_step"));
			qna_re_level=Integer.parseInt(request.getParameter("qna_re_level"));
		}//if end
		
		//해당뷰(jsp)에서 사용할 속성 설정
		request.setAttribute("qna_num", new Integer(qna_num));
		request.setAttribute("qna_ref", new Integer(qna_ref));
		request.setAttribute("qna_re_step", new Integer(qna_re_step));
		request.setAttribute("qna_re_level", new Integer(qna_re_level));
		
		
		return "/craft_board_qna/writeForm.jsp";//뷰 리턴,컨트롤러로 넘긴다 
	}//requestPro() end

}//class end
