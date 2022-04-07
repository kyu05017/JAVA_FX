package dto;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import controllor.ServerControl;

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
						// * ������ ���� �޼����� ���� ������ ���ӵ� ��� Ŭ���̾�Ʈ���� �ٽ� ������ �Ѵ�.
						for(Client temp : ServerControl.clientlist) {
							temp.send(msg); // ���� �޼����� ������ ���ӵ� [ clientlist ]  ��� Ŭ���̾�Ʈ���� ����
						}
					}
				}
				catch(Exception e) {
						
				}
			}
		}; // ��Ƽ ������ ���� ��
		
		// �ش� ��Ƽ�����带 ������ Ǯ�� �־� �ش�.
		ServerControl.threadpool.submit(runnable);
	}
	// 4. ������ �޼����� ������ �޼ҵ�
	public void send(String msg) {	// ��Ƽ ������
		
		// ��Ƽ������
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				
				try {
					while(true) {
						OutputStream outputStream = socket.getOutputStream();
						outputStream.write(msg.getBytes());
						
					}
				}
				catch (Exception e) {
					
				}
			}
		};
		// �ش� ��Ƽ�����带 ������ Ǯ�� �־� �ش�.
		ServerControl.threadpool.submit(runnable);
	}
}
