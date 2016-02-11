package com.pedrovalencia;

import com.pedrovalencia.dictionary.exceptions.DictionaryServiceException;
import com.pedrovalencia.dictionary.services.DictionaryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class DictionaryApplicationTests {

	private MockMvc mvc;

    @Mock
	private DictionaryService dictionaryService;

	@Before
	public void setUp() throws Exception {
		dictionaryService = Mockito.mock(DictionaryService.class);
        DictionaryApplication controller = new DictionaryApplication();
		controller.setDictionaryService(dictionaryService);
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void getPhrasesOK() throws Exception {

		List<String> phrases = Arrays.asList("headache", "sore throat");

		when(dictionaryService.findInDictionary("whatever")).thenReturn(phrases);

		mvc.perform(MockMvcRequestBuilders.get("/dictionary/whatever"))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[\"headache\",\"sore throat\"]")));
	}

	@Test
	public void getTestEmptyResult() throws Exception {

		List<String> phrases =  new ArrayList<>();

		when(dictionaryService.findInDictionary("whatever")).thenReturn(phrases);

		mvc.perform(MockMvcRequestBuilders.get("/dictionary/whatever"))
				.andExpect(status().isNotFound())
				.andExpect(content().string(equalTo("")));
	}

	@Test(expected=Exception.class)
	public void getTestNoOK() throws Exception{

		List<String> phrases = Arrays.asList("headache", "sore throat");

		when(dictionaryService.findInDictionary("whatever")).thenThrow(new DictionaryServiceException("Error", new Throwable()));

		mvc.perform(MockMvcRequestBuilders.get("/dictionary/whatever"))
				.andExpect(content().string(equalTo("")));
	}


}
