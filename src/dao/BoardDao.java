package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BoardDao {
	
	private Connection con; // 1 .DB ������ ���Ǵ� Ŭ���� : DB ����Ŭ����
	private PreparedStatement ps; // 2. ����� DB�� SQL ���� �Ҷ� ����ϴ� �������̽� : DB ����
	private ResultSet rs; // 3. sql ��� 
	
	public static BoardDao dao = new BoardDao();
	
	public BoardDao() { // 4. �����ڿ��� �����ϴ� ���� : ��ü ������ �ٷ� ����
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// 1. DB ����̹� ��������
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx?serverTimezone=UTC","root","1234"); // 2. DB �ּ� ����
			System.out.println("���� DB���� ����");
		} 
		catch (Exception e) {System.out.println("���� DB���� ����" + e);}
	}
	
	
}
