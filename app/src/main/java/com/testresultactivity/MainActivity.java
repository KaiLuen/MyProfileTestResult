package com.testresultactivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {
    public static final String QUERY_STRING = "DataQuery";
    private Button testButton;
    private EditText nameEditText, phoneEditText, emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEditText = (EditText)findViewById(R.id.editText_name);
        phoneEditText = (EditText)findViewById(R.id.editText_phone);
        emailEditText = (EditText)findViewById(R.id.editText_email);

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
            try {
                startActivityForResult(myProfileIntent, 0);
            } catch (ActivityNotFoundException activity) {
                Uri appUri = Uri.parse("http://market.android.com/details?id=cityforfun.myprofile");
                Intent intent1 = new Intent( Intent.ACTION_VIEW,  appUri);
                MainActivity.this.startActivity(intent1);
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((resultCode != 0) || (data == null)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder = builder.setMessage("Error").setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.show();
            return;
        }

        HashMap<String, String> resultMap = (HashMap<String, String>)data.getSerializableExtra("Result");
        nameEditText.setText(resultMap.get("Name"));
        phoneEditText.setText(resultMap.get("Address"));
        emailEditText.setText(resultMap.get("Email"));
    }
}
