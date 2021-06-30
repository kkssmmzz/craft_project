package action.craft_board_qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import craft_qna.QnaDAO;

public class DeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		
		String qna_pageNum=request.getParameter("qna_pageNum");
		int qna_num=Integer.parseInt(request.getParameter("qna_num"));
		String qna_pw=request.getParameter("qna_pw");
		
		QnaDAO dao=QnaDAO.getInstance();//dao객체얻기 
		int qna_check=dao.deleteBoard(qna_num, qna_pw);//dao메서드 호출,num,pw를 인수로 넘긴다 
		//x=1;//정상적으로 삭제 
		//x=0;//삭제 실패,암호틀림
		
		//해다 뷰에서 사용할  속성 지정
		request.setAttribute("qna_pageNum", qna_pageNum);
		request.setAttribute("qna_check", qna_check);
		
		return "/craft_board_qna/deletePro.jsp";//뷰리턴
	}//requestPro()-end

}//class-end
