package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import dto.Room;
import dto.RoomLive;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RoomDao {
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public static RoomDao dao = new RoomDao();
	
	public RoomDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// 1. DB ����̹� ��������
			con = DriverManager.getConnection("jdbc:mysql://database-1.cdocvkszhrus.us-west-2.rds.amazonaws.com:3306/javafx?serverTimezone=UTC","admin","rLARB4595!"); // 2. DB �ּ� ����
			System.out.println("ä�� DB���� ����");
		}
		catch(Exception e) {
			System.out.println("[SQL ä�� ���� ���� ] "+ e);
		}
	}
	
	// 1. �� ����
	public boolean roomadd(Room room) {
		try {
			String sql = "insert into room(ro_name,ro_ip) values(?,?)";
			// ���̺� ���ڵ� �߰� �ϴ¹� : insert into ���̺�� (�ʵ��1,2) values(��1,2)
			ps = con.prepareStatement(sql);
			ps.setString(1, room.getRo_name());
			ps.setString(2, room.getRo_ip());
			ps.executeUpdate(); 	
			return true;
		}
		catch (Exception e) {
			System.out.println("[SQL ä�� ���� ���� ] "+ e);
		}
		return false;
	}
	
	// 2. �� ��ȣ ȣ�� �޼ҵ� [ ���ȣ ��Ʈ��ȣ�� ����� ���� ]
	public int getRoomNum() {
		try {			// max( �ʵ�� ) �ش� �ʵ��� ���� ū ��
			String sql = "select max(ro_num) from room";
			ps = con.prepareStatement(sql);
			rs =  ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		}
		catch (Exception e) {
			System.out.println("[SQL ä�ù� ��ȣ ���� ] "+ e);
		}
		return 0;
	}
	// 3. �� �ҷ�����
	public ObservableList<Room> room_list() {
		
		try {
			// *
			ObservableList<Room> roomlist = FXCollections.observableArrayList();
			String sql = "select * from room order by ro_num desc";
			ps = con.prepareStatement(sql);
			rs =  ps.executeQuery();
			// rs = Resultset �������̽� ��ü : select�� ��� ��������
			// resultset ( �ʱ� �� null )
			// rs.next : select ��� ���ڵ� 1�� ��������
			while(rs.next()) {
				String sql2 = "select count(*) from roomlive where ro_num =" + rs.getInt(1);
				ps = con.prepareStatement(sql2);
				ResultSet rs2 = ps.executeQuery();
				int count = 0;
				if(rs2.next()) {
					count = rs2.getInt(1);
				}
				Room temp = new Room(
					rs.getInt(1), 
					rs.getString(2), 
					rs.getString(3),
					count
				); 
				roomlist.add(temp);
			}
			return roomlist;
		}
		catch (Exception e) {
			System.out.println("[sql ä�ù� �ε� ����] : ���� " + e);
		}
		// ���н� 
		return null;
	}
	// 4. ���� ��� �߰�
	public boolean roomlive_add(RoomLive live) {
		try {
			String sql = "insert into roomlive (ro_num,m_id) values(?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, live.getRo_num());
			ps.setString(2, live.getM_id());
			ps.executeUpdate(); 
			return true;
		}
		catch (Exception e) {
			System.out.println("����̺� �߰� ���� " + e);
		}
		return false;
	}
	// 2. �� ��ȣ ȣ�� �޼ҵ� [ ���ȣ ��Ʈ��ȣ�� ����� ���� ]
	public ArrayList<RoomLive> getlivelist(int ro_num) {
		ArrayList<RoomLive> livelist = new ArrayList<>();
		try {
			
			String sql = "select * from roomlive where ro_num = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, ro_num);
			rs =  ps.executeQuery();
			while(rs.next()) {
				
				RoomLive live = new RoomLive(rs.getInt(1), rs.getInt(2), rs.getString(3));
				livelist.add(live);
			}
			return livelist;
		}
		catch (Exception e) {
			System.out.println("����̺� �������� ���� " + e);
		}
		return null;
	}
	
	public boolean deletelist(String mid) {
		try {
			String sql = "delete from roomlive where m_id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, mid);
			
			ps.executeUpdate(); 
			return true;
		}
		catch (Exception e) {
			System.out.println("ä�ù� ���� ���� " + e);
		}
		
		return false;
	}
	// 7. ä�ù� ���� [ ���� : �ش� ä�ù濡 ���Ӹ���� 0 �̸� ]
	public boolean deleteroom(int ro_num) {
		
		try {
			String sql = "select * from roomlive where ro_num=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, ro_num);
			
			rs =  ps.executeQuery();
			if(rs.next()) { 	// ����� ������ ���
				return false;
			}
			else { // 
				String sql2 = "delete from room where ro_num=?";
				ps = con.prepareStatement(sql2);
				ps.setInt(1, ro_num);
				ps.executeUpdate(); 
				return true;
			}
			
		}
		catch (Exception e) {
			System.out.println("ä�ù� ���� ���� " + e);
		}
		return false;
	}
}

