package controllor;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import controllor.login.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Chatting implements Initializable{
	@FXML
    private TextArea txtcontents;

    @FXML
    private Button btcon;

    @FXML
    private TextField txt;

    @FXML
    private Button btsend;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtipadd;

    @FXML
    private TextField txtport;
    
    Socket socket;
    
    
    public void clientstart() {
    	
    	Thread thread = new Thread() {
    		@Override
    		public void run() {
    			try {
    				socket = new Socket("127.0.0.1",1234);
    				send(Login.member.getM_id() +  "님이 입장 하셨습니다.");
    				receive(); // 접속과 동시에 받기 메소드는 무한루프를 돌림
    			}
    			catch (Exception e) {
    				System.out.println("클라이언스 실행 오류 " + e);
    			}
    		};
    	};
    	// 멀티 스레드 실행
    	thread.start();
    }
	public void clientstop() {try {socket.close();}catch (Exception e) {System.out.println("클라이언트 닫기 실패 " + e);}}
	public void send(String msg) {
		
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
				OutputStream outputStream = socket.getOutputStream(); // 1. 출력 스트림
				outputStream.write(msg.getBytes()); // 2. 내보내기
				outputStream.flush(); // 3. 스트림 초기화
				}
				catch (Exception e) {
					System.out.println("클라이언스 보내기 오류 " + e);
				}
			}
		};
		thread.start();
	}
	public void receive() {
		try {
			while (true) {
				InputStream inputStream = socket.getInputStream();
				byte[] bytes = new byte[1024];
				inputStream.read(bytes);
				String msg = new String(bytes);
				txtcontents.appendText(msg);
			}
		}
		catch (Exception e) {
			System.out.println("클라이언스 받기 오류 " + e);
		}
	}
    
    @FXML
    void acsend(ActionEvent event) { // 전송 버튼 눌렀을때ㅑ
    	String msg = txt.getText() + "\n";
    	send(msg);
    	txt.setText("");
    }

    @FXML
    void actcon(ActionEvent event) {
    	if(btcon.getText().equals("채팅방 입장")){
    		
    		clientstart();
    		txtcontents.appendText("---채팅방 입장 ---\n");
    		btcon.setText("채팅방 나가기");
    	}
    	else {
    		clientstop();
    		txtcontents.appendText("---채팅방 퇴장 ---\n");
    		btcon.setText("채팅방 입장");
    	}
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	// TODO Auto-generated method stub
    	
    }
}
