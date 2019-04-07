# UIModule
## 实验三：UI 组件
### 1.Android ListView的用法
利用SimpleAdapter实现如下界面效果
### 2.创建自定义布局的AlertDialog
创建如图所示的自定义对话框
 请创建一个如图所示的布局， 
 调用 AlertDialog.Builder 对象上的 setView() 将布局添加到 AlertDialog。
### 3.使用XML定义菜单
使用XML定义菜单
 字体大小（有小，中，大这3个选项；分 别对应10号字，16号字和20号字）； 点击之后设置测试文本的字体 
 普通菜单项，点击之后弹出Toast提示 
 字体颜色（有红色和黑色这2个选项）， 点击之后设置测试文本的字体
### 4.创建上下文操作模式(ActionMode)的上下文菜单
创建如图模式的上下文菜单 
 使用ListView或者ListActivity创建 List 
 为List Item创建ActionMode形式 的上下文菜单
## 代码及截图
### 1.Android ListView的用法
### SimpleAdapter主要代码：
```
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
```
### 结果截图：
![image](https://github.com/Magicpanda-orz/UIModule/blob/master/img/SimpleAdapter.PNG)  
##  
### 2.创建自定义布局的AlertDialog
### AlertDialogr主要代码：
```
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
```
### 结果截图：
![image](https://github.com/Magicpanda-orz/UIModule/blob/master/img/AlertDialog.PNG)  
##   
### 3.使用XML定义菜单
### XMLmenu主要代码：
```
public class XMLmenu extends AppCompatActivity {

    private final int size=110;
    private final int common=111;
    private final int color=112;
    private TextView textId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xmlmenu);
        textId=(TextView)findViewById(R.id.textid);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1,size,1,"字体大小");
        menu.add(1,common,2,"普通菜单项");
        menu.add(1,color,3,"字体颜色");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case size:
                final AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("设置字体大小");
                builder.setSingleChoiceItems(new String[]{"10号字体","16号字体","20号字体"},-1,new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:textId.setTextSize(10);
                                dialogInterface.dismiss();
                                break;
                            case 1:textId.setTextSize(16);
                                dialogInterface.dismiss();
                                break;
                            case 2:textId.setTextSize(20);
                                dialogInterface.dismiss();
                                break;
                        }
                    }
                });
                builder.setNegativeButton("取消",null);
                builder.show();
                break;
            case common:
                Toast.makeText(this,"Toast", Toast.LENGTH_LONG).show();
                break;
            case color:
                final AlertDialog.Builder builder2=new AlertDialog.Builder(this);
                builder2.setTitle("设置字体颜色");
                builder2.setSingleChoiceItems(new String[]{"红色","黑色","蓝色"},-1,new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:textId.setTextColor(Color.RED);
                                dialogInterface.dismiss();
                                break;
                            case 1:textId.setTextColor(Color.BLACK);
                                dialogInterface.dismiss();
                                break;
                            case 2:textId.setTextColor(Color.BLUE);
                                dialogInterface.dismiss();
                                break;
                        }
                    }
                });
                builder2.setNegativeButton("取消",null);
                builder2.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
```
### 结果截图：
![image](https://github.com/Magicpanda-orz/UIModule/blob/master/img/XMLmenu1.PNG) ![image](https://github.com/Magicpanda-orz/UIModule/blob/master/img/XMLmenu2.PNG)
![image](https://github.com/Magicpanda-orz/UIModule/blob/master/img/XMLmenu3.PNG) ![image](https://github.com/Magicpanda-orz/UIModule/blob/master/img/XMLmenu4.PNG)
##   
### 4.创建上下文操作模式(ActionMode)的上下文菜单
### ActionMode主要代码：
```
public class ActionModeTest extends AppCompatActivity {
    private ListView listView;
    private List<Map<String, Object>> listItems;
    private SimpleAdapter adapter;
    private int item_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actionmode);
        init();
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {

            }
            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                MenuInflater inflater = actionMode.getMenuInflater();
                inflater.inflate(R.menu.menudelete, menu);
                return true;
            }
            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }
            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_delete:
                        listItems.remove(item_id);
                        adapter.notifyDataSetChanged();
                        actionMode.finish();
                    default:
                        return false;
                }
            }
            @Override
            public void onDestroyActionMode(ActionMode actionMode) {
            }
        });
    }

    public void init() {
        listView = (ListView) findViewById(R.id.listView2);
        listItems = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < 6; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("number", i);
            listItem.put("images", R.mipmap.ic_launcher);
            listItems.add(listItem);
        }
        adapter = new SimpleAdapter(this, listItems, R.layout.action_mode,
                new String[]{"number", "images"}, new int[]{R.id.number, R.id.img});
        listView.setAdapter(adapter);
        //registerForContextMenu(listView);
    }
}
```
### 结果截图：
![image](https://github.com/Magicpanda-orz/UIModule/blob/master/img/Action1.PNG) ![image](https://github.com/Magicpanda-orz/UIModule/blob/master/img/Action2.PNG)
##   

