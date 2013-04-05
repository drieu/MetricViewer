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
package fr.dr.viewer.view;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

/**
 * .
 * User: drieu
 * Date: 05/04/13
 */
public class MetricMenu {


	public void initializeMenu(MenuBar menuBar) {
		Menu menu = new Menu("File");

		menu.getItems().add(new MenuItem("New"));
		menu.getItems().add(new MenuItem("Open"));
		menu.getItems().add(new MenuItem("Save"));
		menu.getItems().add(new MenuItem("Save As"));
		menu.getItems().add(new SeparatorMenuItem());


		menu.getItems().add(createExitMenuItem());
		menuBar.getMenus().add(menu);

		menu = new Menu("Edit");

		menu.getItems().add(new MenuItem("Serveur"));
		menu.getItems().add(new MenuItem("Metric"));
		menu.getItems().add(new SeparatorMenuItem());
		menu.getItems().add(new MenuItem("Exit"));
		menuBar.getMenus().add(menu);

	}

	public MenuItem createExitMenuItem() {
		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				System.exit(0);
			}
		});
		return exit;
	}

}
