package controllor;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import controllor.login.Login;
import dao.BoardDao;
import dao.RoomDao;
import dto.Room;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class Chatting implements Initializable {

    @FXML
    private TableView<Room> roomtable;

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
    public Server server;
    @FXML
    void add(ActionEvent event) { // 방개설 버튼을 눌렀을때
    	Alert alert = new Alert(AlertType.INFORMATION);
    	// 1. 컨트롤에 입력된 방 이름 가져오기
    	String roomname = txtroomname.getText();
    	if(roomname.length() < 4) {
    		alert.setHeaderText("채팅방 이름이 4글자 이상이여야 합니다.");
    		alert.showAndWait();
    		return;
    	}
    	else {
    		// 2. 방 객체
        	Room room = new Room(0, roomname, "127.0.0.1",0);
        	// 3. db 철이
        	boolean result =  RoomDao.dao.roomadd(room);
        	// 4. 해당 방 서버 실행
        	server = new Server();
        	// 5. 서버실행 [ 인수 아이피 포트번호 넘기기 ]
        	server.serverstart(room.getRo_ip(),RoomDao.dao.getRoomNum());
        	if(result) {
        		alert.setHeaderText("채팅방이 개설 되었습니다.");
        		alert.showAndWait();
        		txtroomname.setText("");
        		show();
        	}
    	}
    	
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
    	btnconnect.setDisable(true);
    	txtmidlist.setDisable(true);
    	show();
    }
	public Room selectRoom;
    public void show() {
    	ObservableList<Room> roomlist = RoomDao.dao.room_list();
    	
    	TableColumn<?, ?> tc = roomtable.getColumns().get(0); // 첫번째 열 호출
		tc.setCellValueFactory(new PropertyValueFactory<>("ro_num"));
		
		tc = roomtable.getColumns().get(1); // 두번째 열 호출
		tc.setCellValueFactory(new PropertyValueFactory<>("ro_name"));
		
		tc = roomtable.getColumns().get(2); // 세번째 열 호출
		tc.setCellValueFactory(new PropertyValueFactory<>("m_count"));
		
		roomtable.setItems(roomlist);
		
		roomtable.setOnMouseClicked( e -> {
			try {
				btnconnect.setDisable(false);
				selectRoom = roomtable.getSelectionModel().getSelectedItem();
				lblselect.setText("현재 선택된 채팅방 : " + selectRoom.getRo_name());
			}
			catch (Exception e2) {
				System.out.println("채팅방이 존재하지 않습니다. " + e2);
			}
		});
    }
}