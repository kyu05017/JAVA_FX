package control;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class Loginpane implements Initializable{

	

    @FXML
    private TextField textid;

    @FXML
    private PasswordField textpw;

    @FXML
    private Button btlogin;

    @FXML
    private Button btsignup;

    @FXML
    private Button btfindpw;

    @FXML
    private Button btfindid;

    @FXML
    private Label lbnconform;

    @FXML
    void accfindip(ActionEvent event) {
    	System.out.println("���̵� ã��");
    	Login.login.loadpage("/view/findid");
    }

    @FXML
    void accfindpw(ActionEvent event) {
    	System.out.println("��й�ȣ ã��");
    	Login.login.loadpage("/view/findpw");
    }

    @FXML
    void accsignup(ActionEvent event) {
    	// login ��Ʈ�ѿ� �����ϴ� boarderpane ��ü�� ���� ����
    		// * ���� : ������ ��ü�� �ٸ����� �ִ�
    		// * new �� ���ο� �޸� �Ҵ� [ ���� ������ �ƴ� ���ο� ���� ] 
    		// * ������ login Ŭ�������� ������� boaderpane ���
    	
    	System.out.println("ȸ������");
    	Login.login.loadpage("/view/signuppane");
    }

    @FXML
    void login(ActionEvent event) {
    	System.out.println("�α���");
    	
    }
    
   
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
}
