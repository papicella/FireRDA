package pivotal.fire.tools.firerda.spring.exception;

/*
 * @author  Pas Apicella
 * @version 1.0 
*/
@SuppressWarnings("serial")
public class FireRDAException extends Exception
{
    public FireRDAException(String message) 
    {
        super(message);
    }

    public FireRDAException(Throwable cause)
    {
        super(cause);
    }

    public FireRDAException(String message, Throwable cause) 
    {
        super(message, cause);
    }
}
