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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Chatting implements Initializable{
	@FXML
    private TextArea txtcontents;

    @FXML
    private Button btcon;

    @FXML
    private TextField txt;

    @FXML
    private Button btsend;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtipadd;

    @FXML
    private TextField txtport;
    
    Socket socket;
    
    
    public void clientstart() {
    	Thread thread = new Thread() { // ��Ƽ������ 
    		@Override
    		public void run() {
    			try {
    				socket = new Socket("127.0.0.1",1234); // ������ ip�� ��Ʈ��ȣ �־��ֱ� [ ������ ���� ]
    				send( Login.member.getM_id()+"�� �����߽��ϴ�\n"); // ���Ӱ� ���ÿ� ����޽��� ������ 
    				receive(); // ���Ӱ� ���ÿ� �ޱ� �޼ҵ�� ���ѷ���
    			}catch(Exception e ) {}
    		};
    	};// ��Ƽ������ ���� ��
    	thread.start(); // ��Ƽ������ ����
    }
	public void clientstop() {try {socket.close();}catch (Exception e) {System.out.println("Ŭ���̾�Ʈ �ݱ� ���� " + e);}}
	public void send(String msg) {
		
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
				OutputStream outputStream = socket.getOutputStream(); // 1. ��� ��Ʈ��
				outputStream.write(msg.getBytes()); // 2. ��������
				outputStream.flush(); // 3. ��Ʈ�� �ʱ�ȭ
				}
				catch (Exception e) {
					System.out.println("Ŭ���̾� ������ ���� " + e);
				}
			}
		};
		thread.start();
	}
	public void receive() {
		try {
			while (true) {
				InputStream inputStream = socket.getInputStream();
				byte[] bytes = new byte[1024];
				inputStream.read(bytes);
				String msg = new String(bytes);
				txtcontents.appendText(msg);
			}
		}
		catch (Exception e) {
			System.out.println("Ŭ���̾� �ޱ� ���� " + e);
		}
	}
    
    @FXML
    void acsend(ActionEvent event) { // ���� ��ư ����������
    	String msg = txt.getText() + "\n";
    	send(msg);
    	txt.setText("");
    	txt.requestFocus(); // �������� �޼����Է�â���� ��Ŀ�� ( Ŀ�� ) �̵�
    }

    @FXML
    void actcon(ActionEvent event) {
    	if(btcon.getText().equals("ä�ù� ����")){
    		clientstart();
    		txtcontents.appendText("---ä�ù� ���� ---\n");
    		btcon.setText("ä�ù� ������");
    		txt.setText("");
    		txt.setDisable(false);	// ä���Է�â ���
        	txtcontents.setDisable(false); // ä��â ���
        	btsend.setDisable(false); // ��Ư ��� ����
        	txt.requestFocus();
    	}
    	else {
    		clientstop();
    		txtcontents.appendText("---ä�ù� ���� ---\n");
    		btcon.setText("ä�ù� ����");
    		txt.setDisable(true);	// ä���Է�â ���
        	txtcontents.setDisable(true); // ä��â ���
        	btsend.setDisable(true); // ��Ư ��� ����
    	}
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	txt.setDisable(true);	// ä���Է�â ���
    	txtcontents.setDisable(true); // ä��â ���
    	btsend.setDisable(true); // ��Ư ��� ����
    	txt.setText("ä�ù� ������ ��� �����մϴ�.");
    }
}
