package controllor.login;

import java.net.URL;
import java.util.ResourceBundle;

import dao.MemberDao;
import dto.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;

public class FindPw implements Initializable{

	@FXML
    private TextField textemail;

    @FXML
    private Button btback;

    @FXML
    private Button btfindpw;

    @FXML
    private TextField textid;
    
    @FXML
    private Label lbfindpw;

    @FXML
    void back(ActionEvent event) {
    	Login.login.loadpage("/view/login/loginpane");
    	System.out.println("�ڷΰ���");
    }

    @FXML
    void findpw(ActionEvent event) {
    	
    	System.out.println("��й�ȣ ã��");
    	String email = textemail.getText();
    	String id = textid.getText();

    	String result = MemberDao.dao.findpw(id,email);
    	
    	if(result == null) {
    		// ������ ��ȯ [ ������ ]
    		// * �׽�Ʈ
    		lbfindpw.setText("�������� �ʴ� ȸ�� �Դϴ�.");
    	}
    	else {
    		
    		Member.sendmail(email,result);
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("��й�ȣ ã��");
    		alert.setHeaderText("�̸��Ϸ� ��й�ȣ�� �����߽��ϴ�.");
    		alert.setContentText("�Ϸ�");
    		alert.showAndWait();
    		
    		
    		lbfindpw.setText(result);
    	}
    
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	lbfindpw.setText("");
    	
    }
	    
	    
}
