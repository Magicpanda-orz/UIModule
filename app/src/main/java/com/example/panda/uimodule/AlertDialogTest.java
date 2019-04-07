package com.example.panda.uimodule;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AlertDialogTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        Button bn=(Button)findViewById(R.id.clickme);

        LayoutInflater inflater=AlertDialogTest.this.getLayoutInflater();
        View v= inflater.inflate(R.layout.alertdialog,null,false);
        Context context=AlertDialogTest.this;
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        //创建AlterDialog对象
        builder.setView(v);
        //输入文本
        builder.setCancelable(false);
        final AlertDialog alertDialog=builder.create();
        //创建对象
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();
            }
        });

        v.findViewById(R.id.cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AlertDialogTest.this,"cancle",Toast.LENGTH_LONG).show();
                alertDialog.dismiss();
            }
        });
        v.findViewById(R.id.signin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AlertDialogTest.this,"Sign in",Toast.LENGTH_LONG).show();
                alertDialog.dismiss();
            }
        });
    }
}
