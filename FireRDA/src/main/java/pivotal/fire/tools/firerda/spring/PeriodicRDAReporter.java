package pivotal.fire.tools.firerda.spring;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PeriodicRDAReporter 
{
	  final Logger logger = Logger.getLogger(this.getClass().getName());

	  public void run () throws InterruptedException 
	  {    
	      logger.entering("RDA", "run");
	      
	      ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("query-beans.xml");
	      
	      PeriodicQueryInvoker invoker = (PeriodicQueryInvoker) ctx.getBean("periodicQueryInvoker");
	      logger.log
	        (Level.INFO, String.format("Total Number of Queries to invoke: %s\n", 
	                                   invoker.getQueryCount()));
	    
	      invoker.run();
	      
	      logger.exiting("RDA", "run");    
	  }

	  public static void main(String[] args) throws InterruptedException
	  {
		PeriodicRDAReporter rda = new PeriodicRDAReporter();
	    rda.run();
	  }
}
