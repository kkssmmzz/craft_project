package command;
import javax.servlet.http.HttpServletRequest;//��û��ü�� �����;���.
import javax.servlet.http.HttpServletResponse;

public interface CommandAction {
	//�޼��峻�� ���� �ʰ� ���� �Ѵ�.
	public String requestPro(HttpServletRequest request, 
			HttpServletResponse response) throws Throwable;
	
}
