package pivotal.fire.tools.firerda.spring;

import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import pivotal.fire.tools.firerda.server.ServerConnection;

public class MBeanViewer
{
  private final Logger logger = Logger.getLogger(this.getClass().getName());
  private ServerConnection serverConnection;
  
  public MBeanViewer()
  {
    logger.setLevel(Level.ALL);
  }

  public void setServerConnection(ServerConnection serverConnection)
  {
    this.serverConnection = serverConnection;
  }
  
  public void run() throws Exception
  {
    MBeanServerConnection mBeanServerConnection = serverConnection.getConnection();
    
    Set<ObjectName> mbeans = mBeanServerConnection.queryNames(null, null);
    System.out.println(mbeans.size() + " MBeans found\n");
    for (ObjectName mbeanName : mbeans) 
    {
        System.out.println("- " + mbeanName);
    }
  }
}
