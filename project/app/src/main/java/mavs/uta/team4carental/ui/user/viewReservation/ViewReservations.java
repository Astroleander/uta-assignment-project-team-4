package mavs.uta.team4carental.ui.user.viewReservation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.View;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import mavs.uta.team4carental.R;
import mavs.uta.team4carental.pojo.Rental;
import mavs.uta.team4carental.ui.user.requestCar.requestCarActivity;
import mavs.uta.team4carental.utils.DBHelper;
import mavs.uta.team4carental.utils.EnumTable;
import android.widget.Toast;

import java.util.Calendar;

public class ViewReservations extends AppCompatActivity implements OnClickListener {
    private Reservations reservations;
    private DBHelper dbHelper;
    private Button btn_SearchMyReservation;
    private EditText et_startTime;
    private EditText et_backTime;
    private Calendar cal;
    private int year,month,day,hour,minute;
    private TextView startDate;
    private TextView endDate;
    private TextView startTime;
    private TextView endTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mavs.uta.team4carental.R.layout.activity_view_reservations);
        //获取当前时间分配给全局变量year, month,day
        getDate();

        startDate=(TextView) findViewById(R.id.tv_startDate_reservation);
        startDate.setOnClickListener(this);

        startTime=(TextView)findViewById(R.id.tv_startTime_reservation);
        startTime.setOnClickListener(this);

        endDate=(TextView) findViewById(R.id.tv_endDate_reservation);
        endDate.setOnClickListener(this);

        endTime =(TextView)findViewById(R.id.tv_endTime_reservation);
        endTime.setOnClickListener(this);




        //将布局中的button匹配给变量
        btn_SearchMyReservation = (Button)findViewById(R.id.btn_searchMyReservation);

        dbHelper = new DBHelper(this);

        //设置按钮的跳转
        btn_SearchMyReservation.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                //需要将按钮设置成能够给fragment传参（start time， back time， user name），并更新fragment显示结果的函数
                //从Textview中获取起始日期及时间，结束日期和时间
                String start_time = startDate.getText().toString() + startTime.getText().toString();

                String back_time = endDate.getText().toString() + endTime.getText().toString();

//                //！！这中间可以加一步对输入的处理操作，如果输入了月份或者日期为一个数字，则自动补成两位，若是其他格式则返回报错信息！！
//                String[] start_time_tmp = start_time.split("/");
//                if (start_time_tmp.length != 3) {
//                    Toast.makeText(getApplicationContext(), "Error Input!", Toast.LENGTH_LONG).show();
//                }
//                else {
//                    if (start_time_tmp[0].length() != 4){
//                        Toast.makeText(getApplicationContext(), "Error Input!", Toast.LENGTH_LONG).show();
//                    }
//                    else{
//                        if(start_time_tmp[1].length() == 1){
//                            start_time_tmp[1] = "0" + start_time_tmp[1];
//                        }
//                        if(start_time_tmp[2].length() == 1){
//                            start_time_tmp[2] = "0" + start_time_tmp[2];
//                        }
//                    }
//                }
//                String[] back_time_tmp = back_time.split("/");
//                if (back_time_tmp.length != 3) {
//                    Toast.makeText(getApplicationContext(), "Error Input!", Toast.LENGTH_LONG).show();
//                }
//                else {
//                    if (back_time_tmp[0].length() != 4){
//                        Toast.makeText(getApplicationContext(), "Error Input!", Toast.LENGTH_LONG).show();
//                    }
//                    else{
//                        if(back_time_tmp[1].length() == 1){
//                            back_time_tmp[1] = "0" + back_time_tmp[1];
//                        }
//                        if(back_time_tmp[2].length() == 1){
//                            back_time_tmp[2] = "0" + back_time_tmp[2];
//                        }
//                    }
//                }
//                start_time = String.join("/", start_time_tmp);
//                back_time = String.join("/", back_time_tmp);
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

    //获取当前日期
    private void getDate() {
        cal= Calendar.getInstance();
        year=cal.get(Calendar.YEAR);    //获取年月日时分秒
        Log.i("wxy","year"+year);
        month=cal.get(Calendar.MONTH);  //获取到的月份是从0开始计数
        day=cal.get(Calendar.DAY_OF_MONTH);
        hour = cal.get(Calendar.HOUR_OF_DAY);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_startDate_reservation:
                DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        startDate.setText(year+"-"+(++month)+"-"+day+" ");   //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                    }
                };
                DatePickerDialog dialog=new DatePickerDialog(ViewReservations.this, 0,listener,year,month,day);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog.show();
                break;

            case R.id.tv_startTime_reservation:
                TimePickerDialog.OnTimeSetListener listener1=new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker tp, int hour, int minute) {
                        startTime.setText(hour+":00");
                    }
                };
                TimePickerDialog dialog1 = new TimePickerDialog(ViewReservations.this,listener1,hour,minute,true);
                dialog1.show();

                break;

            case R.id.tv_endDate_reservation:
                listener=new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        endDate.setText(year+"-"+(++month)+"-"+day+" ");   //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                    }
                };
                dialog=new DatePickerDialog(ViewReservations.this, 0,listener,year,month,day);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog.show();
                break;


            case R.id.tv_endTime_reservation:
                listener1=new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker tp, int hour,int minute) {
                        endTime.setText(hour+":00");
                    }
                };
                dialog1 = new TimePickerDialog(ViewReservations.this,listener1,hour,minute,true);
                dialog1.show();

                break;
            default:
                break;
        }
    }
}