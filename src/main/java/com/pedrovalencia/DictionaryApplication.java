package com.pedrovalencia;

import com.pedrovalencia.dictionary.exceptions.NotFoundException;
import com.pedrovalencia.dictionary.services.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/dictionary")
public class DictionaryApplication {

	private DictionaryService dictionaryService;

	/**
	 * GET all matching phrases from the dictionary
	 * @param query
	 * @return
     */
	@RequestMapping(value="/{query}", method= RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getPhrases(@PathVariable String query) {

		List<String> phrases = dictionaryService.findInDictionary(query);
		if(phrases.isEmpty()) {
			throw new NotFoundException("No results in the query");
		}
		return dictionaryService.findInDictionary(query);
	}

	@Autowired
	public void setDictionaryService(DictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DictionaryApplication.class, args);
	}
}
