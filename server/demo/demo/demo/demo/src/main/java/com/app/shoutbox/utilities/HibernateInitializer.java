package com.app.shoutbox.utilities;



import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;








/**
 * Application Lifecycle Listener implementation class HibernateInitializer
 *
 */
@WebListener
public class HibernateInitializer implements ServletContextListener {

    
	Logger log=Logger.getLogger(HibernateInitializer.class);

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
      log.info("ctx destroyed");
        HibernateUtils.getSf().close();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	 log.info("ctx inited");
        HibernateUtils.getSf();
    }
	
}