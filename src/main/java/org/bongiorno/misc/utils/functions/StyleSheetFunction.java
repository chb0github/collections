package org.bongiorno.misc.utils.functions;

import org.bongiorno.misc.utils.Function;

import javax.annotation.Nullable;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;

public class StyleSheetFunction implements Function<String, String> {

    private static TransformerFactory transformerFactory = TransformerFactory.newInstance("net.sf.saxon.TransformerFactoryImpl", null);


    private String xslt;

    public StyleSheetFunction(String xslt) {
        this.xslt = xslt;
    }


    public String getXslt() {
        return xslt;
    }

    @Override
    public String apply(@Nullable String xmlIn) {
        try {
            return StyleSheetFunction.apply(xmlIn, this.xslt);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public static String apply(String xmlIn, String xslt) throws TransformerException {
        Source styleSheet = new StreamSource(new StringReader(xslt));
        Source xmlInput = new StreamSource(new StringReader(xmlIn));
        ByteArrayOutputStream messageBuffer = new ByteArrayOutputStream();
        Transformer transformer = transformerFactory.newTransformer(styleSheet);
        transformer.transform(xmlInput, new StreamResult(messageBuffer));
        return messageBuffer.toString();
    }
}
