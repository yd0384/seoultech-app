package kr.ac.seoultech.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kr.ac.seoultech.myapplication.adapter.TodoAdapter;
import kr.ac.seoultech.myapplication.model.Todo;

import static android.R.attr.id;

public class TodoListActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{

    private final static int REQUEST_CODE_ADD=1;
    public final static int REQUEST_CODE_DETAIL=2;
    private ListView listView;
    private TodoAdapter adapter;
    private EditText etTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
        listView=(ListView)findViewById(R.id.list_view);
        etTitle=(EditText)findViewById(R.id.et_title);

        findViewById(R.id.btn_save).setOnClickListener(this);

        List<Todo> items=findTodoList();
        adapter=new TodoAdapter(this,R.layout.list_item_todo,items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_todo_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_add:{
                Intent intent = new Intent(this,TodoAddActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD);

                return true;
            }

        }

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_OK) return;

        if(requestCode==REQUEST_CODE_ADD){
            Todo todo=(Todo)data.getSerializableExtra("todo");
            adapter.additem(0,todo);
        }
        else if(requestCode==REQUEST_CODE_DETAIL){
            Todo todo = (Todo)data.getSerializableExtra("todo");
            int position = data.getIntExtra("position",-1);

            adapter.setItem(position,todo);
        }
    }

    private List<Todo> findTodoList() {
        List<Todo> list = new ArrayList<Todo>();
        for(int i=0;i<40;i++) {
            list.add(new Todo(0L, "제목","내용"+(i+1), new Date()));
        }
        return list;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save:{
                //TODO
                String title=etTitle.getText().toString();
                Todo todo=new Todo(0L,title,"",new Date());
                adapter.additem(0,todo);
                etTitle.setText("");
                hideKeyboard(etTitle);
                break;
            }
        }
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Todo todo=(Todo)adapter.getItem(position);
        Intent intent = new Intent(this,TodoDetailActivity.class);
        intent.putExtra("todo",todo);
        intent.putExtra("position",position);

        startActivityForResult(intent,REQUEST_CODE_DETAIL);
    }
    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long id) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setTitle("안내");
        builder.setMessage("삭제하시겠습니까?");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                adapter.removeItem(position);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        builder.create().show();

        return true;
    }
    private void hideKeyboard(EditText editText){
        InputMethodManager imm =
                (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }



}
