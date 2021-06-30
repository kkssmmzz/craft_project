package action.craft_board_item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import command.CommandAction;
import craft_item.*;

public class ItemDetailAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		String craft_pageNum=request.getParameter("craft_pageNum");
		int craft_pro_no=Integer.parseInt(request.getParameter("craft_pro_no"));
		
		ItemDAO dao=ItemDAO.getInstance();
		ItemDTO dto=dao.getDetail(craft_pro_no);
		
		request.setAttribute("craft_pro_no", craft_pro_no);
		request.setAttribute("craft_pageNum", craft_pageNum);
		request.setAttribute("dto", dto);
		
		return "/craft_item/itemDetail.jsp";
	}//requestPro() end
}//class end
