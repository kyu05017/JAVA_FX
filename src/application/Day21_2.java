package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Day21_2 extends Application{ // 1. Application클래스로 부터 상속
	
	@Override
	public void start(Stage stage) throws Exception { // 2. Start 메소드를 구현
		
		// 1. Fxml 파일 불러오기
		Parent parent = FXMLLoader.load(getClass().getResource("test.fxml"));
			// Parent : fxml파일 ( 씬빌더 파일 ) 을 객체화
				// FXMLLoader.load(getClass().getResource("경로/ 파일명.fxml"))
		
		
		// 2. 씬 객체 생성 : 씬에 컨테이너 넣기
		Scene scene = new Scene(parent);
		
		// 3. 스테이지에 씬 넣기
		stage.setScene(scene);
		
		// 4. 스테이지 시작
		stage.show();
		
	}
	
	
	public static void main(String[] args) { // 3. 메인스레드 -> Start 메소드 호출
		
		launch(args); // start 메소드 호출
	}
}
