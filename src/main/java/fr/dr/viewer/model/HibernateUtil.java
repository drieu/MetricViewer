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
package fr.dr.viewer.model;

/**
 * .
 * User: drieu
 * Date: 16/04/13
 */
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SessionFactoryObserver;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	private static final  SessionFactory sessionFactory;
	private static final  ServiceRegistry serviceRegistry;
	static {
		try {
			Configuration config = getConfiguration();
			serviceRegistry = new ServiceRegistryBuilder().applySettings(
					config.getProperties()).buildServiceRegistry();
			config.setSessionFactoryObserver(new SessionFactoryObserver() {
				private static final long  serialVersionUID = 1L;

				@Override
				public void sessionFactoryCreated(SessionFactory factory) {
				}

				@Override
				public void sessionFactoryClosed(SessionFactory factory) {
					ServiceRegistryBuilder.destroy(serviceRegistry);
				}
			});
			sessionFactory = config.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static  Session openSession() {
		return sessionFactory.openSession();
	}

	private static  Configuration getConfiguration() {
		Configuration cfg = new Configuration();
		cfg.addFile("src/main/resources/META-INF/persistence.xml");
		cfg.addAnnotatedClass(Metric.class );
		cfg.addAnnotatedClass(Server.class);
		cfg.setProperty("hibernate.connection.driver_class",     "org.h2.Driver");
		cfg.setProperty("hibernate.connection.url","jdbc:h2:mem:test");
		cfg.setProperty("hibernate.connection.username", "sa");
		cfg.setProperty("hibernate.connection.password", "");
		cfg.setProperty("hibernate.show_sql", "true");
		cfg.setProperty("hibernate.dialect","org.hibernate.dialect.H2Dialect");
		cfg.setProperty("hibernate.hbm2ddl.auto", "update");
		cfg.setProperty("hibernate.cache.provider_class","org.hibernate.cache.NoCacheProvider");
		cfg.setProperty("hibernate.current_session_context_class", "thread");

		return cfg;
	}
}
