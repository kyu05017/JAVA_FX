package Aapplication.Day26;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Day26_1 {
	
	// 네트워크 기초 : 두 대 이상의 컴퓨터들을 연결하고 통신할수 있는 것 [ 망 ]
		// 통신망 : 전자 신호를 통해 통신하는 모든 기기가 서로 통신하기 위한 하나의 망
		// 프로토콜 : 컴퓨터 내부/컴퓨터 사이에 데이터 교환 방식을 정의 하는 규칙 [ 체계 ]
			// SMTP ( Simple Mail Transfer Protocol ): 메일 전송 프로토콜
			// IP ( Internet Protocol address ) : 인터넷 프로토콜
	
	// TCP / IP ( Transmission Control Protocol/Internet Protocol )
		// TCP ( Transmission Control Protocol ) : 통신제어
		// IP ( Internet Protocol address ) : 통신 인식 번호 [ 주소 ] 0.0.0.0. ~ 255.255.255.255
			// 우리집 < --[ 우편 ]-- > 친구집 ( 주소를 알아야함 )
			//					경기도 ㅇㅇ시 ㅇㅇ구
			// 컴퓨터 < --[ 접속 ]-- > 네이버[ 홈페이지 ] 
			//					www.naver.com [ 도메인 주소 ] : 문자이기 때문에 사람이 외우기 쉬움
	
	public static void main(String[] args) {
		
		// 1. 현재 pc의 ip주소 확인
			// cmd -> ipconfig 입력시 네트워크 정보를 알수 있다.
		// 2. 자바에서 ip주소 확인하는 법
		try {
			
			InetAddress address = InetAddress.getLocalHost();
			
			System.out.println("현재 pc의 네트워크 객체정보 : "+ address);
		} catch (UnknownHostException e) {}
		
	}
	
}
