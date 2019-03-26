package com.example.rupak.myapplication;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Rect;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UserInterface {

    DatabaseStorage db = new DatabaseStorage(this);
    private EditText user;
    private EditText address;
    private Button save;
    private Button retrive;
    private Button update;
    private String username;
    private String addr;
    private EditText id;
    private String uid;


    private Button del;
    private ArrayList<UserDto> userDtoArrayList;
    private RecyclerView recyclerView;
    private UserAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        user = findViewById(R.id.username);
        address = findViewById(R.id.address);
        id = findViewById(R.id.id);
        save = findViewById(R.id.save);
        retrive = findViewById(R.id.retrive);
        del = findViewById(R.id.delete);
        update = findViewById(R.id.update);
        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });
        retrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData();
            }

        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData(id.getText().toString());
            }
        });

        // LinearLayoutManager linearLayoutManager=LinearLayoutManager.HORIZONTAL;
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


    }

    private void saveData() {
        username = user.getText().toString();
        addr = address.getText().toString();

        if (!username.isEmpty() && !addr.isEmpty()) {
            boolean isInserted = db.insertData(username, addr);
            if (isInserted == true) {
                Toast.makeText(MainActivity.this, "Data inserted Successfully ", Toast.LENGTH_SHORT).show();
                user.setText("");
                address.setText("");
                showData();

            } else {
                Toast.makeText(MainActivity.this, "Data couldnt be inserted", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void deleteData(String uid) {
        String id = uid;
        Integer deletedRows = db.deleteData(id);
        if (deletedRows > 0) {
            Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
            showData();
        } else
            Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
    }


    private void updateData() {
        username = user.getText().toString();
        addr = address.getText().toString();
        uid = id.getText().toString();
        if (!username.isEmpty() && !addr.isEmpty()) {
            boolean isUpdate = db.updateData(uid, username, addr);
            if (isUpdate == true) {
                Toast.makeText(MainActivity.this, "Data Update", Toast.LENGTH_LONG).show();
                user.setText("");
                address.setText("");
                showData();


            } else
                Toast.makeText(MainActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(MainActivity.this, "Data not updataed", Toast.LENGTH_SHORT).show();
        }
    }

    private void showData() {

        userDtoArrayList = new ArrayList<>();
        userDtoArrayList = db.retrieveDatas();
         adapter = new UserAdapter(getApplicationContext(),userDtoArrayList);
        recyclerView.setAdapter(adapter);


//        List<HashMap<String, String>> users = db.retrieveDatas();
//        Log.e("msg", users.toString());
//        if (users.isEmpty()) {
//            Toast.makeText(this, "No data in Database", Toast.LENGTH_SHORT).show();
//        } else {
//            adapter = new UserAdapter(this, this, users);
//            recyclerView.setAdapter(adapter);
//        }
       // recyclerView.setAdapter(adapter);

    }


    @Override
    public void listItemClicked(String name, final String uid) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Do you want to delete this data?");
        alertDialog.setCancelable(true);

        alertDialog.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteData(uid);
                    }
                });

        alertDialog.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = alertDialog.create();
        alert11.show();

    }
}
