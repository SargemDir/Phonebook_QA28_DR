package application;

import models.Contact;
import models.User;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyDataProvider {
    @DataProvider
    public Iterator<Object[]> validLoginDataClassDP() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"anat@gmail.com", "Aa12345$"});
        list.add(new Object[]{"anata@gmail.com", "Aa12345$"});
        list.add(new Object[]{"anatan@gmail.com", "Aa12345$"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> dataFileCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/data.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[]{new User().withEmail(split[0]).withPassword(split[1])});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> dataContactCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contact.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(",");
            list.add(new Object[]{Contact.builder()
                    .name(split[0])
                    .lastname(split[1])
                    .phone(split[2])
                    .email(split[3])
                    .address(split[4])
                    .description(split[5])
                    .build()});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> dataContactDP() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Kira", "Rozhkova", "1111111111", "kira11@gmail.com", "Tel Aviv", "neighbor"});
        list.add(new Object[]{"Kira", "Rozhkova", "1111111112", "kira12@gmail.com", "Tel Aviv", "neighbor"});
        list.add(new Object[]{"Kira", "Rozhkova", "1111111113", "kira13@gmail.com", "Tel Aviv", "neighbor"});


        return list.iterator();
    }
}
