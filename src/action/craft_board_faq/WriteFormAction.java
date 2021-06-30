package action.craft_board_faq;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

//�������̽� ����
public class WriteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//����,���
		int faq_num=0, faq_ref=1, faq_re_step=0, faq_re_level=0;//���� ����
				
		if(request.getParameter("faq_num")!=null){//����̸�
			faq_num=Integer.parseInt(request.getParameter("faq_num"));
			faq_ref=Integer.parseInt(request.getParameter("faq_ref"));
			faq_re_step=Integer.parseInt(request.getParameter("faq_re_step"));
			faq_re_level=Integer.parseInt(request.getParameter("faq_re_level"));
		}//if end
		
		//�ش��(jsp)���� ����� �Ӽ� ����
		request.setAttribute("faq_num", new Integer(faq_num));
		request.setAttribute("faq_ref", new Integer(faq_ref));
		request.setAttribute("faq_re_step", new Integer(faq_re_step));
		request.setAttribute("faq_re_level", new Integer(faq_re_level));
		
		
		return "/craft_board_faq/writeForm.jsp";//�� ����,��Ʈ�ѷ��� �ѱ�� 
	}//requestPro() end

}//class end
