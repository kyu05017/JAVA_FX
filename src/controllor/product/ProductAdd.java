package controllor.product;

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
import javafx.scene.image.ImageView;

public class ProductAdd implements Initializable{
	
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
    	
    	// 3. ��üȭ
    	Product product = new Product(0, name, null, contents, category, new_prise, 1, null, m_num);
    	
    	// 4. DBó��
    	boolean result = ProductDao.dao.addproduct(product);
    	// 5. �������
    	if(result) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("��ǰ�� ��� �Ǿ����ϴ�.");
    		alert.showAndWait();
    		Home.home.loadpage("/view/product/product");
    	}
    }

    @FXML
    void accback(ActionEvent event) {
    	Home.home.loadpage("/view/product/product");
    }	

    @FXML
    void imgadd(ActionEvent event) {

    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	
    }
}
