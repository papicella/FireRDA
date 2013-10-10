package pivotal.fire.tools.firerda.spring;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import pivotal.fire.tools.firerda.server.ServerConnection;
import pivotal.fire.tools.firerda.spring.exception.FireRDAException;
import pivotal.fire.tools.firerda.spring.queries.FireRDAQuery;

/**
 * @author  Pas Apicella
 * @version 1.0 
 */
public class QueryInvoker 
{
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private ServerConnection serverConnection;
    
    private List<FireRDAQuery> rdaQueries = new ArrayList<FireRDAQuery>();
    
    public QueryInvoker() 
    {
      logger.setLevel(Level.ALL);
    }
    
    /**
     * @param rdaQueries the rdaQueries to set
     */
    public void setRdaQueries(List rdaQueries) 
    {
      this.rdaQueries = rdaQueries;
    }
  
    /**
     * @param mbeanServerConnection the mbeanServerConnection to set
     */
    public void setServerConnection(ServerConnection serverConnection) 
    {
      this.serverConnection = serverConnection;
    }
    
    public int getQueryCount() 
    {
      return rdaQueries.size();
    }
    
    public void run()
    {
        logger.log
        	(Level.INFO, String.format("Started FireRDA  at %s", new Date()));
        
        for(FireRDAQuery query: rdaQueries) 
        {  
          try 
          {
              System.out.println
                (query.invoke(serverConnection.getConnection())); 
          } 
          catch (FireRDAException e) 
          {
              logger.log(Level.SEVERE, "Error invoking query", e);
          }
        }   
        
        logger.log
    		(Level.INFO, String.format("Ended FireRDA  at %s", new Date()));
    }
}
