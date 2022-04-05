package controllor;

import java.net.URL;
import java.util.ResourceBundle;

import controllor.login.Login;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;


public class Main implements Initializable{
				// Initializable : view�� ���� �������� �ʱⰪ ���� �޼ҵ� ����
	
	public static Main main;
	
	public Main() {
		main = this; // super : ����Ŭ���� ( ��� ) this : ��Ŭ���� 
		// ��Ŭ���� ��ü �޸� ȣ��
	}

	
    @FXML
    private BorderPane boarderPane; // ���������� ���� �����̳� ��ü
     


    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			// 1. boarderpane 
		
		System.out.println("Main�䰡 ���� �Ǿ����ϴ�.");
		loadpage("/view/login/login"); // loadpage �޼ҵ� ȣ��� ( ���� ��� ) 
	}
	
	public void loadpage( String page ) { // loadpage ( ���ϰ�� )
		
		try {
			// ������(fxml) ��üȭ 
			Parent parent = FXMLLoader.load( getClass().getResource(page+".fxml") ); // ������ ����ó��
			boarderPane.setCenter(parent); // �����̳�(fxml) ����� ������ �ֱ� 
		}
		catch( Exception e ) { // ������ �������� ���� ��� ����ó��
			System.out.println("Ȩ ������ ���� ���� "+ e); 
		}
	}
}
