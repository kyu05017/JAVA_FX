package Aapplication.Day26;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Day26_client {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		// 1. ���� ���ѱ�
		Socket socket = new Socket();
		
		// 2. ���� �������� �����ϱ� [ ������ �����ǿ� ��Ʈ��ȣ�� �Է� ]
		try {
			while(true) {
				socket.connect(new InetSocketAddress("127.168.102.5",5000));
				System.out.println("[ ���� ���� ���� ]");
				// 3. ������ ������ �۽��ϱ� [ ������ ] : ��Ʈ���̿��� �ܺ� ��Ʈ��ũ ���
					// 1. ������ �Է¹ޱ�
				System.out.println("�������� ���� �޼���");
				String text = scanner.nextLine();
					// 2. ������ ��� ��Ʈ�� ��������
				OutputStream outputStream = socket.getOutputStream();
					// 3. �����ױ� ( ��Ŵ��� ����Ʈ )
				outputStream.write(text.getBytes());
				
				InputStream inputStream = socket.getInputStream();
				byte[] bytes = new byte[1000];
				inputStream.read(bytes);
				System.out.println("������ ���� �޼��� " + new String(bytes));
				
				
			} 
		}
		catch (IOException e) {

		}
	}
	
}
