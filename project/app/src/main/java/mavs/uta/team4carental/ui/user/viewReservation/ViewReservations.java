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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import mavs.uta.team4carental.R;
import mavs.uta.team4carental.adapter.CarListAdapter;
import mavs.uta.team4carental.adapter.ReservationListAdapter;
import mavs.uta.team4carental.pojo.Car;
import mavs.uta.team4carental.pojo.Rental;
import mavs.uta.team4carental.ui.user.requestCar.RequestCarActivity;
import mavs.uta.team4carental.utils.DBHelper;
import mavs.uta.team4carental.utils.EnumTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class ViewReservations extends AppCompatActivity implements OnClickListener {
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
    private ArrayList<Rental> reservation_ListItems;
    private ListView reservationListView;
    private ReservationListAdapter adapter;


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

        initReservationList();




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
                String start_time = startDate.getText().toString() +'-'+ startTime.getText().toString();

                String back_time = endDate.getText().toString() + '-'+endTime.getText().toString();




//                String userName = "wang";
                //从Intent中获取userName
                String userName = getIntent().getStringExtra("userName");
                //将结果存入reservationListItems中
                ViewReservations.this.queryReservations(userName, start_time, back_time);
                //显示整个列表
                adapter = new ReservationListAdapter(ViewReservations.this, ViewReservations.this.reservation_ListItems);
                reservationListView.setAdapter(adapter);

            }
        });

    }

    private void initReservationList() { reservationListView = findViewById(R.id.reservation_list); }


    //获取当前日期
    private void getDate() {
        cal= Calendar.getInstance();
        year=cal.get(Calendar.YEAR);    //获取年月日时分秒
        Log.i("wxy","year"+year);
        month=cal.get(Calendar.MONTH);  //获取到的月份是从0开始计数
        day=cal.get(Calendar.DAY_OF_MONTH);
        hour = cal.get(Calendar.HOUR_OF_DAY);
    }

    private void queryReservations(String userName, String startTime, String endTime) {
        dbHelper = new DBHelper(this);
        //给queryReservations提供参数使其能够进行查找操作
        Rental[] reservation_list;
        reservation_list = dbHelper.queryReservations(userName, startTime.toString(), endTime.toString());

        int k=0;
        String[] reservation_names = new String[100];
        for(Rental a:reservation_list){
            reservation_names[k]=a.getCarName();
        }
        ArrayList<Rental> result = new ArrayList<>(Arrays.asList(reservation_list));
        this.reservation_ListItems = result;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_startDate_reservation:
                DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        String tmp_month = String.valueOf(month + 1);
                        String tmp_day = String.valueOf(day);

                        if (month < 9){
                            tmp_month = ("0" + String.valueOf(month + 1));
                        }
                        if (day < 10){
                            tmp_day = ("0" + String.valueOf(day));
                        }
                        startDate.setText(year+"-"+tmp_month+"-"+tmp_day+" ");
                    }
                };
                DatePickerDialog dialog=new DatePickerDialog(ViewReservations.this, 0,listener,year,month,day);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog.show();
                break;

            case R.id.tv_startTime_reservation:
                TimePickerDialog.OnTimeSetListener listener1=new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker tp, int hour, int minute) {
                        String res = "";
                        if (hour < 10){
                            res = ("0"+hour).toString();
                            startTime.setText(res + ":00");
                            return;
                        }
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
                        String tmp_month = String.valueOf(month + 1);
                        String tmp_day = String.valueOf(day);

                        if (month < 9){
                            tmp_month = ("0" + String.valueOf(month + 1));
                        }
                        if (day < 10){
                            tmp_day = ("0" + String.valueOf(day));
                        }
                        endDate.setText(year+"-"+tmp_month+"-"+tmp_day+" ");   //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                    }
                };
                dialog=new DatePickerDialog(ViewReservations.this, 0,listener,year,month,day);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog.show();
                break;


            case R.id.tv_endTime_reservation:
                listener1=new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker tp, int hour,int minute) {
                        String res;
                        if (hour < 10){
                            res = ("0"+hour).toString();
                            endTime.setText(res + ":00");
                            return;
                        }
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