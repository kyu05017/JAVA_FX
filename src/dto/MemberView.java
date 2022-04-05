package dto;

public class MemberView {
	private String id;
	private int b_num;
	private String date;
	
	public MemberView() {}

	public MemberView(String id, int b_num, String date) {
		this.id = id;
		this.b_num = b_num;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getB_num() {
		return b_num;
	}

	public void setB_num(int b_num) {
		this.b_num = b_num;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}
