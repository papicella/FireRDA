package pivotal.fire.tools.firerda.server;

import java.util.Hashtable;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXServiceURL;

/**
 * @author  Pas Apicella
 * @version 1.0 
 */
public interface ServerConnection 
{
	public void doConnection(String url) throws Exception;
	public void doConnection(JMXServiceURL jmxServiceURL, Hashtable env) throws Exception;
	public MBeanServerConnection getConnection();
	public boolean isConnected();
	public void close ();
}
