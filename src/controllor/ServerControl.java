package controllor;

import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

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
    public static Vector<Client> client = new Vector<>();
    // * 멀티스레드를 관리해주는 스레드풀
    
    // 1. 서버 소켓 맨둘기
    ServerSocket serverSocket;
    // 2. 서버 실행 메소드 맨둘기
    public void serverstart() {
    	
    }
    // 3. 서버 종료 메소드 맨둘기
    public void serverstop() {
	
    }
    
    @FXML
    void server(ActionEvent event) { // 서버 실행 버튼
    	if(btserver.getText().equals("서버 실행")) {
    		txtserver.appendText("\t\t [[ 서버를 실행 합니다. ]] \n");
    		btserver.setText("서버 중지");
    	}
    	else {
    		txtserver.appendText("\t\t [[ 서버를 중지 합니다. ]] \n");
    		btserver.setText("서버 실행");
    	}
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtserver.setDisable(true);
		
	}
}
