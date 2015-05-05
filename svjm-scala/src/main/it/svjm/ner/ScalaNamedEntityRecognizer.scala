package it.svjm.ner

import scala.collection.JavaConversions.asJavaCollection
import scala.collection.JavaConversions.iterableAsScalaIterable

class ScalaNamedEntityRecognizer(val neRepo: NamedEntityRepository) extends NamedEntityRecognizer {

  override def extractNamedEntities(tokens: java.util.List[String]) = extractNamedEntities(tokens, 1)

  override def extractNamedEntities(tokens: java.util.List[String], ngramSize: Int) = {
      
      // your code here!
      
      null;
  }

}
