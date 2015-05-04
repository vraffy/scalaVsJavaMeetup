package it.svjm.textanalysis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import it.svjm.ner.model.NamedEntity.EntityType;
import it.svjm.textanalysis.model.AnalyzedText;
import it.svjm.textanalysis.model.Annotation;
import it.svjm.textanalysis.model.Entity;
import it.svjm.textanalysis.model.Mood;
import it.svjm.textanalysis.model.Polarity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public abstract class AnalyzedTextProcessorTest {

	private final AnalyzedTextProcessor processor;

	public AnalyzedTextProcessorTest() {
		processor = getProcessor();
	}

	/** extractMood */

	@Test
	public void extractMoodFromAnalyzedTextWithoutAnnotations() {

		final AnalyzedText analyzedText = new AnalyzedText(
				"testo senza annotazioni");

		final Mood mood = processor.extractMood(analyzedText);

		assertEquals(mood, Mood.NONE);
	}

	@Test
	public void extractMoodFromAnalyzedTextWithoutPolarityAnnotations() {

		final AnalyzedText analyzedText = new AnalyzedText(
				"questo testo parla di Torino, Cuneo e Einstein, ma non esprime sentimenti");
		analyzedText.addAnnotation(new Entity(0, 22, 28, EntityType.LOCATION,
				"https://it.wikipedia.org/wiki/Torino"));
		analyzedText.addAnnotation(new Entity(1, 30, 35, EntityType.LOCATION,
				"https://it.wikipedia.org/wiki/Cuneo"));
		analyzedText.addAnnotation(new Entity(2, 38, 46, EntityType.PERSON,
				"https://it.wikipedia.org/wiki/Einstein"));

		final Mood mood = processor.extractMood(analyzedText);

		assertEquals(mood, Mood.NONE);
	}

	@Test
	public void extractMoodFromAnalyzedTextWithNegativePolarity() {

		final AnalyzedText analyzedText = new AnalyzedText(
				"Torino è una delle città più inquinate d'Italia :(");
		analyzedText.addAnnotation(new Entity(0, 0, 6, EntityType.LOCATION,
				"https://it.wikipedia.org/wiki/Torino"));
		analyzedText.addAnnotation(new Polarity(0, 0, analyzedText.getText()
				.length(), 27));

		final Mood mood = processor.extractMood(analyzedText);

		assertEquals(mood, Mood.NEGATIVE);
	}

	@Test
	public void extractMoodFromAnalyzedTextWithPositivePolarity() {

		final AnalyzedText analyzedText = new AnalyzedText(
				"Einstein è stato un grande scienziato");
		analyzedText.addAnnotation(new Entity(0, 0, 8, EntityType.PERSON,
				"https://it.wikipedia.org/wiki/Einstein"));
		analyzedText.addAnnotation(new Polarity(0, 0, analyzedText.getText()
				.length(), 89));

		final Mood mood = processor.extractMood(analyzedText);

		assertEquals(mood, Mood.POSITIVE);
	}

	@Test
	public void extractMoodFromAnalyzedTextWithMultiplePolarity() {

		final AnalyzedText analyzedText = new AnalyzedText(
				"Mi piace vivere a Torino anche se purtroppo è molto inquinata");
		analyzedText.addAnnotation(new Entity(0, 18, 24, EntityType.LOCATION,
				"https://it.wikipedia.org/wiki/Torino"));
		analyzedText.addAnnotation(new Polarity(0, 0, analyzedText.getText()
				.length(), 95));
		analyzedText.addAnnotation(new Polarity(0, 0, analyzedText.getText()
				.length(), 27));

		final Mood mood = processor.extractMood(analyzedText);

		assertEquals(mood, Mood.POSITIVE);
	}

	/** indexPersons */

	@Test
	public void indexPersonsFromAnalyzedTextWithoutAnnotations() {

		final AnalyzedText analyzedText = new AnalyzedText(
				"testo senza annotazioni");

		Map<Integer, Annotation> personIdx = processor
				.indexPersons(analyzedText);

		assertEquals(personIdx.size(), 0);
	}

	@Test
	public void indexPersonsFromAnalyzedTextWithoutPersonAnnotations() {

		final AnalyzedText analyzedText = new AnalyzedText(
				"Mi piace vivere a Torino anche se purtroppo è molto inquinata");
		analyzedText.addAnnotation(new Entity(0, 18, 24, EntityType.LOCATION,
				"https://it.wikipedia.org/wiki/Torino"));

		Map<Integer, Annotation> personIdx = processor
				.indexPersons(analyzedText);

		assertEquals(personIdx.size(), 0);
	}

	@Test
	public void indexPersonsFromAnalyzedTextWithPersonAnnotations() {

		final AnalyzedText analyzedText = new AnalyzedText(
				"Il 29 settembre del 1901 nasceva a Roma Enrico Fermi. Paragonato a Galileo, Fermi è stato uno scienziato brillante. È morto a Chicago nel 1954.");
		analyzedText.addAnnotation(new Entity(0, 35, 39, EntityType.LOCATION,
				"https://it.wikipedia.org/wiki/Roma"));
		analyzedText.addAnnotation(new Entity(1, 40, 52, EntityType.PERSON,
				"https://it.wikipedia.org/wiki/Enrico_Fermi"));
		analyzedText.addAnnotation(new Entity(2, 67, 74, EntityType.PERSON,
				"https://it.wikipedia.org/wiki/Galileo"));
		analyzedText.addAnnotation(new Entity(3, 76, 80, EntityType.PERSON,
				"https://it.wikipedia.org/wiki/Enrico_Fermi"));
		analyzedText.addAnnotation(new Entity(4, 126, 133, EntityType.LOCATION,
				"https://it.wikipedia.org/wiki/Chicago"));

		Map<Integer, Annotation> personIdx = processor
				.indexPersons(analyzedText);

		assertEquals(personIdx.size(), 3);

		assertEquals(personIdx.get(40).getId(), 1);
		assertEquals(personIdx.get(76).getId(), 3);
		assertEquals(personIdx.get(67).getId(), 2);
	}

	/** indexLocations */

	@Test
	public void indexLocationsFromAnalyzedTextWithoutAnnotations() {

		final AnalyzedText analyzedText = new AnalyzedText(
				"testo senza annotazioni");

		Map<Integer, Annotation> locationIdx = processor
				.indexLocations(analyzedText);

		assertEquals(locationIdx.size(), 0);
	}

	@Test
	public void indexLocationsFromAnalyzedTextWithoutLocationAnnotations() {

		final AnalyzedText analyzedText = new AnalyzedText(
				"Einstein è stato un grande scienziato");
		analyzedText.addAnnotation(new Entity(0, 0, 8, EntityType.PERSON,
				"https://it.wikipedia.org/wiki/Einstein"));

		Map<Integer, Annotation> locationIdx = processor
				.indexLocations(analyzedText);

		assertEquals(locationIdx.size(), 0);
	}

	@Test
	public void indexLocationsFromAnalyzedTextWithPersonAnnotations() {

		final AnalyzedText analyzedText = new AnalyzedText(
				"Il 29 settembre del 1901 nasceva a Roma Enrico Fermi. Paragonato a Galileo, Fermi è stato uno scienziato brillante. È morto a Chicago nel 1954.");
		analyzedText.addAnnotation(new Entity(0, 35, 39, EntityType.LOCATION,
				"https://it.wikipedia.org/wiki/Roma"));
		analyzedText.addAnnotation(new Entity(1, 40, 52, EntityType.PERSON,
				"https://it.wikipedia.org/wiki/Enrico_Fermi"));
		analyzedText.addAnnotation(new Entity(2, 67, 74, EntityType.PERSON,
				"https://it.wikipedia.org/wiki/Galileo"));
		analyzedText.addAnnotation(new Entity(3, 76, 80, EntityType.PERSON,
				"https://it.wikipedia.org/wiki/Enrico_Fermi"));
		analyzedText.addAnnotation(new Entity(4, 126, 133, EntityType.LOCATION,
				"https://it.wikipedia.org/wiki/Chicago"));

		Map<Integer, Annotation> locationIdx = processor
				.indexLocations(analyzedText);

		assertEquals(locationIdx.size(), 2);
		assertEquals(locationIdx.get(35).getId(), 0);
		assertEquals(locationIdx.get(126).getId(), 4);
	}

	/** indexShortEntities */

	@Test
	public void indexShortEntitiesFromAnalyzedTextWithoutAnnotations() {

		final AnalyzedText analyzedText = new AnalyzedText(
				"testo senza annotazioni");

		Map<Integer, Annotation> shortEntititiesIdx = processor
				.indexShortEntities(analyzedText, 10);

		assertEquals(shortEntititiesIdx.size(), 0);
	}

	@Test
	public void indexShortEntitiesFromAnalyzedTextWithMultipleAnnotations() {

		final AnalyzedText analyzedText = new AnalyzedText(
				"Il 29 settembre del 1901 nasceva a Roma Enrico Fermi. Paragonato a Galileo, Fermi è stato uno scienziato brillante. È morto a Chicago nel 1954.");
		analyzedText.addAnnotation(new Entity(0, 35, 39, EntityType.LOCATION,
				"https://it.wikipedia.org/wiki/Roma"));
		analyzedText.addAnnotation(new Entity(1, 40, 52, EntityType.PERSON,
				"https://it.wikipedia.org/wiki/Enrico_Fermi"));
		analyzedText.addAnnotation(new Entity(2, 67, 74, EntityType.PERSON,
				"https://it.wikipedia.org/wiki/Galileo"));
		analyzedText.addAnnotation(new Entity(3, 76, 80, EntityType.PERSON,
				"https://it.wikipedia.org/wiki/Enrico_Fermi"));
		analyzedText.addAnnotation(new Entity(4, 126, 133, EntityType.LOCATION,
				"https://it.wikipedia.org/wiki/Chicago"));

		Map<Integer, Annotation> shortEntititiesIdx = processor
				.indexShortEntities(analyzedText, 10);

		assertEquals(shortEntititiesIdx.size(), 4);
		assertEquals(shortEntititiesIdx.get(35).getId(), 0);
		assertEquals(shortEntititiesIdx.get(126).getId(), 4);
		assertEquals(shortEntititiesIdx.get(67).getId(), 2);
		assertEquals(shortEntititiesIdx.get(76).getId(), 3);
	}

	/** mostFrequentEntities */

	@Test
	public void mostFrequentEntitiesFromAnalyzedTextWithoutAnnotations() {

		final AnalyzedText analyzedText = new AnalyzedText(
				"testo senza annotazioni");

		Collection<String> entities = processor
				.mostFrequentEntities(analyzedText);

		assertEquals(entities.size(), 0);
	}

	@Test
	public void mostFrequentEntitiesFromAnalyzedTextWithSingleMaxFreqEntity() {

		final AnalyzedText analyzedText = new AnalyzedText(
				"Il 29 settembre del 1901 nasceva a Roma Enrico Fermi. Paragonato a Galileo, Fermi è stato uno scienziato brillante. È morto a Chicago nel 1954.");
		analyzedText.addAnnotation(new Entity(0, 35, 39, EntityType.LOCATION,
				"https://it.wikipedia.org/wiki/Roma"));
		analyzedText.addAnnotation(new Entity(1, 40, 52, EntityType.PERSON,
				"https://it.wikipedia.org/wiki/Enrico_Fermi"));
		analyzedText.addAnnotation(new Entity(2, 67, 74, EntityType.PERSON,
				"https://it.wikipedia.org/wiki/Galileo"));
		analyzedText.addAnnotation(new Entity(3, 76, 80, EntityType.PERSON,
				"https://it.wikipedia.org/wiki/Enrico_Fermi"));
		analyzedText.addAnnotation(new Entity(4, 126, 133, EntityType.LOCATION,
				"https://it.wikipedia.org/wiki/Chicago"));

		Collection<String> entities = processor
				.mostFrequentEntities(analyzedText);

		assertEquals(entities.size(), 1);
		assertEquals(entities.iterator().next(),
				"https://it.wikipedia.org/wiki/Enrico_Fermi");
	}

	@Test
	public void mostFrequentEntitiesFromAnalyzedTextWithMultipleMaxFreqEntities() {

		final AnalyzedText analyzedText = new AnalyzedText(
				"Il 29 settembre del 1901 nasceva a Roma Enrico Fermi. Paragonato a Galileo, Fermi è stato uno scienziato brillante. Nel 1927 occupa la cattedra di fisica teorica a Roma dove formerà il gruppo dei 'ragazzi di via Panisperna'.");
		analyzedText.addAnnotation(new Entity(0, 35, 39, EntityType.LOCATION,
				"https://it.wikipedia.org/wiki/Roma"));
		analyzedText.addAnnotation(new Entity(1, 40, 52, EntityType.PERSON,
				"https://it.wikipedia.org/wiki/Enrico_Fermi"));
		analyzedText.addAnnotation(new Entity(2, 67, 74, EntityType.PERSON,
				"https://it.wikipedia.org/wiki/Galileo"));
		analyzedText.addAnnotation(new Entity(3, 76, 80, EntityType.PERSON,
				"https://it.wikipedia.org/wiki/Enrico_Fermi"));
		analyzedText.addAnnotation(new Entity(4, 164, 168, EntityType.LOCATION,
				"https://it.wikipedia.org/wiki/Roma"));

		Collection<String> entities = processor
				.mostFrequentEntities(analyzedText);

		assertEquals(entities.size(), 2);

		Set<String> result = new HashSet<String>(entities);
		assertTrue(result.contains("https://it.wikipedia.org/wiki/Roma"));
		assertTrue(result
				.contains("https://it.wikipedia.org/wiki/Enrico_Fermi"));
	}

	protected abstract AnalyzedTextProcessor getProcessor();
}
