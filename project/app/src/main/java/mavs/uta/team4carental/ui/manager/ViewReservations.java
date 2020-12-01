package mavs.uta.team4carental.ui.manager;

import androidx.appcompat.app.AppCompatActivity;
import mavs.uta.team4carental.R;
import mavs.uta.team4carental.adapter.ManagerReservationListAdapter;
import mavs.uta.team4carental.adapter.ReservationListAdapter;
import mavs.uta.team4carental.pojo.Rental;
import mavs.uta.team4carental.utils.DBHelper;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class ViewReservations extends AppCompatActivity {
    private EditText startDate;
    private EditText startTime;
    private DatePickerDialog dStartDate;
    private TimePickerDialog dStartTime;

    private Button search;

    private EditText endDate;
    private EditText endTime;
    private DatePickerDialog dEndDate;
    private TimePickerDialog dEndTime;

    private Calendar calendar;
    private int defaultYear;
    private int defaultMonth;
    private int defaultDay;
    private int defaultHour;
    private int defaultMinute;

    private ArrayList<Rental> reservations;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_reservations);
        this.initTick();
        this.initPickerEditor();
        this.initDialog();
        this.initList();
        this.initButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String start_time = startDate.getText().toString() +'-'+ startTime.getText().toString();
        String end_time = endDate.getText().toString() + '-' + endTime.getText().toString();
        ArrayList<Rental> result = this.queryReservations(start_time, end_time);
        listView.setAdapter(new ManagerReservationListAdapter(ViewReservations.this, result));
    }

    private void initList() {
        listView = findViewById(R.id.list);
        reservations = new ArrayList<>();
        listView.setAdapter(new ManagerReservationListAdapter(ViewReservations.this, reservations));
    }

    private void initButton() {
        search = findViewById(R.id.query);
        search.setOnClickListener(v -> {
            String start_time = startDate.getText().toString() +'-'+ startTime.getText().toString();
            String end_time = endDate.getText().toString() + '-' + endTime.getText().toString();
            ArrayList<Rental> result = this.queryReservations(start_time, end_time);
            listView.setAdapter(new ManagerReservationListAdapter(this, result));
        });
    }

    //获取当前日期
    private void initTick() {
        calendar     = Calendar.getInstance();
        defaultYear  = calendar.get(Calendar.YEAR);    //获取年月日时分秒
        defaultMonth = calendar.get(Calendar.MONTH);  //获取到的月份是从0开始计数
        defaultDay   = calendar.get(Calendar.DAY_OF_MONTH);
        defaultHour  = calendar.get(Calendar.HOUR_OF_DAY);
    }
    private void initDialog() {
        dStartDate = new DatePickerDialog(
                this, 0,  createOnDateSetListener(startDate), defaultYear, defaultMonth, defaultDay
        );
        dEndDate = new DatePickerDialog(
                this, 0,  createOnDateSetListener(endDate), defaultYear, defaultMonth, defaultDay
        );
        dStartTime = new TimePickerDialog(
                this, createOnTimeSetListener(startTime), defaultHour, defaultMinute, true
        );
        dEndTime = new TimePickerDialog(
                this, createOnTimeSetListener(endTime), defaultHour, defaultMinute, true
        );
    }
    private void initPickerEditor() {
        startDate = findViewById(R.id.start_date);
        startDate.setInputType(InputType.TYPE_NULL);
        startTime = findViewById(R.id.start_time);
        startTime.setInputType(InputType.TYPE_NULL);
        endDate = findViewById(R.id.end_date);
        endDate.setInputType(InputType.TYPE_NULL);
        endTime = findViewById(R.id.end_time);
        endTime.setInputType(InputType.TYPE_NULL);
        startDate.setOnClickListener(v -> {
            dStartDate.show();
        });
        startTime.setOnClickListener(v -> {
            dStartTime.show();
        });
        endDate.setOnClickListener(v -> {
            dEndDate.show();
        });
        endTime.setOnClickListener(v -> {
            dEndTime.show();
        });
    }

    private DatePickerDialog.OnDateSetListener createOnDateSetListener(EditText editText) {
        return (arg0, year, month, day) -> {
            String tmp_month = String.valueOf(month + 1);
            String tmp_day = String.valueOf(day);
            if (month < 9){
                tmp_month = ("0" + String.valueOf(month + 1));
            }
            if (day < 10){
                tmp_day = ("0" + String.valueOf(day));
            }
            editText.setText(year+"-"+tmp_month+"-"+tmp_day+" ");
        };
    }
    private TimePickerDialog.OnTimeSetListener createOnTimeSetListener(EditText editText) {
        return (tp, hour, minute) -> {
            String res = "";
            if (hour < 10){
                res = ("0"+hour).toString();
                editText.setText(res + ":00");
                return;
            }
            editText.setText(hour+":00");
        };
    }

    private ArrayList<Rental> queryReservations(String startTime, String endTime) {
        DBHelper dbHelper = new DBHelper(this);
        //给queryReservations提供参数使其能够进行查找操作
        Rental[] reservation_list;
        reservation_list = dbHelper.queryAllReservations(startTime.toString(), endTime.toString());
        return new ArrayList<>(Arrays.asList(reservation_list));
    }

}