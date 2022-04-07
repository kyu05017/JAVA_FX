package controllor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    public static Vector<Client> clientlist = new Vector<>();
    // * ��Ƽ�����带 �������ִ� ������Ǯ
    	// ExecutorService : ������Ǯ ���� ���ִ� �������̽� [ �������̽��� [ ���� Ŭ���� ( implements  ) VS �������� ]
    public static ExecutorService threadpool;
    
    // 1. ���� ���� �ǵѱ�
    ServerSocket serverSocket;
    // 2. ���� ���� �޼ҵ� �ǵѱ�
    public void serverstart() {
    	try {
    		// 1. ���� ���� �޸� �Ҵ�
			serverSocket = new ServerSocket();
			// 2. ���� ���� ���ε� [ ip �� port ���� ]
			serverSocket.bind(new InetSocketAddress("127.0.0.1",1234));
		} catch (IOException e) {
			// 3. Ŭ���̾�Ʈ�� ��û ��� [ ��Ƽ ������ ] : Ŭ���̾�Ʈ 1. ����, 2. ������, 3. �ޱ� [ ����ó�� ]
			Runnable runnable = new Runnable() {
				
				@Override
				public void run() {
					try {
						while (true) {
							Socket socket = serverSocket.accept();  // 1. ��û ������ ������ ������ ��ȯ
							clientlist.add( new Client(socket));	// 2. ����� Ŭ���̾�Ʈ ( ����� ���� )  �����Ŀ� ����Ʈ�� ����
						}
					}
					catch(Exception e) {
						System.out.println("���� ���� ���� " + e);
					}
				}
				
			};// ���ʺ� ��
			// ������ Ǯ�� �޸� �ʱ�ȭ
			threadpool = Executors.newCachedThreadPool();
			threadpool.submit(runnable);
		}
    	
    }
    // 3. ���� ���� �޼ҵ� �ǵѱ�
    public void serverstop() {
    	try {
	    	// 1. ���ӵ� ��� Ŭ���̾�Ʈ�� ������ ����
	    	for(Client temp : clientlist) {
	    		temp.socket.close();
	    	}
	    	serverSocket.close();
	    	threadpool.shutdown();
    	}
    	catch(Exception e) {
    		System.out.println("�����ݱ� ���� " + e);
    	}
    }
    
    @FXML
    void server(ActionEvent event) { // ���� ���� ��ư
    	if(btserver.getText().equals("���� ����")) {
    		txtserver.appendText("\t\t [[ ������ ���� �մϴ�. ]] \n");
    		serverstart();
    		btserver.setText("���� ����");
    	}
    	else {
    		txtserver.appendText("\t\t [[ ������ ���� �մϴ�. ]] \n");
    		serverstop();
    		btserver.setText("���� ����");
    	}
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtserver.setDisable(true);
		
	}
}
