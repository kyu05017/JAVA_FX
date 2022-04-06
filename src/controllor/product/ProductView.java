package controllor.product;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.ResourceBundle;

import controllor.home.Home;
import controllor.login.Login;
import dao.MemberDao;
import dao.ProductDao;
import dto.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ProductView implements Initializable{
	
	Product product = ProductControl.select;
	
	@FXML
    private Button btback;

    @FXML
    private TextField txtpname;

    @FXML
    private TextArea txtpcontents;

    @FXML
    private ImageView img;

    @FXML
    private Button btdelete;

    @FXML
    private Button btupdate;

    @FXML
    private Label txtcondition;

    @FXML
    private Label txtdate;

    @FXML
    private Label txtmid;

    @FXML
    private Label txtprise;
    
    @FXML
    private TextArea txtrefiled;

    @FXML
    private Button btredel;

    @FXML
    private Button btreadd;

    @FXML
    private Button btreupdate;
    
    @FXML
    void accreadd(ActionEvent event) {

    }

    @FXML
    void accredel(ActionEvent event) {

    }

    @FXML
    void accreupdate(ActionEvent event) {
    	Home.home.loadpage("/view/product/productupdate");
    }
    
    @FXML
    void accback(ActionEvent event) {
    	Home.home.loadpage("/view/product/product");
    }

    @FXML
    void accdelete(ActionEvent event) {
    	// 1. 메세지 설정
		Alert alert = new Alert(AlertType.CONFIRMATION); // 확인,취소 버튼 타입
		alert.setHeaderText("정말 삭제 하시겠습니까?");
		// 2. 버튼 확인 [ Optional 클래스 ]
		Optional<ButtonType> optional = alert.showAndWait();
		//Optional 클래스 == null값을 객체로 저장하는 클래스
		if(optional.get() == ButtonType.OK) { // ok를 늘렀을때ㅑ 회원 탈퇴
			System.out.println("삭제");
			boolean result =  ProductDao.dao.delete(product.getP_num());
			
			if(result) {
				// 페이지 전황
				Home.home.loadpage("/view/product/product");
				Alert alert2 = new Alert(AlertType.INFORMATION);
	    		alert2.setTitle("글삭제");
	    		alert2.setHeaderText("게시글 삭제가 완료 되었습니다.");
	    		alert2.setContentText("완료");
	    		alert2.showAndWait();
			}
			else {
				System.out.println("삭제 실패했데");
			}
		}
		else if(optional.get() == ButtonType.CANCEL) {
			System.out.println("취소");
		}
    }

    @FXML
    void accupdate(ActionEvent event) {

    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	
    	img.setImage(new Image(product.getP_img()) );
    	txtpname.setText(product.getP_name());
    	txtpcontents.setText(product.getP_contents());
    	txtdate.setText(product.getP_date());
    	DecimalFormat df2 = new DecimalFormat("#,##0원");
    	String aa = df2.format(product.getP_money());
    	txtprise.setText(aa);
    	
    	txtpcontents.setEditable(false);
    	txtpname.setEditable(false);
    	
    	if(product.getP_condition() == 1) {
    		txtcondition.setText("판매중");
    	}
    	if(product.getP_condition() == 2) {
    		txtcondition.setText("거래중");
    	}
    	if(product.getP_condition() == 3) {
    		txtcondition.setText("판매완료");
    	}
    	// * 회원 번호를 이용한 회원 id 찾기 
    	String writer = MemberDao.dao.getM_id(product.getM_num());
    	txtmid.setText(writer);
    	if(!(Login.member.getM_num() == product.getM_num())) {
    		btdelete.setVisible(false);
    		btupdate.setVisible(false);
    	}
    	
    }
}
