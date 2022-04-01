package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.Member;

public class MemberDao {	// DB ���� ��ü

	private Connection con; // SB ������ ���Ǵ� Ŭ���� : DB ����Ŭ����
	private PreparedStatement ps; // ����� DB�� SQL ���� �Ҷ� ����ϴ� �������̽� : DB ����
	private ResultSet rs; // �˻� [ select ]
	
	// * DB �����Ҷ����� ��ü ����� ���ʿ��� �ڵ带 ����
	
	private static MemberDao dao; // DB ���� ��ü;
	
	public MemberDao() {
		try {
			// DB ���� 
			Class.forName("com.mysql.cj.jdbc.Driver");// 1. DB ����̹� ��������
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx?serverTimezone=UTC","root","1234"); // 2. DB �ּ� ����
			dao = this;
			System.out.println("DB���� ����");
		} catch (Exception e) {
			System.out.println("DB���� ����");
		}

	}
	// �޼ҵ�
		// 1. ȸ������ �޼ҵ� ( �μ��� DB�� ���� ���̵� ��й�ȣ �̸��� �ּ� )
	public boolean signup(Member member){
		return false;
	}
		// 2. �α��� �޼ҵ� ( �α��ο� �ʿ��� ���̵� ��й�ȣ )
	public boolean login(String id, String pw) {
		return false;
	}
		// 3. ���̵� ã�� �޼ҵ� ( ���̵� ã�⿡ �ʿ��� �̸��� )
	public String findid(String email) {
		return null;
	}
		// 4. ��й�ȣ ã�� �޼ҵ� ( ��й�ȣ ã�⿡ �ʿ��� ���̵� �̸��� ) 
	public String findpw(String id ,String email) {
		return null;
	}
}
