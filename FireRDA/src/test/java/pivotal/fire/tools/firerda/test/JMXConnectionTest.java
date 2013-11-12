package pivotal.fire.tools.firerda.test;

import java.util.Set;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import pivotal.fire.tools.firerda.server.ConnectionFactory;
import pivotal.fire.tools.firerda.server.ServerConnection;

public class JMXConnectionTest 
{
	  public static void main(String[] args) throws Exception
	  {
	    ServerConnection jmxConn = ConnectionFactory.getFireConnection();
	    System.out.println("Is connected = " + jmxConn.isConnected());
	    
	    MBeanServerConnection mBeanServerConnection = jmxConn.getConnection();
	    
	    Set<ObjectName> mbeans = mBeanServerConnection.queryNames(null, null);
	    System.out.println(mbeans.size() + " MBeans found\n");
	    for (ObjectName mbeanName : mbeans) 
	    {
	        System.out.println("- " + mbeanName);
	    }
	    
	    jmxConn.close();

	  }
}
