package dto;


public class Board {
	
	private int b_num; // 회원번호
	private String b_title;
	private String b_contents;
	private String b_writer;
	private String b_date;
	private int b_view;
	
	public Board() {}
	public Board(int b_num, String b_title, String b_contents, String b_writer, String b_date, int b_view) {

		this.b_num = b_num;
		this.b_title = b_title;
		this.b_contents = b_contents;
		this.b_writer = b_writer;
		this.b_date = b_date;
		this.b_view = b_view;
	}

	
	public int getB_num() {
		return b_num;
	}

	public void setB_num(int b_num) {
		this.b_num = b_num;
	}

	public String getB_title() {
		return b_title;
	}
	
	public void setB_title(String b_title) {
		this.b_title = b_title;
	}

	
	public String getB_contents() {
		return b_contents;
	}

	public void setB_contents(String b_contents) {
		this.b_contents = b_contents;
	}

	public String getB_writer() {
		return b_writer;
	}

	public void setB_writer(String b_writer) {
		this.b_writer = b_writer;
	}

	public String getB_date() {
		return b_date;
	}

	public void setB_date(String b_date) {
		this.b_date = b_date;
	}

	public int getB_view() {
		return b_view;
	}

	public void setB_view(int b_view) {
		this.b_view = b_view;
	}
	
	

}
