package craft_member;
//모델 빈
public class MemberDTO {
	//전역 변수 = 프로퍼티 = 필드 = property = 속성
	private String craft_id;
	private String craft_pw;
	private String craft_name;
	private String craft_email;
	private String craft_tel;
	private String craft_zipcode;

	private String craft_addr;
	private String craft_addr2;
	private String craft_regdate;
	
	public MemberDTO(){}

	public String getCraft_id() {
		return craft_id;
	}

	public void setCraft_id(String craft_id) {
		this.craft_id = craft_id;
	}

	public String getCraft_pw() {
		return craft_pw;
	}

	public void setCraft_pw(String craft_pw) {
		this.craft_pw = craft_pw;
	}

	public String getCraft_name() {
		return craft_name;
	}

	public void setCraft_name(String craft_name) {
		this.craft_name = craft_name;
	}

	public String getCraft_email() {
		return craft_email;
	}

	public void setCraft_email(String craft_email) {
		this.craft_email = craft_email;
	}

	public String getCraft_tel() {
		return craft_tel;
	}

	public void setCraft_tel(String craft_tel) {
		this.craft_tel = craft_tel;
	}

	public String getCraft_zipcode() {
		return craft_zipcode;
	}

	public void setCraft_zipcode(String craft_zipcode) {
		this.craft_zipcode = craft_zipcode;
	}

	public String getCraft_addr() {
		return craft_addr;
	}

	public void setCraft_addr(String craft_addr) {
		this.craft_addr = craft_addr;
	}

	public String getCraft_addr2() {
		return craft_addr2;
	}

	public void setCraft_addr2(String craft_addr2) {
		this.craft_addr2 = craft_addr2;
	}

	public String getCraft_regdate() {
		return craft_regdate;
	}

	public void setCraft_regdate(String craft_regdate) {
		this.craft_regdate = craft_regdate;
	}

}//class end
