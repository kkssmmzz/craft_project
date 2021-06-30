package craft_faq;
import java.util.*;//Date

public class faqDTO {
	//전역변수=필드=프로퍼티=property
	private int faq_num;
	private String faq_writer;
	private String faq_subject;
	private String faq_pw;
	private Date faq_regdate;
	private int faq_readcount;
	private int faq_ref;//글그룹
	private int faq_re_step;//글순서 처리
	private int faq_re_level;//답글 깊이 처리 할 변수 
	private String faq_content;
	private String faq_ip;

	
	public faqDTO(){}


	public int getFaq_num() {
		return faq_num;
	}


	public void setFaq_num(int faq_num) {
		this.faq_num = faq_num;
	}


	public String getFaq_writer() {
		return faq_writer;
	}


	public void setFaq_writer(String faq_writer) {
		this.faq_writer = faq_writer;
	}


	public String getFaq_subject() {
		return faq_subject;
	}


	public void setFaq_subject(String faq_subject) {
		this.faq_subject = faq_subject;
	}


	public String getFaq_pw() {
		return faq_pw;
	}


	public void setFaq_pw(String faq_pw) {
		this.faq_pw = faq_pw;
	}


	public Date getFaq_regdate() {
		return faq_regdate;
	}


	public void setFaq_regdate(Date faq_regdate) {
		this.faq_regdate = faq_regdate;
	}


	public int getFaq_readcount() {
		return faq_readcount;
	}


	public void setFaq_readcount(int faq_readcount) {
		this.faq_readcount = faq_readcount;
	}


	public int getFaq_ref() {
		return faq_ref;
	}


	public void setFaq_ref(int faq_ref) {
		this.faq_ref = faq_ref;
	}


	public int getFaq_re_step() {
		return faq_re_step;
	}


	public void setFaq_re_step(int faq_re_step) {
		this.faq_re_step = faq_re_step;
	}


	public int getFaq_re_level() {
		return faq_re_level;
	}


	public void setFaq_re_level(int faq_re_level) {
		this.faq_re_level = faq_re_level;
	}


	public String getFaq_content() {
		return faq_content;
	}


	public void setFaq_content(String faq_content) {
		this.faq_content = faq_content;
	}


	public String getFaq_ip() {
		return faq_ip;
	}


	public void setFaq_ip(String faq_ip) {
		this.faq_ip = faq_ip;
	}

	
}//class end
   