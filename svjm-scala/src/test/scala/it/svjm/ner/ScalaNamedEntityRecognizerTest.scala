package it.svjm.ner

import org.junit.Test

@Test
class ScalaNamedEntityRecognizerTest extends NamedEntityRecognizerTest {

  override def getNER(neRepo: NamedEntityRepository) = new ScalaNamedEntityRecognizer(neRepo)

}
