package loki;

/**
 * A key-value pair.
 */

public class Pair<A, B>
{
    private A key;
    private B value;
    
    /**
     * Create a new key-value pair.
     * 
     * @param key       The key.
     * @param value     The value.
     */
    
    public Pair(A key, B value)
    {
        this.key = key;
        this.value = value;
    }
    
    /**
     * Returns the key.
     * 
     * @return      the key.
     */
    
    public A getKey()
    {
        return key;
    }
    
    /**
     * Returns the value.
     * 
     * @return      the value.
     */

    public B getValue()
    {
        return value;
    }
    
    /**
     * Updates the key.
     * 
     * @param key       The new key.
     */

    public void setKey(A key)
    {
        this.key = key;
    }
    
    /**
     * Updates the value.
     * 
     * @param value     The new value.
     */

    public void setValue(B value)
    {
        this.value = value;
    }
    
    /**
     * A short-hand utility method to create a new instance without having to specify the generic components with the constructor.
     * 
     * @param key       The key.
     * @param value     The value.
     * @return          a new instance with the given key and value.
     */
    
    public static <A, B> Pair<A, B> create(A key, B value)
    {
        return new Pair<A, B>(key, value);
    }
}