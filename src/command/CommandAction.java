package command;
import javax.servlet.http.HttpServletRequest;//요청객체가 먼저와야함.
import javax.servlet.http.HttpServletResponse;

public interface CommandAction {
	//메서드내용 넣지 않고 선언만 한다.
	public String requestPro(HttpServletRequest request, 
			HttpServletResponse response) throws Throwable;
	
}
