package controllor;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import controllor.login.Login;
import dao.BoardDao;
import dao.RoomDao;
import dto.Room;
import dto.RoomLive;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class Chatting implements Initializable {

    @FXML
    private TableView<Room> roomtable;

    @FXML
    private TextField txtroomname;

    @FXML
    private Button btnadd;

    @FXML
    private Label lblselect;

    @FXML
    private TextArea txtmidlist;

    @FXML
    private Button btnconnect;

    @FXML
    private TextArea txtcontent;

    @FXML
    private TextField txtmsg;

    @FXML
    private Button btnsend;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtip;

    @FXML
    private TextField txtport;
    
    Socket socket;   // 1. Ŭ���̾�Ʈ ���� ���� 
    public static Room selectRoom;
    
    public void show() {
    	
    	
    	ObservableList<Room> roomlist = RoomDao.dao.room_list();
    	
    	
    	TableColumn<?, ?> tc = roomtable.getColumns().get(0); // ù��° �� ȣ��
		tc.setCellValueFactory(new PropertyValueFactory<>("ro_num"));
		
		tc = roomtable.getColumns().get(1); // �ι�° �� ȣ��
		tc.setCellValueFactory(new PropertyValueFactory<>("ro_name"));
		
		tc = roomtable.getColumns().get(2); // ����° �� ȣ��
		tc.setCellValueFactory(new PropertyValueFactory<>("m_count"));
		
		roomtable.setItems(roomlist);
		
		roomtable.setOnMouseClicked( e -> {
			try {
				btnconnect.setDisable(false);
				selectRoom = roomtable.getSelectionModel().getSelectedItem();
				lblselect.setText("���� ���õ� ä�ù� : " + selectRoom.getRo_name());
				midshow();
			}
			catch (Exception e2) {
				System.out.println("ä�ù��� �������� �ʽ��ϴ�. " + e2);
			}
		});
		for(Room temp : roomlist) {
    		System.out.println(temp.getRo_name() +" "+ temp.getM_count());
    	}
    }
    // 2. Ŭ���̾�Ʈ ���� �޼ҵ�
    public void clientstart(String ip,int port) {
    	Thread thread = new Thread() { // ��Ƽ������ 
    		@Override
    		public void run() {
    			try {
    				socket = new Socket(ip,port); // ������ ip�� ��Ʈ��ȣ �־��ֱ� [ ������ ���� ]
    				send(Login.member.getM_id()+"�� �����߽��ϴ�\n"); // ���Ӱ� ���ÿ� ����޽��� ������ 
    				receive(); // ���Ӱ� ���ÿ� �ޱ� �޼ҵ�� ���ѷ���
    			}catch(Exception e ) {
    				System.out.println("Ŭ���̾�Ʈ ���� ����" + e);
    			}
    		};
    	};// ��Ƽ������ ���� ��
    	thread.start(); // ��Ƽ������ ����
    }
    // 3. Ŭ���̾�Ʈ ���� �޼ҵ� 
    public void clientstop() {  try{ socket.close(); }catch(Exception e ) {System.out.println("Ŭ���̾�Ʈ ���� ����" + e);} }
    
    // 4. �������� �޽��� ������ �޼ҵ� 
    public void send( String msg ) {
    	Thread thread = new Thread() { 
    		String new_msg = Login.member.getM_id() + " : " + msg;
    		@Override
    		public void run() {
    			try {
    				OutputStream outputStream = socket.getOutputStream(); // 1. ��� ��Ʈ��
    				outputStream.write(new_msg.getBytes() ); // 2. ��������
    				outputStream.flush(); // 3. ��Ʈ�� �ʱ�ȭ [ ��Ʈ�� �� ����Ʈ ����� ]
    			}catch( Exception e ) {
    				System.out.println("�޼��� �ޱ� ���� " + e);
    			} 
    		}
    	};// ��Ƽ������ ���� �� 
    	thread.start();
    }
    // 5. �������� �޽��� �ޱ� �޼ҵ� 
    public void receive() {
    	try {
	    	while(true) {
	    		InputStream inputStream = socket.getInputStream(); // 1. �Է� ��Ʈ��
	    		byte[] bytes = new byte[1000]; 	// 2. ����Ʈ�迭 ���� 
	    		inputStream.read(bytes);		// 3. �о���� 
	    		String msg = new String(bytes);	// 4. ����Ʈ�� -> ���ڿ� ��ȯ
	    		txtcontent.appendText(msg); 	// 4. ���� ���ڿ��� �޽���â�� ���� 
	    	}
    	}catch( Exception e ) {
    		System.out.println("ä�� �ޱ� ���� " + e);
    	}
    }
    public Server server;
    @FXML
    void add(ActionEvent event) { // �氳�� ��ư�� ��������
    	Alert alert = new Alert(AlertType.INFORMATION);
    	// 1. ��Ʈ�ѿ� �Էµ� �� �̸� ��������
    	String roomname = txtroomname.getText();
    	if(roomname.length() < 4) {
    		alert.setHeaderText("ä�ù� �̸��� 4���� �̻��̿��� �մϴ�.");
    		alert.showAndWait();
    		return;
    	}
    	else {
    		// 2. �� ��ü
        	Room room = new Room(0, roomname, "127.0.0.1",0);
        	// 3. db ö��
        	boolean result =  RoomDao.dao.roomadd(room);
        	// 4. �ش� �� ���� ����
        	server = new Server();
        	// 5. �������� [ �μ� ������ ��Ʈ��ȣ �ѱ�� ]
        	server.serverstart(room.getRo_ip(),RoomDao.dao.getRoomNum());
        	if(result) {
        		alert.setHeaderText("ä�ù��� ���� �Ǿ����ϴ�.");
        		alert.showAndWait();
        		txtroomname.setText("");
        		
        	}
    	}
    	
    }
    public void midshow() {
    	
    	ArrayList<RoomLive> roomlivelist 
			= RoomDao.dao.getlivelist( selectRoom.getRo_num() );
		txtmidlist.setText("");
		int i = 1; 
		for( RoomLive temp : roomlivelist ) {
			txtmidlist.appendText( i +"�� "+ temp.getM_id() +"\n");
			i++;
		}
    }
    @FXML
    void msg(ActionEvent event) {
    	String msg = txtmsg.getText()+"\n"; // ���� �޽���
    	send( msg ); // �޽��� ������ 
    	txtmsg.setText(""); 	// ������ �� �޽����Է�â �����
    	txtmsg.requestFocus();	// ������ �� �޽����Է�â���� ��Ŀ��(Ŀ��) �̵�
    }
    @FXML
    void send(ActionEvent event) { // ���� ��ư�� ��������
    	String msg = txtmsg.getText()+"\n"; // ���� �޽���
    	send( msg ); // �޽��� ������ 
    	txtmsg.setText(""); 	// ������ �� �޽����Է�â �����
    	txtmsg.requestFocus();	// ������ �� �޽����Է�â���� ��Ŀ��(Ŀ��) �̵�
    }
    @FXML
    void connect(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	if( btnconnect.getText().equals("ä�ù� ����") ) {// ���࿡ ��ư�� �ؽ�Ʈ�� "ä�ù� ����" �̸� 
    		
    		clientstart(selectRoom.getRo_ip(),selectRoom.getRo_num()); // Ŭ���̾�Ʈ ���� �޼ҵ� 
    			// ���� �� ���� ��� �߰�
    		RoomLive live = new RoomLive(0, selectRoom.getRo_num(), Login.member.getM_id());
    		
    		RoomDao.dao.roomlive_add(live);
    		txtcontent.appendText("---[ä�ù� ����]---\n");
    		btnconnect.setText("ä�ù� ������");
    		
    		txtmsg.setText("");				// ä���Է�â ��ĭ���� ����
    		txtmsg.setDisable(false); 		// ä���Է�â ��밡�� 
        	txtcontent.setDisable(false); 	// ä��â ��� ��밡��
        	btnsend.setDisable(false); 		// ���۹�ư ��밡��
        	txtmsg.requestFocus();  		// ä���Է�â���� ��Ŀ��[Ŀ��] �̵�
        	
        	txtroomname.setDisable(true); // ä�ù� �̸� �Է±���
        	btnadd.setDisable(true);	// ä�ù� ���� ����
        	roomtable.setDisable(true);	// ä�ù� ��� ������
        	
    	}else {
    		clientstop(); // Ŭ���̾�Ʈ ���� �޼ҵ� 
    		
    		txtcontent.appendText("---[ä�ù� ����]---\n");
    		btnconnect.setText("ä�ù� ����");
    		
    		txtmsg.setText("ä�ù� ������ ��밡��");
        	txtmsg.setDisable(true); 		// ä���Է�â ������ 
        	txtcontent.setDisable(true); 	// ä��â ��� ������
        	btnsend.setDisable(true); 		// ���۹�ư ������
        	
        	txtroomname.setDisable(false); // ä�ù� �̸� �Է±���
        	btnadd.setDisable(false);	// ä�ù� ���� ����
        	roomtable.setDisable(false);	// ä�ù� ��� ������
        	txtmidlist.setText("");
        	
        	// 1. �� ���� ��ܿ��� ���� [ ���� ] �ϱ�
        	boolean result = RoomDao.dao.deletelist(Login.member.getM_id());
        	
        	if(result) {
        		alert.setHeaderText("ä�ù��� �����̽��ϴ�.");
        		alert.showAndWait();
        	}
        	// 2. ���࿡ �� ���� ����� 0 ���̸� �����
        	boolean result2 = RoomDao.dao.deleteroom(selectRoom.getRo_num());
        	if(result2) {
        		server.serverstop();
        		System.out.println("ä�ù� ������");
        		
        	}
        	else {
        		System.out.println("ä���� �����ȵ�");
        	}
        	// 3. ���࿡ ���� ������ �������� ����
        	// * ���̺�信�� ���õ� �� �ʱ�ȭ
        	selectRoom = null;
        	// * ���õ� �� ���̺� �ʱ�ȭ
        	lblselect.setText("���� ���õ� ä�ù� : ");
    	}
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	// ä��fxml �������� �ʱⰪ �޼ҵ� 
    		// * ä�ù� �������� �Ʒ� fxid�� �����ϰ� ���� 
    	txtmsg.setText("ä�ù� ������ ��밡��");
    	txtmsg.setDisable(true); 		// ä���Է�â ������ 
    	txtcontent.setDisable(true); 	// ä��â ��� ������
    	btnsend.setDisable(true); 		// ���۹�ư ������
    	btnconnect.setDisable(true);
    	txtmidlist.setDisable(true);
    	Thread thread = new Thread() {
    		@Override
    		public void run() {
    			try {
    				while(true) {
    					show();
    					Thread.sleep(500);
    				}
    				
    			}
    			catch (Exception e) {}
    		}
    	};
    	thread.start();
    	Thread thread2 = new Thread() {
    		@Override
    		public void run() {
    			try {
    				while(true) {
    					midshow();
    					Thread.sleep(500);
    				}
    				
    			}
    			catch (Exception e) {}
    		}
    	};
    	thread2.start();
    }
	
}