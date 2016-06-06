package traceback.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import traceback.common.AbstractDao;
import traceback.model.Breed;

@Repository("breedDao")
public class BreedDao  extends AbstractDao<String, Breed>
{
    public List<Breed>  queryAllBread()
    {
        Criteria criteria = createEntityCriteria();
        return (List<Breed>)criteria.list();
    }
}
