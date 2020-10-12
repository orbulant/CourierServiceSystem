package org.aaaa.FileHandler;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main (String[] args) throws IOException {
        FileHandler fhreader = new FileHandler("C:\\Users\\perso\\IdeaProjects\\CourierServiceSystem\\src\\main\\java\\org\\aaaa\\FileHandler\\account.txt");
        FileHandler fhreader2 = new FileHandler("C:\\Users\\perso\\IdeaProjects\\CourierServiceSystem\\src\\main\\java\\org\\aaaa\\FileHandler\\account.txt");

        //Just to see if some things work
        System.out.println(fhreader.getContent());
        System.out.println(fhreader2.getContentsinglearr());

        //Testing
        List<List<String>> iostream1 = fhreader.getContent();
        List<String> iostream2 = fhreader2.getContentsinglearr();

        //Write to output
        FileHandler fhwriter = new FileHandler("C:\\Users\\perso\\IdeaProjects\\CourierServiceSystem\\src\\main\\java\\org\\aaaa\\FileHandler\\output.txt");
        fhwriter.writeContenttosinglearr(iostream2);

    }
}
