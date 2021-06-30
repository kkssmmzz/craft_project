package action.craft_board;
import javax.servlet.http.*;

import command.CommandAction;//오버라이딩 - 클래스를 만들고 가장 먼저 해줘야한다.
import craft_board.BoardDAO;

import java.util.*;//List,ArrayList

//인터페이스 상속 받아 구현 클래스 작업하는 곳
//1. action은 jsp처리 로직을 action에 한다.
//2. DAO메서드 호출하여 결과값을 얻는다.
public class ListAction implements CommandAction{

	@Override     //요청처리하는 곳이라 메서드가 requestPro()이다.
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//jsp의 처리내용, DAO메서드 호출
		String craft_pageNum=request.getParameter("craft_pageNum");
		if(craft_pageNum==null){
			craft_pageNum="1";
		}//if end
		
		int pageSize=10;//한 페이지 당 글 갯수
		int currentPage=Integer.parseInt(craft_pageNum);
		
		int startRow=(currentPage-1)*pageSize+1;//한 페이지의 시작 글번호
		int endRow=currentPage*pageSize;//한페이지의 마지막 글번호
		
		int count=0;//총 글갯수 넣을 변수
		int number=0;//글 번호 처리하기 위한 변수
		int pageBlock=10;//블럭당 페이지 수
		
		List boardList=null;
		BoardDAO dao=BoardDAO.getInstance();//dao객체얻기
		count=dao.getBoardCount();//전체 글갯수 얻기
		
		if(count>0){//글이 있으면 
			boardList=dao.getList(startRow, pageSize);//dao메서드 호출하고 결과 받는다
		}else{//글이 없을때
			boardList=Collections.EMPTY_LIST;//비어있다는 뜻
		}//else end
		
		number=count-(currentPage-1)*pageSize;//출력할 글번호 역순
		
		int pageCount=count/pageSize+(count%pageSize==0?0:1);//총 페이지 수
		//                 몫                       꽁다리 레코드 수(31개 글, 페이지는 4개의 페이지)
		int startPage=(currentPage/10)*10+1;//시작페이지
		//                
		int endPage=startPage+pageBlock-1;
		//             1+10-1=10 end페이지
		
		//JSP에서 사용하도록 request,setAttribute("key",value) 작업을 한다
		request.setAttribute("craft_pageNum", craft_pageNum);
		request.setAttribute("currentPage", currentPage);
		
		request.setAttribute("startRow", startRow);
		request.setAttribute("endRow", endRow);

		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);

		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		
		request.setAttribute("boardList", boardList);
		
		
	return "/craft_board/list.jsp";//뷰(/board/list.jsp)를 리턴 ==> 컨트롤러로 가서 ==> 뷰로 넘어간다
	}//requestPro() end
}//class end

