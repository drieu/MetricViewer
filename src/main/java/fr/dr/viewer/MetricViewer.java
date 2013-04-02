package fr.dr.viewer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * .
 * User: drieu
 * Date: 3/7/13
 * Time: 10:37 PM
 */
public class MetricViewer extends
        Application {


    public static void main(String[] args) {
        MetricViewer.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fr/dr/viewer/metric_viewer.fxml"));

        stage.setTitle("Metric viewer");

        Scene scene = new Scene(root, 800, 600);


        scene.getStylesheets().add("/stylesheets/metric_viewer.css");

        stage.setScene(scene);

        stage.show();
    }
}
