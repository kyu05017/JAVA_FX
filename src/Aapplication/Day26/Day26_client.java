package Aapplication.Day26;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Day26_client {
	
	public static void main(String[] args) {
		
		// 1. 소켓 만둘기
		Socket socket = new Socket();
		
		// 2. 서버 소켓으로 연결하기 [ 연결할 아이피와 포트번호를 입력 ]
		try {
			while(true) {
				socket.connect(new InetSocketAddress("127.168.102.5",5000));
				System.out.println("[ 서버 연결 성공 ]");
				
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
