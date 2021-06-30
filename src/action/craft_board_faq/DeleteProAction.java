package action.craft_board_faq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import craft_faq.faqDAO;

public class DeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		
		String faq_pageNum=request.getParameter("faq_pageNum");
		int faq_num=Integer.parseInt(request.getParameter("faq_num"));
		String faq_pw=request.getParameter("faq_pw");
		
		faqDAO dao=faqDAO.getInstance();//dao객체얻기 
		int faq_check=dao.deleteBoard(faq_num, faq_pw);//dao메서드 호출,num,pw를 인수로 넘긴다 
		//x=1;//정상적으로 삭제 
		//x=0;//삭제 실패,암호틀림
		
		//해다 뷰에서 사용할  속성 지정
		request.setAttribute("faq_pageNum", faq_pageNum);
		request.setAttribute("faq_check", faq_check);
		
		return "/craft_board_faq/deletePro.jsp";//뷰리턴
	}//requestPro()-end

}//class-end
