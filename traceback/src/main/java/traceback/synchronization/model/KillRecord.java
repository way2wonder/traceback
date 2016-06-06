package traceback.synchronization.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="T_BUS_KILLRECORDS")
public class KillRecord  implements  Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String nh;
    private String batchNo;
    private String killMan;
    private String coarseSegmentMan;
    private String legPackMan;
    private String bonePackMan;
    private String state;

    
    private Date killTime;
    private Date coarseSegmentTime;
    private Date legPackTime;
    private Date bonePackTime;
    
    private Date mostRecent;
    
    @Id
    @Column(name="NH",length=50)
    public String getNh()
    {
        return nh;
    }
    public void setNh(String nh)
    {
        this.nh = nh;
    }
    
    @Column(name="BATCHNO",length=20)
    public String getBatchNo()
    {
        return batchNo;
    }
    public void setBatchNo(String batchNo)
    {
        this.batchNo = batchNo;
    }
    
    
    @Column(name="KILL_MAN",length=50)
    public String getKillMan()
    {
        return killMan;
    }
    public void setKillMan(String killMan)
    {
        this.killMan = killMan;
    }
    
    
    @Column(name="COARSE_SEGMENT_MAN",length=50)
    public String getCoarseSegmentMan()
    {
        return coarseSegmentMan;
    }
    public void setCoarseSegmentMan(String coarseSegmentMan)
    {
        this.coarseSegmentMan = coarseSegmentMan;
    }
    
    
    @Column(name="LEG_PACK_MAN",length=50)
    public String getLegPackMan()
    {
        return legPackMan;
    }
    public void setLegPackMan(String legPackMan)
    {
        this.legPackMan = legPackMan;
    }
    
    @Column(name="BONE_PACK_MAN",length=50)
    public String getBonePackMan()
    {
        return bonePackMan;
    }
    public void setBonePackMan(String bonePackMan)
    {
        this.bonePackMan = bonePackMan;
    }
    
    
    
    @Column(name="STATE",length=1)
    public String getState()
    {
        return state;
    }
    public void setState(String state)
    {
        this.state = state;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="KILL_TIME")
    public Date getKillTime()
    {
        return killTime;
    }
    public void setKillTime(Date killTime)
    {
        this.killTime = killTime;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="COARSE_SEGMENT_TIME")
    public Date getCoarseSegmentTime()
    {
        return coarseSegmentTime;
    }
    public void setCoarseSegmentTime(Date coarseSegmentTime)
    {
        this.coarseSegmentTime = coarseSegmentTime;
    }
    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="LEG_PACK_TIME")
    public Date getLegPackTime()
    {
        return legPackTime;
    }
    public void setLegPackTime(Date legPackTime)
    {
        this.legPackTime = legPackTime;
    }
    
    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="BONE_PACK_TIME")
    public Date getBonePackTime()
    {
        return bonePackTime;
    }
    public void setBonePackTime(Date bonePackTime)
    {
        this.bonePackTime = bonePackTime;
    }
    
    
    @Transient
    public Date getMostRecent()
    {
        return mostRecent;
    }
    public void setMostRecent(Date mostRecent)
    {
        this.mostRecent = mostRecent;
    }
}
