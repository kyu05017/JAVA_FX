package dto;

public class Reply_Product {
	
	private int r_num;
	private String r_contents;
	private String r_writerr;
	private String r_date;
	private int p_num;
	
	public Reply_Product() {}

	public Reply_Product(int r_num, String r_contents, String r_writerr, String r_date, int p_num) {
		this.r_num = r_num;
		this.r_contents = r_contents;
		this.r_writerr = r_writerr;
		this.r_date = r_date;
		this.p_num = p_num;
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

	public int getP_num() {
		return p_num;
	}

	public void setP_num(int p_num) {
		this.p_num = p_num;
	}
	
	
	
}
