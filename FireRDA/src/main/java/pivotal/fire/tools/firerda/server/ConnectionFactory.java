package pivotal.fire.tools.firerda.server;

import pivotal.fire.tools.firerda.server.connections.FireConnection;

/**
 * @author  Pas Apicella
 * @version 1.0
 */
public class ConnectionFactory 
{
	  public static ServerConnection getFireConnection() throws Exception
	  {
	    return (ServerConnection) FireConnection.getInstance(); 
	  }
}
