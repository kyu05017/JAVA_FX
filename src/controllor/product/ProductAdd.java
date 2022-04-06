package controllor.product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import controllor.home.Home;
import controllor.login.Login;
import dao.ProductDao;
import dto.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ProductAdd implements Initializable{
	
	private String pimage = null;
	
	@FXML
    private Button btback;

    @FXML
    private TextField txtpname;

    @FXML
    private TextArea txtpcontents;

    @FXML
    private TextField txtpprise;

    @FXML
    private Button btimg;

    @FXML
    private RadioButton opt1;

    @FXML
    private ToggleGroup category;

    @FXML
    private RadioButton opt4;

    @FXML
    private RadioButton opt2;

    @FXML
    private RadioButton opt3;

    @FXML
    private ImageView img;

    @FXML
    private Button btaddp;

    @FXML
    private Label txtpath;

    @FXML
    void accadd(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	try {
	    	// 1. ��Ʈ�ѿ� �Էµ� ������ �ޱ�
	    	String name = txtpname.getText();
	    	String prise = txtpprise.getText();
	    	int new_prise = Integer.parseInt(prise);
	    	String contents = txtpcontents.getText();
	    	
	    	// * ī�װ� 
	    	String category = "";
	    	if(opt1.isSelected()) {
	    		category = "�����Ƿ�";
	    	}
	    	else if(opt2.isSelected()){
	    		category = "�����Ƿ�";
	    	}
	    	else if(opt3.isSelected()){
	    		category = "������ǰ";
	    	}
	    	else if(opt4.isSelected()){
	    		category = "��Ȱ��ǰ";
	    	}
	    	int m_num = Login.member.getM_num();
	    	// 2. ��ȿ�� �˻�
	    	if(!(opt1.isSelected() || opt2.isSelected() || opt3.isSelected() || opt4.isSelected()) ) {
	    		alert.setHeaderText("ī�װ��� �����ϼ���");
	    		alert.showAndWait();
	    		return;
	    	}
	    	
	    	if(txtpname.getText().equals("")) {
	    		alert.setHeaderText("������ �Է��� �ּ���.");
	    		alert.showAndWait();
	    		return;
	    	}
	    	else if(txtpcontents.getText().equals("")) {
	    		alert.setHeaderText("������ �Է��� �ּ���.");
	    		alert.showAndWait();
	    		return;
	    	}
	    	else {
	    		// 3. ��üȭ
	        	Product product = new Product(0, name, pimage, contents, category, new_prise, 1, null, m_num);
	        	
	        	// 4. DBó��
	        	boolean result = ProductDao.dao.addproduct(product);
	        	// 5. �������
	        	if(result) {
	        		alert.setHeaderText("��ǰ�� ��� �Ǿ����ϴ�.");
	        		alert.showAndWait();
	        		Home.home.loadpage("/view/product/product");
	        	}
	    	}
    	}
    	catch(NullPointerException e) {
    		alert.setHeaderText("������ �Է��ϼ���");
    		System.out.println(e);
    		alert.showAndWait();
    	}
    	catch(NumberFormatException e) {
    		alert.setHeaderText("���� �Է��� �߸��Ǿ����ϴ�.");
    		System.out.println(e);
    		alert.showAndWait();
    	}
    }

    @FXML
    void accback(ActionEvent event) {
    	Home.home.loadpage("/view/product/product");
    }	
    
    
    
    @FXML
    void imgadd(ActionEvent event) { // �̹������ ��ư Ŭ��������
    	// 1. ���� ���� Ŭ���� 
    	FileChooser fileChooser = new FileChooser();
    	// 2. *���� ����[����] ���� 
    		fileChooser.getExtensionFilters().add( 
    				new ExtensionFilter("�̹�������:image file" , "*png" , "*jpeg" , "*jpg","*gif") );
    	// 3. ���ο� stage���� ���ϼ��� ȭ�� ���� 
    	File file = fileChooser.showOpenDialog( new Stage() );
    		// ���ϼ��ð�ü.showOpenDialog( �������� ) ;
    		// fileChooser ȭ�鿡�� ���õ� ������ file Ŭ������üȭ
    	// 4. ������ ������ ��� ǥ��
    	txtpath.setText("���� ��� : " + file.getPath() ); // ��� ���м� : \
    	// 5. ���ϰ�� 
    	pimage = file.toURI().toString(); // ��� ���м� : /
    	// 6. �̸����� �̹�����Ʈ�ѿ� ����
    	Image image = new Image( pimage ); // �ش� �̹����� ��� ���� ��  / ���� �Ǿ� �־���� 
    	img.setImage(image); // ImageView �� �ش� �̹��� �־��ֱ�
    	
    	// * ������ ������ ���� ������Ʈ ������ ����(�̵�) �ؿ���
    	try {
    	// 1. ���� �Է� ��Ʈ��[ �̵� ���� : ����Ʈ ]
    		FileInputStream inputStream = new FileInputStream(file); // file : fileChooser���� ���õ� ���� ��ü
    	// 2. ���� ��� ��Ʈ�� // C:\Users\504\git\JAVA_FX\src\img
    		File copyfile = new File("C:/Users/504/git/JAVA_FX/src/img/" +file.getName() );
    		FileOutputStream outputStream = new FileOutputStream( copyfile );
    	// 3. ����Ʈ �迭 ���� 
    		byte[] bytes = new byte[1024*1024*1024]; // 1����Ʈ -> 1024����Ʈ -> 1Ű�ι���Ʈ -> 1024Ű�ι���Ʈ-> 1�ް�����Ʈ
    	// 4. �ݺ����� �̿��� inputStream�� ���� ��Ʈ�� ��� �о���� 
    		int size;
    		while(  ( size = inputStream.read( bytes ) ) > 0 ) { // �о�� ����Ʈ�� 0���� ������ �ݺ��� ���� [ �о�� ����Ʈ�� ���������� �ݺ� ]
    			outputStream.write(bytes , 0 , size ); // �о�� ����Ʈ��ŭ ��������
    		}
    	// 5. �뷮 ū ��쿡�� ��Ʈ�� ���� �ʼ�!!!
    		inputStream.close();
    		outputStream.close();
    	// * ����� ������ ��θ� db ����
    		pimage = copyfile.toURI().toString();
    	}catch (Exception e) {System.out.println(e);}
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	
    }
}
