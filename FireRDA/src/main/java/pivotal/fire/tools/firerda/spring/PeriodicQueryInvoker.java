package pivotal.fire.tools.firerda.spring;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import pivotal.fire.tools.firerda.server.ServerConnection;
import pivotal.fire.tools.firerda.spring.exception.FireRDAException;
import pivotal.fire.tools.firerda.spring.queries.FireRDAQuery;

public class PeriodicQueryInvoker 
{
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private ServerConnection serverConnection;
    private int numOfIterations;
    private int pauseBetweenIterations;
    
    private List<FireRDAQuery> rdaQueries = new ArrayList<FireRDAQuery>();
    
  
    public PeriodicQueryInvoker() 
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

    public void setNumOfIterations(int numOfIterations)
    {
      this.numOfIterations = numOfIterations;
    }
  
    public int getNumOfIterations()
    {
      return numOfIterations;
    }
  
    public void setPauseBetweenIterations(int pauseBetweenIterations)
    {
      this.pauseBetweenIterations = pauseBetweenIterations;
    }
  
    public int getPauseBetweenIterations()
    {
      return pauseBetweenIterations;
    }
  
    public void run() throws InterruptedException
    {
        logger.log
          (Level.INFO, String.format("Started FireRDA at %s", new Date()));

        logger.log
          (Level.INFO, 
           String.format("-> Number of iterations = %s, Pause between runs = %s (seconds)", 
                       numOfIterations,
                       pauseBetweenIterations));
      
        List<Integer> iterationList = new ArrayList<Integer>();
        for (int i = 1; i <= numOfIterations; i++)
        {
          iterationList.add(i);
        }
        
        for (int i = 1; i <= numOfIterations; i++)
        {
          logger.log
            (Level.INFO, String.format("Performing iteration number %s", i));
          
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
          
          // only need to pause if not the final iteration
          if (i != numOfIterations)
          {
            // need to convert to milliseconds
            Thread.sleep(pauseBetweenIterations * 1000);
          }
          
        }
        
        logger.log
    		(Level.INFO, String.format("Ended FireRDA at %s", new Date()));

    }

}
