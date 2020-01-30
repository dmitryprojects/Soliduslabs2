package com.soliduslabs;

import com.soliduslabs.model.Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class Utils {

    public static List<Item> getItemsFromFile(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        input.useDelimiter(", |\r\n");

        List<Item> itemList = new ArrayList<>();
        while (input.hasNext()) {
            String id = input.next();
            int price = input.nextInt();
            Item newItem = new Item(id, price);
            itemList.add(newItem);
        }
        return itemList;
    }

    public static void printItems(List<Item> itemList) {
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    public static List<Item> getPairOfGifts(List<Item> itemList, int balance) {
        List<Item> newItemList = itemList.stream()
                .filter(item -> item.getPrice() <= balance)
                .collect(Collectors.toList());

        int i = 0;
        int j = newItemList.size() - 1;
        int sum = 0;
        List<Item> items = null;
        while (i < j) {
            int tempSum = newItemList.get(i).getPrice() + newItemList.get(j).getPrice();
            if (tempSum == balance) {
                return asList(newItemList.get(i), newItemList.get(j));
            } else if (tempSum < balance) {
                if (sum < tempSum) {
                    sum = tempSum;
                    items = asList(newItemList.get(i), newItemList.get(j));
                }
                i++;
            } else {
                j--;
            }
        }
        return items;
    }

    public static void printGiftsResult(List<Item> pairOfGifts) {
        if (Objects.isNull(pairOfGifts)) {
            System.out.println("Not possible");
        }else {
            StringJoiner joiner = new StringJoiner(",");
            for (Item item : pairOfGifts) {
                joiner.add(item.toString());
            }
            System.out.println(joiner.toString());
        }
    }
}
