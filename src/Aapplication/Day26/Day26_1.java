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
	
	public static void main(String[] args) {
		
		// 1. ���� pc�� ip�ּ� Ȯ��
			// cmd -> ipconfig �Է½� ��Ʈ��ũ ������ �˼� �ִ�.
		// 2. �ڹٿ��� ip�ּ� Ȯ���ϴ� ��
		try {
			
			InetAddress address = InetAddress.getLocalHost();
			
			System.out.println("���� pc�� ��Ʈ��ũ ��ü���� : "+ address);
		} catch (UnknownHostException e) {}
		
	}
	
}
