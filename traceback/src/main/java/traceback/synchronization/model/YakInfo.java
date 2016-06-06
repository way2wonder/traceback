package traceback.synchronization.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="yak_infomation")
public class YakInfo  
{
    private String nh;

    private String sex;

    private String breedCode;

    private Date birthday;

    private String appearance;

    private String herdsmanCode;

    private String departCode;

    private String state;

    private Date outDate;

    private String killflag;

    
    @Id
    @Column(name="NH",length=50,nullable=false)
    public String getNh()
    {
        return nh;
    }

    public void setNh(String nh)
    {
        this.nh = nh;
    }

    @Column(name="SEX",length=2)
    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    
    @Column(name="BREED_CODE",length=50)
    public String getBreedCode()
    {
        return breedCode;
    }

    public void setBreedCode(String breedCode)
    {
        this.breedCode = breedCode;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="BIRTHDAY")
    public Date getBirthday()
    {
        return birthday;
    }

    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }

    @Column(name="APPEARANCE",length=2)
    public String getAppearance()
    {
        return appearance;
    }

    public void setAppearance(String appearance)
    {
        this.appearance = appearance;
    }

    
    @Column(name="HERDSMAN_CODE",length=50)
    public String getHerdsmanCode()
    {
        return herdsmanCode;
    }

    public void setHerdsmanCode(String herdsmanCode)
    {
        this.herdsmanCode = herdsmanCode;
    }

    
    @Column(name="DEPART_CODE",length=50)
    public String getDepartCode()
    {
        return departCode;
    }

    public void setDepartCode(String departCode)
    {
        this.departCode = departCode;
    }

    
    @Column(name="STATE",length=2)
    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="OUT_DATE")
    public Date getOutDate()
    {
        return outDate;
    }

    public void setOutDate(Date outDate)
    {
        this.outDate = outDate;
    }

    
    
    @Column(name="KILL_FLAG",length=2)
    public String getKillflag()
    {
        return killflag;
    }

    public void setKillflag(String killflag)
    {
        this.killflag = killflag;
    }

}
