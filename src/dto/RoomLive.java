package dto;

public class RoomLive {
	private int live_num;
	private int ro_num;
	private String m_id;
	
	public RoomLive() {}

	public RoomLive(int live_num, int ro_num, String m_id) {
		this.live_num = live_num;
		this.ro_num = ro_num;
		this.m_id = m_id;
	}

	public int getLive_num() {
		return live_num;
	}

	public void setLive_num(int live_num) {
		this.live_num = live_num;
	}

	public int getRo_num() {
		return ro_num;
	}

	public void setRo_num(int ro_num) {
		this.ro_num = ro_num;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	} 
	
	
	
}
