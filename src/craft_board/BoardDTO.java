package craft_board;
import java.util.*;//Date

public class BoardDTO {
	//전역변수=필드=프로퍼티=property
	private int craft_num;
	private String craft_writer;
	private String craft_subject;
	private String craft_pw;
	private Date craft_regdate;
	private int craft_readcount;
	private int craft_ref;//글그룹
	private int craft_re_step;//글순서 처리
	private int craft_re_level;//답글 깊이 처리 할 변수 
	private String craft_content;
	private String craft_ip;
	
	public BoardDTO(){}

	public int getCraft_num() {
		return craft_num;
	}

	public void setCraft_num(int craft_num) {
		this.craft_num = craft_num;
	}

	public String getCraft_writer() {
		return craft_writer;
	}

	public void setCraft_writer(String craft_writer) {
		this.craft_writer = craft_writer;
	}

	public String getCraft_subject() {
		return craft_subject;
	}

	public void setCraft_subject(String craft_subject) {
		this.craft_subject = craft_subject;
	}

	public String getCraft_pw() {
		return craft_pw;
	}

	public void setCraft_pw(String craft_pw) {
		this.craft_pw = craft_pw;
	}

	public Date getCraft_regdate() {
		return craft_regdate;
	}

	public void setCraft_regdate(Date craft_regdate) {
		this.craft_regdate = craft_regdate;
	}

	public int getCraft_readcount() {
		return craft_readcount;
	}

	public void setCraft_readcount(int craft_readcount) {
		this.craft_readcount = craft_readcount;
	}

	public int getCraft_ref() {
		return craft_ref;
	}

	public void setCraft_ref(int craft_ref) {
		this.craft_ref = craft_ref;
	}

	public int getCraft_re_step() {
		return craft_re_step;
	}

	public void setCraft_re_step(int craft_re_step) {
		this.craft_re_step = craft_re_step;
	}

	public int getCraft_re_level() {
		return craft_re_level;
	}

	public void setCraft_re_level(int craft_re_level) {
		this.craft_re_level = craft_re_level;
	}

	public String getCraft_content() {
		return craft_content;
	}

	public void setCraft_content(String craft_content) {
		this.craft_content = craft_content;
	}

	public String getCraft_ip() {
		return craft_ip;
	}

	public void setCraft_ip(String craft_ip) {
		this.craft_ip = craft_ip;
	}
}//class end
   