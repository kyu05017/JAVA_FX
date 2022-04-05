package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dto.Board;
import dto.Reply;
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
		try {
			// 1. sql �ۼ�
			String sql = "delete from board where b_num=?";
			// 2. sql ����
			ps = con.prepareStatement(sql);
			ps.setInt(1, b_num);
			
			// 3. sql ����
			ps.executeUpdate();
			// 4. sql ���
			return true;
			
		} catch (SQLException e) {System.out.println("��������"+e);}
		
		return false;
	}
	// 4. �ۼ���
	public boolean updaete(int b_num, String title, String contents) {
		try {
			// 1. SQL �ۼ�
			// select * from ���̺�� where ����=( �ʵ�� = �� )
			String sql = "UPDATE board SET b_title=?,b_contents=? where b_num=?";
			// 2. sql ����
			ps = con.prepareStatement(sql);
			
			ps.setString(1, title);
			ps.setString(2, contents);
			ps.setInt(3, b_num);

			// 3. SQL ����
			ps.executeUpdate(); 
			
			// 4. sql ���
			return true;
		}
		catch(Exception e) {
			System.out.println("�������� " + e);
		}
		return false;
	}
	public boolean view(int num,int view) {
		try {
			// 1. SQL �ۼ�
			// select * from ���̺�� where ����=( �ʵ�� = �� )
			String sql = "UPDATE board SET b_view=? where b_num=?";
			// 2. sql ����
			ps = con.prepareStatement(sql);
			int new_view = view + 1;
			controllor.board.Board.board.setB_view(new_view);
			ps.setInt(1, new_view);
			System.out.println(new_view);
			ps.setInt(2, num);
			// 3. SQL ����
			ps.executeUpdate(); 
			
			return true;
		}
		catch(Exception e) {
			System.out.println("�������� " + e);
		}
		return false;
	}
	// 6. ��� �ۼ�
	public boolean reply_write(Reply reply) {
		try {
			// 1. SQL �ۼ� [ ȸ����ȣ ( �ڵ� )������ ��� �ʵ� ����  ]
			String sql = "insert into reply(r_contents,r_writerr,b_num) values(?,?,?)";
			// 2. SQL ����
			ps = con.prepareStatement(sql);
			ps.setString(1, reply.getR_contents());
			ps.setString(2, reply.getR_writerr());
			ps.setInt(3, reply.getB_num());
			
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
	// 7 . �ҷ�����
	public ObservableList<Reply> reply_list() {
		
		try {
			// *
			ObservableList<Reply> replylist = FXCollections.observableArrayList();
			
			// 1. sql  �ۼ� [ ������ ȣ�� ]
				//select * (��� �ʵ�) from ���̺��
			 	// �������� by b_num asc = b_num�������� �������� ����
				// �������� by b_num desc = b_num�������� �������� ����
			String sql = "select * from reply order by r_num desc";
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
				Reply temp = new Reply(
					rs.getInt(1), 
					rs.getString(2), 
					rs.getString(3),
					rs.getString(4),
					rs.getInt(5)
					); 
				replylist.add(temp);
			}
			// �ݺ����� ����Ǹ� ����Ʈ ��ȯ
			// ������ ������ ��� ��ȯ
			return replylist;
		}
		catch (Exception e) {
			System.out.println("[sql ���� ����] : ���� " + e);
		}
		// ���н� 
		return null;
	}
}