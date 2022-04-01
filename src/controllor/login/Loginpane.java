package controllor.login;


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
    	Login.login.loadpage("/view/login/findid"); 
    }

    @FXML
    void accfindpw(ActionEvent event) {
    	System.out.println("��й�ȣ ã��");
    	Login.login.loadpage("/view/login/findpw");
    }

    @FXML
    void accsignup(ActionEvent event) {
    	// login ��Ʈ�ѿ� �����ϴ� boarderpane ��ü�� ���� ����
    		// * ���� : ������ ��ü�� �ٸ����� �ִ�
    		// * new �� ���ο� �޸� �Ҵ� [ ���� ������ �ƴ� ���ο� ���� ] 
    		// * ������ login Ŭ�������� ������� boaderpane ���
    	
    	System.out.println("ȸ������");
    	Login.login.loadpage("/view/login/signuppane");
    }

    @FXML
    void login(ActionEvent event) {
    	System.out.println("�α���");
    	String id = textid.getText(); // �ش� fxid�� �Էµ� �� ��������
    	String pw = textpw.getText(); // �ش� fxid�� �Էµ� �� ��������
    	
    	if(id.equals("admin") && pw.equals("1234")) {
    		System.out.println("������");
    		lbnconform.setText("������ ����");
    	}
    	else if(id.equals("1234") && pw.equals("1234")){
    		System.out.println("�Ϲ�ȸ��");
    		lbnconform.setText("�Ϲ�ȸ�� ����");
    	}
    	System.out.println( textid.getText() + "���̵� �ν�");
    	System.out.println( textpw.getText() + "��й�ȣ �ν�");
    	
    }
    
   
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lbnconform.setText("");
		
	}
	
	
}
