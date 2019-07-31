package com.example.appcontact;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.example.appcontact.Contact.Contact;
import com.example.appcontact.Contact.ContactAdapter;
import com.example.appcontact.Contact.MyOnClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvList;
    private Switch sLayout;
    private FloatingActionButton fabAdd;

    private List<Contact> listContact;
    public static ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Radiation();
        CreateAdapter();
        Event();
    }

    private void Event() {
        sLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sLayout.isChecked()){
                    GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this,2);
                    rvList.setLayoutManager(layoutManager);

                }else{
                    LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                    rvList.setLayoutManager(layoutManager);
                }
            }
        });

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDialogCustom();
            }
        });

        contactAdapter.setMyOnClickListener(new MyOnClickListener() {
            @Override
            public void onClick(Contact contact) {
                Toast.makeText(MainActivity.this,"OK",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ShowDialogCustom() {
        final Dialog dialog = new Dialog(MainActivity.this);

        //Delete title
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set view
        dialog.setContentView(R.layout.custom_alertdialog);

        //Radiation dialog
        final EditText edtContactName = (EditText) dialog.findViewById(R.id.edtContactName);
        final EditText edtContactNumber = (EditText) dialog.findViewById(R.id.edtContactNumber);
        Button btnClose = (Button) dialog.findViewById(R.id.btnClose);
        Button btnSave = (Button) dialog.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtContactName.getText().toString();
                String number = edtContactNumber.getText().toString();

                //add array fist ArrayList
                listContact.add(0,new Contact(name,number));

                //Update status

                contactAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,"Save Success",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }


    private void CreateAdapter() {
        rvList.setHasFixedSize(true);
        listContact = new ArrayList<Contact>();

        for (int i = 0 ; i< 10 ; i++) {
            listContact.add(new Contact("Heo " + i, "035624196" + i));
        }

        //array reverse 0-> 9 to 9->0
        Collections.reverse(listContact);

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        rvList.setLayoutManager(layoutManager);
        contactAdapter = new ContactAdapter(MainActivity.this,listContact);
        rvList.setAdapter(contactAdapter);

    }

    private void Radiation() {
        rvList = (RecyclerView) findViewById(R.id.rvList);
        sLayout = (Switch) findViewById(R.id.sLayout);
        fabAdd=(FloatingActionButton) findViewById(R.id.fabAdd);
    }
}
