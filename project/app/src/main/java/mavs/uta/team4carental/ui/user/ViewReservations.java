package mavs.uta.team4carental.ui.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import mavs.uta.team4carental.R;
import mavs.uta.team4carental.pojo.Rental;
import mavs.uta.team4carental.utils.DBHelper;
import mavs.uta.team4carental.utils.EnumTable;

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
//        //将布局中的et匹配给变量
//        et_startTime = (EditText)findViewById(R.id.et_startTime);
//        et_backTime = (EditText)findViewById(R.id.et_endTime);

//        String start_time = et_startTime.getText().toString();
//        String back_time = et_backTime.getText().toString();
//        //实例化Reservations fragement
//        reservations = Reservations.newInstance(userName, start_time, back_time);
        //实例化dbHelper
        dbHelper = new DBHelper(this);
        //初始实例化fragment
        reservations = new Reservations();
        //把Reservations fragement 添加到fragment_flcontaomer中
        getSupportFragmentManager().beginTransaction().add(R.id.reservation_flcontainer, reservations).commitAllowingStateLoss();

        //设置按钮的跳转
        btn_SearchMyReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //将布局中的et匹配给变量
                et_startTime = (EditText)findViewById(R.id.et_startTime);
                et_backTime = (EditText)findViewById(R.id.et_endTime);
                //需要将按钮设置成能够给fragment传参（start time， back time， user name），并更新fragment显示结果的函数
                String start_time = et_startTime.getText().toString();
                String back_time = et_backTime.getText().toString();
                String userName = "123";
                //实例化Reservations fragement
                reservations = Reservations.newInstance(userName, start_time, back_time);
                //之后需要更新整个fragment
                getSupportFragmentManager().beginTransaction().replace(R.id.reservation_flcontainer, reservations).commitAllowingStateLoss();

            }
        });

    }
}