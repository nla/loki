package loki.ui;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * A wrapper of a JS DOM element that can be manipulated and added to other DOM entities as a child.
 */

public class Element extends Entity
{
    /**
     * Creates a new instance with the given tag name.
     * 
     * Example usage:
     * 
     * Element panel = new Element("div"); Document.getRootDocument().getBody().add(panel);
     * Element image = new Element("img"); image.setAttribute("src", "/static/image.png"); panel.add(image);
     * 
     * @param tag The tag to create.
     */

    public Element(String tag)
    {
        setReference(createElement(tag));
    }

    /**
     * Creates a new instance with the given tag name and css className.
     * 
     * Example usage:
     * 
     * Element panel = new Element("div", "panel-nav"); Document.getRootDocument().getBody().add(panel);
     * Element image = new Element("img", "image-large"); image.setAttribute("src", "/static/image.png"); panel.add(image);
     * 
     * @param tag The tag to create.
     */

    public Element(String tag, String classNames)
    {
        this(tag);
        setClassNames(classNames);
    }

    /**
     * Replaces the CSS className of the underlying JS object with the specified className value.
     * 
     * @param classNames The replacement value.
     */

    public Element setClassNames(String classNames)
    {
        setClassNames(getReference(), classNames);
        return this;
    }

    /**
     * Adds the specified list of elements to this element as child nodes.
     * 
     * @param elements The list of elements to add as child nodes.
     * @return this element.
     */

    public Element with(Element... elements)
    {
        add(elements);
        return this;
    }

    /**
     * Returns the CSS className value for the underlying JS element.
     * 
     * @return the CSS className value for the underlying element.
     */

    public String getClassNames(String attribute)
    {
        return getClassNames(getReference());
    }

    /**
     * Sets the attribute of the underlying JS to the specified value.
     * 
     * @param attribute The attribute to update.
     * @param value     The value to set for the specified attribute.
     * @return this element.
     */

    public <T> Element setAttribute(String attribute, String value)
    {
        setAttribute(getReference(), attribute, value);
        return this;
    }

    /**
     * Returns the value of the attribute specified from the underlying JS element.
     * 
     * @param attribute The attribute to retrieve.
     * @return the value of the specified attribute.
     */

    public String getAttribute(String attribute)
    {
        return getAttribute(getReference(), attribute);
    }

    /**
     * Replaces the inner HTML of the underlying JS element.
     * 
     * @param html The value to specify for the inner HTML of the underlying JS element.
     */

    public void setInnerHTML(String html)
    {
        setInnerHTML(getReference(), html);
    }

    /**
     * Returns the inner HTML of the underlying JS element.
     * 
     * @return the inner HTML of the underlying JS element.
     */

    public String getInnerHTML()
    {
        return getInnerHTML();
    }

    /**
     * Specifies a click handler for the underlying JS element. This is called if this element or any of its children are clicked.
     * 
     * @param runnable The runnable that is run when the underlying JS element is clicked.
     */

    public void setOnClick(Runnable runnable)
    {
        setOnClick(getReference(), runnable);
    }

    /**
     * Specifies a click handler for the underlying JS element. This is called if this element only is clicked; it is not called if one of its children are clicked.
     * 
     * @param runnable The runnable that is run when the underlying JS element is clicked.
     */

    public void setOnClickSelfOnly(Runnable runnable)
    {
        setOnClick(getReference(), getReference(), runnable);
    }

    /**
     * Sets a CSS style value for the underlying JS element. Style names should be specified in the same format they are specified in JavaScript; for example backgroundColor for CSS background-color.
     * 
     * @param styleName The name of the CSS style to modify.
     * @param value     The CSS value to set.
     */

    public void setStyle(String styleName, String value)
    {
        setStyle(getReference(), styleName, value);
    }

    private native JavaScriptObject createElement(String tag)
    /*-{
      return $doc.createElement(tag);
    }-*/;

    private native void setClassNames(JavaScriptObject reference, String classNames)
    /*-{
      reference.className = classNames;
    }-*/;

    private native String getClassNames(JavaScriptObject reference)
    /*-{
      return reference.className;
    }-*/;

    private native void setAttribute(JavaScriptObject reference, String attribute, String value)
    /*-{
      reference.setAttribute(attribute, value);
    }-*/;

    private native String getAttribute(JavaScriptObject reference, String attribute)
    /*-{
      return reference.getAttribute(attribute);
    }-*/;

    private native void setInnerHTML(JavaScriptObject reference, String html)
    /*-{
      reference.innerHTML = html;
    }-*/;

    private native String getInnerHTML(JavaScriptObject reference)
    /*-{
      return reference.innerHTML;
    }-*/;

    private native void setOnClick(JavaScriptObject reference, Runnable runnable)
    /*-{
      reference.onclick = function(event){
          runnable.@java.lang.Runnable::run()();
      };
    }-*/;

    private native void setOnClick(JavaScriptObject reference, JavaScriptObject strictTarget, Runnable runnable)
    /*-{
      reference.onclick = function(event){
          if(event.target==strictTarget)
          {
              runnable.@java.lang.Runnable::run()();
          }
      };
    }-*/;

    private native void setStyle(JavaScriptObject reference, String styleName, String value)
    /*-{
      reference.style[styleName] = value;
    }-*/;
}