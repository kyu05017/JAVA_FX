package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dto.Board;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BoardDao {
	
	private Connection con; // 1 .DB ������ ���Ǵ� Ŭ���� : DB ����Ŭ����
	private PreparedStatement ps; // 2. ����� DB�� SQL ���� �Ҷ� ����ϴ� �������̽� : DB ����
	private ResultSet rs; // 3. sql ��� 
	
	public static BoardDao dao = new BoardDao();	// db ���� ��ü
	
	public BoardDao() { // 4. �����ڿ��� �����ϴ� ���� : ��ü ������ �ٷ� ����
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// 1. DB ����̹� ��������
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx?serverTimezone=UTC","root","1234"); // 2. DB �ּ� ����
			System.out.println("���� DB���� ����");
		} 
		catch (Exception e) {System.out.println("���� DB���� ����" + e);}
	}
	
	// �޼ҵ�
	//1. �۾���
	public boolean write(Board board) {
		try {
			// 1. SQL �ۼ� [ ȸ����ȣ ( �ڵ� )������ ��� �ʵ� ����  ]
			String sql = "insert into board(b_title,b_contents,b_writer) values(?,?,?)";
			// 2. SQL ����
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getB_title());
			ps.setString(2, board.getB_contents());
			ps.setString(3, board.getB_writer());
			
			// 3. SQL ����
			ps.executeUpdate(); 	
			
			// * ������ 
			return true;
		} 
		catch (SQLException e) {
			System.out.println("[SQL �Խ��� ���� ���� ]" + e);
		}
		return false;// * ���н�
	}
	
	// 2. �۸�� 
	public ObservableList<Board> list() {
		
		try {
			// *
			ObservableList<Board> boardlist = FXCollections.observableArrayList();
			
			// 1. sql  �ۼ� [ ������ ȣ�� ]
				//select * (��� �ʵ�) from ���̺��
			 	// �������� by b_num asc = b_num�������� �������� ����
				// �������� by b_num desc = b_num�������� �������� ����
			String sql = "select * from board order by b_num desc";
			// 2. SQL ���� [ DB �� ����� ��ü�� ���ۤ��������̽� ���� ]
			ps = con.prepareStatement(sql);
			
			// 3. sql ���� [ ResultSet �������̽� java.sql ��Ű�� ]
			rs =  ps.executeQuery();
			
			// * ����� �ϳ��� �ƴϰ� ������ �̹Ƿ� �ݺ��� ����ؼ�
				// ���پ�[���ڵ�] ��üȭ -> ����Ʈ�� ����
			
			// rs.next() �˻������ ���� ���ڵ�
			// rs.getint() �˻������ �������� ���
			// rs.getstring() �˻������ �������� ���
			while(rs.next()) {
				// 1. ���ٽ� [ ���ڵ� ] ���� ��üȭ
				Board temp = new Board(
					rs.getInt(1), 
					rs.getString(2), 
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getInt(6)
					); 
				boardlist.add(temp);
			}
			// �ݺ����� ����Ǹ� ����Ʈ ��ȯ
			// ������ ������ ��� ��ȯ
			return boardlist;
		}
		catch (Exception e) {
			System.out.println("[sql ���� ����] : ���� " + e);
		}
		// ���н� 
		return null;
	}

	// 3. �ۻ���
	public boolean delete(int b_num) {
		return false;
	}
	// 4. �ۼ���
	public boolean updaete(int b_num, String title, String contents) {
		return false;
	}
}
