package com.roy_sun.infomanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roy_sun.infomanager.R;
import com.roy_sun.infomanager.domain.Person;
import com.roy_sun.infomanager.infodb.dao.PersonDao;

public class MainActivity extends AppCompatActivity {
    private EditText et_perID;
    private EditText et_name;
    private EditText et_phone;
    private PersonDao dao;

    private ListView list_info;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name = (EditText) findViewById(R.id.et_name);
        et_perID = (EditText) findViewById(R.id.et_perID);
        et_phone = (EditText) findViewById(R.id.et_phone);
        list_info = (ListView) findViewById(R.id.infolist);
        dao = new PersonDao(this);

        list_info.setAdapter(new PersonAdapter());
    }

    private class PersonAdapter extends BaseAdapter{

        @Override
        public int getCount () {
            //返回数据库的记录数
            return dao.getTotal();
        }

        @Override
        public View getView (int position, View convertView, ViewGroup parent) {
            Person person = dao.getPersonInfo(position);
            TextView tv = new TextView(MainActivity.this);
            tv.setText(person.toString());
            return tv;
        }

        @Override
        public Object getItem (int position) {
            return null;
        }

        @Override
        public long getItemId (int position) {
            return 0;
        }
    }


    //点击空白处可以隐藏软键盘
/*    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

    @Override
    public boolean onTouchEvent (MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (MainActivity.this.getCurrentFocus() != null) {
                if (MainActivity.this.getCurrentFocus().getWindowToken() != null) {
                    imm.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }
        return super.onTouchEvent(event);
    }*/

    public void add (View view) {
        String per_num = et_perID.getText().toString().trim();
        String name = et_name.getText().toString().trim();
        String phone = et_phone.getText().toString().trim();

        //判断输入的数据,为空则报错
        if (TextUtils.isEmpty(per_num) || TextUtils.isEmpty(name) || TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请检查输入的数据", Toast.LENGTH_SHORT).show();
        }

        //判断输入的数据是否存在
        if (dao.find(per_num)) {
            Toast.makeText(this, "数据已存在", Toast.LENGTH_SHORT).show();
            return;
        }


        boolean result = dao.add(per_num, name, phone);
        if (result) {
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
            list_info.setAdapter(new PersonAdapter());
        } else {
            Toast.makeText(this, "数据重复", Toast.LENGTH_SHORT).show();
        }
    }

    public void clear (View view) {
        et_phone.setText("");
        et_perID.setText("");
        et_name.setText("");

    }


}
