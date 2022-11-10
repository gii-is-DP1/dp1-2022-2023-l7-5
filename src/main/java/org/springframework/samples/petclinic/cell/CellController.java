package org.springframework.samples.petclinic.cell;

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

public class CellController {
	private final String CELLS_LISTING_VIEW = "cells/CellsListing";
	private final String CELLS_FORM = "cells/createOrUpdateCellsForm";
	
	private CellService service;
	
	@Autowired
	public CellController(CellService service) {
		this.service = service;
	}
	@Transactional(readOnly = true)
	@GetMapping(value = "/cells/CellsListing")
	public ModelAndView showCells() {
		ModelAndView mav = new ModelAndView(CELLS_LISTING_VIEW);
		mav.addObject("cells", service.getCells());
		return mav;
	}
	
	@Transactional()
	@GetMapping("/{id}/delete")
	public ModelAndView deleteCell(@PathVariable int id) {
		service.deleteCellById(id);
		return showCells();
	}
	
	@Transactional(readOnly = true)
	@PostMapping("/{id}/edit")
	public ModelAndView editCell(@PathVariable int id) {
		Cell cell = service.getCellById(id);
		ModelAndView result = new ModelAndView(CELLS_FORM);
		result.addObject("cells", cell);
		return result;
	}
	
	@Transactional
	@GetMapping("/{id}/edit")
	public ModelAndView saveCell(@PathVariable int id, @Valid Cell cell, BindingResult br) {
		if(br.hasErrors()) {
			return new ModelAndView(CELLS_FORM, br.getModel());
		}
		Cell cellToBeUpdated = service.getCellById(id);
		BeanUtils.copyProperties(cell, cellToBeUpdated, "id");
		service.save(cellToBeUpdated);
		return showCells();
	}
	
	@GetMapping(value = "/cells/new")
	public ModelAndView createCell() {
		Cell cell = new Cell();
		ModelAndView mav = new ModelAndView(CELLS_FORM);
		mav.addObject(cell);
		return mav;
	}
	
	
	@PostMapping(value = "/cells/new")
	public ModelAndView saveNewCell(@Valid Cell cell, BindingResult br) {
		if(br.hasErrors()) {
			return new ModelAndView(CELLS_FORM, br.getModel());
		}
		service.save(cell);
		ModelAndView result = showCells();
		result.addObject("message", "The cell was created successfully");
		return result;
	}
}
