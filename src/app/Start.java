package app;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Start extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		
		// �������� �ΰ� ������
			// 1. �̹��� �ҷ�����
		Image image = new Image("img/a.png");
		
		stage.setTitle("Ezen"); // 2. �������� â �̸�
		stage.show(); // �������� ����
	}
	
	
	public static void main(String[] args) {
		
		launch(args);
	}
	
	
}
