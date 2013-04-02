package fr.dr.viewer;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import jfxtras.labs.scene.control.CalendarTextField;


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
	private ListView serversListView;

	@FXML
	private LineChart<Double, Double> lineChart;

	@FXML
	protected void initialize() {

	}
}
