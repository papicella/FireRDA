package pivotal.fire.tools.firerda.spring.queries;

import java.io.StringWriter;
import java.util.List;
import java.util.Map;
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
	      StringWriter writer = new StringWriter();
	    	
	      List<Map> attrs = (List<Map>) super.invoke(mbs);
	      
	      writer.write(String.format("*** [MBean: %s] ***\n\n", this.mBeanName));
    	  writer.write(String.format("Attributes [Size = %s]\n\n", attrs.size()));
    	
    	  for (Map m: attrs)
    	  {
	    		writer.write
	    		  (String.format("%s (%s) %s %s %s\n",
	    				         m.get("spacer1"),
	    				         m.get("type"),
	    				         m.get("name"),
	    				         m.get("spacer2"),
	    				         m.get("value")));
    	  }
    	  
	      return writer.toString();
	  }
}
