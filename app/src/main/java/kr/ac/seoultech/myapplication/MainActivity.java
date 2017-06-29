package kr.ac.seoultech.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "on create");

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "on start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "on resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "on pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "on stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "on destroy");
    }

    @Override
    public void onClick (View v){
        switch (v.getId()) {
            case R.id.btn1: {
                Toast.makeText(this, "버튼1클릭됨", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn2: {
                Intent i = new Intent(this, SubActivity.class);
                startActivity(i);
                break;
            }
            case R.id.btn3: {
                Intent i = new Intent(this, LoginActivity.class);
                startActivity(i);
                break;
            }
            case R.id.btn4: {
                Intent i = new Intent(this, SimpleListActivity.class);
                startActivity(i);
                break;
            }
            case R.id.btn5: {
                startActivity(new Intent(this, TodoListActivity.class));
                break;
            }
        }
    }
}
