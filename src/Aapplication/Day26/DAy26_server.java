package Aapplication.Day26;

import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class DAy26_server {
	
	
	// ��ſ� ���� �ǵѱ�
		// 1. ���� ���� �ǵѱ�
			// ���� : Ŭ���̾�Ʈ���� ������ ���񽺸� �������ִ� ��ǻ��
			// ���� : PC���� ��� ������
		// 2. ���� ���� ���ε�
			// ip : ��� �ν� ��ȣ [ pc�� �ĺ��ϴ� ��ȣ ]
			// port : pc�� ���μ��� ( ���α׷� ) �� �ĺ��ϴ� ��ȣ
				// ip �� 6���� ������ ��Ʈ��ȣ ��� ����
	
	
	public static void main(String[] args) {
		
		
		try {
			// 1. ���� ���� �����
			ServerSocket socket = new ServerSocket();
			// 2. ���� ���� ���ε�
			socket.bind(new InetSocketAddress("127.168.102.5",5000));
			// 3. Ŭ���̾�Ʈ�� ��û ���
			while(true) {
				System.out.println("[ ������ ���� ����� �Դϴ�. ]");
			}
		}
		catch(Exception e) {
			
		}
	}
}
