package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import controllor.Server;
import controllor.Server.Client;
import dto.Reply;
import dto.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RoomDao {
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public static RoomDao dao = new RoomDao();
	
	public RoomDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx?serverTimezone=UTC","root","1234"); // 2. DB �ּ� ����
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
	// 7 . �ҷ�����
		public ObservableList<Room> room_list() {
			
			try {
				// *
				ObservableList<Room> roomlist = FXCollections.observableArrayList();
				String sql = "select * from room";
				ps = con.prepareStatement(sql);
				rs =  ps.executeQuery();
				while(rs.next()) {
					Room temp = new Room(
						rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3),
						0
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
	
	
}
