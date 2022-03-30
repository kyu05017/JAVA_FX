package app;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Start extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		
		// 스테이지 로고 설정ㄷ
			// 1. 이미지 불러오기
		Image image = new Image("img/a.png");
		
		stage.setTitle("Ezen"); // 2. 스테이지 창 이름
		stage.show(); // 스테이지 열기
	}
	
	
	public static void main(String[] args) {
		
		launch(args);
	}
	
	
}
