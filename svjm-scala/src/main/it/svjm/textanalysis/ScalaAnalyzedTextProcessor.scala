package it.svjm.textanalysis

import java.util.Collections.emptySet
import scala.collection.JavaConversions.setAsJavaSet
import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.JavaConversions.mapAsJavaMap
import it.svjm.textanalysis.model.AnalyzedText
import it.svjm.textanalysis.model.Annotation
import it.svjm.textanalysis.model.Entity
import it.svjm.textanalysis.model.Mood
import it.svjm.textanalysis.model.Polarity
import it.svjm.ner.model.NamedEntity.EntityType

class ScalaAnalyzedTextProcessor extends AnalyzedTextProcessor {

  override def extractMood(analyzedText: AnalyzedText) = {
      
      // your code here!
      
      null;
  }

  override def indexPersons(analyzedText: AnalyzedText) = {
      
      // your code here!
      
      null;
  }

  override def indexLocations(analyzedText: AnalyzedText) = {
      
      // your code here!
      
      null;
  }
  override def indexShortEntities(analyzedText: AnalyzedText, maxLength: Int) = {
      
      // your code here!
      
      null;
  }

  private def indexEntities(analyzedText: AnalyzedText, predicate: Annotation => Boolean) = {
      
      // your code here!
      
      null;
  }

  override def mostFrequentEntities(analyzedText: AnalyzedText) = {
      
      // your code here!
      
      null;
  }

}