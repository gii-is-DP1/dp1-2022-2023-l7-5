package org.springframework.samples.petclinic.scoreboard;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/scoreboards")
public class ScoreBoardController {
    private final String  ScoreBoard_LISTING_VIEW = "/scoreboard/ScoreBoardListing";
    private final String  ScoreBoard_FORM = "/scoreboard/createOrUpdateScoreBoardForm";
    
    private ScoreBoardService service;
    
    @Autowired
    public ScoreBoardController(ScoreBoardService service) {
    	this.service = service;
    }
    @Transactional(readOnly = true)
    @GetMapping("/ScoreBoardsListing")
    public ModelAndView showScoreBoards() {
    	ModelAndView mav = new ModelAndView(ScoreBoard_LISTING_VIEW);
    	mav.addObject("scoreboard", service.getScoreBoards());
    	return mav;
    }
    
    @Transactional()
    @GetMapping("/{id}/delete")
    public ModelAndView deleteScoreBoard(@PathVariable int id){
        service.deleteScoreBoardById(id);        
        return showScoreBoards();
    }
    
    @Transactional(readOnly = true)
    @GetMapping("/{id}//edit")
    public ModelAndView editScoreboard(@PathVariable int id){
    	ScoreBoard scoreboard = service.getScoreBoardById(id);        
        ModelAndView result = new ModelAndView(ScoreBoard_FORM);
        result.addObject("scoreboard", scoreboard);
        return result;
    }
    
    @Transactional
    @PostMapping("/{id}/edit")
    public ModelAndView saveTile(@PathVariable int id, @Valid ScoreBoard scoreboard, BindingResult br){
    	if (br.hasErrors()) {
    		return new ModelAndView(ScoreBoard_FORM, br.getModel());
    	}
    	ScoreBoard scoreboardToBeUpdated=service.getScoreBoardById(id);
        BeanUtils.copyProperties(scoreboard,scoreboardToBeUpdated,"id");
        service.save(scoreboardToBeUpdated);
        return showScoreBoards();
    }
    
    @Transactional(readOnly = true)
    @GetMapping("/new")
    public ModelAndView createScoreboard() {
    	ScoreBoard scoreboard = new ScoreBoard();
    	ModelAndView mav = new ModelAndView(ScoreBoard_FORM);
    	mav.addObject(scoreboard);
    	return mav;
    }
    
    @Transactional
    @PostMapping("/new")
    public ModelAndView saveNewScoreboard(@Valid ScoreBoard scoreboard, BindingResult br){
    	if (br.hasErrors()) {
    		return new ModelAndView(ScoreBoard_FORM, br.getModel());
    	}
        service.save(scoreboard);
        ModelAndView result=showScoreBoards();
        result.addObject("message", "The scoreboard was created successfully");
        return result;
    }
}
