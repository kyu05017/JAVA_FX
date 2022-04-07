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
		// 1. 소켓 만둘기
		Socket socket = new Socket();
		
		// 2. 서버 소켓으로 연결하기 [ 연결할 아이피와 포트번호를 입력 ]
		try {
			while(true) {
				socket.connect(new InetSocketAddress("127.168.102.5",5000));
				System.out.println("[ 서버 연결 성공 ]");
				// 3. 서버에 데이터 송신하기 [ 보내기 ] : 스트림이용한 외부 네트워크 통신
					// 1. 데이터 입력받기
				System.out.println("서버에게 보낼 메세지");
				String text = scanner.nextLine();
					// 2. 소켓의 출력 스트림 가져오기
				OutputStream outputStream = socket.getOutputStream();
					// 3. 내보네기 ( 통신단위 바이트 )
				outputStream.write(text.getBytes());
				
				InputStream inputStream = socket.getInputStream();
				byte[] bytes = new byte[1000];
				inputStream.read(bytes);
				System.out.println("서버가 보낸 메세지 " + new String(bytes));
				
				
			} 
		}
		catch (IOException e) {

		}
	}
	
}
