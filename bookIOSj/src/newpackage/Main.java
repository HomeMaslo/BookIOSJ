/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pavel
 */
public class Main {

    public static final String STORE_DIRECTORY = "save";

    public static void main(String[] arg) {
        File storeDir = new File(STORE_DIRECTORY);

        if (!storeDir.exists()) {
            storeDir.mkdir();
        }

        ArrayList<People> contactsList = new ArrayList<>();

        File[] listOfFiles = storeDir.listFiles();

        if (listOfFiles != null) {
            for (int i = 0; i < listOfFiles.length; i++) {
                File listedFile = listOfFiles[i];
                if (listedFile.isFile()) {
                    try (ObjectInputStream oos = new ObjectInputStream(new FileInputStream(listedFile))) {
                        contactsList.add((People) oos.readObject());
                    } catch (IOException | ClassNotFoundException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter 'r' or 'a' ");
            System.out.println(" 'r' - read contacts");
            System.out.println(" 'a' - add contacts");

            System.out.println(" if you want exit  any last  button");

            String nextLine = scanner.nextLine();
            if ("r".equalsIgnoreCase(nextLine)) {
                for (People b : contactsList) {
                    System.out.println(b.getName());
                    System.out.println(b.getTell());
                    System.out.println(b.getSkype());
                    System.out.println("______");

                }
            } else if ("a".equalsIgnoreCase(nextLine)) {
                People objt = new People();

                System.out.println("enter name");
                objt.setName(scanner.nextLine());

                System.out.println("enter tell");
                objt.setTell(scanner.nextLine());

                System.out.println("enter skype");
                objt.setSkype(scanner.nextLine());

                contactsList.add(objt);
                System.out.println(" 's' - safe contacts");
                nextLine = scanner.nextLine();
                
                if ("s".equalsIgnoreCase(nextLine)) {
                    File contactFile = new File(storeDir, String.format("%d.bin", System.currentTimeMillis()));
                    
                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(contactFile))) {
                        oos.writeObject(objt);
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    System.out.println("Contacts is safe");
                }

//                System.out.println(objt.getName());
//                System.out.println(objt.getTell());
//                System.out.println(objt.getSkype());
            } else if ("s".equalsIgnoreCase(nextLine)) {
                for (People objt : contactsList) {
                    long time = System.currentTimeMillis();
                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STORE_DIRECTORY + Long.toString(time) + ".bin"))) {
                        oos.writeObject(objt);
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("Contacts is safe");
                }
            } else {

                System.out.println("Your Exit");
                break;
            }

        }
    }
}
