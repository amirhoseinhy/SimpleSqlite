package com.niksan.simplesqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.niksan.simplesqlite.Model.DataAccess.UserDA;
import com.niksan.simplesqlite.Model.TableObject.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    UserDA userDA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDA = new UserDA(this);
        userDA.openDb();

        List<User> users = userDA.getAllUsers();
        if(users.size() != 0){
            for(User user:users){
                System.out.println("ID is : "+ user.getId());
                System.out.println("Name is : "+ user.getName());
            }
        }

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
