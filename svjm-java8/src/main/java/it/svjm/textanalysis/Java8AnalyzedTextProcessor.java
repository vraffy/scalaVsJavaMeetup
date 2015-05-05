package it.svjm.textanalysis;

import static it.svjm.ner.model.NamedEntity.EntityType.LOCATION;
import static it.svjm.ner.model.NamedEntity.EntityType.PERSON;
import static java.util.Comparator.naturalOrder;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import it.svjm.textanalysis.model.AnalyzedText;
import it.svjm.textanalysis.model.Annotation;
import it.svjm.textanalysis.model.Entity;
import it.svjm.textanalysis.model.Mood;
import it.svjm.textanalysis.model.Polarity;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Java8AnalyzedTextProcessor implements AnalyzedTextProcessor {

    @Override
    public Mood extractMood(final AnalyzedText analyzedText) {
    	
    	// your code here!
    	
        return null;
    }

    @Override
    public Map<Integer, Annotation> indexPersons(AnalyzedText analyzedText) {
    	
    	// your code here!
    	
        return null;
    }


    @Override
    public Map<Integer, Annotation> indexLocations(AnalyzedText analyzedText) {
    	
    	// your code here!
    	
        return null;
    }


    @Override
    public Map<Integer, Annotation> indexShortEntities(AnalyzedText analyzedText, int maxLength) {
    	
    	// your code here!
    	
        return null;
    }

    private Map<Integer, Annotation> indexEntities(AnalyzedText analyzedText, Predicate<Annotation> predicate) {
    	
    	// your code here!
    	
        return null;
    }


    @Override
    public Collection<String> mostFrequentEntities(AnalyzedText analyzedText) {

        Stream<Entity> entities = analyzedText.getAnnotations()
                .stream()
                .filter(a -> (a instanceof Entity))
                .map(a -> (Entity) a);

        Map<String, Long> entitiesFreq = entities.collect(groupingBy(Entity::getIri, counting()));

        Optional<Long> maxFreq = entitiesFreq.values().stream().max(naturalOrder());

        return entitiesFreq.keySet().stream().filter(iri -> entitiesFreq.get(iri).equals(maxFreq.get())).collect(toList());
    }


}
