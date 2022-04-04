package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controllor.login.Login;
import controllor.login.Loginpane;


public class BoardDao {
	
	private Connection con; // DB ������ü
	private PreparedStatement ps; // ����� DB�� SQL ���� �Ҷ� ����ϴ� �������̽� : DB ����
	private ResultSet rs; // �˻� [ select ]
	
	
	// 2. ������
	public BoardDao() {
		
		try { // �ڹٿ� ����� ������ �Ϲ� ���� �߻� 
			// 1. DB ����̺� Ŭ���� ȣ�� [ DB ȸ�� ���� �ٸ��� ������ �ϱ�X ����O ]
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2. DB ����
			con = DriverManager.getConnection(
					// JDBC : JAVA DATABASE CONNECTE
					// jdbc:mysql://IP�ּ�( ����[����PC] �̸� localhost):port��ȣ/DB�̸�?�ð�����
					// , ������ , ��й�ȣ
					"jdbc:mysql://localhost:3306/javafx?serverTimezone=UTC",
					"root","1234");
			System.out.println("DB���� ����");
		}
		catch(Exception e) {
			System.out.println("DB���� ����");
		}
	}
	// 3. �޼ҵ�
	
	
	
	// 1. ����
	public boolean write(String content) {
		
		try {
			
			
		// 1. SQL �ۼ� [ DB�� ���̺� ������ ���� ]
			// db�� ���̺� ������ ���� : insert into ���̺��1 ( �ʵ��1 , �ʵ��2 ) values ( �ʵ��1�� �� , �ʵ��2�� ��) 
		String sql = "insert into test(writer,content) values(?,?)";
		// 2. SQL ���� [ ����� DB�� SQL ���� ] 
			// PreparableStatement : ����� DB������ ���̺� ���� ( ����, ���� ���� ���� ) �Ҷ� ���� �������̽�
		PreparedStatement ps = con.prepareStatement(sql);
		
		//ps.setString(1 , id2);	// sql�� �ۼ��� ù��° ? �� ���� �ֱ� [ 1 : ù��° ]
		
		ps.setString(1 ,Loginpane.loginpane.getid() );
		ps.setString(2 , content);	//  [ 2 : �ι�° ]
		
		// 3. sql ����
		ps.executeUpdate(); // sql ����
		
		// ������ true
		return true;
		}
		catch (Exception e) {
			System.out.println("[sql ���� ����] : ���� " + e);
		}
		
		// ���н� false
		return false;
	}
	
	


	// 2. ������ ȣ��
	public ArrayList<dto.Data> get() {
		
		try {
			// *
			ArrayList<dto.Data> datelsit = new ArrayList<>();
			
			// 1. sql  �ۼ� [ ������ ȣ�� ]
				//select * (��� �ʵ�) from ���̺��
			String sql = "select * from test";
			
			// 2. SQL ���� [ DB �� ����� ��ü�� ���ۤ��������̽� ���� ]
			PreparedStatement ps = con.prepareStatement(sql);
			
			// 3. sql ���� [ ResultSet �������̽� java.sql ��Ű�� ]
			ResultSet rs =  ps.executeQuery();
			
			// * ����� �ϳ��� �ƴϰ� ������ �̹Ƿ� �ݺ��� ����ؼ�
				// ���پ�[���ڵ�] ��üȭ -> ����Ʈ�� ����
			while(rs.next()) {
				// 1. ���ٽ� [ ���ڵ� ] ���� ��üȭ
				dto.Data temp = new dto.Data(
					rs.getInt(1), // �ش� �� [ ���ڵ� ]�� ù��° �ʵ� [ ���� ] ���� ��������
					rs.getString(2), // �ش� �� [ ���ڵ� ]�� ù��° �ʵ� [ ���� ] ���� ��������
					rs.getString(3)); // �ش� �� [ ���ڵ� ]�� ù��° �ʵ� [ ���� ] ���� ��������
				datelsit.add(temp);
			}
			// �ݺ����� ����Ǹ� ����Ʈ ��ȯ
			// ������ ������ ��� ��ȯ
			return datelsit;
		}
		catch (Exception e) {
			System.out.println("[sql ���� ����] : ���� " + e);
		}
		// ���н� 
		return null;
	}
	
	
}
