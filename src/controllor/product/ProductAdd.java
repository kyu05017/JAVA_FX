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
    	// 1. 컨트롤에 입력된 데이터 받기
    	String name = txtpname.getText();
    	String prise = txtpprise.getText();
    	int new_prise = Integer.parseInt(prise);
    	String contents = txtpcontents.getText();
    	
    	// * 카테고리 
    	String category = "";
    	if(opt1.isSelected()) {
    		category = "남성의류";
    	}
    	else if(opt2.isSelected()){
    		category = "여성의류";
    	}
    	else if(opt3.isSelected()){
    		category = "전자제품";
    	}
    	else if(opt4.isSelected()){
    		category = "생활용품";
    	}
    	int m_num = Login.member.getM_num();
    	// 2. 유효성 검사
    	
    	// 3. 객체화
    	Product product = new Product(0, name, null, contents, category, new_prise, 1, null, m_num);
    	
    	// 4. DB처리
    	boolean result = ProductDao.dao.addproduct(product);
    	// 5. 결과러리
    	if(result) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("제품이 등록 되었습니다.");
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
