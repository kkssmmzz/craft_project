package action.craft_board_qna;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import craft_qna.*;

//Action : jsp로직,  DAO메서드 호출, 뷰를 리턴
public class WriteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		QnaDTO qna_dto=new QnaDTO();//객체생성
		
		//클라이언트가 보내준 데이터 받아서 dto에 저장
		qna_dto.setQna_num(Integer.parseInt(request.getParameter("qna_num")));
		qna_dto.setQna_writer(request.getParameter("qna_writer"));
		qna_dto.setQna_subject(request.getParameter("qna_subject"));
		
		qna_dto.setQna_ref(Integer.parseInt(request.getParameter("qna_ref")));
		qna_dto.setQna_re_step(Integer.parseInt(request.getParameter("qna_re_step")));
		qna_dto.setQna_re_level(Integer.parseInt(request.getParameter("qna_re_level")));
		
		qna_dto.setQna_pw(request.getParameter("qna_pw"));
		qna_dto.setQna_content(request.getParameter("qna_content"));
		qna_dto.setQna_ip(request.getRemoteAddr());//IP
		
		QnaDAO qna_dao=QnaDAO.getInstance();//dao객체 얻기 
		qna_dao.insertBoard(qna_dto);//dao메서드 호출 , dto를 인수로 넘긴다 
		
		return "/craft_board_qna/writePro.jsp";//뷰 리턴
	}//requestPro() -end
  
}//WriteProAction end
