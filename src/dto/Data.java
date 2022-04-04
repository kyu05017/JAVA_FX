package dto;

public class Data {

	
	private int number;
	private String writer;
	private String contents;
	
	public Data() {
	}

	public Data(int number, String writer, String contents) {
		super();
		this.number = number;
		this.writer = writer;
		this.contents = contents;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
	
	
}
