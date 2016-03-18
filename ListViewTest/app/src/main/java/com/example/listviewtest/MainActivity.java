package com.example.listviewtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
/*    private String[] data = {"Apple", "Orange", "Pear", "Watermelon", "Grape", "Banana", "Cherry", "Strawberry", "Mango",
            "Grapefruit", "Peach", "Jack fruit", "Pineapple", "Litchi"};*/
    private List<Fruit> fruitList = new ArrayList<Fruit>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruit();
        FruitAdapter fruitAdapter = new FruitAdapter(this,R.layout.fruit_item,fruitList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(fruitAdapter);
        listView.setOnItemClickListener(this);
    }

    private void initFruit() {
        Fruit apple = new Fruit("Apple",R.drawable.apple);
        fruitList.add(apple);
        Fruit orange = new Fruit("Orange",R.drawable.orange);
        fruitList.add(orange);
        Fruit pear = new Fruit("Pear",R.drawable.pear);
        fruitList.add(pear);
        Fruit  watermelon = new Fruit("Watermelon",R.drawable.watermelon);
        fruitList.add(watermelon);
        Fruit grape = new Fruit("Grape",R.drawable.grape);
        fruitList.add(grape);
        Fruit banana = new Fruit("Banana",R.drawable.banana);
        fruitList.add(banana);
        Fruit cherry = new Fruit("Cherry",R.drawable.cherry);
        fruitList.add(cherry);
        Fruit strawberry = new Fruit("Strawberry",R.drawable.strawberry);
        fruitList.add(strawberry);
        Fruit mango = new Fruit("Mango",R.drawable.mango);
        fruitList.add(mango);
        Fruit grapefruit = new Fruit("grapefruit",R.drawable.grapefruit);
        fruitList.add(grapefruit);
        Fruit peach = new Fruit("Peach",R.drawable.peach);
        fruitList.add(peach);
        Fruit jackfruit = new Fruit("Jackfruit",R.drawable.jackfruit);
        fruitList.add(jackfruit);
        Fruit pineapple = new Fruit("Pineapple",R.drawable.pineapple);
        fruitList.add(pineapple);
        Fruit litchi = new Fruit("Litchi",R.drawable.litchi);
        fruitList.add(litchi);

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,fruitList.get(position).getName(),Toast.LENGTH_SHORT).show();
    }
}

