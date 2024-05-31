package loki;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import thor.Storable;

/**
 * A map of key-value properties that can have multiple parents. When a value is
 * looked up using a key, if the key isn't present in this map, the parents will
 * be used to obtain a value.
 */

public class Attributes<T> implements Storable
{
    private static final long serialVersionUID = 1L;
    private HashMap<String, T> map;
    private List<Attributes<T>> parents;

    public Attributes()
    {
        this.map = new HashMap<String, T>();
        this.parents = new ArrayList<Attributes<T>>();
    }

    /**
     * Adds a parent. When a value is looked up using a key, if the key isn't
     * present in this map, the parents will be used to obtain a value.
     * 
     * @param parent The parent to add.
     */

    public void addParent(Attributes<T> parent)
    {
        parents.add(parent);
    }

    /**
     * Removes a parent. When a value is looked up using a key, if the key isn't
     * present in this map, the parents will be used to obtain a value.
     * 
     * @param parent The parent to remove.
     */

    public void removeParent(Attributes<T> parent)
    {
        parents.remove(parent);
    }

    /**
     * Clears the key-value map. Does not affect parents, and does not affect the
     * list of parents.
     */

    public void clear()
    {
        map.clear();
    }

    /**
     * Removes all parents.
     */

    public void removeAllParents()
    {
        parents.clear();
    }

    /**
     * Retrieves a value from this map. If the key isn't found in this map, each
     * parent is asked (in the order in which they were added) for a value by
     * calling this same method on the parent. If the exists in neither this map nor
     * any parent, null is returned.
     * 
     * @param key The key associated with the value to be returned.
     * @return the value associated with the specified key, or null if the key does
     *         not exist in this map or any parent.
     */

    public T get(String key)
    {
        T result = map.get(key);

        if(result == null)
        {
            for(Attributes<T> parent: parents)
            {
                result = parent.get(key);

                if(result != null)
                {
                    return result;
                }
            }
        }

        return result;
    }

    /**
     * Retrieves a value from this map. If the key isn't found in this map, each
     * parent is asked (in the order in which they were added) for a value by
     * calling this same method on the parent. If the exists in neither this map nor
     * any parent, the fallback value is returned.
     * 
     * @param key      The key associated with the value to be returned.
     * @param fallback The value to return if the key does not exist in this map or
     *                 any parent.
     * @return the value associated with the specified key, or the fallback value if
     *         the key does not exist in this map or any parent.
     */

    public T get(String key, T fallback)
    {
        T result = get(key);
        return result == null ? fallback : result;
    }

    /**
     * Adds a key-value association within this map. The value specified can be
     * retrieved using the get() method and using the same key.
     * 
     * @param key   The key to associate with the value.
     * @param value The value to associate with the key.
     */

    public void set(String key, T value)
    {
        map.put(key, value);
    }

    /**
     * Returns whether or not the key exists within this map. Does not check
     * parents.
     * 
     * @param key The key to check.
     * @return true if the key was found, otherwise false.
     */

    public boolean has(String key)
    {
        return has(key, false);
    }

    /**
     * Returns whether or not the key exists within this map. Also checks the
     * parents in its ancestor hierarchy if specified.
     * 
     * @param key          The key to check.
     * @param checkParents Whether or not to also check the parents in this map's
     *                     ancestor hierarchy.
     * @return true if the key was found, otherwise false.
     */

    public boolean has(String key, boolean checkParents)
    {
        if(map.containsKey(key))
        {
            return true;
        }
        if(checkParents)
        {
            for(Attributes<T> parent: parents)
            {
                if(parent.has(key, checkParents))
                {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Returns the collection of keys belonging to this map. Does not include
     * parents.
     * 
     * @return the collection of keys belonging to this map.
     */

    public Collection<String> keys()
    {
        return map.keySet();
    }

    /**
     * Returns the number of keys in this map. Does not include parents.
     * 
     * @return the number of keys in this map.
     */

    public int size()
    {
        return map.size();
    }
}