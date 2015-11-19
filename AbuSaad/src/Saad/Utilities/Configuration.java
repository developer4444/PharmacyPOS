package Saad.Utilities;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;


public final class Configuration {
	public static String configurationFilePath = new String("configuration.txt");
        public static Properties configurations = new Properties();

        public static void loadProperties()
        {
            try
            {
                FileInputStream input = new FileInputStream(configurationFilePath);
                configurations.load(input);
                input.close();
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }

	public static String readStringValueFromFile(String parameter)
	{
            try
            {
                File dataFile = new File(configurationFilePath);
                Scanner scanner = new Scanner(dataFile);
                while(scanner.hasNextLine())
                {
                    String line = scanner.nextLine();
                    String tokens[] = line.split("=");
                    if(tokens[0].equalsIgnoreCase(parameter))
                    {
                            return tokens[1];
                    }
                }
            } catch(Exception ex)
            {
            }
            return null;
	}
}
