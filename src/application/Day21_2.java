package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Day21_2 extends Application{ // 1. ApplicationŬ������ ���� ���
	
	@Override
	public void start(Stage stage) throws Exception { // 2. Start �޼ҵ带 ����
		
		// 1. Fxml ���� �ҷ�����
		Parent parent = FXMLLoader.load(getClass().getResource("test.fxml"));
			// Parent : fxml���� ( ������ ���� ) �� ��üȭ
				// FXMLLoader.load(getClass().getResource("���/ ���ϸ�.fxml"))
		
		
		// 2. �� ��ü ���� : ���� �����̳� �ֱ�
		Scene scene = new Scene(parent);
		
		// 3. ���������� �� �ֱ�
		stage.setScene(scene);
		
		// 4. �������� ����
		stage.show();
		
	}
	
	
	public static void main(String[] args) { // 3. ���ν����� -> Start �޼ҵ� ȣ��
		
		launch(args); // start �޼ҵ� ȣ��
	}
}
