package com.testresultactivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {
    public static final String QUERY_STRING = "DataQuery";
    private Button testButton;
    private TextView nameTextView, phoneTextView, addressTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameTextView = (TextView)findViewById(R.id.textView_name);
        phoneTextView = (TextView)findViewById(R.id.textView_phone);
        addressTextView = (TextView)findViewById(R.id.textView_address);

        testButton = (Button)findViewById(R.id.button);
        testButton.setOnClickListener(test);
    }

    private Button.OnClickListener test = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent myProfileIntent = new Intent("cityforfun.myprofile.query");
            ArrayList<String> queryList = new ArrayList<>();
            queryList.add("Name");
            queryList.add("Address");
            queryList.add("Email");
            myProfileIntent.putExtra(QUERY_STRING, queryList);
            startActivityForResult(myProfileIntent, 0);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder = builder.setMessage("User Denied").setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.show();
            return;
        }
        HashMap<String, String> resultMap = (HashMap<String, String>)data.getSerializableExtra("Result");
        nameTextView.setText(resultMap.get("Name"));
        phoneTextView.setText(resultMap.get("Email"));
        addressTextView.setText(resultMap.get("Address"));
    }
}
