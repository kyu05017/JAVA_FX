package controllor.login;


import java.net.URL;
import java.util.ResourceBundle;
import dao.MemberDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    	// 1. ��Ʈ�ѿ� �Էµ� �� ��������
    	String id = textid.getText(); // �ش� fxid�� �Էµ� �� ��������
    	String pw = textpw.getText(); // �ش� fxid�� �Էµ� �� ��������
    	
    	// 2. db ��ü�� �޼ҵ� ȣ��
    	boolean result = MemberDao.dao.login(id, pw);
    	
    	// 3. ���Ȯ��
    	if(result) {
    		// ������ ��ȯ [ ������ ]
    		// * �׽�Ʈ
    		lbnconform.setText("�α��μ���");
    	}
    	else {
    		lbnconform.setText("�α��ν���");
    	}
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lbnconform.setText("");
	}
	
	
}
