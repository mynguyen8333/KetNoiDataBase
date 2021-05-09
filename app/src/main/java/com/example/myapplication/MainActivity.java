package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    DataUser dataUser;
    Button btnadd,btnremove,btncancer;
    EditText name_edt;
    ListView lvName;
    ArrayList nameList;
    ArrayAdapter adapter;
    ArrayList idList;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataUser = new DataUser(this,"userdb.sqlite",null,1);
        //dataUser.addUser(new User(1,"my nguyen"));
//        dataUser.addUser(new User("my"));
//        dataUser.addUser(new User("meo"));
//        dataUser.addUser(new User("online"));
        lvName = findViewById(R.id.lvName);
        name_edt = findViewById(R.id.name_edt);
        btnadd = findViewById(R.id.btnadd);


        btncancer = findViewById(R.id.btncancer);
        btnremove = findViewById(R.id.btnremove);
        idList = new ArrayList();
        nameList = new ArrayList();
//        nameList = getNameList();
        getNameList();

        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,nameList);
        lvName.setAdapter(adapter);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataUser.addUser(new User(name_edt.getText().toString()));
                getNameList();
                adapter.notifyDataSetChanged();
            }
        });
        btnremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataUser.remove((int)idList.get(index));
                getNameList();
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,"Sesses",Toast.LENGTH_SHORT).show();

            }
        });

        lvName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                index = i;

            }
        });
    }
    private ArrayList getNameList(){
        nameList.clear();
        idList.clear();
        for (Iterator iterator = dataUser.getAll().iterator(); iterator.hasNext(); ) {
            User user =(User)  iterator.next();
            nameList.add(user.getName());
            idList.add(user.getId());
        }
        return  nameList;
    }
}