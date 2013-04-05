package fr.dr.viewer;

import fr.dr.viewer.model.Metric;
import fr.dr.viewer.view.MetricMenu;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import jfxtras.labs.scene.control.CalendarTextField;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


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
	private MenuBar menuBar;



	private EntityManagerFactory emf;
	private EntityManager em;

	@FXML
	protected void initialize() {
		emf = Persistence.createEntityManagerFactory("MetricPU");
		em = emf.createEntityManager();

		initializeMenu();
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
		List<String> results = new ArrayList<>();
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

		em.close();

		return results;
	}
}
