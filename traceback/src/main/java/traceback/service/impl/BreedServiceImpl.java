package traceback.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import traceback.dao.BreedDao;
import traceback.model.Breed;
import traceback.service.BreedService;


@Service("breedService")
@Transactional
public class BreedServiceImpl  implements  BreedService
{
    @Autowired
    BreedDao breedDao;
    
    
    @Override
    public List<Breed> queryAllBreed()
    {
        return breedDao.queryAllBread();
    }
  
}
