package dto;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import controllor.ServerControl;

public class Client {
	// 서버에 접속된 클라이언트의 클래스
	
	// 1. 소켓
	Socket socket;
	// 2. 생성자
	public Client(Socket socket) {
		this.socket = socket;
	}
	
	// 3. 서버로 메세지를 받는 메소드
	public void receive() {	// 멀티 스레드
		
		// 멀티스레드 [ Thread 클래스 VS Runnable 인터페이스 ]
		// run 메소드를 필수적으로 구현 해야한다.
		// 인터페이스는 추상메소드가 존재하기 때문에 구현을 필수로 해야한다. 
		// 클래스에서 implements를 하거나 익명으로 한다.
		Runnable runnable = new Runnable() { // 익명 구현 객체
			
			@Override
			public void run() { // 추상메소드 구현
				// 계속적으로 메세지를 받는 상태
				try {
					while(true) {
						//  1.입력 스트림
						InputStream inputStream = socket.getInputStream(); // 1. 입력객체 선언
						byte[] bytes = new byte[1024]; // 2. 바이트 배열선언 [ 스트림은 바이트로 통신한다 ]
						inputStream.read(bytes); //3. 입력스트림으로 읽어옴
						String msg = new String(bytes); // 4. 바이트열-> 문자열로 변환
						// * 서버가 받은 메세지를 현재 서버에 접속된 모든 클라이언트에게 다시 보내야 한다.
						for(Client temp : ServerControl.clientlist) {
							temp.send(msg); // 받은 메세지를 서버에 접속된 [ clientlist ]  모든 클라이언트에게 전송
						}
					}
				}
				catch(Exception e) {
						
				}
			}
		}; // 멀티 수레두 구현 끝
		
		// 해당 멀티스레드를 스레드 풀에 넣어 준다.
		ServerControl.threadpool.submit(runnable);
	}
	// 4. 서버가 메세지를 보내는 메소드
	public void send(String msg) {	// 멀티 스레드
		
		// 멀티스레드
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
		// 해당 멀티스레드를 스레드 풀에 넣어 준다.
		ServerControl.threadpool.submit(runnable);
	}
}
