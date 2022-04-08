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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx?serverTimezone=UTC","root","1234"); // 2. DB 주소 연결
			System.out.println("채팅 DB연동 성공");
		}
		catch(Exception e) {
			System.out.println("[SQL 채팅 연동 실패 ] "+ e);
		}
	}
	
	// 1. 방 저장
	public boolean roomadd(Room room) {
		try {
			String sql = "insert into room(ro_name,ro_ip) values(?,?)";
			// 테이블에 레코드 추가 하는법 : insert into 테이블명 (필드명1,2) values(값1,2)
			ps = con.prepareStatement(sql);
			ps.setString(1, room.getRo_name());
			ps.setString(2, room.getRo_ip());
			ps.executeUpdate(); 	
			return true;
		}
		catch (Exception e) {
			System.out.println("[SQL 채팅 생성 실패 ] "+ e);
		}
		return false;
	}
	
	// 2. 방 번호 호출 메소드 [ 방번호 포트번호로 사용할 예정 ]
	public int getRoomNum() {
		try {			// max( 필드명 ) 해당 필드의 가장 큰 값
			String sql = "select max(ro_num) from room";
			ps = con.prepareStatement(sql);
			rs =  ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		}
		catch (Exception e) {
			System.out.println("[SQL 채팅방 번호 실패 ] "+ e);
		}
		return 0;
	}
	// 7 . 불러오시
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
				System.out.println("[sql 채팅방 로딩 실패] : 사유 " + e);
			}
			// 실패시 
			return null;
		}
	
	
}
