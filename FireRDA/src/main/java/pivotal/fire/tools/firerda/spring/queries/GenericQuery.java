package pivotal.fire.tools.firerda.spring.queries;

import java.util.logging.Logger;

import javax.management.MBeanServerConnection;

import pivotal.fire.tools.firerda.spring.exception.FireRDAException;

public class GenericQuery extends FireRDAQueryBase 
{
	  Logger logger = Logger.getLogger(this.getClass().getName());
	  
	  @Override
	  public Object invoke(MBeanServerConnection mbs) throws FireRDAException 
	  {
	      logger.info("Generic Query called");
	      return super.invoke(mbs);
	  }
}
