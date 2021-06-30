package action.craft_board_item;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import craft_item.*;

public class ItemListAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//jsp�� ó������, DAO�޼��� ȣ��
		String craft_pageNum=request.getParameter("craft_pageNum");
		if(craft_pageNum==null){
			craft_pageNum="1";
		}//if end
		
		int pageSize=8;
		int currentPage=Integer.parseInt(craft_pageNum);
		
		int startItem=(currentPage-1)*pageSize;//�� �������� ���� �۹�ȣ
		//                1       -1 *   8    +1=1
		//                2       -1 *   8    +1=9
	    int endItem=currentPage*pageSize;
		
		int count=0;
		int pageBlock=10;
		
		ItemDAO dao=ItemDAO.getInstance();
		count=dao.getItemCount();
		
		List<ItemDTO> list=new ArrayList<ItemDTO>();
		
		if(count>0){//���� ������ 
	         list=dao.getGoodList(startItem, pageSize);//dao�޼��� ȣ���ϰ� ��� �޴´�
	    }else{//���� ������
	         list=Collections.emptyList();//����ִٴ� ��
	    }//else end
		
		int pageCount=count/pageSize+(count%pageSize==0?0:1);
		int startPage=(currentPage/10)*10+1;                
		int endPage=startPage+pageBlock-1;
		
		if(endPage>pageCount){//��������
			endPage=pageCount;
		}//if end
		
		//JSP���� ����ϵ��� request,setAttribute("key",value) �۾��� �Ѵ�
		request.setAttribute("craft_pageNum", craft_pageNum);
		request.setAttribute("currentPage", currentPage);
		
		request.setAttribute("startItem", startItem);
	    request.setAttribute("endItem", endItem);
		
	    request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);

		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		
		request.setAttribute("list", list);
		
		return "/craft_item/itemList.jsp";
	}//requestPro() end
}//class end
