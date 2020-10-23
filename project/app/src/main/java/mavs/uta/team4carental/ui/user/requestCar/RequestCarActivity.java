package mavs.uta.team4carental.ui.user.requestCar;


import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;


import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.view.View.OnClickListener;

import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import mavs.uta.team4carental.R;

public class RequestCarActivity extends AppCompatActivity implements OnClickListener{
    private TextView startDate;
    private TextView endDate;
    private TextView startTime;
    private TextView endTime;
    private Button btn_request;
    private Calendar cal;
    private int year,month,day,hour,minute;
    private CarItemFragment carresult;
    private EditText capacity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_car);

        //获取当前日期
        getDate();

        startDate=(TextView) findViewById(R.id.startDate);
        startDate.setOnClickListener(this);

        startTime=(TextView)findViewById(R.id.startTime);
        startTime.setOnClickListener(this);

        endDate=(TextView) findViewById(R.id.endDate);
        endDate.setOnClickListener(this);

        endTime =(TextView)findViewById(R.id.endTime);
        endTime.setOnClickListener(this);

        capacity = (EditText)findViewById(R.id.capacity);
        btn_request = findViewById(R.id.btn_requestCar);

        String userName = getIntent().getStringExtra("userName");
        
        btn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //将布局中的et匹配给变量
                
//                String userName = "wang";
                //从Intent中获取userName
                
                //实例化Reservations fragement
                carresult = CarItemFragment.newInstance(userName,startDate.toString(), endDate.toString(),capacity.toString());
                //之后需要更新整个fragment
                getSupportFragmentManager().beginTransaction().replace(R.id.requestCar_flcontainer, carresult).commitAllowingStateLoss();

            }
        });

    }

    //获取当前日期
    private void getDate() {
        cal=Calendar.getInstance();
        year=cal.get(Calendar.YEAR);    //获取年月日时分秒
        Log.i("wxy","year"+year);
        month=cal.get(Calendar.MONTH);  //获取到的月份是从0开始计数
        day=cal.get(Calendar.DAY_OF_MONTH);
        hour = cal.get(Calendar.HOUR_OF_DAY);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startDate:
                OnDateSetListener listener=new OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        startDate.setText(year+"-"+(++month)+"-"+day);   //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                    }
                };
                DatePickerDialog dialog=new DatePickerDialog(RequestCarActivity.this, 0,listener,year,month,day);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog.show();
                break;

            case R.id.startTime:
                OnTimeSetListener listener1=new OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker tp, int hour,int minute) {
                        startTime.setText(hour+":00");
                    }
                };
                TimePickerDialog dialog1 = new TimePickerDialog(RequestCarActivity.this,listener1,hour,minute,true);
                dialog1.show();

                break;

            case R.id.endDate:
                listener=new OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        endDate.setText(year+"-"+(++month)+"-"+day);   //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                    }
                };
                dialog=new DatePickerDialog(RequestCarActivity.this, 0,listener,year,month,day);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog.show();
                break;


            case R.id.endTime:
                 listener1=new OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker tp, int hour,int minute) {
                        endTime.setText(hour+":00");
                    }
                };
                dialog1 = new TimePickerDialog(RequestCarActivity.this,listener1,hour,minute,true);
                dialog1.show();

                break;
            default:
                break;
        }
    }

}
