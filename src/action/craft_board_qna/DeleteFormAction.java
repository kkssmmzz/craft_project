package action.craft_board_qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import command.CommandAction;

//인터페이스를 상속 받아서 구현하는 클래스
public class DeleteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		String qna_pageNum=request.getParameter("qna_pageNum");
		int qna_num=Integer.parseInt(request.getParameter("qna_num"));
		
		//해당 뷰에서 사용할 속성들
		request.setAttribute("qna_pageNum", qna_pageNum);
		request.setAttribute("qna_num", new Integer(qna_num));
		
		return "/craft_board_qna/deleteForm.jsp";//뷰리턴
	}//requestPro()-end

}//class end



