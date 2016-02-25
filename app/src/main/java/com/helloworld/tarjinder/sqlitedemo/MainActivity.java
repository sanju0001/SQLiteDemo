package com.helloworld.tarjinder.sqlitedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etInputbox;
    TextView tvinputText;
    MyDBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etInputbox = (EditText) findViewById(R.id.etInputbox);
        tvinputText = (TextView) findViewById(R.id.tvinputText);
        dbHandler = new MyDBHandler(this,null,null,1);
        printDatabase();
    }

    //add product to the database
    public void addButtonClicked(View view){
        Products product = new Products(etInputbox.getText().toString());
        dbHandler.addProduct(product);
        printDatabase();
    }

    //delete product from database
    public void deleteButtonClicked(View view){
        String productName = etInputbox.getText().toString();
        dbHandler.deleteProduct(productName);
        printDatabase();
    }

    private void printDatabase() {
        String dbString = dbHandler.databaseToString();
        tvinputText.setText(dbString);
        etInputbox.setText("");
        }


}
