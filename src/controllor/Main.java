package controllor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class Main implements Initializable{
				// Initializable : view�� ���� �������� �ʱⰪ ���� �޼ҵ� ����
	
    @FXML
    private BorderPane boarderPane; // ���������� ���� �����̳� ��ü
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("Main�䰡 ���� �Ǿ����ϴ�.");
		// 1. boarderpane 
		loadpage("/view/login");
	}
	
	public void loadpage(String page) {
		try {
			Parent parent = FXMLLoader.load( getClass().getResource(page+".fxml"));
			boarderPane.setCenter(parent);
		} catch (IOException e) {
			System.out.println("������ ���� ����");
		}
		
	}
}
