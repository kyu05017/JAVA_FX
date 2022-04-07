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

    	if(btchange.getText().equals("�ŷ���")) {
    		btchange.setText("�ǸſϷ�");
    		txtcondition.setText("�ŷ���");
    	}
    	else if(btchange.getText().equals("�Ǹ���")) {
    		btchange.setText("�ŷ���");
    		txtcondition.setText("�Ǹ���");
    	}
    	else if(btchange.getText().equals("�ǸſϷ�")) {
    		btchange.setText("�Ǹ���");
    		txtcondition.setText("�ǸſϷ�");
    	}
    }
    
    public static Reply_Product reply_Product;
    
    @FXML
    void accreadd(ActionEvent event) {
    	String reply_contents = txtrefiled.getText();
    	
    	if(txtrefiled.getText().equals("")) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("������ �Է����ּ���.");
    		alert.showAndWait();
    	}
    	else {
    		Reply_Product reply = new Reply_Product(0, reply_contents, Login.member.getM_id(),null,product.getP_num());
        	
        	boolean result =  ProductDao.dao.reply_write(reply);
        	
        	if(result) {
        		Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setHeaderText("��� �ۼ��� �Ϸ� �Ǿ����ϴ�.");
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
		
		tc = retalbe.getColumns().get(1); // �ι�° �� ȣ��
		tc.setCellValueFactory(new PropertyValueFactory<>("r_writerr"));
		
		tc = retalbe.getColumns().get(2); // �ι�° �� ȣ��
		tc.setCellValueFactory(new PropertyValueFactory<>("r_contents"));
		
		tc = retalbe.getColumns().get(3); // �ι�° �� ȣ��
		tc.setCellValueFactory(new PropertyValueFactory<>("r_date"));
		retalbe.setItems(replylist);
		
    }
    @FXML
    void accredel(ActionEvent event) {
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION); // Ȯ��,��� ��ư Ÿ��
		alert.setHeaderText("���� ���� �Ͻðڽ��ϱ�?");
		// 2. ��ư Ȯ�� [ Optional Ŭ���� ]
		Optional<ButtonType> optional = alert.showAndWait();
		//Optional Ŭ���� == null���� ��ü�� �����ϴ� Ŭ����
		if(optional.get() == ButtonType.OK) { // ok�� �÷������� ȸ�� Ż��
			System.out.println("����");
			boolean result =  ProductDao.dao.reply_delete(reply_Product.getR_num());
			
			if(result) {
				// ������ ��Ȳ
				Alert alert2 = new Alert(AlertType.INFORMATION);
	    		alert2.setTitle("�ۻ���");
	    		alert2.setHeaderText("�Խñ� ������ �Ϸ� �Ǿ����ϴ�.");
	    		alert2.setContentText("�Ϸ�");
	    		alert2.showAndWait();
	    		replyshow();
			}
			else {
				System.out.println("���� �����ߵ�");
			}
		}
		else if(optional.get() == ButtonType.CANCEL) {
			System.out.println("���");
		}
    }
    
    boolean re_upcheck = true;
    @FXML
    void accreupdate(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION); // 1. �޼��� ���
    	
    	if(re_upcheck) {
    		alert.setHeaderText("��� ������ ���� �Ϸ� ��ư�� �����ּ���.");
    		alert.showAndWait();
			re_upcheck = false;
    	}
    	else {

	    	boolean result = ProductDao.dao.re_updaete(reply_Product.getR_num(),txtrefiled.getText());
	    	
	    	if(result) {
	    		alert.setHeaderText("������ �Ϸ� �Ǿ����ϴ�.");
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
				System.out.println("��۾��� �κ� ����");
			}
		});
    	
    	img.setImage(new Image(product.getP_img()) );
    	txtpname.setText("�ۼ��� "+product.getP_name());
    	txtpcontents.setText(product.getP_contents());
    	txtdate.setText("�ۼ��� "+product.getP_date());
    	DecimalFormat df2 = new DecimalFormat("#,##0��");
    	String aa = df2.format(product.getP_money());
    	txtprise.setText(aa);
    	
    	txtpcontents.setEditable(false);
    	txtpname.setEditable(false);
    	
    	if(product.getP_condition() == 1) {
    		txtcondition.setText("�Ǹ���");
    		btchange.setText("�ŷ���");
    	}
    	if(product.getP_condition() == 2) {
    		txtcondition.setText("�ŷ���");
    		btchange.setText("�ǸſϷ�");
    	}
    	if(product.getP_condition() == 3) {
    		txtcondition.setText("�ǸſϷ�");
    		btchange.setText("�Ǹ���");
    	}
    	
    	// * ȸ�� ��ȣ�� �̿��� ȸ�� id ã�� 
    	String writer = MemberDao.dao.getM_id(product.getM_num());
    	txtmid.setText(writer);
    	if(!(Login.member.getM_num() == product.getM_num())) {
    		btdelete.setVisible(false);
    		btupdate.setVisible(false);
    		btchange.setVisible(false);
    	}
    	
    }
}
