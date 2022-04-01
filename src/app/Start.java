package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Start extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		
		// 5. 컨테이너 불러오기
		Parent parent = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
		
		// 6. 신 객체 -> 컨테이너 연결
		Scene scene = new Scene(parent);
		
		// 7. 씬 -> 스테이지
		stage.setScene(scene);
		
		// 스테이지 로고 설정ㄷ
			// 1. 이미지 불러오기 
		Image image = new Image("img/logo.png"); // 상대 경로
		//Image image2 = new Image("//C:\\Users\\504\\git\\JAVA_FX\\src\\img"); 절대경로
			// 절대 경로 VS 상대 경로
			// 절대 경로 : 모든 경로
				// C:\Users\504\git\JAVA_FX\src\img
			// 상대 경로 : 현위치 [ 프로젝트 기준 src ] 기준 경로
				// 생략 [ src폴더부터 ] img/파일명.확장자
			// 2. 스테이지 설정 
		stage.getIcons().add(image);
		stage.setResizable(false);	// 3. 스테이지 크기 고정
		
		stage.setTitle("콩방맨"); // 2. 스테이지 창 이름
		stage.show(); // 스테이지 열기
	}
	
	
	public static void main(String[] args) {
		
		launch(args);
	}
	
	
}
