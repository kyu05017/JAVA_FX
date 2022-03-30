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
		
		// 5. �����̳� �ҷ�����
		Parent parent = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
		
		// 6. �� ��ü -> �����̳� ����
		Scene scene = new Scene(parent);
		
		// 7. �� -> ��������
		stage.setScene(scene);
		
		// �������� �ΰ� ������
			// 1. �̹��� �ҷ�����
		Image image = new Image("img/logo.png"); // ��� ���
		//Image image2 = new Image("//C:\\Users\\504\\git\\JAVA_FX\\src\\img"); ������
			// ���� ��� VS ��� ���
			// ���� ��� : ��� ���
				// C:\Users\504\git\JAVA_FX\src\img
			// ��� ��� : ����ġ [ ������Ʈ ���� src ] ���� ���
				// ���� [ src�������� ] img/���ϸ�.Ȯ����
			// 2. �������� ����
		stage.getIcons().add(image);
		stage.setResizable(false);	// 3. �������� ũ�� ����
		
		stage.setTitle("������ ���ɹ��� ���"); // 2. �������� â �̸�
		stage.show(); // �������� ����
	}
	
	
	public static void main(String[] args) {
		
		launch(args);
	}
	
	
}
