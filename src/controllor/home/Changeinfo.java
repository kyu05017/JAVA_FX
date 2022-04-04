package controllor.home;

import java.net.URL;
import java.util.ResourceBundle;

import controllor.Main;
import controllor.login.Login;
import dao.MemberDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Changeinfo implements Initializable{
	 @FXML
    private TextField txtadd;

    @FXML
    private TextField txtemail;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtemail.setText(Login.member.getM_email());
		txtadd.setText(Login.member.getM_address());
		
	}
	@FXML
	private Button btok;

 
	@FXML
    void accchange(ActionEvent event) {
    	System.out.println("��������");
    	// 1. ��Ʈ�ѿ� �Էµ� �� ��������
    	String add = txtadd.getText(); // �ش� fxid�� �Էµ� �� ��������
    	String email = txtemail.getText(); // �ش� fxid�� �Էµ� �� ��������
    	
   	
    	// 2. db ��ü�� �޼ҵ� ȣ��
    	boolean result = MemberDao.dao.changeInfo(Login.member.getM_num(),add, email);
    	
    	if(result) {
    		// �޼��� ���
    		System.out.println("�����Ϸ�");
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("ȸ������ ����");
    		alert.setHeaderText("�̸��ϰ� �ּҰ� �����Ǿ����ϴ�. [ �ٽ� �α��� �ϼ���. ]");
    		alert.setContentText("�Ϸ�");
    		alert.showAndWait();
    		Login.member = null;
    		Main.main.loadpage("/view/login/login");
    		System.out.println("�α׾ƿ�");
    	}
    	else {
    		System.out.println("��������");
    		
    	}

    }
    
}
