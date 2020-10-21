package mavs.uta.team4carental.ui.user.requestCar;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.TextView;

import mavs.uta.team4carental.R;
import mavs.uta.team4carental.ui.user.viewReservation.Reservations;

public class requestCarActivity extends Activity implements OnClickListener{
    private TextView startDate;
    private TextView endDate;
    private Button btn_request;
    private Calendar cal;
    private int year,month,day;
    private carResult carresult;
    private EditText capacity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_car);

        //获取当前日期
        getDate();

        startDate=(TextView) findViewById(R.id.startDate);
        startDate.setOnClickListener(this);

        endDate=(TextView) findViewById(R.id.endDate);
        endDate.setOnClickListener(this);

        capacity = (EditText)findViewById(R.id.capacity);
        btn_request = findViewById(R.id.btn_requestCar);
        
        btn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //将布局中的et匹配给变量
                
//                String userName = "wang";
                //从Intent中获取userName
                
                //实例化Reservations fragement
                carresult = carResult.newInstance(startDate.toString(), endDate.toString(),capacity.toString());
                //之后需要更新整个fragment
//                getSupportFragmentManager().beginTransaction().replace(R.id.requestCar_flcontainer, carresult).commitAllowingStateLoss();

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
                DatePickerDialog dialog=new DatePickerDialog(requestCarActivity.this, 0,listener,year,month,day);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog.show();
                break;
            case R.id.endDate:
                listener=new OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        endDate.setText(year+"-"+(++month)+"-"+day);   //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                    }
                };
                dialog=new DatePickerDialog(requestCarActivity.this, 0,listener,year,month,day);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog.show();
                break;

            default:
                break;
        }
    }

}
