package com.example.asharani.alertmenu;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text;
    SharedPreferences sharedPreferences;

    public void setlanguage(String language){
        sharedPreferences = this.getSharedPreferences("com.example.asharani.alertmenu",Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("userlanguage",language).apply();
        text.setText("you chose "+language);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.englang:
                setlanguage("english");
                return true;
            case R.id.japlang:
                setlanguage("japanese");
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView)findViewById(R.id.textView);
        sharedPreferences = this.getSharedPreferences("com.example.asharani.alertmenu",Context.MODE_PRIVATE);
        String language = sharedPreferences.getString("userlanguage","Error");
        if(language.equals("Error")) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Language preference")
                    .setMessage("choose a language")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setlanguage("english");
                        }
                    })
                    .setNegativeButton("japanese", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setlanguage("japanese");
                        }
                    })
                    .show();
        }else {
            text.setText("you chose " +
                    ""+language);
        }
    }
}
