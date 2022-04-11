package controllor;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import dao.BoardDao;
import dao.MemberDao;
import dao.ProductDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class Record implements Initializable{

	@FXML
    private Label lblmembers;

    @FXML
    private Label lblitems;

    @FXML
    private Label lblboards;

    @FXML
    private BarChart<?, ?> mbc;
	
    @FXML
    private BarChart<?, ?> pbc;

    @FXML
    private BarChart<?, ?> bbc;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// ��ü ȸ���� 
		int total_member  = MemberDao.dao.total_member();
		lblmembers.setText(total_member+"��");
		// ��ü ��ǰ��
		int total_product = ProductDao.dao.total_product();
		if(total_product == 0) {
			lblitems.setText("��ǰ����");
		}
		else {
			lblitems.setText(total_product+"��");
		}
		// ��ü �Խù���
		int total_board = BoardDao.dao.total_board();
		if(total_board == 0) {
			lblboards.setText("�Խù� ����.");
		}
		else {
			lblboards.setText(total_board+"��");
		}

		// ��¥�� ȸ�� ���Լ�'
		XYChart.Series series = new XYChart.Series<>();
		// XYChart.Series = �迭 Ŭ����
			//  XYChart.Data  = �迭 ������ Ŭ���� [ x ���� �� , y ���� �� ] 
			// ��¥���� ȸ�����Լ� �� üũ [ 2022-04-11 , 3 ]
			// map �÷��� => Ű�� ������ �ϳ��� ��Ʈ�� ����
			// Ű = ��¥ \\ �� ���Լ�
			
		Map<String, Integer> total = MemberDao.dao.date_total("member");
		
		for(String temp : total.keySet()) {
			XYChart.Data data = new XYChart.Data(temp,total.get(temp));
			series.getData().add(data);
		}
		mbc.getData().add(series);
		
		XYChart.Series series2 = new XYChart.Series<>();
		Map<String, Integer> b_total = MemberDao.dao.date_total("board");
		for(String temp : b_total.keySet()) {
			XYChart.Data data = new XYChart.Data(temp,b_total.get(temp));
			series2.getData().add(data);
		}
		bbc.getData().add(series2);
		
		XYChart.Series series3 = new XYChart.Series<>();
		Map<String, Integer> p_total = MemberDao.dao.date_total("product");
		for(String temp : p_total.keySet()) {
			XYChart.Data data = new XYChart.Data(temp,p_total.get(temp));
			series3.getData().add(data);
		}
		pbc.getData().add(series3);
	}
}
