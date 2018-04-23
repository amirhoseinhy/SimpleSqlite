package com.niksan.simplesqlite;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.niksan.simplesqlite.Adapter.MyAdapter;
import com.niksan.simplesqlite.Model.DataAccess.UserDA;
import com.niksan.simplesqlite.Model.TableObject.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private UserDA userDA;


    RecyclerView recyclerView;
    MyAdapter adapter;
    Button btnAdd, btnDelete;
    EditText edtAdd, edtDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        edtAdd = findViewById(R.id.edtAdd);
        edtDelete = findViewById(R.id.edtDelete);


        userDA = new UserDA(this);
        userDA.openDb();


        final List<User> users = userDA.getAllUsers();
        adapter = new MyAdapter(users);
        

        if (users.size() != 0) {
            for (User user2 : users) {
                System.out.println("ID is : " + user2.getId());
                System.out.println("Name is : " + user2.getName());
            }
        }

//////////////////////////////////////////////
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        if (users.size() != 0) {
            for (int i = 0; i < users.size(); i++) {
                User user1 = new User();
                user1.getName();
            }
        }
        /////////////////////////////////////////////////////////////
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                String name = edtAdd.getText().toString();
                user.setName(name);
                userDA.addUser(user);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = edtDelete.getText().toString();
                //  int id = edtDelete.getId();
                userDA.deleteUserById((Integer.parseInt(t)));

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        userDA.closeDb();
    }

    @Override
    protected void onResume() {
        super.onResume();
        userDA.openDb();
    }
}
