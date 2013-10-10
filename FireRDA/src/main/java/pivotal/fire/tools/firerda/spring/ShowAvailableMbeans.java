package pivotal.fire.tools.firerda.spring;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pivotal.fire.tools.firerda.server.ServerConnection;

public class ShowAvailableMbeans 
{
	final Logger logger = Logger.getLogger(this.getClass().getName());
	private ConfigurableApplicationContext ctx;

	public void run () 
	{    
      logger.entering("Show MBeans", "run");
      
      ctx = new ClassPathXmlApplicationContext("query-beans.xml");
      
      MBeanViewer invoker = (MBeanViewer) ctx.getBean("showMBeans");

      try
      {
        invoker.run();
      }
      catch (Exception e)
      {
        logger.log(Level.SEVERE, e.getMessage());
      }
      
      logger.exiting("Show MBeans", "run");    
	}
  
    public static void main(String[] args)
    {
    	ShowAvailableMbeans showAvailableMbeans = new ShowAvailableMbeans();
    	showAvailableMbeans.run();
    }
}
