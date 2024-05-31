package loki.ui;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * A wrapper of an inherent JS DOM entity that child elements can be added to.
 */

public class Entity
{
    private JavaScriptObject reference;

    protected Entity(){}

    protected void setReference(JavaScriptObject reference)
    {
        this.reference = reference;
    }

    /**
     * Returns a reference to the underlying JS object.
     */

    public JavaScriptObject getReference()
    {
        return reference;
    }

    /**
     * Adds the specified list of elements to this entity as child nodes.
     * 
     * @param elements The list of elements to add as child nodes.
     */

    public void add(Element... elements)
    {
        for(Element element: elements)
        {
            addElement(reference, element.getReference());
        }
    }

    private native void addElement(JavaScriptObject parent, JavaScriptObject child)
    /*-{
        parent.appendChild(child);
    }-*/;

    /**
     * Removes the specified child element.
     * 
     * @param element The element to use.
     */

    public void remove(Element element)
    {
        removeElement(reference, element.getReference());
    }

    private native void removeElement(JavaScriptObject parent, JavaScriptObject child)
    /*-{
        parent.removeChild(child);
    }-*/;

    /**
     * Removes this element from its parent, if it has one.
     */

    public void removeSelf()
    {
        removeSelf(reference);
    }

    private native void removeSelf(JavaScriptObject child)
    /*-{
        if(child.parentNode)
        {
            child.parentNode.removeChild(child);
        }
    }-*/;

    /**
     * Removes all child elements.
     */

    public void clear()
    {
        clear(reference);
    }

    private native void clear(JavaScriptObject parent)
    /*-{
        while(parent.firstChild)
        {
            parent.removeChild(parent.lastChild);
        }
    }-*/;
}