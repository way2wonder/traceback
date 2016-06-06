package traceback.synchronization.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_bus_printrecords")
public class PrintRecord  implements  Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Long wid;
    private String nh;
    private String product_code;
    private Float weight;
    private String unit;
    private Date print_time;
    private Integer print_num;
    private String print_man;
    private String state;
    @Id
    @Column(name="wid",length=20)
    public Long getWid()
    {
        return wid;
    }
    public void setWid(Long wid)
    {
        this.wid = wid;
    }
    
    
    @Column(name="nh",length=50)
    public String getNh()
    {
        return nh;
    }
    public void setNh(String nh)
    {
        this.nh = nh;
    }
    
    @Column(name="product_code",length=2)
    public String getProduct_code()
    {
        return product_code;
    }
    public void setProduct_code(String product_code)
    {
        this.product_code = product_code;
    }
    
    
    @Column(name="weight",length=8)
    public Float getWeight()
    {
        return weight;
    }
    public void setWeight(Float weight)
    {
        this.weight = weight;
    }
    
    @Column(name="unit",length=10)
    public String getUnit()
    {
        return unit;
    }
    public void setUnit(String unit)
    {
        this.unit = unit;
    }
    
    @Column(name="print_time")
    public Date getPrint_time()
    {
        return print_time;
    }
    public void setPrint_time(Date print_time)
    {
        this.print_time = print_time;
    }
    
    @Column(name="print_num",length=4)
    public Integer getPrint_num()
    {
        return print_num;
    }
    public void setPrint_num(Integer print_num)
    {
        this.print_num = print_num;
    }
    
    @Column(name="print_man",length=20)
    public String getPrint_man()
    {
        return print_man;
    }
    public void setPrint_man(String print_man)
    {
        this.print_man = print_man;
    }
    
    @Column(name="state",length=4)
    public String getState()
    {
        return state;
    }
    public void setState(String state)
    {
        this.state = state;
    }
}
