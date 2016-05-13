package traceback.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "t_bi_breed")
public class Breed
{
    private String wid;

    private String breedName;

    
    @Id
    @Column(name="WID",unique=true)
    public String getWid()
    {
        return wid;
    }

    public void setWid(String wid)
    {
        this.wid = wid;
    }

    
    @Column(name="BREED_NAME",length=50)
    public String getBreedName()
    {
        return breedName;
    }

    public void setBreedName(String breedName)
    {
        this.breedName = breedName;
    }

}
