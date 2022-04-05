package dto;

public class Reply {
	
	private int r_num;
	private String r_contents;
	private String r_writerr;
	private String r_date;
	private int b_num;
	
	public Reply() {}

	public Reply(int r_num, String r_contents, String r_writerr, String r_date, int b_num) {
		this.r_num = r_num;
		this.r_contents = r_contents;
		this.r_writerr = r_writerr;
		this.r_date = r_date;
		this.b_num = b_num;
	}

	public int getR_num() {
		return r_num;
	}

	public void setR_num(int r_num) {
		this.r_num = r_num;
	}

	public String getR_contents() {
		return r_contents;
	}

	public void setR_contents(String r_contents) {
		this.r_contents = r_contents;
	}

	public String getR_writerr() {
		return r_writerr;
	}

	public void setR_writerr(String r_writerr) {
		this.r_writerr = r_writerr;
	}

	public String getR_date() {
		return r_date;
	}

	public void setR_date(String r_date) {
		this.r_date = r_date;
	}

	public int getB_num() {
		return b_num;
	}

	public void setB_num(int b_num) {
		this.b_num = b_num;
	}
	
	
	
}
