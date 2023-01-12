package org.springframework.samples.petclinic.cell;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class CellController {
	private final String CELLS_LISTING_VIEW = "cells/CellsListing";
	
	private CellService service;
	
	@Autowired
	public CellController(CellService service) {
		this.service = service;
	}
	@GetMapping(value = "/cells/CellsListing")
	public ModelAndView showCells() {
		ModelAndView mav = new ModelAndView(CELLS_LISTING_VIEW);
		mav.addObject("cells", service.getCells());
		return mav;
	}
}
