package craft_noti;
import java.util.*;

public class notiDTO {
	//전역변수
	private int noti_num;
	private String noti_writer;
	private String noti_subject;
	private String noti_pw;
	private Date noti_regdate;
	private int noti_readcount;
	private String noti_content;
	private String noti_ip;
	
	//디폴트 생성자
	public notiDTO(){}

	public int getNoti_num() {
		return noti_num;
	}

	public void setNoti_num(int noti_num) {
		this.noti_num = noti_num;
	}

	public String getNoti_writer() {
		return noti_writer;
	}

	public void setNoti_writer(String noti_writer) {
		this.noti_writer = noti_writer;
	}

	public String getNoti_subject() {
		return noti_subject;
	}

	public void setNoti_subject(String noti_subject) {
		this.noti_subject = noti_subject;
	}

	public String getNoti_pw() {
		return noti_pw;
	}

	public void setNoti_pw(String noti_pw) {
		this.noti_pw = noti_pw;
	}

	public Date getNoti_regdate() {
		return noti_regdate;
	}

	public void setNoti_regdate(Date noti_regdate) {
		this.noti_regdate = noti_regdate;
	}

	public int getNoti_readcount() {
		return noti_readcount;
	}

	public void setNoti_readcount(int noti_readcount) {
		this.noti_readcount = noti_readcount;
	}

	public String getNoti_content() {
		return noti_content;
	}

	public void setNoti_content(String noti_content) {
		this.noti_content = noti_content;
	}

	public String getNoti_ip() {
		return noti_ip;
	}

	public void setNoti_ip(String noti_ip) {
		this.noti_ip = noti_ip;
	}
}//class end