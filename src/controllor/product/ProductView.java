package controllor.product;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.ResourceBundle;

import controllor.home.Home;
import controllor.login.Login;
import dao.BoardDao;
import dao.MemberDao;
import dao.ProductDao;
import dto.Product;
import dto.Reply;
import dto.Reply_Product;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableView<Reply_Product> retalbe;
    
    @FXML
    private Button btchange;
    
    @FXML
    void accchange(ActionEvent event) {

    	if(btchange.getText().equals("거래중")) {
    		btchange.setText("판매완료");
    		txtcondition.setText("거래중");
    	}
    	else if(btchange.getText().equals("판매중")) {
    		btchange.setText("거래중");
    		txtcondition.setText("판매중");
    	}
    	else if(btchange.getText().equals("판매완료")) {
    		btchange.setText("판매중");
    		txtcondition.setText("판매완료");
    	}
    }
    
    public static Reply_Product reply_Product;
    
    @FXML
    void accreadd(ActionEvent event) {
    	String reply_contents = txtrefiled.getText();
    	
    	if(txtrefiled.getText().equals("")) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("내용을 입력해주세요.");
    		alert.showAndWait();
    	}
    	else {
    		Reply_Product reply = new Reply_Product(0, reply_contents, Login.member.getM_id(),null,product.getP_num());
        	
        	boolean result =  ProductDao.dao.reply_write(reply);
        	
        	if(result) {
        		Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setHeaderText("댓글 작성이 완료 되었습니다.");
        		alert.showAndWait();
        		txtrefiled.setText("");
        		replyshow();
        	}
    	}
    }
    public void replyshow() {
    	ObservableList<Reply_Product> replylist = ProductDao.dao.reply_list(product.getP_num());
		
		TableColumn<?, ?> tc = retalbe.getColumns().get(0);
		tc.setCellValueFactory(new PropertyValueFactory<>("r_num"));
		
		tc = retalbe.getColumns().get(1); // 두번째 열 호출
		tc.setCellValueFactory(new PropertyValueFactory<>("r_writerr"));
		
		tc = retalbe.getColumns().get(2); // 두번째 열 호출
		tc.setCellValueFactory(new PropertyValueFactory<>("r_contents"));
		
		tc = retalbe.getColumns().get(3); // 두번째 열 호출
		tc.setCellValueFactory(new PropertyValueFactory<>("r_date"));
		retalbe.setItems(replylist);
		
    }
    @FXML
    void accredel(ActionEvent event) {
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION); // 확인,취소 버튼 타입
		alert.setHeaderText("정말 삭제 하시겠습니까?");
		// 2. 버튼 확인 [ Optional 클래스 ]
		Optional<ButtonType> optional = alert.showAndWait();
		//Optional 클래스 == null값을 객체로 저장하는 클래스
		if(optional.get() == ButtonType.OK) { // ok를 늘렀을때ㅑ 회원 탈퇴
			System.out.println("삭제");
			boolean result =  ProductDao.dao.reply_delete(reply_Product.getR_num());
			
			if(result) {
				// 페이지 전황
				Alert alert2 = new Alert(AlertType.INFORMATION);
	    		alert2.setTitle("글삭제");
	    		alert2.setHeaderText("게시글 삭제가 완료 되었습니다.");
	    		alert2.setContentText("완료");
	    		alert2.showAndWait();
	    		replyshow();
			}
			else {
				System.out.println("삭제 실패했데");
			}
		}
		else if(optional.get() == ButtonType.CANCEL) {
			System.out.println("취소");
		}
    }
    
    boolean re_upcheck = true;
    @FXML
    void accreupdate(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION); // 1. 메세지 출력
    	
    	if(re_upcheck) {
    		alert.setHeaderText("댓글 수정후 수정 완료 버튼을 눌러주세요.");
    		alert.showAndWait();
			re_upcheck = false;
    	}
    	else {

	    	boolean result = ProductDao.dao.re_updaete(reply_Product.getR_num(),txtrefiled.getText());
	    	
	    	if(result) {
	    		alert.setHeaderText("수정이 완료 되었습니다.");
				alert.showAndWait();
				txtrefiled.setText("");
				re_upcheck = true;
	    	}
    	}
    
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
    	Home.home.loadpage("/view/product/productupdate");
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	replyshow();
    	btreupdate.setVisible(false);
    	btredel.setVisible(false);
    	
    	retalbe.setOnMouseClicked( e -> {
			try {
				reply_Product = retalbe.getSelectionModel().getSelectedItem();
				if(reply_Product.getR_writerr().equals(Login.member.getM_id())) {
					btredel.setVisible(true);
					btreupdate.setVisible(true);
					btreadd.setVisible(false);
					
				}
				else {
					btreupdate.setVisible(false);
					btredel.setVisible(false);
					btreadd.setVisible(true);
				}
			}
			catch(Exception e2) {
				System.out.println("댓글없는 부분 누름");
			}
		});
    	
    	img.setImage(new Image(product.getP_img()) );
    	txtpname.setText("작성자 "+product.getP_name());
    	txtpcontents.setText(product.getP_contents());
    	txtdate.setText("작성일 "+product.getP_date());
    	DecimalFormat df2 = new DecimalFormat("#,##0원");
    	String aa = df2.format(product.getP_money());
    	txtprise.setText(aa);
    	
    	txtpcontents.setEditable(false);
    	txtpname.setEditable(false);
    	
    	if(product.getP_condition() == 1) {
    		txtcondition.setText("판매중");
    		btchange.setText("거래중");
    	}
    	if(product.getP_condition() == 2) {
    		txtcondition.setText("거래중");
    		btchange.setText("판매완료");
    	}
    	if(product.getP_condition() == 3) {
    		txtcondition.setText("판매완료");
    		btchange.setText("판매중");
    	}
    	
    	// * 회원 번호를 이용한 회원 id 찾기 
    	String writer = MemberDao.dao.getM_id(product.getM_num());
    	txtmid.setText(writer);
    	if(!(Login.member.getM_num() == product.getM_num())) {
    		btdelete.setVisible(false);
    		btupdate.setVisible(false);
    		btchange.setVisible(false);
    	}
    	
    }
}
