package pivotal.fire.tools.firerda.spring.queries;

import javax.management.MBeanServerConnection;

import pivotal.fire.tools.firerda.spring.exception.FireRDAException;

public interface FireRDAQuery 
{
    public Object invoke(MBeanServerConnection mbs) throws FireRDAException;
    public void setMBeanName(String mbeanName);
}
