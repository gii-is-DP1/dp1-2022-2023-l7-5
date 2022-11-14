package org.springframework.samples.petclinic.game;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

public class ModeFormatter implements Formatter<Mode> {
	
	private final GameService gamService;
	
	@Autowired
	public ModeFormatter(GameService gameService) {
		this.gamService = gameService;
	}
	
	@Override
	public String print(Mode mode, Locale locale) {
		return mode.getName();
	}

	@Override
	public Mode parse(String text, Locale locale) throws ParseException {
		Collection<Mode> findModes = this.gamService.findModes();
		for (Mode mode : findModes) {
			if (mode.getName().equals(text)) {
				return mode;
			}
		}
		throw new ParseException("mode not found: " + text, 0);
	}
}