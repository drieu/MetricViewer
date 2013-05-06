package fr.dr.viewer.controller;

import fr.dr.viewer.model.Metric;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jfxtras.labs.scene.control.CalendarTextField;
import org.apache.log4j.Logger;

import java.io.IOException;


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
    private TableView metricsView;

	@FXML
	private LineChart<Double, Double> lineChart;

	@FXML
	private MenuBar menuBar;

    @FXML private TextField metricNameField;

    //@FXML private TableView tableView;

    //private EntityManagerFactory emf;

    //private EntityManager em;

    //private MetricService metricService = new MetricServiceImpl();

    private static final Logger log = Logger.getLogger(MetricViewerController.class);


	@FXML protected void handleSubmitButtonAction(ActionEvent event) {

		ObservableList<String> data = metricsComboBox.getItems();
        log.info("Ajout d'une métrique :" + metricNameField.getText());
        data.add(metricNameField.getText());
        Metric metric = new Metric(metricNameField.getText());
        metricsView.getItems().add(metric);
	}



	@FXML
	protected void initialize() {


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

