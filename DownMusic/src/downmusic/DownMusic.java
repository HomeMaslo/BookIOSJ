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
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DownMusic implements Runnable {

    private final File store;
    private final URL downloadUrl;

    public DownMusic(File store, String downloadAddress) throws MalformedURLException {
        this.store = store;
        this.downloadUrl = new URL(downloadAddress);
    }

    @Override
    public void run() {
        try {
            URLConnection conn = downloadUrl.openConnection();
            
            String mime = conn.getContentType();
            String name = URLDecoder.decode(new File(conn.getURL().getFile()).getName(), "utf-8");
            
            System.out.printf("Downloading: (%s) %s\n", mime, name);

            download(conn.getInputStream(), name);

            System.out.printf("Downloaded: %s [%db]\n", name, conn.getContentLengthLong());
        } catch (IOException ex) {
            Logger.getLogger(DownMusic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void download(InputStream data, String filename) throws IOException {
//        try (OutputStream os = new FileOutputStream(new File(store, filename))) {
//            byte[] buff = new byte[204800];
//            int length;
//            
//            while ((length = data.read(buff)) != -1) {
//                os.write(buff, 0, length);
//            }
//        }

        Files.copy(data, new File(store, filename).toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

}
