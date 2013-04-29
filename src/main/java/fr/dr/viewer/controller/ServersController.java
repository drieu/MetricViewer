/*
 * Copyright (C) 2012 Rieu Damien.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package fr.dr.viewer.controller;

import fr.dr.viewer.model.Server;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javax.persistence.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


/**
 * Server controller use by servers.fxml.
 * User: drieu
 * Date: 05/04/13
 */
public class ServersController {

	@FXML private TextField serverNameField;
	@FXML private TextField urlField;
	@FXML private Text actiontarget;
	@FXML private TableView tableView;

	@FXML protected void handleSubmitButtonAction(ActionEvent event) {
		final String name = serverNameField.getText();
		final String url = urlField.getText();

		if ((name == null) || name.isEmpty()) {
			actiontarget.setText("Vous devez saisir un nom de serveur.");

		} else if ((url == null) || url.isEmpty()) {
			actiontarget.setText("Vous devez saisir un nom de serveur.");

		} else {
			saveServer(name, url);
			actiontarget.setText("Sauvegarde efectuée.");
		}
	}

	@FXML
	protected void initialize() {
		emf = Persistence.createEntityManagerFactory("MetricPU");
		em = emf.createEntityManager();
		refreshObsListFromDb();

	}

	private EntityManagerFactory emf;

	private EntityManager em;

	/**
	 * Save a server.
	 * @param name
	 * @param url
	 */
	private void saveServer(String name, String url) {
		Server server = new Server();
		server.setName(name);
		server.setUrl(url);
		em.getTransaction().begin();

		em.persist(server);
		em.flush();

		em.getTransaction().commit();

		em.refresh(server);
		refreshObsListFromDb();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/dr/viewer/metric_viewer.fxml"));
		try {
			Parent root = (Parent) loader.load();
			MetricViewerController controller = (MetricViewerController)loader.getController();
			controller.refresh();
		} catch (IOException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}

	}



	/**
	 * Refresh list of server in table view by retrieve all data in database.
	 */
	private void refreshObsListFromDb() {
		ObservableList<Server> data = tableView.getItems();
		//  Get all data in person table.
		try {
			em.getTransaction().begin();
			//Select all the record from student table
			Query query = em.createQuery("SELECT pt FROM Server pt");
			List lst = query.getResultList();
			Iterator it = lst.iterator();
			while (it.hasNext()) {
				Server m = (Server) it.next();
				data.add(m);
				System.out.print("Name:" + m.getName());
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		//em.close();
	}

}
