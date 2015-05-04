package it.svjm.textanalysis;

import it.svjm.textanalysis.AnalyzedTextProcessor;
import it.svjm.textanalysis.AnalyzedTextProcessorTest;
import it.svjm.textanalysis.JavaAnalyzedTextProcessor;

public class JavaAnalyzedTextProcessorTest extends AnalyzedTextProcessorTest {

    @Override
    protected AnalyzedTextProcessor getProcessor() {
        return new JavaAnalyzedTextProcessor();
    }

}
