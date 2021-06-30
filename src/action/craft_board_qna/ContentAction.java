package action.craft_board_qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import craft_qna.*;

public class ContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int qna_num=Integer.parseInt(request.getParameter("qna_num"));
		String qna_pageNum=request.getParameter("qna_pageNum");
		
		QnaDAO dao=QnaDAO.getInstance();//dao객체 얻기
		QnaDTO dto=dao.getBoard(qna_num);//dto메서드 호출, qna_num을 인수로 넘긴다
		
		//해당 뷰에서 사용할 속성 설정
		request.setAttribute("qna_num", new Integer(qna_num));
		request.setAttribute("qna_pageNum", new Integer(qna_pageNum));
		request.setAttribute("dto", dto);
		
		String qna_content=dto.getQna_content();
		qna_content=qna_content.replace("\n", "<br>");
		request.setAttribute("qna_content", qna_content);
		
		int qna_ref=dto.getQna_ref();
		int qna_re_step=dto.getQna_re_step();
		int qna_re_level=dto.getQna_re_level();
		
		request.setAttribute("qna_ref", qna_ref);
		request.setAttribute("qna_re_step", qna_re_step);
		request.setAttribute("qna_re_level", qna_re_level);
		
		return "/craft_board_qna/content.jsp";// 뷰를 리턴
	}//requestPro() end

}//class end
