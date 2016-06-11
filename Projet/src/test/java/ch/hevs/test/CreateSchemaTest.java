package ch.hevs.test;

import junit.framework.TestCase;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

public class CreateSchemaTest extends TestCase {

	@Test
	public void test() {

		Configuration cfg = new Configuration();
		cfg.addAnnotatedClass(ch.hevs.businessobject.Artist.class);
		cfg.addAnnotatedClass(ch.hevs.businessobject.Album.class);
		cfg.addAnnotatedClass(ch.hevs.businessobject.Song.class);
		cfg.addAnnotatedClass(ch.hevs.businessobject.Type.class);
		cfg.setProperty("hibernate.dialect",
				"org.hibernate.dialect.HSQLDialect");
		cfg.setProperty("hibernate.connection.driver_class",
				"org.hsqldb.jdbcDriver");
		cfg.setProperty("hibernate.connection.driver_class",
				"org.hsqldb.jdbcDriver");
		cfg.setProperty("hibernate.connection.url",
				"jdbc:hsqldb:hsql://localhost/DB");
		cfg.setProperty("hibernate.connection.username", "sa");

		new SchemaExport(cfg).setOutputFile("schema.ddl").create(false, true);
	}
}
