package loki.server.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Supplier;
import loki.Attributes;

/**
 * Static utility methods that can be called from server-side code.
 */

public abstract class ServerUtils
{
    private static final String CONFIG_FILE_COMMENT = "#";
    private static final String CONFIG_FILE_SEPARATOR = "=";

    private static final ThreadLocal<SimpleDateFormat> LOG_FORMAT = ThreadLocal.withInitial(new Supplier<SimpleDateFormat>(){
                public SimpleDateFormat get()
                {
                    return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                }
            });

    /**
     * Loads a property map from a property file at the given path on the file
     * system.
     * 
     * @param path The path of the property file.
     * @return The property map derived from the property file.
     */

    public static final Attributes<String> loadFromFile(String path) throws Exception
    {
        Attributes<String> attributes = new Attributes<String>();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path))))
        {
            String line;

            while((line = reader.readLine()) != null)
            {
                line = line.trim();

                if(!line.isEmpty() && !line.startsWith(CONFIG_FILE_COMMENT))
                {
                    int index = line.indexOf(CONFIG_FILE_SEPARATOR);
                    attributes.set(line.substring(0, index).trim(), line.substring(index + 1).trim());
                }
            }
        }

        return attributes;
    }

    /**
     * Prints the specified message to standard output with a timetamp. The output
     * is printed using System.out.println() in the format: [dd-MM-yyyy HH:mm:ss]
     * message.
     * 
     * @param message The message to log.
     */

    public static final void log(String message)
    {
        System.out.println("[" + LOG_FORMAT.get().format(new Date()) + "] " + message);
    }

    /**
     * Prints the specified throwable/exception to standard output with a timetamp.
     * The output is printed using System.out.println() in the format: [dd-MM-yyyy
     * HH:mm:ss] throwable.
     * 
     * @param e The throwable to log.
     */

    public static final void log(Throwable e)
    {
        System.out.println("[" + LOG_FORMAT.get().format(new Date()) + "]");
        e.printStackTrace(System.out);
    }
}