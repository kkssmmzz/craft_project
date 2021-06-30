package craft_item;
import java.util.*;

public class ItemDTO {
	private int craft_pro_no;
	private String craft_name;
	private String craft_detail;
	private Date craft_regdate;
	private String craft_image;
	
	public ItemDTO(){}

	public int getCraft_pro_no() {
		return craft_pro_no;
	}

	public void setCraft_pro_no(int craft_pro_no) {
		this.craft_pro_no = craft_pro_no;
	}

	public String getCraft_name() {
		return craft_name;
	}

	public void setCraft_name(String craft_name) {
		this.craft_name = craft_name;
	}

	public String getCraft_detail() {
		return craft_detail;
	}

	public void setCraft_detail(String craft_detail) {
		this.craft_detail = craft_detail;
	}

	public Date getCraft_regdate() {
		return craft_regdate;
	}

	public void setCraft_regdate(Date craft_regdate) {
		this.craft_regdate = craft_regdate;
	}

	public String getCraft_image() {
		return craft_image;
	}

	public void setCraft_image(String craft_image) {
		this.craft_image = craft_image;
	}
}
