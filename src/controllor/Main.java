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
				// Initializable : view가 새로 열렸을때 초기값 설정 메소드 제공
	
    @FXML
    private BorderPane boarderPane; // 씬빌더에서 만든 컨테이너 객체
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("Main뷰가 실행 되었습니다.");
		// 1. boarderpane 
		loadpage("/view/login");
	}
	
	public void loadpage(String page) {
		try {
			Parent parent = FXMLLoader.load( getClass().getResource(page+".fxml"));
			boarderPane.setCenter(parent);
		} catch (IOException e) {
			System.out.println("페이지 연결 실패");
		}
		
	}
}
