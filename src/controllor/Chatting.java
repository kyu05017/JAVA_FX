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
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Chatting implements Initializable {

    @FXML
    private TableView<?> roomtable;

    @FXML
    private TextField txtroomname;

    @FXML
    private Button btnadd;

    @FXML
    private Label lblselect;

    @FXML
    private TextArea txtmidlist;

    @FXML
    private Button btnconnect;

    @FXML
    private TextArea txtcontent;

    @FXML
    private TextField txtmsg;

    @FXML
    private Button btnsend;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtip;

    @FXML
    private TextField txtport;
    
    Socket socket;   // 1. 클라이언트 소켓 선언 
    
    // 2. 클라이언트 실행 메소드
    public void clientstart() {
    	Thread thread = new Thread() { // 멀티스레드 
    		@Override
    		public void run() {
    			try {
    				socket = new Socket("127.0.0.1",1234); // 서버의 ip와 포트번호 넣어주기 [ 서버와 연결 ]
    				send(Login.member.getM_id()+"님 입장했습니다\n"); // 접속과 동시에 입장메시지 보내기 
    				receive(); // 접속과 동시에 받기 메소드는 무한루프
    			}catch(Exception e ) {}
    		};
    	};// 멀티스레드 구현 끝
    	thread.start(); // 멀티스레드 실행
    }
    // 3. 클라이언트 종료 메소드 
    public void clientstop() {  try{ socket.close(); }catch(Exception e ) {} }
    
    // 4. 서버에게 메시지 보내기 메소드 
    public void send( String msg ) {
    	Thread thread = new Thread() { 
    		String new_msg = Login.member.getM_id() + " : " + msg;
    		@Override
    		public void run() {
    			try {
    				OutputStream outputStream = socket.getOutputStream(); // 1. 출력 스트림
    				outputStream.write(new_msg.getBytes() ); // 2. 내보내기
    				outputStream.flush(); // 3. 스트림 초기화 [ 스트림 내 바이트 지우기 ]
    			}catch( Exception e ) {} 
    		}
    	};// 멀티스레드 구현 끝 
    	thread.start();
    }
    // 5. 서버에게 메시지 받기 메소드 
    public void receive() {
    	try {
	    	while(true) {
	    		InputStream inputStream = socket.getInputStream(); // 1. 입력 스트림
	    		byte[] bytes = new byte[1000]; 	// 2. 바이트배열 선언 
	    		inputStream.read(bytes);		// 3. 읽어오기 
	    		String msg = new String(bytes);	// 4. 바이트열 -> 문자열 변환
	    		txtcontent.appendText(msg); 	// 4. 받은 문자열을 메시지창에 띄우기 
	    	}
    	}catch( Exception e ) {}
    }
    @FXML
    void add(ActionEvent event) {

    }

    @FXML
    void msg(ActionEvent event) {
    	String msg = txtmsg.getText()+"\n"; // 보낼 메시지
    	send( msg ); // 메시지 보내기 
    	txtmsg.setText(""); 	// 보내기 후 메시지입력창 지우기
    	txtmsg.requestFocus();	// 보내기 후 메시지입력창으로 포커스(커서) 이동
    }
    @FXML
    void connect(ActionEvent event) {
    	if( btnconnect.getText().equals("채팅방 입장") ) {// 만약에 버튼의 텍스트가 "채팅방 입장" 이면 
    		
    		clientstart(); // 클라이언트 실행 메소드 
    		
    		txtcontent.appendText("---[채팅방 입장]---\n");
    		btnconnect.setText("채팅방 나가기");
    		
    		txtmsg.setText("");				// 채팅입력창 빈칸으로 설정
    		txtmsg.setDisable(false); 		// 채팅입력창 사용가능 
        	txtcontent.setDisable(false); 	// 채팅창 목록 사용가능
        	btnsend.setDisable(false); 		// 전송버튼 사용가능
        	txtmsg.requestFocus();  		// 채팅입력창으로 포커스[커서] 이동
    		
    	}else {
    		clientstop(); // 클라이언트 종료 메소드 
    		
    		txtcontent.appendText("---[채팅방 퇴장]---\n");
    		btnconnect.setText("채팅방 입장");
    		
    		txtmsg.setText("채팅방 입장후 사용가능");
        	txtmsg.setDisable(true); 		// 채팅입력창 사용금지 
        	txtcontent.setDisable(true); 	// 채팅창 목록 사용금지
        	btnsend.setDisable(true); 		// 전송버튼 사용금지
    	}
    }
    @FXML
    void send(ActionEvent event) { // 전송 버튼을 눌렀을때
    	String msg = txtmsg.getText()+"\n"; // 보낼 메시지
    	send( msg ); // 메시지 보내기 
    	txtmsg.setText(""); 	// 보내기 후 메시지입력창 지우기
    	txtmsg.requestFocus();	// 보내기 후 메시지입력창으로 포커스(커서) 이동
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	// 채팅fxml 열렸을때 초기값 메소드 
    		// * 채팅방 입장전에 아래 fxid를 사용못하게 금지 
    	txtmsg.setText("채팅방 입장후 사용가능");
    	txtmsg.setDisable(true); 		// 채팅입력창 사용금지 
    	txtcontent.setDisable(true); 	// 채팅창 목록 사용금지
    	btnsend.setDisable(true); 		// 전송버튼 사용금지
    }
	
}