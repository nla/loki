package loki;

import java.util.List;

/**
 * Contains static utility methods usable in both server-side and GWT web code.
 */

public class CommonUtils
{
    /**
     * Concatenates the toString() values of all the items in the given array, separated by the specified separator.
     * 
     * @param items         The array of items.
     * @param separator     The separator to use between items.
     */
    
    public static <T> String printArray(T[] items, String separator)
    {
        StringBuilder b = new StringBuilder();
        
        for(int i=0; i<items.length; i++)
        {
            if(i>0)
            {
                b.append(separator);
            }
            
            b.append(items[i].toString());
        }
        
        return b.toString();
    }
    
    /**
     * Concatenates the toString() values of all the items in the given list, separated by the specified separator.
     * 
     * @param items         The list of items.
     * @param separator     The separator to use between items.
     */
    
    public static <T> String printArray(List<T> items, String separator)
    {
        StringBuilder b = new StringBuilder();
        
        for(int i=0; i<items.size(); i++)
        {
            if(i>0)
            {
                b.append(separator);
            }
            
            b.append(items.get(i).toString());
        }
        
        return b.toString();
    }
}