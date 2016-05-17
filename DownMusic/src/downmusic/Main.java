/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downmusic;

import java.io.IOException;
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
        List<String> urls = new ArrayList<>();
        List<DownMusic> ob = new ArrayList<>();
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
            ob.add(new DownMusic(url));
        }
        for (DownMusic music : ob) {

            new Thread(music).start();
        }

        /*   
         Runnable b = new DownMusic("http://www.ex.ua/get/59146911");
         Runnable r = new DownMusic("http://www.ex.ua/get/59146991");
         new Thread(r).start();
         new Thread(b).start();
         */
    }
}
