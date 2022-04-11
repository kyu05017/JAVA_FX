package controllor;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import dao.MemberDao;
import dao.ProductDao;
import dto.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
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
    private PieChart ppc;
    
    @FXML
    private BarChart<?, ?> mbc;
	
    @FXML
    private BarChart<?, ?> pbc;

    @FXML
    private BarChart<?, ?> bbc;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// ��ü ȸ���� 
		int total_member  = MemberDao.dao.total_member("member");
		lblmembers.setText(total_member+"��");
		// ��ü ��ǰ��
		int total_product = MemberDao.dao.total_member("product");
		if(total_product == 0) {
			lblitems.setText("��ǰ����");
		}
		else {
			lblitems.setText(total_product+"��");
		}
		// ��ü �Խù���
		int total_board = MemberDao.dao.total_member("board");
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
		
		// ����Ʈ ��ǰ ī�װ� ���� [ ObservableList �ۿ� �ȵ� ] 
		Map<String, Integer> c_list = MemberDao.dao.date_total("category");
		// 1. ObservableList < ���� ��Ʈ ������ �ڷ��� > ����Ʈ�� ����
		ObservableList<PieChart.Data> pielist = FXCollections.observableArrayList();
		// 2. PieChart.Data ��ü ���� 
		// 3. �����͸� ����Ʈ�� �߰�
		for(String temp : c_list.keySet()) {
			PieChart.Data temp2 = new PieChart.Data(temp, c_list.get(temp));
			pielist.add(temp2);
		}
		// 4. ����Ʈ�� ������Ʈ�� �߰�
		ppc.setData(pielist);
	}
}
