package Aapplication.Day26;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Day26_client {
	
	public static void main(String[] args) {
		
		// 1. ���� ���ѱ�
		Socket socket = new Socket();
		
		// 2. ���� �������� �����ϱ� [ ������ �����ǿ� ��Ʈ��ȣ�� �Է� ]
		try {
			while(true) {
				socket.connect(new InetSocketAddress("127.168.102.5",5000));
				System.out.println("[ ���� ���� ���� ]");
				
			} 
		}
		catch (IOException e) {
			try {
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
}
