package chan.retailer.servlet;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import chan.db.util.DBHelper;

public class InitServlet implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Map<Object, Object> connectionParams = new HashMap<>();		
		try {
			/*connectionParams.put("host", chan.db.util.PropertiesUtil.getHost());
			connectionParams.put("dbname", chan.db.util.PropertiesUtil.getDBName());
			DBHelper.initConnections(connectionParams ,1);*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("DB Connection iniatilasse");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		DBHelper.closeConnections();
		System.out.println("DB Connection destroyrf");
	}
	
}
