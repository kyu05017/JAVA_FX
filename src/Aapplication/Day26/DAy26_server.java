package Aapplication.Day26;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

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
		
		Scanner scanner = new Scanner(System.in);
		
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
				
				// 6. Ŭ���̾�Ʈ���� ������ ���� [ �ޱ� ]
				
				InputStream inputStream = socket.getInputStream();
				byte[] bytes = new byte[1000];
				inputStream.read(bytes);
				System.out.println("Ŭ���̾�Ʈ�� ���� �޼��� " + new String(bytes));
				
				// 7. Ŭ���̾�Ʈ���� ������ �۽�
				System.out.println("Ŭ���̾�Ʈ���� ���� �޼��� : ");
				String text = scanner.nextLine();
				
				OutputStream outputStream = socket.getOutputStream();
				outputStream.write(text.getBytes());
				
			}
		}
		catch(Exception e) {
			
		}
	}
}