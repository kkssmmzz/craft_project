package craft_qna;
import java.util.*;//Date

public class QnaDTO {
	//전역변수=필드=프로퍼티=property
	private int qna_num;
	private String qna_writer;
	private String qna_subject;
	private String qna_pw;
	private Date qna_regdate;
	private int qna_readcount;
	private int qna_ref;//글그룹
	private int qna_re_step;//글순서 처리
	private int qna_re_level;//답글 깊이 처리 할 변수 
	private String qna_content;
	private String qna_ip;

	
	public QnaDTO(){}

	public int getQna_num() {
		return qna_num;
	}

	public void setQna_num(int qna_num) {
		this.qna_num = qna_num;
	}

	public String getQna_writer() {
		return qna_writer;
	}

	public void setQna_writer(String qna_writer) {
		this.qna_writer = qna_writer;
	}

	public String getQna_subject() {
		return qna_subject;
	}

	public void setQna_subject(String qna_subject) {
		this.qna_subject = qna_subject;
	}

	public String getQna_pw() {
		return qna_pw;
	}

	public void setQna_pw(String qna_pw) {
		this.qna_pw = qna_pw;
	}

	public Date getQna_regdate() {
		return qna_regdate;
	}

	public void setQna_regdate(Date qna_regdate) {
		this.qna_regdate = qna_regdate;
	}
	

	public int getQna_readcount() {
		return qna_readcount;
	}

	public void setQna_readcount(int qna_readcount) {
		this.qna_readcount = qna_readcount;
	}

	public int getQna_ref() {
		return qna_ref;
	}

	public void setQna_ref(int qna_ref) {
		this.qna_ref = qna_ref;
	}

	public int getQna_re_step() {
		return qna_re_step;
	}

	public void setQna_re_step(int qna_re_step) {
		this.qna_re_step = qna_re_step;
	}

	public int getQna_re_level() {
		return qna_re_level;
	}

	public void setQna_re_level(int qna_re_level) {
		this.qna_re_level = qna_re_level;
	}

	public String getQna_content() {
		return qna_content;
	}

	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}

	public String getQna_ip() {
		return qna_ip;
	}

	public void setQna_ip(String qna_ip) {
		this.qna_ip = qna_ip;
	}
	
}//class end
   