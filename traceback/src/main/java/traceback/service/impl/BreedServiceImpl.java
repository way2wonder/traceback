package traceback.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import traceback.dao.BreedDao;
import traceback.model.Breed;
import traceback.service.BreedService;


@Service("breedService")
@Transactional(propagation = Propagation.REQUIRED)
public class BreedServiceImpl  implements  BreedService
{   
    @Autowired
    BreedDao breedDao;
    
    @Override
    //@DataSource("local")
    public List<Breed> queryAllBreed()
    {
        return breedDao.queryAllBread();
    }


    @Override
    //@DataSource("server")
    public List<Breed> queryServerBreed()
    {
        return breedDao.queryAllBread();
    }
  
}
