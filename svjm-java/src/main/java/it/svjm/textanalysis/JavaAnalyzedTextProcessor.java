package it.svjm.textanalysis;

import it.svjm.ner.model.NamedEntity.EntityType;
import it.svjm.textanalysis.model.AnalyzedText;
import it.svjm.textanalysis.model.Annotation;
import it.svjm.textanalysis.model.Entity;
import it.svjm.textanalysis.model.Mood;
import it.svjm.textanalysis.model.Polarity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class JavaAnalyzedTextProcessor implements AnalyzedTextProcessor {

    @Override
    public Mood extractMood(final AnalyzedText analyzedText) {
    	
    	// your code here!
    	
        return null;
    }

    public Mood extractMood_UGLIEST(final AnalyzedText analyzedText) {
    	
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


    @Override
    public Collection<String> mostFrequentEntities(AnalyzedText analyzedText) {
    	
    	// your code here!
    	
        return null;
    }


}
