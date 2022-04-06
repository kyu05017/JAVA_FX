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
    		alert.setHeaderText("제품 수정이 완료되었습니다.");
    		alert.showAndWait();
    		Home.home.loadpage("/view/product/product");
    	}
    }

    @FXML
    void accback(ActionEvent event) {
    	Home.home.loadpage("/view/product/productview");
    }

    @FXML
    void imgadd(ActionEvent event) { // 이미지등록 버튼 클릭했을때
    	// 1. 파일 선택 클래스 
    	FileChooser fileChooser = new FileChooser();
    	// 2. *파일 선택[필터] 형식 
    		fileChooser.getExtensionFilters().add( 
    				new ExtensionFilter("이미지파일:image file" , "*png" , "*jpeg" , "*jpg","*gif") );
    	// 3. 새로운 stage에서 파일선택 화면 실행 
    	File file = fileChooser.showOpenDialog( new Stage() );
    		// 파일선택객체.showOpenDialog( 스테이지 ) ;
    		// fileChooser 화면에서 선택된 파일을 file 클래스객체화
    	// 4. 선택한 파일의 경로 표시
    	txtpath.setText("파일 경로 : " + file.getPath() ); // 경로 구분선 : \
    	// 5. 파일경로 
    	pimage = file.toURI().toString(); // 경로 구분선 : /
    	// 6. 미리보기 이미지컨트롤에 띄우기
    	Image image = new Image( pimage ); // 해당 이미지의 경로 구분 이  / 구분 되어 있어야함 
    	img.setImage(image); // ImageView 에 해당 이미지 넣어주기
    	
    	// * 선택한 파일을 현재 프로젝트 폴더로 복사(이동) 해오기
    	try {
    	// 1. 파일 입력 스트림[ 이동 단위 : 바이트 ]
    		FileInputStream inputStream = new FileInputStream(file); // file : fileChooser에서 선택된 파일 객체
    	// 2. 파일 출력 스트림 // C:\Users\504\git\JAVA_FX\src\img
    		File copyfile = new File("C:/Users/504/git/JAVA_FX/src/img/" +file.getName() );
    		FileOutputStream outputStream = new FileOutputStream( copyfile );
    	// 3. 바이트 배열 선언 
    		byte[] bytes = new byte[1024*1024*1024]; // 1바이트 -> 1024바이트 -> 1키로바이트 -> 1024키로바이트-> 1메가바이트
    	// 4. 반복문을 이용한 inputStream의 파일 스트림 모두 읽어오기 
    		int size;
    		while(  ( size = inputStream.read( bytes ) ) > 0 ) { // 읽어온 바이트가 0보다 작으면 반복문 종료 [ 읽어올 바이트가 없을때까지 반복 ]
    			outputStream.write(bytes , 0 , size ); // 읽어온 바이트만큼 내보내기
    		}
    	// 5. 용량 큰 경우에는 스트림 종료 필수!!!
    		inputStream.close();
    		outputStream.close();
    	// * 복사된 파일의 경로를 db 저장
    		pimage = copyfile.toURI().toString();
    	}catch (Exception e) {System.out.println(e);}
    }
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		img.setImage(new Image(product.getP_img()) );
    	txtpname.setText(product.getP_name());
    	txtpcontents.setText(product.getP_contents());
    	DecimalFormat df2 = new DecimalFormat("#,##0원");
    	String aa = df2.format(product.getP_money());
    	txtpprise.setText(aa);
    	txtpath.setText("파일경로"+product.getP_img());
    	if(product.getP_category().equals("남성의류")){ opt1.setSelected(true);}
    	if(product.getP_category().equals("여성의류")){ opt2.setSelected(true);}
    	if(product.getP_category().equals("전자기기")){ opt3.setSelected(true);}
    	if(product.getP_category().equals("생활용품")){ opt4.setSelected(true);}
    	
	}
	
}
