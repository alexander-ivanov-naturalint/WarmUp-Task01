package com.ivaa.naturalint;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/*
 * Created by ivaa on 9/29/2015.
 */
public class Main {
    private static Properties properties;

    public static void main(String[] args) throws IOException
    {
        //print to console
        System.out.println("Hello World");

        //read properties file
        ReadProperties();

        //print to file
        PrintWriter writer = null;
        String fileName = properties.getProperty("fileName");

        //get date in defined format
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
        Date dt = new Date();
        String datetime = dateFormat.format(dt);

        //print datetime to console
        System.out.println(datetime);

        //open file and print to it
        if (fileName != null)
        {
            File file = new File(fileName);
            try
            {
                file.getCanonicalPath();
                writer = new PrintWriter(fileName, "UTF-8");
                writer.println("Hello world");
                writer.println(datetime);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
            finally
            {
                //close file
                if (writer != null)
                    writer.close();
            }
        }
    }

    private static void ReadProperties()
    {
        properties = new Properties();
        InputStream inputStream = null;

        try
        {
            URL url = Main.class.getClassLoader().getResource("config.properties");
            if (url != null)
            {
                String filePath = URLDecoder.decode(url.getPath(), "UTF-8");
                File file = new File(filePath);//
                inputStream = new FileInputStream(filePath);
                properties.load(inputStream);
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        finally {
            if (inputStream != null)
            {
                try
                {
                    inputStream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
