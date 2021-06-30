package action.craft_board_qna;
import javax.servlet.http.*;

import command.CommandAction;//오버라이딩 - 클래스를 만들고 가장 먼저 해줘야한다.
import craft_qna.*;

import java.util.*;//List,ArrayList

//인터페이스 상속 받아 구현 클래스 작업하는 곳
//1. action은 jsp처리 로직을 action에 한다.
//2. DAO메서드 호출하여 결과값을 얻는다.
public class ListAction implements CommandAction{

	@Override     //요청처리하는 곳이라 메서드가 requestPro()이다.
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//jsp의 처리내용, DAO메서드 호출
		String qna_pageNum=request.getParameter("qna_pageNum");
		if(qna_pageNum==null){
			qna_pageNum="1";
		}//if end
		
		int qna_pageSize=10;//한 페이지 당 글 갯수
		int qna_currentPage=Integer.parseInt(qna_pageNum);
		
		int qna_startRow=(qna_currentPage-1)*qna_pageSize+1;//한 페이지의 시작 글번호
		int qna_endRow=qna_currentPage*qna_pageSize;//한페이지의 마지막 글번호
		
		int qna_count=0;//총 글갯수 넣을 변수
		int qna_number=0;//글 번호 처리하기 위한 변수
		int qna_pageBlock=10;//블럭당 페이지 수
		
		List qna_boardList=null;
		QnaDAO qna_dao=QnaDAO.getInstance();//dao객체얻기
		qna_count=qna_dao.getBoardCount();//전체 글갯수 얻기
		
		if(qna_count>0){//글이 있으면 
			qna_boardList=qna_dao.getList(qna_startRow, qna_pageSize);//dao메서드 호출하고 결과 받는다
		}else{//글이 없을때
			qna_boardList=Collections.EMPTY_LIST;//비어있다는 뜻
		}//else end
		
		qna_number=qna_count-(qna_currentPage-1)*qna_pageSize;//출력할 글번호 역순
		
		int qna_pageCount=qna_count/qna_pageSize+(qna_count%qna_pageSize==0?0:1);//총 페이지 수
		//                 몫                       꽁다리 레코드 수(31개 글, 페이지는 4개의 페이지)
		int qna_startPage=(qna_currentPage/10)*10+1;//시작페이지
		//                
		int qna_endPage=qna_startPage+qna_pageBlock-1;
		//             1+10-1=10 end페이지
		
		//if(endPage>pageCount){//에러방지
			//endPage=pageCount;
		//}//if end		
		
		//JSP에서 사용하도록 request,setAttribute("key",value) 작업을 한다
		request.setAttribute("qna_pageNum", qna_pageNum);
		request.setAttribute("qna_currentPage", qna_currentPage);
		
		request.setAttribute("qna_startRow", qna_startRow);
		request.setAttribute("qna_endRow", qna_endRow);

		request.setAttribute("qna_pageBlock", qna_pageBlock);
		request.setAttribute("qna_pageCount", qna_pageCount);
		request.setAttribute("qna_startPage", qna_startPage);
		request.setAttribute("qna_endPage", qna_endPage);

		request.setAttribute("qna_count", new Integer(qna_count));
		request.setAttribute("qna_pageSize", new Integer(qna_pageSize));
		request.setAttribute("qna_number", new Integer(qna_number));
		
		request.setAttribute("qna_boardList", qna_boardList);
		
		
	return "/craft_board_qna/list.jsp";//뷰(/board/list.jsp)를 리턴 ==> 컨트롤러로 가서 ==> 뷰로 넘어간다
	}//requestPro() end
}//class end

