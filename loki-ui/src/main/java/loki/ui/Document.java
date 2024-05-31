package loki.ui;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * A reference to a JavaScript Document element.
 */

public class Document
{
    private JavaScriptObject reference;

    private Document(JavaScriptObject reference)
    {
        this.reference = reference;
    }

    /**
     * Returns the body element of this document.
     * 
     * @return the body element of this document.
     */

    public Entity getBody()
    {
        Entity body = new Entity();
        body.setReference(getBodyElementReference(reference));
        return body;
    }

    private native JavaScriptObject getBodyElementReference(JavaScriptObject document)
    /*-{
      return document.body;
    }-*/;

    /**
     * A static method to returns root document of the context this application is running in.
     * 
     * @return the root document.
     */

    public static Document getRootDocument()
    {
        return new Document(getRootDocumentReference());
    }

    private static native JavaScriptObject getRootDocumentReference()
    /*-{
      return $doc;
    }-*/;
}