package pivotal.fire.tools.firerda.server;

import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;


/**
 * @author  Pas Apicella
 * @version 1.0 
 * @see ServerConnection
 */
public abstract class ServerConnectionBase implements ServerConnection
{
  private JMXConnector jmxCon = null;
  private Logger logger = Logger.getLogger(this.getClass().getName());
  
  public ServerConnectionBase()
  {
  }

  public void doConnection(String url) throws Exception
  {
    logger.log(Level.INFO, "JMX Service URL = " + url);
    JMXServiceURL serviceURL = new JMXServiceURL(url);

    jmxCon = JMXConnectorFactory.connect(serviceURL);
  }

  public void doConnection(JMXServiceURL jmxServiceURL, Hashtable env) throws Exception
  {
    logger.log(Level.INFO, "Service URL Path = " + jmxServiceURL.getURLPath());
    jmxCon = JMXConnectorFactory.connect(jmxServiceURL, env);    
  }
  
  public MBeanServerConnection getConnection()
  {
    MBeanServerConnection mbs = null;

    if (jmxCon != null) 
    {
        try 
        {
            mbs = jmxCon.getMBeanServerConnection();
        } 
        catch (Throwable t) 
        {
            logger.log(Level.SEVERE, "Unable to retrieve MBeanServerConnection");
        }
    }

    return mbs;
  }

  public boolean isConnected()
  {
    boolean ret = false;
    if (jmxCon == null) 
    {
        return false;
    } 
    else 
    {
        try 
        {
            jmxCon.getConnectionId();
            return true;
        } 
        catch (Throwable t) 
        {
            // no need to do anything here
        }
    }

    return ret;
  }

  public void close()
  {
    if (jmxCon != null) 
    {
        try 
        {
            jmxCon.close();
            jmxCon = null;
        } 
        catch (Throwable t) 
        {
        }
    }
  }

}

