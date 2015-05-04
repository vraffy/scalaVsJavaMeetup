package it.svjm.ner

import org.junit.Test
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ScalaNamedEntityRecognizerTest extends NamedEntityRecognizerTest {

  override def getNER(neRepo: NamedEntityRepository) = new ScalaNamedEntityRecognizer(neRepo)

}
