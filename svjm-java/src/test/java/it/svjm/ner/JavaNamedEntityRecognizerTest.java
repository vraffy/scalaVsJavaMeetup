package it.svjm.ner;


public class JavaNamedEntityRecognizerTest extends NamedEntityRecognizerTest {

    @Override
    protected NamedEntityRecognizer getNER(NamedEntityRepository neRepo) {
        return new JavaNamedEntityRecognizer(neRepo);
    }
}
