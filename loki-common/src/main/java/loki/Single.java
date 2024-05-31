package loki;

/**
 * A single value wrapper. This can be used to allow a value to be mutable within nested methods and classes.
 */

public class Single<T>
{
    private T item;
    
    /**
     * Creates a new instance with the given initial value.
     * 
     * @param item     The initial value.
     */

    public Single(T item)
    {
        this.item = item;
    }
    
    /**
     * Returns the current value.
     * 
     * @return  the current value.
     */

    public T getItem()
    {
        return item;
    }
    
    /**
     * Update the value.
     * 
     * @param   the new value.
     */

    public void setItem(T item)
    {
        this.item = item;
    }
    
    /**
     * A short-hand utility method to create a new instance without having to specify the generic component with the constructor.
     * 
     * @param item      The initial item.
     * @return          a new instance with the value.
     */
    
    public static <T> Single<T> create(T item)
    {
        return new Single<T>(item);
    }
}
