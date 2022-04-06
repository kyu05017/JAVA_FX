package controllor.product;

import java.io.File;
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
	    	if(txtpname.getText().equals("")) {
	    		alert.setHeaderText("제목을 입력해 주세요.");
	    		alert.showAndWait();
	    	}
	    	else if(txtpcontents.getText().equals("")) {
	    		alert.setHeaderText("내용을 입력해 주세요.");
	    		alert.showAndWait();
	    	}
	    	else {
	    		// 3. 객체화
	        	Product product = new Product(0, name, pimage, contents, category, new_prise, 1, null, m_num);
	        	
	        	// 4. DB처리
	        	boolean result = ProductDao.dao.addproduct(product);
	        	// 5. 결과러리
	        	if(result) {
	        		alert.setHeaderText("제품이 등록 되었습니다.");
	        		alert.showAndWait();
	        		Home.home.loadpage("/view/product/product");
	        	}
	    	}
    	}
    	catch(NullPointerException e) {
    		alert.setHeaderText("가격은 숫자만 입력 가능 합니다.");
    		alert.showAndWait();
    	}
//    	catch(Exception e) {
//    		alert.setHeaderText("관리자에게 문의 하세요");
//    		System.out.println(e);
//    		alert.showAndWait();
//    	}
    }

    @FXML
    void accback(ActionEvent event) {
    	Home.home.loadpage("/view/product/product");
    }	
    
    
    
    @FXML
    void imgadd(ActionEvent event) {// 이미지 등록 버튼을 클릭했을때
    	// 1. 파일 선택 클래스
    	FileChooser chooser = new FileChooser(); 
    	
    	// 2. 파일 선택 [ 형식 필터링 ]
    	chooser.getExtensionFilters().add(new ExtensionFilter("이미지파일 : image file", "*png","*jepg","*gif","*jpg"));
    	
    	// 3. 새로운 stage에서 파일 선택 화면 실행 
    	File file = chooser.showOpenDialog( new Stage() );
    		// 파일 선택 객체 .showOpenDialog( 스테이지 이름 );
    		// FileChooser 화면에서 선택된 파일을 file 클래스 객체화
    	
    	// 4. 선택한 파일의 경로
    	txtpath.setText("이미지 경로 : "+file.getPath());
    	
    	// 5. 파일경로 
    	pimage = file.toURI().toString();

    	
    	// 5. 이미지 미리보기 컨트롤에 이미지 띄우기
    	Image image = new Image(pimage); // 해당 이미지 경로 값이 / 로 구분 되어야 함
    	img.setImage(image);
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	
    }
}
