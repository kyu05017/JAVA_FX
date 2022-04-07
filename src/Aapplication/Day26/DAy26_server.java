package Aapplication.Day26;

import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class DAy26_server {
	
	
	// 통신용 서버 맨둘기
		// 1. 서버 소켓 맨둘기
			// 서버 : 클라이언트에게 정보나 서비스를 제공해주는 컴퓨터
			// 소켓 : PC간의 통신 종착점
		// 2. 서버 소켓 바인딩
			// ip : 통신 인식 번호 [ pc를 식별하는 번호 ]
			// port : pc내 프로세스 ( 프로그램 ) 를 식별하는 번호
				// ip 당 6만개 정도의 포트번호 사용 가능
	
	
	public static void main(String[] args) {
		
		
		try {
			// 1. 서버 소켓 만들기
			ServerSocket socket = new ServerSocket();
			// 2. 서버 소켓 바인딩
			socket.bind(new InetSocketAddress("127.168.102.5",5000));
			// 3. 클라이언트의 요청 대기
			while(true) {
				System.out.println("[ 서버가 연결 대기중 입니다. ]");
			}
		}
		catch(Exception e) {
			
		}
	}
}
