package action.craft_board_faq;
import javax.servlet.http.*;

import command.CommandAction;//오버라이딩 - 클래스를 만들고 가장 먼저 해줘야한다.
import craft_faq.*;

import java.util.*;//List,ArrayList

//인터페이스 상속 받아 구현 클래스 작업하는 곳
//1. action은 jsp처리 로직을 action에 한다.
//2. DAO메서드 호출하여 결과값을 얻는다.
public class ListAction implements CommandAction{

	@Override     //요청처리하는 곳이라 메서드가 requestPro()이다.
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//jsp의 처리내용, DAO메서드 호출
		String faq_pageNum=request.getParameter("faq_pageNum");
		if(faq_pageNum==null){
			faq_pageNum="1";
		}//if end
		
		int faq_pageSize=10;//한 페이지 당 글 갯수
		int faq_currentPage=Integer.parseInt(faq_pageNum);
		
		int faq_startRow=(faq_currentPage-1)*faq_pageSize+1;//한 페이지의 시작 글번호
		int faq_endRow=faq_currentPage*faq_pageSize;//한페이지의 마지막 글번호
		
		int faq_count=0;//총 글갯수 넣을 변수
		int faq_number=0;//글 번호 처리하기 위한 변수
		int faq_pageBlock=10;//블럭당 페이지 수
		
		List faq_boardList=null;
		faqDAO faq_dao=faqDAO.getInstance();//dao객체얻기
		faq_count=faq_dao.getBoardCount();//전체 글갯수 얻기
		
		if(faq_count>0){//글이 있으면 
			faq_boardList=faq_dao.getList(faq_startRow, faq_pageSize);//dao메서드 호출하고 결과 받는다
		}else{//글이 없을때
			faq_boardList=Collections.EMPTY_LIST;//비어있다는 뜻
		}//else end
		
		faq_number=faq_count-(faq_currentPage-1)*faq_pageSize;//출력할 글번호 역순
		
		int faq_pageCount=faq_count/faq_pageSize+(faq_count%faq_pageSize==0?0:1);//총 페이지 수
		//                 몫                       꽁다리 레코드 수(31개 글, 페이지는 4개의 페이지)
		int faq_startPage=(faq_currentPage/10)*10+1;//시작페이지
		//                
		int faq_endPage=faq_startPage+faq_pageBlock-1;
		//             1+10-1=10 end페이지
		
		//if(endPage>pageCount){//에러방지
			//endPage=pageCount;
		//}//if end		
		
		//JSP에서 사용하도록 request,setAttribute("key",value) 작업을 한다
		request.setAttribute("faq_pageNum", faq_pageNum);
		request.setAttribute("faq_currentPage", faq_currentPage);
		
		request.setAttribute("faq_startRow", faq_startRow);
		request.setAttribute("faq_endRow", faq_endRow);

		request.setAttribute("faq_pageBlock", faq_pageBlock);
		request.setAttribute("faq_pageCount", faq_pageCount);
		request.setAttribute("faq_startPage", faq_startPage);
		request.setAttribute("faq_endPage", faq_endPage);

		request.setAttribute("faq_count", new Integer(faq_count));
		request.setAttribute("faq_pageSize", new Integer(faq_pageSize));
		request.setAttribute("faq_number", new Integer(faq_number));
		
		request.setAttribute("faq_boardList", faq_boardList);
		
		
	return "/craft_board_faq/list.jsp";//뷰(/board/list.jsp)를 리턴 ==> 컨트롤러로 가서 ==> 뷰로 넘어간다
	}//requestPro() end
}//class end

