package action.craft_board_qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import command.CommandAction;

//�������̽��� ��� �޾Ƽ� �����ϴ� Ŭ����
public class DeleteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		String qna_pageNum=request.getParameter("qna_pageNum");
		int qna_num=Integer.parseInt(request.getParameter("qna_num"));
		
		//�ش� �信�� ����� �Ӽ���
		request.setAttribute("qna_pageNum", qna_pageNum);
		request.setAttribute("qna_num", new Integer(qna_num));
		
		return "/craft_board_qna/deleteForm.jsp";//�丮��
	}//requestPro()-end

}//class end



