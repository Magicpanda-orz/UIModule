package com.example.panda.uimodule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.List;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import android.widget.AdapterView.OnItemClickListener;

public class SimpleAdapterTest extends AppCompatActivity {

    //用于显示布局里的动物名称
    private String[] names = new String[]{"Lion","Tiger","Monkey","Dog","Cat","Elephant"};
    private int[] image = new int[]{R.drawable.lion,R.drawable.tiger,
            R.drawable.monkey,R.drawable.dog,R.drawable.cat,R.drawable.elephant};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //此处引用布局文件
        setContentView(R.layout.activity_simple_adapter);
        //创建一个List集合，list集合的元素是Map
        List<Map<String,Object>> ListItems=new ArrayList<Map<String,Object>>();
        for(int i=0;i<names.length;i++){
            Map<String,Object> listItem=new HashMap<String,Object>();
            listItem.put("header",names[i]);
            listItem.put("images",image[i]);
            //加入list集合
            ListItems.add(listItem);
        }

        //创建一个SimpleAdapter，此处严格按照定义数组names与image顺序,否则会出现程序build成功却运行失败且难以解决错误
        SimpleAdapter simpleAdapter=new SimpleAdapter(
                this,ListItems,R.layout.simple_item,new String[]{"header","images"},new int[]{R.id.header,R.id.images});
        ListView list=(ListView)findViewById(R.id.mylist);
        //为ListView设置Adapter
        list.setAdapter(simpleAdapter);

        //对应点击事件
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Toast.makeText(SimpleAdapterTest.this,names[position],Toast.LENGTH_SHORT).show();
            }
        });

    }
}
