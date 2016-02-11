package com.pedrovalencia.dictionary.services;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by pedrovalencia
 * on 11/02/2016.
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {
    @Override
    public List<String> findInDictionary(String phrase)  {
        List<String> results;

        try (BufferedReader reader = Files.newBufferedReader(
                Paths.get(this.getClass().getResource("/phrases").toURI()),
                StandardCharsets.UTF_8)) {

            results = reader
                    .lines()
                    .filter(line -> phrase.contains(line))
                    .collect(Collectors.toList());


        } catch (IOException| URISyntaxException ex) {
            //TODO to log exception
            return new ArrayList<>();
        }

        return results;
    }
}
