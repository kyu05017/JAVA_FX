package Aapplication.Day26;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Day26_1 {
	
	// ��Ʈ��ũ ���� : �� �� �̻��� ��ǻ�͵��� �����ϰ� ����Ҽ� �ִ� �� [ �� ]
		// ��Ÿ� : ���� ��ȣ�� ���� ����ϴ� ��� ��Ⱑ ���� ����ϱ� ���� �ϳ��� ��
		// �������� : ��ǻ�� ����/��ǻ�� ���̿� ������ ��ȯ ����� ���� �ϴ� ��Ģ [ ü�� ]
			// SMTP ( Simple Mail Transfer Protocol ): ���� ���� ��������
			// IP ( Internet Protocol address ) : ���ͳ� ��������
	
	// TCP / IP ( Transmission Control Protocol/Internet Protocol )
		// TCP ( Transmission Control Protocol ) : �������
		// IP ( Internet Protocol address ) : ��� �ν� ��ȣ [ �ּ� ] 0.0.0.0. ~ 255.255.255.255
			// �츮�� < --[ ���� ]-- > ģ���� ( �ּҸ� �˾ƾ��� )
			//					��⵵ ������ ������
			// ��ǻ�� < --[ ���� ]-- > ���̹�[ Ȩ������ ] 
			//					www.naver.com [ ������ �ּ� ] : �����̱� ������ ����� �ܿ�� ����
			//					223.130.195.200 [ ������ �ּ� ] 
			//					IP�ּ� -> �����ּ� ( DNS )
	public static void main(String[] args) {
		
		// 1. ���� pc�� ip�ּ� Ȯ��
			// cmd -> ipconfig �Է½� ��Ʈ��ũ ������ �˼� �ִ�.
		
		try {
			// 2. �ڹٿ��� ip�ּ� Ȯ���ϴ� ��
			// �ڹ� �� ����ϰ� �Ǵ� ��� ����ó�� �߻�
			InetAddress address = InetAddress.getLocalHost();
			//InetAddress.getLocalHost(); // ����ȣ��Ʈ�� ������ ȣ��
			System.out.println("���� pc�� ��Ʈ��ũ ��ü���� : "+ address);
			System.out.println("���� pc�� �̸� " + address.getHostName());
			System.out.println("���� pc�� ������ " + address.getHostAddress());
			
			// 3. ���̹��� �����Ǹ� ã�ƺ���
			InetAddress address2 = InetAddress.getByName("www.naver.com");
			System.out.println("���̹� pc�� ���� "+address2);
			System.out.println("���̹� pc�� �̸� "+address2.getHostName());
			System.out.println("���̹� pc�� ������ "+address2.getHostAddress());
			
			InetAddress address3 = InetAddress.getByName("www.facebook.com");
			System.out.println("���̽��� pc�� ���� "+address3);
			System.out.println("���̽��� pc�� �̸� "+address3.getHostName());
			System.out.println("���̽��� pc�� ������ "+address3.getHostAddress());
		} catch (UnknownHostException e) {}
		
	}
	
}
