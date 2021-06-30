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
		
	      //jsp의 처리내용, DAO메서드 호출
	      String pageNum=request.getParameter("pageNum");
	      if(pageNum==null){
	         pageNum="1";
	      }//if end
	      
	      int pageSize=10;//한 페이지 당 글 갯수
	      int currentPage=Integer.parseInt(pageNum);
	      
	      int startRow=(currentPage-1)*pageSize+1;//한 페이지의 시작 글번호
	      int endRow=currentPage*pageSize;//한페이지의 마지막 글번호
	      
	      int noti_count=0;//총 글갯수 넣을 변수
	      int noti_number=0;//글 번호 처리하기 위한 변수
	      int pageBlock=10;//블럭당 페이지 수
	      
	      List boardList=null;
	      notiDAO dao=notiDAO.getInstance();//dao객체얻기
	      noti_count=dao.getBoardCount();//전체 글갯수 얻기
	      
	      if(noti_count>0){//글이 있으면 
	         boardList=dao.getList(startRow, pageSize);//dao메서드 호출하고 결과 받는다
	      }else{//글이 없을때
	         boardList=Collections.EMPTY_LIST;//비어있다는 뜻
	      }//else end
	      
	      noti_number=noti_count-(currentPage-1)*pageSize;//출력할 글번호 역순
	      
	      int pageCount=noti_count/pageSize+(noti_count%pageSize==0?0:1);//총 페이지 수
	      //                 몫                       꽁다리 레코드 수(31개 글, 페이지는 4개의 페이지)
	      int startPage=(currentPage/10)*10+1;//시작페이지
	      //                
	      int endPage=startPage+pageBlock-1;
	      //             1+10-1=10 end페이지
	      
	      //if(endPage>pageCount){//에러방지
	         //endPage=pageCount;
	      //}//if end      
	      
	      //JSP에서 사용하도록 request,setAttribute("key",value) 작업을 한다
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
