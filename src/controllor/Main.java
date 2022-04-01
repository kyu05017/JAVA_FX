package controllor;

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
			// 1. boarderpane 
		
		System.out.println("Main뷰가 실행 되었습니다.");
		loadpage("/view/login/login"); // loadpage 메소드 호출시 ( 파일 경로 ) 
	}
	
	public void loadpage( String page ) { // loadpage ( 파일경로 )
		
		try {
			// 페이지(fxml) 객체화 
			Parent parent = FXMLLoader.load( getClass().getResource(page+".fxml") ); // 무조건 예외처리
			boarderPane.setCenter(parent); // 컨테이너(fxml) 가운데에 페이지 넣기 
		}
		catch( Exception e ) { // 파일이 존재하지 않을 경우 예외처리
			System.out.println("페이지 연결 실패"+ e); 
		}
	}
}
