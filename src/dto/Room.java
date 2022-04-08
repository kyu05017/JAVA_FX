package dto;

public class Room {
	private int ro_num;
	private String ro_name;
	private String ro_ip;
	private int m_count;

	public Room() {}

	public Room(int ro_num, String ro_name, String ro_ip, int m_count) {
		this.ro_num = ro_num;
		this.ro_name = ro_name;
		this.ro_ip = ro_ip;
		this.m_count = m_count;
	}
	public int getRo_num() {
		return ro_num;
	}
	public void setRo_num(int ro_num) {
		this.ro_num = ro_num;
	}
	public String getRo_name() {
		return ro_name;
	}
	public void setRo_name(String ro_name) {
		this.ro_name = ro_name;
	}
	public String getRo_ip() {
		return ro_ip;
	}
	public void setRo_ip(String ro_ip) {
		this.ro_ip = ro_ip;
	}
	public int getM_count() {
		return m_count;
	}
	public void setM_count(int m_count) {
		this.m_count = m_count;
	}
}	
