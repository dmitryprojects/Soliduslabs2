package com.soliduslabs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Item {

    private String id;

    @Getter
    private int price;

    @Override
    public String toString() {
        return String.format("%s %s", id, price);
    }
}
