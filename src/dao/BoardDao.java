package dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import dto.Board;
import dto.MemberView;
import dto.Reply;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BoardDao { // 2022 04 06 06 12
	
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
		catch (Exception e) {
			System.out.println("[SQL ��� ���� ���� ] "+ e);
		}
	}
	
	// �޼ҵ�
	public static void viewSave() {
		try {
			FileOutputStream outputStream = new FileOutputStream("D:/viewlist.txt");
			for(MemberView temp : controllor.board.Board.m_view) {
				String Save = temp.getId()+","+temp.getB_num()+","+temp.getDate()+"\n";
				outputStream.write(Save.getBytes());
			}
		}
		catch(Exception e) {
			
		}
	}
	public static void viewLoad() {
		try {
			FileInputStream inputStream = new FileInputStream("D:/viewlist.txt");
			byte[] bytes = new byte[1024]; 
			inputStream.read(bytes); 
			String file = new String(bytes); 
			String[]list = file.split("\n"); 
			int i = 0;
			for(String temp : list) {
				if( i+1 == list.length ) {
					break; 
				}
				String[] filed = temp.split(",");
				MemberView temp2 = new MemberView(filed[0],Integer.parseInt(filed[1]),filed[2]);
				controllor.board.Board.m_view.add(temp2);
				i++;
			}
			
		}
		catch(Exception e) {
		}
	}
	
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
			System.out.println("[SQL �۸�� �ε� ���� ] "+ e);
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
			
		} catch (SQLException e) {
			System.out.println("[SQL �Խñ� ���� ���� ] "+ e);
			}
		
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
	public boolean view(int num,int view,String id) {
		BoardDao.viewLoad();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	String today = sdf.format(new Date());
			for(MemberView temp : controllor.board.Board.m_view) {
				if(temp.getId().equals(id) && temp.getB_num() == num) {
					if(temp.getDate().equals(today)) {
						System.out.println("��ȸ�� ������");
						break;
					}
					else if((!temp.getDate().equals(today))){
						System.out.println("��ȸ�� ����");
						temp.setDate(today);
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
						BoardDao.viewSave();
						break;
					}
				}
			}
			// 1. SQL �ۼ�
			// select * from ���̺�� where ����=( �ʵ�� = �� )
			
			
			return true;
		}
		catch(Exception e) {
			System.out.println("[SQL ��ȸ�� ���� ] "+ e);
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
	public ObservableList<Reply> reply_list(int b_num) {
		
		try {
			// *
			ObservableList<Reply> replylist = FXCollections.observableArrayList();
			
			// 1. sql  �ۼ� [ ������ ȣ�� ]
				//select * (��� �ʵ�) from ���̺��
			 	// �������� by b_num asc = b_num�������� �������� ����
				// �������� by b_num desc = b_num�������� �������� ����
			String sql = "select * from reply where b_num=?";
			// 2. SQL ���� [ DB �� ����� ��ü�� ���ۤ��������̽� ���� ]
			ps = con.prepareStatement(sql);
			ps.setInt(1, b_num);
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
	// 8. ��� ����
	public boolean reply_delete(int r_num) {
		try {
			// 1. sql �ۼ�
			String sql = "delete from reply where r_num=?";
			// 2. sql ����
			ps = con.prepareStatement(sql);
			ps.setInt(1, r_num);
			
			// 3. sql ����
			ps.executeUpdate();
			// 4. sql ���
			return true;
			
		} catch (SQLException e) {
			System.out.println("[SQL ��� ���� ���� ] "+ e);
			}
		
		return false;
	}
	// 9. ��� ����
	public boolean re_updaete(int r_num,String contents) {
		try {
			// 1. SQL �ۼ�
			// select * from ���̺�� where ����=( �ʵ�� = �� )
			String sql = "UPDATE reply SET r_contents=? where r_num=?";
			// 2. sql ����
			ps = con.prepareStatement(sql);
			
			ps.setString(1, contents);
			ps.setInt(2, r_num);

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
	// 9.�� ��Ȯ��
	public ObservableList<Board> my_boardlist(String id) {
		
		try {
			// *
			ObservableList<Board> myboardlist = FXCollections.observableArrayList();

			String sql = "select * from board where b_writer=?";

			ps = con.prepareStatement(sql);
			ps.setString(1, id);

			rs =  ps.executeQuery();

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
				myboardlist.add(temp);
			}
			// �ݺ����� ����Ǹ� ����Ʈ ��ȯ
			// ������ ������ ��� ��ȯ
			return myboardlist;
		}
		catch (Exception e) {
			System.out.println("[sql ���� ����] : ���� " + e);
		}
		// ���н� 
		return null;
	}
	public int total_board() {
		try {
			String sql = "select count(*) from board";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()) { // ���࿡ ���� ������� �����ϸ� => �ش���̵� ���� => �ߺ�
				return rs.getInt(1); // �ش� ���̵�� �ߺ��� ����
			}
		}
		catch (Exception e) {
			System.out.println("�Խù��� �ҷ����� ���� " + e);
		}
		return 0;
	}
	public Map<String, Integer> date_Btotal(String name) {
		try {
			Map<String ,Integer> btotal = new HashMap<>();
			String sql = null;
			if(name.equals("board")) {
				sql = "SELECT substring_index(b_date,' ', 1), count(*) FROM "+name+" group by substring_index(b_date, ' ' , 1)  ";
			}
			else if(name.equals("product")){
				sql = "SELECT substring_index(p_date,' ', 1), count(*) FROM "+name+" group by substring_index(p_date, ' ' , 1)  ";
			}
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				btotal.put(rs.getString(1), rs.getInt(2));
			}
			return btotal;
		}
		catch (Exception e) {
			System.out.println("��¥ ȸ���� ��ȸ ����" + e);
		}
		return null;
	}
}