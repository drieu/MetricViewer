package fr.dr.viewer.controller;

import fr.dr.viewer.model.Metric;
import fr.dr.viewer.model.Server;
import fr.dr.viewer.model.service.MetricService;
import fr.dr.viewer.model.service.MetricServiceImpl;
import fr.dr.viewer.view.MetricMenu;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jfxtras.labs.scene.control.CalendarTextField;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * .
 * User: drieu
 * Date: 3/7/13
 * Time: 10:50 PM
 */
public class MetricViewerController {

    @FXML
    private Text info;

    @FXML
    private CalendarTextField calendarStartDateTextField;

    @FXML
    private CalendarTextField calendarEndDateTextField;

	@FXML
	private ComboBox<String> metricsComboBox;

	@FXML
	private TableView serversListView;

	@FXML
	private LineChart<Double, Double> lineChart;

	@FXML
	private MenuBar menuBar;


	@FXML protected void handleSubmitButtonAction(ActionEvent event) {

		ObservableList<String> data = metricsComboBox.getItems();
		List<Metric> metrics = metricService.listMetric();
		for (Metric m : metrics) {
			data.add(m.getName());
		}

//		ObservableList<String> data = serversListView.getItems();
//		List<Metric> metrics = metricService.listMetric();
//		for (Metric m : metrics) {
//			data.add(m.getName());
//		}
	}

	@FXML private TextField metricNameField;
	@FXML private TableView tableView;

	private EntityManagerFactory emf;

	private EntityManager em;

	private MetricService metricService = new MetricServiceImpl();

	@FXML
	protected void initialize() {

		ObservableList<String> data = metricsComboBox.getItems();
		List<Metric> metrics = metricService.listMetric();
		for (Metric m : metrics) {
			data.add(m.getName());
		}
	}





	private void fillListView() {

	}



	private void initializeMenu() {
		MetricMenu metric = new MetricMenu();
		metric.initializeMenu(menuBar);


	}


	/**
	 * Find all metrics.
	 * TODO : unused.
	 * @return
	 *
	 */
	private List<String> findAllMetric() {
		List<String> results = new ArrayList<String>();
		//  Get all data in person table.
		try {
			em.getTransaction().begin();
			//Select all the record from student table
			Query query = em.createQuery("SELECT pt FROM Metric pt");
			List lst = query.getResultList();
			Iterator it = lst.iterator();
			while (it.hasNext()) {
				Metric m = (Metric) it.next();
				results.add(m.getName());
				System.out.print("Name:" + m.getName());
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		//em.close();

		return results;
	}

	/**
	 * Refresh list of server in table view by retrieve all data in database.
	 */
	private void refreshserversListView() {
		//  Get all data in person table.
		try {
			em.getTransaction().begin();
			//Select all the record from student table
			Query query = em.createQuery("SELECT pt FROM Server pt");
			List lst = query.getResultList();
			Iterator it = lst.iterator();
			while (it.hasNext()) {
				Server m = (Server) it.next();
				serversListView.getItems().add(m.getName());
				System.out.print("=====>Add in server list:" + m.getName());
			}
			//tableView.getColumns().get(0).setVisible(false);
			serversListView.setVisible(true);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void refresh() {
		final Stage serversStage = new Stage(StageStyle.UTILITY);
		serversStage.setTitle("Configuration des serveurs");
		Parent serverRoot = null;
		try {
			serverRoot = FXMLLoader.load(getClass().getResource("/fr/dr/viewer/metric_viewer.fxml"));
		} catch (IOException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
		Scene serversScene = new Scene(serverRoot, 600, 600);
		serversScene.getStylesheets().add("/stylesheets/metric_viewer.css");
		serversStage.setScene(serversScene);
		serversStage.show();

	}

}

