package controllor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dto.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class ServerControl implements Initializable{
	
	@FXML
    private Button btserver;

    @FXML
    private TextArea txtserver;
    
    // * 서버에 연결된 클라이언트를 저장하는 리스트
    public static Vector<Client> clientlist = new Vector<>();
    // * 멀티스레드를 관리해주는 스레드풀
    	// ExecutorService : 스레드풀 지원 해주는 인터페이스 [ 인터페이스는 [ 구현 클래스 ( implements  ) VS 직접구현 ]
    public static ExecutorService threadpool;
    
    // 1. 서버 소켓 맨둘기
    ServerSocket serverSocket;
    // 2. 서버 실행 메소드 맨둘기
    public void serverstart() {
    	try {
    		// 1. 서버 소켓 메모리 할당
			serverSocket = new ServerSocket();
			// 2. 서버 소켓 바인딩 [ ip 와 port 설정 ]
			serverSocket.bind(new InetSocketAddress("127.0.0.1",1234));
		} catch (IOException e) {
			// 3. 클라이언트의 요청 대기 [ 멀티 스래드 ] : 클라이언트 1. 연결, 2. 보내기, 3. 받기 [ 동시처리 ]
			Runnable runnable = new Runnable() {
				
				@Override
				public void run() {
					try {
						while (true) {
							Socket socket = serverSocket.accept();  // 1. 요청 수락후 수락된 소켓을 반환
							clientlist.add( new Client(socket));	// 2. 연결된 클라이언트 ( 연결된 소켓 )  생성후에 리스트에 저장
						}
					}
					catch(Exception e) {
						System.out.println("서버 시작 실패 " + e);
					}
				}
				
			};// 런너블 끝
			// 스레드 풀에 메모리 초기화
			threadpool = Executors.newCachedThreadPool();
			threadpool.submit(runnable);
		}
    	
    }
    // 3. 서버 종료 메소드 맨둘기
    public void serverstop() {
    	try {
	    	// 1. 접속된 모든 클라이언트의 소켓을 닫음
	    	for(Client temp : clientlist) {
	    		temp.socket.close();
	    	}
	    	serverSocket.close();
	    	threadpool.shutdown();
    	}
    	catch(Exception e) {
    		System.out.println("서버닫기 오류 " + e);
    	}
    }
    
    @FXML
    void server(ActionEvent event) { // 서버 실행 버튼
    	if(btserver.getText().equals("서버 실행")) {
    		txtserver.appendText("\t\t [[ 서버를 실행 합니다. ]] \n");
    		serverstart();
    		btserver.setText("서버 중지");
    	}
    	else {
    		txtserver.appendText("\t\t [[ 서버를 중지 합니다. ]] \n");
    		serverstop();
    		btserver.setText("서버 실행");
    	}
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtserver.setDisable(true);
		
	}
}
