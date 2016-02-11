package com.pedrovalencia.dictionary.services;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

/**
 * Created by pedrovalencia
 * on 11/02/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
public class DictionaryServiceImplTest {


    private DictionaryServiceImpl dictionaryService;

    @Before
    public void setup() throws Exception {
        dictionaryService = new DictionaryServiceImpl();
    }

    @Test
    public void testWithEmptyQuery() throws Exception {
        List<String> phrasesFound = dictionaryService.findInDictionary("");

        Assert.assertNotNull(phrasesFound);
        Assert.assertEquals(phrasesFound.size(),0);
    }

    @Test
    public void testWithNoResult() throws Exception {
        List<String> phrasesFound = dictionaryService.findInDictionary("Example");

        Assert.assertNotNull(phrasesFound);
        Assert.assertEquals(phrasesFound.size(),0);
    }

    @Test
    public void testWithResults() throws Exception {
        List<String> phrasesFound =
                dictionaryService.findInDictionary("I have a sore throat and headache.");

        Assert.assertNotNull(phrasesFound);
        Assert.assertEquals(phrasesFound.size(),2);
        Assert.assertTrue(phrasesFound.contains("sore throat"));
        Assert.assertTrue(phrasesFound.contains("headache"));


    }

}
