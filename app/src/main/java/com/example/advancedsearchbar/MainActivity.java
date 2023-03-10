package com.example.advancedsearchbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;
    MaterialSearchView searchView;

    List<String>listSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.toolbar);
        listView=findViewById(R.id.list_view);
        searchView=findViewById(R.id.search_view);
        listSource=new ArrayList<>();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Advanced SearchBar");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        fillListSource();


        ArrayAdapter arrayAdapter=new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_activated_1,listSource);
        listView.setAdapter(arrayAdapter);



        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
                ArrayAdapter arrayAdapter=new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_activated_1,listSource);
                listView.setAdapter(arrayAdapter);
            }
        });


        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if(newText !=null && !newText.isEmpty()){

                    List<String>listFound=new ArrayList<>();

                    for(String item : listSource){
                        if(item.contains(newText)){
                            listFound.add(item);
                        }
                    }

                    ArrayAdapter arrayAdapter=new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_activated_1,listFound);
                    listView.setAdapter(arrayAdapter);

                }else {
                    ArrayAdapter arrayAdapter=new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_activated_1,listSource);
                    listView.setAdapter(arrayAdapter);
                }

                
                return true;
            }
        });



    }


    public void fillListSource(){
        listSource.add("number 1");
        listSource.add("number 2");
        listSource.add("number 3");
        listSource.add("number 4");
        listSource.add("number 5");
        listSource.add("number 6");
        listSource.add("number 7");
        listSource.add("number 8");
        listSource.add("number 9");
        listSource.add("number 10");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

     getMenuInflater().inflate(R.menu.menu_search_view,menu);
        MenuItem menuItem=menu.findItem(R.id.action_search);
       searchView.setMenuItem(menuItem);

        return true;
    }
}