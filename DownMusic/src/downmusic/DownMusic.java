/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downmusic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DownMusic implements Runnable {

    public String myUrl;// параметр 

    public DownMusic(String myUrl) {// через конструтор передадим параметр
        // передаём в конструктор все параметры, которые могут пигодится потоку
        this.myUrl = myUrl; // сохраняем параметры как поля - мне нужен только один =))
    }

    public void run() {
        try {
            this.save(this.myUrl);
        } catch (IOException ex) {
            Logger.getLogger(DownMusic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void save(String myUrl) throws IOException {
        URL url = new URL(myUrl);

        File f = new File(myUrl);
        String destinationFile = "save/"+f.getName() + ".mp3";

        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destinationFile);

        byte[] b = new byte[204800];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        is.close();
        os.close();
    }

}
