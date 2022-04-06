package dto;

public class Product {
	
	private int p_num;
	private String p_name;
	private String p_img;
	private String p_contents;
	private String p_category;
	private int p_money;
	private int p_condition;
	private String p_date;
	private int m_num;
	
	public Product() {}

	public Product(int p_num, String p_name, String p_img, String p_contents, String p_category, int p_money,
			int p_condition, String p_date, int m_num) {
		this.p_num = p_num;
		this.p_name = p_name;
		this.p_img = p_img;
		this.p_contents = p_contents;
		this.p_category = p_category;
		this.p_money = p_money;
		this.p_condition = p_condition;
		this.p_date = p_date;
		this.m_num = m_num;
	}

	public int getP_num() {
		return p_num;
	}

	public void setP_num(int p_num) {
		this.p_num = p_num;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public String getP_img() {
		return p_img;
	}

	public void setP_img(String p_img) {
		this.p_img = p_img;
	}

	public String getP_contents() {
		return p_contents;
	}

	public void setP_contents(String p_contents) {
		this.p_contents = p_contents;
	}

	public String getP_category() {
		return p_category;
	}

	public void setP_category(String p_category) {
		this.p_category = p_category;
	}

	public int getP_money() {
		return p_money;
	}

	public void setP_money(int p_money) {
		this.p_money = p_money;
	}

	public int getP_condition() {
		return p_condition;
	}

	public void setP_condition(int p_condition) {
		this.p_condition = p_condition;
	}

	public String getP_date() {
		return p_date;
	}

	public void setP_date(String p_date) {
		this.p_date = p_date;
	}

	public int getM_num() {
		return m_num;
	}

	public void setM_num(int m_num) {
		this.m_num = m_num;
	}
	
	
	
}
