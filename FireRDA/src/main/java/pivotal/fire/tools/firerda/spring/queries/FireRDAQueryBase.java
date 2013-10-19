package pivotal.fire.tools.firerda.spring.queries;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.ReflectionException;

import pivotal.fire.tools.firerda.spring.exception.FireRDAException;

public abstract class FireRDAQueryBase implements FireRDAQuery
{
	protected String mBeanName = null;
	private List<String> nukedAttributes = new ArrayList<String>();
	  
	public FireRDAQueryBase()
	{
	}
	
    public Object invoke(MBeanServerConnection mbs) throws FireRDAException
    {

    	List<Map> attrs = null;
    	
    	ObjectName objectName = null;
    	
		try 
		{
			objectName = createObjectName(this.mBeanName);
	    	attrs = createAttributeData(mbs, objectName);	    	
	    	
		} 
		catch (Exception e) 
		{
		      throw new FireRDAException
		        (String.format("Error invoking mbean [%s]", this.mBeanName), e);
		}

    	
    	return attrs;
    }
    
    public void setMBeanName(String mBeanName)
    {
      this.mBeanName = mBeanName.trim();
    }
    
    /**
     * Create an ObjectName from the MBean Spring definition
     * @param mBeanName MBean name
     * @return An ObjectName used to invoke the MBean
     * @throws Exception
     */
    protected ObjectName createObjectName(String mBeanName) throws Exception 
    {
      ObjectName objectName = new ObjectName(mBeanName);
      return objectName;
    }

    protected List<Map> createAttributeData
    (MBeanServerConnection mbs, ObjectName objectName) throws Exception 
    {
      StringBuffer buffer = new StringBuffer();
      MBeanInfo info = mbs.getMBeanInfo(objectName);
      ArrayList attributeList = new ArrayList();
      Map map = null;
      
      for (MBeanAttributeInfo attr : info.getAttributes()) 
      {
          if (!nukeIt(attr.getName())) 
          {   
              // Add some basic formatting
              map = new HashMap();
              map.put("spacer1", calcspaces(30,attr.getType()));
              map.put("type", attr.getType());
              map.put("name", attr.getName());
              map.put("spacer2", calcspaces(30,attr.getName()) + ":");
              map.put("value", displayAttribute(mbs, attr, objectName));
              
              attributeList.add(map);
          } 
      }
      
      return attributeList;
    }

    private Object displayAttribute 
      (MBeanServerConnection mbs, MBeanAttributeInfo attr, ObjectName objectName)
      throws MBeanException, 
             AttributeNotFoundException,
             InstanceNotFoundException, 
             ReflectionException, 
             IOException
    {
      StringBuffer sb = new StringBuffer();
      int j = 0;

      try
      {
        if (mbs.getAttribute(objectName, attr.getName()) instanceof String[])
        {
          String[] data =
            (String[]) mbs.getAttribute(objectName, attr.getName());
          for (String s: data)
          {
            j++;
            if (data.length != j)
            {
              sb.append(s + ", ");
            }
            else
            {
              sb.append(s);
            }
          }
        }
        else if (mbs.getAttribute(objectName, attr.getName()) instanceof
                 int[])
        {
          int[] data = (int[]) mbs.getAttribute(objectName, attr.getName());
          for (int i: data)
          {
            j++;
            if (data.length != j)
            {
              sb.append(i + ", ");
            }
            else
            {
              sb.append(i);
            }
          }
        }
        else if (mbs.getAttribute(objectName, attr.getName()) instanceof
                 Integer[])
        {
          Integer[] data =
            (Integer[]) mbs.getAttribute(objectName, attr.getName());
          for (Integer i: data)
          {
            j++;
            if (data.length != j)
            {
              sb.append(i + ", ");
            }
            else
            {
              sb.append(i);
            }
          }
        }
        else if (mbs.getAttribute(objectName, attr.getName()) instanceof
                 javax.management.ObjectName[])
        {
          javax.management.ObjectName[] data =
            (javax.management.ObjectName[]) mbs.getAttribute(objectName,
                                                             attr.getName());

          for (javax.management.ObjectName o: data)
          {
            j++;
            if (data.length != j)
            {
              sb.append(o + ", ");
            }
            else
            {
              sb.append(o);
            }
          }
        }
        else
        {
          return mbs.getAttribute(objectName, attr.getName()) == null? 
            "" : mbs.getAttribute(objectName, attr.getName());
        }
      }
      catch (Exception e)
      {
        // display why we can't access the attribute but still continue
        sb.append(e.getMessage());
      }
      
      return sb.toString();
    }
    
    private String calcspaces(int width, String in) 
    {
        StringBuffer buf = new StringBuffer();
        
        for(int i=0; i<width-in.length();i++) 
        {
            buf.append(" ");
        }
    
        return buf.toString();    
    }

    protected boolean nukeIt(String attr) 
    {  
      return nukedAttributes.contains(attr);     
    }

    public String getMBeanName()
    {
      return mBeanName;
    }

    public void setNukedAttributes(List<String> nukedAttributes)
    {
      this.nukedAttributes = nukedAttributes;
    }

    public List<String> getNukedAttributes()
    {
      return nukedAttributes;
    }


}
