package traceback.scrollScreen.model;


import java.util.Date;


public class ActionDisplay
{
    private String nh;
    private String actionName;

    private Date actionTime;

    public String getActionName()
    {
        return actionName;
    }

    public void setActionName(String actionName)
    {
        this.actionName = actionName;
    }

    public Date getActionTime()
    {
        return actionTime;
    }

    public void setActionTime(Date actionTime)
    {
        this.actionTime = actionTime;
    }

    public String getNh()
    {
        return nh;
    }

    public void setNh(String nh)
    {
        this.nh = nh;
    }
    
}
