package pivotal.fire.tools.firerda.spring;

import java.util.logging.Logger;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

/**
 * @author  Pas Apicella
 * @version 1.0 
 */
public class RDAReporter 
{
	  final Logger logger = Logger.getLogger(this.getClass().getName());

	  public void run () 
	  {    
	      logger.entering("RDA", "run");
	      
	      ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("query-beans.xml");
	      
	      QueryInvoker invoker = (QueryInvoker) ctx.getBean("queryInvoker");
	      logger.log
	        (Level.INFO, String.format("Total Number of Queries to invoke: %s\n", 
	                                   invoker.getQueryCount()));
	    
	      invoker.run();
	      
	      logger.exiting("RDA", "run");    
	  }

	  public static void main(String[] args)
	  {
	    RDAReporter rda = new RDAReporter();
	    rda.run();
	  }
}
