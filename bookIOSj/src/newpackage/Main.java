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

    public static final String PATCH = "save/";

    public static void main(String[] arg) {
        ArrayList<People> Contacts = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        File folder = new File(PATCH);
        File[] listOfFiles = folder.listFiles();
        People student = null;
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                try (ObjectInputStream oos = new ObjectInputStream(new FileInputStream(PATCH + listOfFiles[i].getName()))) {
                    Contacts.add((People) oos.readObject());
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        while (true) {
            System.out.println("Enter 'r' or 'a' ");
            System.out.println(" 'r' - read contacts");
            System.out.println(" 'a' - add contacts");

            System.out.println(" if you want exit  any last  button");

            String nextLine = scanner.nextLine();
            if ("r".equalsIgnoreCase(nextLine)) {
                for (People b : Contacts) {
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

                Contacts.add(objt);
                System.out.println(" 's' - safe contacts");
                nextLine = scanner.nextLine();
                if ("s".equalsIgnoreCase(nextLine)) {

                    long time = System.currentTimeMillis();
                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATCH + Long.toString(time) + ".bin"))) {
                        oos.writeObject(objt);
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("Contacts is safe");
                }

                System.out.println(objt.getName());
                System.out.println(objt.getTell());
                System.out.println(objt.getSkype());
            } else if ("s".equalsIgnoreCase(nextLine)) {
for (People objt:Contacts){
                    long time = System.currentTimeMillis();
                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATCH + Long.toString(time) + ".bin"))) {
                        oos.writeObject(objt);
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("Contacts is safe");
}
                } else{

                System.out.println("Your Exit");
                break;
            }

        }
    }
}
