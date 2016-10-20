package com.example.amit.kaastkaar;

import java.util.ArrayList;

/**
 * Created by amit on 19-10-2016.
 */
public class ItemsList {

    private String itemName;
    private String quantity;
    private String price;

    public ItemsList(String itemName, String quantity, String price){
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getItemName(){
      return  itemName;
    }
    public String getQuantity(){
        return quantity;
    }
    public String getPrice(){
        return price;
    }

 public static ArrayList<ItemsList> createItemsList(String itemName, String quantity, String price){
     ArrayList<ItemsList> arrayList = new ArrayList<ItemsList>();
     arrayList.add(new ItemsList(itemName,quantity,price));
     return arrayList;
 }
}
