package loki.server.text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * A utility class for reading from text files on the file system.
 */

public class TextFileReaderService
{
    /**
     * Reads all the lines of a file and returns them as a list.
     * If the file is very large, use iterateLines() instead.
     * 
     * @param path      The path of the file to read.
     * @return          All the lines of the file as a list.
     */
    
    public List<String> readAllLines(String path) throws Exception
    {
        List<String> list = new ArrayList<String>();
        iterateLines(path, new TextFileLineReceiver(){
            public void onLine(int index, String line) throws Exception
            {
                list.add(line);
            }
        });
        return list;
    }
    
    /**
     * Reads the lines of a file one at a time and notifies the receiver of each line.
     * 
     * @param path      The path of the file to read.
     * @param receiver  The receiver that will be notified of lines read from the file.
     */
    
    public void iterateLines(String path, TextFileLineReceiver receiver) throws Exception
    {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path))))
        {
            int index = 0;
            String line;
            
            while((line=reader.readLine())!=null)
            {
                receiver.onLine(index, line);
                index++;
            }
        }
    }
}