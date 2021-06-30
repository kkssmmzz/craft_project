package action.craft_board_qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import craft_qna.*;
public class UpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		
		request.setCharacterEncoding("UTF-8");
		String qna_pageNum=request.getParameter("qna_pageNum");
		
		QnaDTO qna_dto=new QnaDTO();//객체생성
		
		//클라이언트가 보내준 데이터를 받아서 dto에 저장
		qna_dto.setQna_num(Integer.parseInt(request.getParameter("qna_num")));
		qna_dto.setQna_writer(request.getParameter("qna_writer"));
		qna_dto.setQna_subject(request.getParameter("qna_subject"));
		qna_dto.setQna_content(request.getParameter("qna_content"));
		qna_dto.setQna_pw(request.getParameter("qna_pw"));
		
		QnaDAO qna_dao=QnaDAO.getInstance();//dao객체 얻기
		int x=qna_dao.updateBoard(qna_dto);//dao메서드 호출, dto를 인수로 넘긴다.		
			//x=1;//정상적인 수정
			//x=0;//암호틀릴때
		
		request.setAttribute("qna_check", x);
		request.setAttribute("qna_pageNum", qna_pageNum);
		
		return "/craft_board_qna/updatePro.jsp";
	}//requestPro() end

}//class end
