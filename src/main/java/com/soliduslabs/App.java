package com.soliduslabs;

import com.soliduslabs.model.Item;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;
import java.io.File;
import static com.soliduslabs.Utils.*;

@SpringBootApplication
public class App implements CommandLineRunner {
    private Scanner scanner;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String command = args[0];
        String fileName = args[1];

        int balance = 0;
        try {
            balance = Integer.parseInt(args[2]);
        } catch (Exception e) {
            if (!command.equals("cat")) {
                System.out.println("Missing balance!");
                return;
            }
        }
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        List<Item> itemList = getItemsFromFile(file);

        if (command.equals("cat")) {
            printItems(itemList);
        }
        if (command.equals("find-pair")) {
            List<Item> pairOfGifts = getPairOfGifts(itemList, balance);
            printGiftsResult(pairOfGifts);
        }
    }
}
