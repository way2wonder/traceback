package traceback.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import traceback.model.Breed;

@Repository("breedDao")
public class BreedDao  extends AbstractDao<String, Breed>
{
    public List<Breed>  queryAllBread()
    {
        org.hibernate.Criteria criteria = createEntityCriteria();
        return (List<Breed>)criteria.list();
    }
}
