package it.svjm.textanalysis

import org.junit.Test

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ScalaAnalyzedTextProcessorTest extends AnalyzedTextProcessorTest {

  override def getProcessor() = new ScalaAnalyzedTextProcessor

}


