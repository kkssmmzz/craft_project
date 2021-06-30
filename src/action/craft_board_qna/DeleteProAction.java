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
		
		QnaDAO dao=QnaDAO.getInstance();//dao��ü��� 
		int qna_check=dao.deleteBoard(qna_num, qna_pw);//dao�޼��� ȣ��,num,pw�� �μ��� �ѱ�� 
		//x=1;//���������� ���� 
		//x=0;//���� ����,��ȣƲ��
		
		//�ش� �信�� �����  �Ӽ� ����
		request.setAttribute("qna_pageNum", qna_pageNum);
		request.setAttribute("qna_check", qna_check);
		
		return "/craft_board_qna/deletePro.jsp";//�丮��
	}//requestPro()-end

}//class-end
