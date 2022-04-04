package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Member;

public class MemberDao {	// DB ���� ��ü

	private Connection con; // SB ������ ���Ǵ� Ŭ���� : DB ����Ŭ����
	private PreparedStatement ps; // ����� DB�� SQL ���� �Ҷ� ����ϴ� �������̽� : DB ����
	private ResultSet rs; // �˻� [ select ]
	
	// * DB �����Ҷ����� ��ü ����� ���ʿ��� �ڵ带 ����
	
	public static MemberDao dao = new MemberDao(); // DB ���� ��ü;
	
	public MemberDao() {
		
		try {
			// DB ���� 
			Class.forName("com.mysql.cj.jdbc.Driver");// 1. DB ����̹� ��������
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx?serverTimezone=UTC","root","1234"); // 2. DB �ּ� ����
			System.out.println("DB���� ����");
		} catch (Exception e) {
			System.out.println("DB���� ����" + e);
		}

	}
	// �޼ҵ�
		// * ���̵� �ߺ� üũ �޼ҵ� ( �μ� : ���̵� �μ��� �޾� db�� ����������üũ ]
	public boolean idcheck(String id) {
		try {
			// 1. SQL �ۼ�
				// select * from ���̺�� where ����=( �ʵ�� = �� )
			String sql = "select * from member where m_id=?";
			
			// 2. SQL ����
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			// 3. SQL ����
			rs = ps.executeQuery();//  select ������ ������� ���� -> resulitSet O
			// ResultSet ó�� ������� null -- next > ��� ���ڵ�
			// 4. SQL ���
			if(rs.next()) { // ���࿡ ���� ������� �����ϸ� => �ش���̵� ���� => �ߺ�
				return true; // �ش� ���̵�� �ߺ��� ����
			}
		}
		catch (SQLException e) {
			System.out.println("[SQL ���� ]" + e);
		}
		return false; // �ش���̵� �ߺ� ����
	}
	
		// 1. ȸ������ �޼ҵ� ( �μ��� DB�� ���� ���̵� ��й�ȣ �̸��� �ּ� )
	public boolean signup(Member member){
		
		try {
		// 1. SQL �ۼ� [ ȸ����ȣ ( �ڵ� )������ ��� �ʵ� ����  ]
		String sql = "insert into member(m_id,m_pw,m_email,m_address,m_point,m_since) values(?,?,?,?,?,?)";
		
		
		// 2. SQL ����
		ps = con.prepareStatement(sql);
		ps.setString(1, member.getM_id());
		ps.setString(2, member.getM_pw());
		ps.setString(3, member.getM_email());
		ps.setString(4, member.getM_address());
		ps.setInt(5, member.getM_point());
		ps.setString(6, member.getM_since());
		
		// 3. SQL ����
		ps.executeUpdate(); 	
		
		// * ������ 
		return true;
		
		} catch (SQLException e) {
			System.out.println("[SQL ���� ���� ]" + e);
		}
		// * ���н�
		return false;
	}
		// 2. �α��� �޼ҵ� ( �α��ο� �ʿ��� ���̵� ��й�ȣ )
	public boolean login(String id, String pw) {
		try {
			// 1. SQL �ۼ� 
					// and : ����1 && ����2
					// or  : ����1 || ����2
			String sql = "select * from member where m_id=? and m_pw=?";
			// 2. SQL ����
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			// 3. SQL ����
			rs = ps.executeQuery();
			// 4. ���
			if(rs.next()) { // ���࿡ ���� ������� �����ϸ� => �ش���̵� ���� => �ߺ�
				return true; // �ش� ���̵�� �ߺ��� ����
			}
		}
		catch (SQLException e) {
			System.out.println("[SQL ����]" + e);
		}
		return false;
	}
		// 3. ���̵� ã�� �޼ҵ� ( ���̵� ã�⿡ �ʿ��� �̸��� )
	public String findid(String email) {
		try {
			// 1. SQL �ۼ�
				// select * from ���̺�� where ����=( �ʵ�� = �� )
			String sql = "select * from member where m_email=?";
			
			// 2. SQL ����
			ps = con.prepareStatement(sql);
			ps.setString(1, email);
			// 3. SQL ����
			rs = ps.executeQuery();//  select ������ ������� ���� -> resulitSet O
			// ResultSet ó�� ������� null -- next > ��� ���ڵ�
			// 4. SQL ���
			if(rs.next()) { // ���࿡ ���� ������� �����ϸ� => �ش���̵� ���� => �ߺ�
				return rs.getString(2); // �ش� ���̵�� �ߺ��� ����
			}
		}
		catch (SQLException e) {
			System.out.println("[SQL ���� ]" + e);
		}
		return null; // �ش���̵� �ߺ� ����
	}
		// 4. ��й�ȣ ã�� �޼ҵ� ( ��й�ȣ ã�⿡ �ʿ��� ���̵� �̸��� ) 
	public String findpw(String id ,String email) {
		try {
			// 1. SQL �ۼ�
				// select * from ���̺�� where ����=( �ʵ�� = �� )
			String sql = "select * from member where m_id=? and m_email=?";
			
			// 2. SQL ����
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, email);
			// 3. SQL ����
			rs = ps.executeQuery();//  select ������ ������� ���� -> resulitSet O
			// ResultSet ó�� ������� null -- next > ��� ���ڵ�
			// 4. SQL ���
			if(rs.next()) { // ���࿡ ���� ������� �����ϸ� => �ش���̵� ���� => �ߺ�
				return rs.getString(3); // �ش� ���̵�� �ߺ��� ����
			}
		}
		catch (SQLException e) {
			System.out.println("[SQL ���� ]" + e);
		}
		return null; // �ش���̵� �ߺ� ����
	}
	
	// 5. ���̵�� ȸ������ ȣ��
	public Member getmember (String id) {
		try {
			// 1. sql �ۼ�
			String sql = "select * from member where m_id=?";
			// 2. sql ����
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			// 3. sql ����
			rs = ps.executeQuery();
			// 4. sql ���
			if(rs.next()) { // ���࿡ ���� ������� �����ϸ� => �ش���̵� ���� => �ߺ�
				Member member = new Member(
					rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getInt(6),
					rs.getString(7)
					//rs.next() : ����� ���� ���ڵ� ( ��, ���� )
					//rs.getint : �ش� �ʵ��� �ڷ����� ���������� ������
					//rs.getsttring : �ش��ʵ��� �ڷ����� ���ڿ��� ������
				);
				return member;
				
			}
			
		}
		catch(Exception e ) {
			System.out.println(e);
		}
		return null;
		 
	}
	// 6. ȸ��Ż�� = ȸ����ȣ�� �Է¹޾� �μ��� �޾� �ش� ȸ����ȣ�� ���ڵ带 ����
	public boolean signOut(int num) {
		
		
		try {
			// 1. sql �ۼ�
			String sql = "delete from member where m_num=?";
			// 2. sql ����
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			
			// 3. sql ����
			ps.executeUpdate();
			// 4. sql ���
			return true;
			
		} catch (SQLException e) {System.out.println("Ż�����"+e);}
		
		
		
		return false;
	}
	// 7. ȸ������
	
}
