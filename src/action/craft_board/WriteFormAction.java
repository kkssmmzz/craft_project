package action.craft_board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

//�������̽� ����
public class WriteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//����,���
		int craft_num=0, craft_ref=1, craft_re_step=0, craft_re_level=0;//���� ����
				
		if(request.getParameter("craft_num")!=null){//����̸�
			craft_num=Integer.parseInt(request.getParameter("craft_num"));
			craft_ref=Integer.parseInt(request.getParameter("craft_ref"));
			craft_re_step=Integer.parseInt(request.getParameter("craft_re_step"));
			craft_re_level=Integer.parseInt(request.getParameter("craft_re_level"));
		}//if end
		
		//�ش��(jsp)���� ����� �Ӽ� ����
		request.setAttribute("craft_num", new Integer(craft_num));
		request.setAttribute("craft_ref", new Integer(craft_ref));
		request.setAttribute("craft_re_step", new Integer(craft_re_step));
		request.setAttribute("craft_re_level", new Integer(craft_re_level));
		
		
		return "/craft_board/writeForm.jsp";//�� ����,��Ʈ�ѷ��� �ѱ�� 
	}//requestPro() end

}//class end
