package action.craft_board_qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import craft_qna.*;

public class UpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int qna_num=Integer.parseInt(request.getParameter("qna_num"));
		String qna_pageNum=request.getParameter("qna_pageNum");
		
		QnaDAO qna_dao=QnaDAO.getInstance();//dao��ü ���
		QnaDTO qna_dto=qna_dao.getUpdate(qna_num);//dao�޼��� ȣ�� num�� ���ڷ� �ѱ��
		
		request.setAttribute("qna_dto", qna_dto);
		request.setAttribute("qna_pageNum", qna_pageNum);
		request.setAttribute("qna_num", new Integer(qna_num));
		
		return "/craft_board_qna/updateForm.jsp";//�丮��
	}//requestPro() end

}//class end
