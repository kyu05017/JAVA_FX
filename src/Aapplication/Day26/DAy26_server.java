package Aapplication.Day26;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

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
			ServerSocket serverSocket = new ServerSocket();
			// 2. ���� ���� ���ε�
			serverSocket.bind(new InetSocketAddress("127.168.102.5",5000));
			// 3. Ŭ���̾�Ʈ�� ��û ���
			while(true) {
				System.out.println("[ ������ ���� ����� �Դϴ�. ]");
				// 4. ��û�� ������� ����
				Socket socket = serverSocket.accept();
				// 5. ������ ������ ���� Ȯ��
				InetSocketAddress inetSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
				System.out.println("[ Ŭ���̾�Ʈ�� ������ �Ǿ����ϴ�. ����� ���� "+ inetSocketAddress + " ]");
			}
		}
		catch(Exception e) {
			
		}
	}
}
