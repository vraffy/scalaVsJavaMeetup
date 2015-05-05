package it.svjm.ner;

import it.svjm.ner.model.NamedEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JavaNamedEntityRecognizer implements NamedEntityRecognizer {

	private final NamedEntityRepository neRepo;

	public JavaNamedEntityRecognizer(NamedEntityRepository neRepo) {
		this.neRepo = neRepo;
	}

	@Override
	public Collection<NamedEntity> extractNamedEntities(List<String> tokens) {
		
		// your code here!
		
		return null;
	}

	@Override
	public Collection<NamedEntity> extractNamedEntities(List<String> tokens,
			int ngramSize) {
		
		// your code here!
		
		return null;
	}

}
