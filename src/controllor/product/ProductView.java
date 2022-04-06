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
    	// 1. �޼��� ����
		Alert alert = new Alert(AlertType.CONFIRMATION); // Ȯ��,��� ��ư Ÿ��
		alert.setHeaderText("���� ���� �Ͻðڽ��ϱ�?");
		// 2. ��ư Ȯ�� [ Optional Ŭ���� ]
		Optional<ButtonType> optional = alert.showAndWait();
		//Optional Ŭ���� == null���� ��ü�� �����ϴ� Ŭ����
		if(optional.get() == ButtonType.OK) { // ok�� �÷������� ȸ�� Ż��
			System.out.println("����");
			boolean result =  ProductDao.dao.delete(product.getP_num());
			
			if(result) {
				// ������ ��Ȳ
				Home.home.loadpage("/view/product/product");
				Alert alert2 = new Alert(AlertType.INFORMATION);
	    		alert2.setTitle("�ۻ���");
	    		alert2.setHeaderText("�Խñ� ������ �Ϸ� �Ǿ����ϴ�.");
	    		alert2.setContentText("�Ϸ�");
	    		alert2.showAndWait();
			}
			else {
				System.out.println("���� �����ߵ�");
			}
		}
		else if(optional.get() == ButtonType.CANCEL) {
			System.out.println("���");
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
    	DecimalFormat df2 = new DecimalFormat("#,##0��");
    	String aa = df2.format(product.getP_money());
    	txtprise.setText(aa);
    	
    	txtpcontents.setEditable(false);
    	txtpname.setEditable(false);
    	
    	if(product.getP_condition() == 1) {
    		txtcondition.setText("�Ǹ���");
    	}
    	if(product.getP_condition() == 2) {
    		txtcondition.setText("�ŷ���");
    	}
    	if(product.getP_condition() == 3) {
    		txtcondition.setText("�ǸſϷ�");
    	}
    	// * ȸ�� ��ȣ�� �̿��� ȸ�� id ã�� 
    	String writer = MemberDao.dao.getM_id(product.getM_num());
    	txtmid.setText(writer);
    	if(!(Login.member.getM_num() == product.getM_num())) {
    		btdelete.setVisible(false);
    		btupdate.setVisible(false);
    	}
    	
    }
}
