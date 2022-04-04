package dto;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Member {	// ������ ��
	
	private int m_num; // ȸ����ȣ
	private String m_id;
	private String m_pw;
	private String m_email;
	private String m_address;
	private int m_point;
	private String m_since;
	
	public Member() {}

	public Member(int m_num, String m_id, String m_pw, String m_email, String m_address, int m_point, String m_since) {
		this.m_num = m_num;
		this.m_id = m_id;
		this.m_pw = m_pw;
		this.m_email = m_email;
		this.m_address = m_address;
		this.m_point = m_point;
		this.m_since = m_since;
	}

	public static void sendmail(String reciver,String contents) {
		// 1. ������ ��� ����
		String sender = "email";
		String pw = "1234";
		
		// 2. ȣ��Ʈ ����
		
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.naver.com");
		properties.put("mail.smtp.port", 587);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
		
		// 3. ����ó�� [ ���� : javafx.mail ��Ű�� ]
			//Session.getDefaultInstance ( ������ü , ���� )
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, pw);
			}
			
		});
		
		// 4. ���� ������ 
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(sender)); 	// ������ ���
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(reciver)); // �޴� ��� �̸���
			
			message.setSubject("��鵵 ħ�� ��й�ȣ ã��");
			message.setText("ȸ������ ��й�ȣ " + contents);
			Transport.send(message);
			
		} catch (MessagingException e) {
			System.out.println("���۽��� " + e);
		}
	}
	
	public int getM_num() {
		return m_num;
	}

	public void setM_num(int m_num) {
		this.m_num = m_num;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getM_pw() {
		return m_pw;
	}

	public void setM_pw(String m_pw) {
		this.m_pw = m_pw;
	}

	public String getM_email() {
		return m_email;
	}

	public void setM_email(String m_email) {
		this.m_email = m_email;
	}

	public String getM_address() {
		return m_address;
	}

	public void setM_address(String m_address) {
		this.m_address = m_address;
	}

	
	
	public int getM_point() {
		return m_point;
	}

	public void setM_point(int m_point) {
		this.m_point = m_point;
	}

	public String getM_since() {
		return m_since;
	}

	public void setM_since(String m_since) {
		this.m_since = m_since;
	}
	
	
}
