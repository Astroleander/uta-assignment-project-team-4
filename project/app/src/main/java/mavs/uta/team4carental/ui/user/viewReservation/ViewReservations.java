package mavs.uta.team4carental.ui.user.viewReservation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import mavs.uta.team4carental.R;
import mavs.uta.team4carental.pojo.Rental;
import mavs.uta.team4carental.utils.DBHelper;
import mavs.uta.team4carental.utils.EnumTable;
import android.widget.Toast;

public class ViewReservations extends AppCompatActivity {
    private Reservations reservations;
    private DBHelper dbHelper;
    private Button btn_SearchMyReservation;
    private EditText et_startTime;
    private EditText et_backTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mavs.uta.team4carental.R.layout.activity_view_reservations);
        //将布局中的button匹配给变量
        btn_SearchMyReservation = (Button)findViewById(R.id.btn_searchMyReservation);

        dbHelper = new DBHelper(this);

        //设置按钮的跳转
        btn_SearchMyReservation.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                //将布局中的et匹配给变量.输入格式必须为yyyy/mm/dd  ！！
                et_startTime = (EditText)findViewById(R.id.et_startTime);
                et_backTime = (EditText)findViewById(R.id.et_endTime);
                //需要将按钮设置成能够给fragment传参（start time， back time， user name），并更新fragment显示结果的函数
                //再加一个初始值，开始时间设置为当前日期，结束时间设置为明天！！！！！！！！！
                String start_time = et_startTime.getText().toString();
                String back_time = et_backTime.getText().toString();

                //！！这中间可以加一步对输入的处理操作，如果输入了月份或者日期为一个数字，则自动补成两位，若是其他格式则返回报错信息！！
                String[] start_time_tmp = start_time.split("/");
                if (start_time_tmp.length != 3) {
                    Toast.makeText(getApplicationContext(), "Error Input!", Toast.LENGTH_LONG).show();
                }
                else {
                    if (start_time_tmp[0].length() != 4){
                        Toast.makeText(getApplicationContext(), "Error Input!", Toast.LENGTH_LONG).show();
                    }
                    else{
                        if(start_time_tmp[1].length() == 1){
                            start_time_tmp[1] = "0" + start_time_tmp[1];
                        }
                        if(start_time_tmp[2].length() == 1){
                            start_time_tmp[2] = "0" + start_time_tmp[2];
                        }
                    }
                }
                String[] back_time_tmp = back_time.split("/");
                if (back_time_tmp.length != 3) {
                    Toast.makeText(getApplicationContext(), "Error Input!", Toast.LENGTH_LONG).show();
                }
                else {
                    if (back_time_tmp[0].length() != 4){
                        Toast.makeText(getApplicationContext(), "Error Input!", Toast.LENGTH_LONG).show();
                    }
                    else{
                        if(back_time_tmp[1].length() == 1){
                            back_time_tmp[1] = "0" + back_time_tmp[1];
                        }
                        if(back_time_tmp[2].length() == 1){
                            back_time_tmp[2] = "0" + back_time_tmp[2];
                        }
                    }
                }
                start_time = String.join("/", start_time_tmp);
                back_time = String.join("/", back_time_tmp);
                //
//                String userName = "wang";
                //从Intent中获取userName
                String userName = getIntent().getStringExtra("userName");
                //实例化Reservations fragement
                reservations = Reservations.newInstance(userName, start_time, back_time);
                //之后需要更新整个fragment
                getSupportFragmentManager().beginTransaction().replace(R.id.reservation_flcontainer, reservations).commitAllowingStateLoss();

            }
        });

    }
}