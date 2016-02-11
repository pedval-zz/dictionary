package com.pedrovalencia.dictionary.services;

import java.io.IOException;
import java.util.List;

/**
 * Created by pedrovalencia on 11/02/2016.
 */
public interface DictionaryService {

    List<String> findInDictionary(String phrase);
}
