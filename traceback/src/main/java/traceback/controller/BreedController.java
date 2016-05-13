package traceback.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import traceback.model.Breed;
import traceback.service.BreedService;

@Controller
public class BreedController
{
    private static final Logger logger = LogManager.getLogger(BreedController.class);
    
    @Autowired
    BreedService breedService;
    
    @RequestMapping("/breedlist")
    public ModelAndView list()
    {
        logger.info("begin");
        List<Breed> result =  breedService.queryAllBreed();
        ModelAndView view = new ModelAndView("breedList");
        view.addObject("breedList", result);
        logger.info("end");
        return view;
    }

    public BreedService getBreedService()
    {
        return breedService;
    }

    public void setBreedService(BreedService breedService)
    {
        this.breedService = breedService;
    }
    
    
}
