package loki;

import thor.Storable;

/**
 * A short-hand utility class that extends {@link Pair} specifically for a String key-value pair.
 * This class can be used as a short-hand construct to avoid having to specify the generic components.
 * For example, StringPair instead of Pair<String, String>.
 */

public class StringPair extends Pair<String, String> implements Storable
{
    private static final long serialVersionUID = 1L;

    /**
     * Create a new key-value pair of String objects.
     * 
     * @param key       The key.
     * @param value     The value.
     */
    
    public StringPair(String key, String value)
    {
        super(key, value);
    }
}