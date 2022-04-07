package dto;

import java.net.Socket;

public class Client {
	// 서버에서 등러오는 클라이언트의 클래스
	
	// 1. 소켓
	Socket socket;
	// 2. 생성자
	public Client(Socket socket) {
		this.socket = socket;
	}
	
	// 3. 서버로 메세지를 받는 메소드
	
	// 4. 서버가 메세지를 보내는 메소드
}
