package Scraper;

import java.io.*;
import java.awt.GraphicsEnvironment;
import java.net.URISyntaxException;

/*
 * Main Func. Created to throw up a cmd during runtime. 
 * Big Thanks to: https://stackoverflow.com/questions/7704405/how-do-i-make-my-java-application-open-a-console-terminal-window
 */
public class Scraper {
    public static void main (String [] args) throws IOException, InterruptedException, URISyntaxException, ClassNotFoundException{
        Console console = System.console();
        if(console == null && !GraphicsEnvironment.isHeadless()){
            String filename = Scraper.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6);
            Runtime.getRuntime().exec(new String[]{"cmd","/c","start","cmd","/k","java -jar \"" + filename + "\""});
        }else{
            ScraperFuncs.menuFunction(new String[0]);
            System.out.println("Program has ended, please type 'exit' to close the console");
        }
    }
}