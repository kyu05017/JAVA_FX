package dto;

import java.io.InputStream;
import java.net.Socket;

public class Client {
	// ������ ���ӵ� Ŭ���̾�Ʈ�� Ŭ����
	
	// 1. ����
	Socket socket;
	// 2. ������
	public Client(Socket socket) {
		this.socket = socket;
	}
	
	// 3. ������ �޼����� �޴� �޼ҵ�
	public void receive() {	// ��Ƽ ������
		
		// ��Ƽ������ [ Thread Ŭ���� VS Runnable �������̽� ]
		// run �޼ҵ带 �ʼ������� ���� �ؾ��Ѵ�.
		// �������̽��� �߻�޼ҵ尡 �����ϱ� ������ ������ �ʼ��� �ؾ��Ѵ�. 
		// Ŭ�������� implements�� �ϰų� �͸����� �Ѵ�.
		Runnable runnable = new Runnable() { // �͸� ���� ��ü
			
			@Override
			public void run() { // �߻�޼ҵ� ����
				// ��������� �޼����� �޴� ����
				try {
					while(true) {
						//  1.�Է� ��Ʈ��
						InputStream inputStream = socket.getInputStream(); // 1. �Է°�ü ����
						byte[] bytes = new byte[1024]; // 2. ����Ʈ �迭���� [ ��Ʈ���� ����Ʈ�� ����Ѵ� ]
						inputStream.read(bytes); //3. �Է½�Ʈ������ �о��
						String msg = new String(bytes); // 4. ����Ʈ��-> ���ڿ��� ��ȯ
					}
				}
				catch(Exception e) {
						
				}
			}
		};
		
	}
	// 4. ������ �޼����� ������ �޼ҵ�
	public void send() {	// ��Ƽ ������
		
		
		
	}
}
