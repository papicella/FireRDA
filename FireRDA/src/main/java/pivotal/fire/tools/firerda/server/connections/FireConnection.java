package pivotal.fire.tools.firerda.server.connections;

import pivotal.fire.tools.firerda.server.ServerConnectionBase;

import java.net.URL;

import java.util.Properties;
import java.util.logging.Logger;

/**
 * @author  Pas Apicella
 * @version 1.0 
 */
public class FireConnection extends ServerConnectionBase
{
	  private static FireConnection instance = null;
	  private String serviceURL = null;
	  private Logger logger = Logger.getLogger(this.getClass().getName());
	  
	  static
	  {
	    try
	    {
	      instance = new FireConnection();
	    }
	    catch (Exception e)
	    {
	      throw new RuntimeException(e.getMessage(), e);
	    }
	  }
	  
	  private FireConnection () throws Exception
	  {
	    if (instance == null)
	    {
	      
	      Properties props = new Properties();
	      URL url = ClassLoader.getSystemResource("server.properties");
	      props.load(url.openStream());
	      
	      serviceURL = (String) props.getProperty("serviceurl");
	    }
	    
	    super.doConnection(serviceURL); 
	  }
	  
	  public static FireConnection getInstance() throws Exception
	  { 
	    return instance;
	  }

	  public String getServiceURL()
	  {
	    return serviceURL;
	  }

}
