/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downmusic;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pavel
 */
public class Main {

    public static void main(String[] arg) {
        File storeDir = new File("Downloads");
        
        if (!storeDir.exists()) {
            storeDir.mkdir();
        }
        
        List<String> urls = new ArrayList<>();
        
        try {
            URL url = new URL("http://www.ex.ua/playlist/7026463.m3u");
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNextLine()) {
                urls.add(scanner.nextLine());
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(urls);
        for (String url : urls) {
            try {
                new Thread(new DownMusic(storeDir, url)).start();
            } catch (MalformedURLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /*   
         Runnable b = new DownMusic("http://www.ex.ua/get/59146911");
         Runnable r = new DownMusic("http://www.ex.ua/get/59146991");
         new Thread(r).start();
         new Thread(b).start();
         */
    }
}
