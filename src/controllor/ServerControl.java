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
    
    // * ������ ����� Ŭ���̾�Ʈ�� �����ϴ� ����Ʈ
    public static Vector<Client> client = new Vector<>();
    // * ��Ƽ�����带 �������ִ� ������Ǯ
    
    // 1. ���� ���� �ǵѱ�
    ServerSocket serverSocket;
    // 2. ���� ���� �޼ҵ� �ǵѱ�
    public void serverstart() {
    	
    }
    // 3. ���� ���� �޼ҵ� �ǵѱ�
    public void serverstop() {
	
    }
    
    @FXML
    void server(ActionEvent event) { // ���� ���� ��ư
    	if(btserver.getText().equals("���� ����")) {
    		txtserver.appendText("\t\t [[ ������ ���� �մϴ�. ]] \n");
    		btserver.setText("���� ����");
    	}
    	else {
    		txtserver.appendText("\t\t [[ ������ ���� �մϴ�. ]] \n");
    		btserver.setText("���� ����");
    	}
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtserver.setDisable(true);
		
	}
}
