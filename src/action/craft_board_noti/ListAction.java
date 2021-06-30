package action.craft_board_noti;

import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import craft_noti.notiDAO;
import command.CommandAction;

public class ListAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
	      //jsp�� ó������, DAO�޼��� ȣ��
	      String pageNum=request.getParameter("pageNum");
	      if(pageNum==null){
	         pageNum="1";
	      }//if end
	      
	      int pageSize=10;//�� ������ �� �� ����
	      int currentPage=Integer.parseInt(pageNum);
	      
	      int startRow=(currentPage-1)*pageSize+1;//�� �������� ���� �۹�ȣ
	      int endRow=currentPage*pageSize;//���������� ������ �۹�ȣ
	      
	      int noti_count=0;//�� �۰��� ���� ����
	      int noti_number=0;//�� ��ȣ ó���ϱ� ���� ����
	      int pageBlock=10;//���� ������ ��
	      
	      List boardList=null;
	      notiDAO dao=notiDAO.getInstance();//dao��ü���
	      noti_count=dao.getBoardCount();//��ü �۰��� ���
	      
	      if(noti_count>0){//���� ������ 
	         boardList=dao.getList(startRow, pageSize);//dao�޼��� ȣ���ϰ� ��� �޴´�
	      }else{//���� ������
	         boardList=Collections.EMPTY_LIST;//����ִٴ� ��
	      }//else end
	      
	      noti_number=noti_count-(currentPage-1)*pageSize;//����� �۹�ȣ ����
	      
	      int pageCount=noti_count/pageSize+(noti_count%pageSize==0?0:1);//�� ������ ��
	      //                 ��                       �Ǵٸ� ���ڵ� ��(31�� ��, �������� 4���� ������)
	      int startPage=(currentPage/10)*10+1;//����������
	      //                
	      int endPage=startPage+pageBlock-1;
	      //             1+10-1=10 end������
	      
	      //if(endPage>pageCount){//��������
	         //endPage=pageCount;
	      //}//if end      
	      
	      //JSP���� ����ϵ��� request,setAttribute("key",value) �۾��� �Ѵ�
	      request.setAttribute("pageNum", pageNum);
	      request.setAttribute("currentPage", currentPage);
	      
	      request.setAttribute("startRow", startRow);
	      request.setAttribute("endRow", endRow);

	      request.setAttribute("pageBlock", pageBlock);
	      request.setAttribute("pageCount", pageCount);
	      request.setAttribute("startPage", startPage);
	      request.setAttribute("endPage", endPage);

	      request.setAttribute("noti_count", new Integer(noti_count));
	      request.setAttribute("pageSize", new Integer(pageSize));
	      request.setAttribute("noti_number", new Integer(noti_number));
	      
	      request.setAttribute("boardList", boardList);

		return "/craft_board_noti/list.jsp";
	}//requestPro() end

}//class end
