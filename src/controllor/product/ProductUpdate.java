package controllor.product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import controllor.home.Home;
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
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class ProductUpdate implements Initializable{
	
	Product product = ProductControl.select;
	
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
    	
    	if(pimage == null) {pimage = product.getP_img();}
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
    	int aa = Integer.parseInt(txtpprise.getText());
    	Product update = new Product(
    			product.getP_num(),
    			txtpname.getText(),
    			pimage,
    			txtpcontents.getText(), 
    			category, 
    			aa, 
    			0, 
    			null, 
    			0);
    	boolean result = ProductDao.dao.updaete(update);
    	if(result) {
    		Alert alert = new Alert(AlertType.CONFIRMATION); 
    		alert.setHeaderText("��ǰ ������ �Ϸ�Ǿ����ϴ�.");
    		alert.showAndWait();
    		Home.home.loadpage("/view/product/product");
    	}
    }

    @FXML
    void accback(ActionEvent event) {
    	Home.home.loadpage("/view/product/productview");
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
		
		img.setImage(new Image(product.getP_img()) );
    	txtpname.setText(product.getP_name());
    	txtpcontents.setText(product.getP_contents());
    	DecimalFormat df2 = new DecimalFormat("#,##0��");
    	String aa = df2.format(product.getP_money());
    	txtpprise.setText(aa);
    	txtpath.setText("���ϰ��"+product.getP_img());
    	if(product.getP_category().equals("�����Ƿ�")){ opt1.setSelected(true);}
    	if(product.getP_category().equals("�����Ƿ�")){ opt2.setSelected(true);}
    	if(product.getP_category().equals("���ڱ��")){ opt3.setSelected(true);}
    	if(product.getP_category().equals("��Ȱ��ǰ")){ opt4.setSelected(true);}
    	
	}
	
}
