package it.svjm.ner;

import static java.lang.Math.max;
import it.svjm.ner.model.NamedEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Java8NamedEntityRecognizer implements NamedEntityRecognizer {

    private final NamedEntityRepository neRepo;

    public Java8NamedEntityRecognizer(NamedEntityRepository neRepo) {
        this.neRepo = neRepo;
    }

    @Override
    public Collection<NamedEntity> extractNamedEntities(List<String> tokens) {
    	
    	// your code here!
    	
        return null;
    }

    @Override
    public Collection<NamedEntity> extractNamedEntities(List<String> tokens, int ngramSize) {
    	
    	// your code here!
    	
        return null;
    }

}
