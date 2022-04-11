package controllor.login;


import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import dao.MemberDao;
import dto.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Signuppane implements Initializable{

	@FXML
    private TextField textid;

    @FXML
    private PasswordField textpw;

    @FXML
    private Button btback;

    @FXML
    private Button btsignup;

    @FXML
    private PasswordField textpwcheck;

    @FXML
    private TextField textaddress;

    @FXML
    private TextField textemail;

    @FXML
    private Label lbltext;

    @FXML
    void back(ActionEvent event) {
    	Login.login.loadpage("/view/login/loginpane");
    	System.out.println("�ڷΰ���");
    }

    @FXML
    void signup(ActionEvent event) {	
    	System.out.println("ȸ������");
    	// * ��Ʈ�ѿ� �Էµ� ������ �������� [ fxid��.gettext ] 
    	String id = textid.getText();
    	String pw = textpw.getText();
    	String pwcheck = textpwcheck.getText();
    	String address = textaddress.getText();
    	String email = textemail.getText();
    	
    	// ���� ��¥ ��������
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String since = sdf.format(new Date());
    	
    	// * ��ȿ�� �˻� [ ���� ]
    		// 1. ���̵� �ߺ�üũ
    	Boolean result2 = MemberDao.dao.idcheck(id);
    	if(result2) {
    		lbltext.setText("[�˸�] ������� ���̵� �Դϴ�.");
    		return;
    	}
    		// 2. ���̵� ����
    	if(id.length() < 4 || id.length() > 10) {
    		lbltext.setText("[�˸�] 4~10�ڸ��� �����մϴ�.");
    		return;
    	}
    		// 3. ��й�ȣ ���� üũ
    	if(pw.length() < 4 || pw.length() > 10 || pwcheck.length() < 4 || pwcheck.length() > 10) {
    		lbltext.setText("[�˸�] 4~10�ڸ��� �����մϴ�.");
    		return;
    	}
    		// 4. ��й�ȣ ��ġ
    	if(!pw.equals(pwcheck)) { // �н������ �н�����˻簡 ������ ������
    		lbltext.setText("[�˸�] ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
    		return;
    	}
    		// 5. �̸��� üũ
    	if(email.indexOf("@") == -1) {
    		lbltext.setText("[�˸�] �ùٸ��� �ʴ� �̸��� �����Դϴ�.");
    		return;
    	}
    		// 6. �ּ� üũ
    	if(!address.contains("��") && address.contains("��") &&address.contains("��")) {
    		lbltext.setText("[�˸�] �� , �� ,���� ���� �Ǿ��־�� �մϴ�.");
    		return;
    	}
    	// * ��� ��ȿ�� �˻縦 ����� DB�� ���� 
    		// 1. ��üȭ [ ȸ����ȣ ����, id, pw, �̸���, �ּ�, ����Ʈ, ������ ]
    	Member member = new Member(0, id, pw, email, address, 0, since,"yyyy-MM-dd");
    		// 2. DB ��ü�� �޼ҵ� ȣ��
    	boolean result = MemberDao.dao.signup(member);
    	
    	if(result) {
    		// 1. �޼��� â [ Alert : �޼��� Ŭ���� ]
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("ȸ������");
    		alert.setHeaderText("�䳢 �߰��� �����߽��ϴ�.");
    		alert.setContentText("�Ϸ�");
    		alert.showAndWait();
    		// 2. ȭ�� ��ȯ
    		
    		Login.login.loadpage("/view/login/loginpane");
    	}
    	else {
    		lbltext.setText("[�˸�] DB ���� ������ ����");
    		return;
    	}
    	
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lbltext.setText("");
	}
}
