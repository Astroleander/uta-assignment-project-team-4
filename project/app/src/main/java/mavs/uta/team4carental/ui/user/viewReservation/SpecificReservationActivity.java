package mavs.uta.team4carental.ui.user.viewReservation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import mavs.uta.team4carental.R;
import mavs.uta.team4carental.adapter.CarListAdapter;
import mavs.uta.team4carental.adapter.ReservationListAdapter;
import mavs.uta.team4carental.pojo.Car;
import mavs.uta.team4carental.pojo.Rental;
import mavs.uta.team4carental.utils.DBHelper;
import mavs.uta.team4carental.utils.EnumTable;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class SpecificReservationActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private Button btn_cancelMyReservation;
    private Button btn_editMyReservation;
    private void removeReservation(Rental reservation) {
        dbHelper = new DBHelper(this);
        dbHelper.removeReservation(reservation);
    }
    private void editReservation(Rental reservation){
        dbHelper = new DBHelper(this);
        dbHelper.editReservation(reservation);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_reservation);
        Intent i = getIntent();
        Rental reservation = (Rental) i.getSerializableExtra(ReservationListAdapter.RESERVATION_INTENT_TOKEN);
        // 将两个按钮赋值给变量
        btn_cancelMyReservation = (Button)findViewById(R.id.cancel_reservation);
        btn_editMyReservation = (Button)findViewById(R.id.edit_reservation);
        // 设置一些条目为可编辑
        ((EditText) findViewById(R.id.Start)).setEnabled(true);
        ((EditText) findViewById(R.id.Back)).setEnabled(true);

        if (reservation != null) {
            ((EditText) findViewById(R.id.Number_of_Reservation)).setText(reservation.getID());
            ((EditText) findViewById(R.id.UserName)).setText(reservation.getUsername());
            ((EditText) findViewById(R.id.Start)).setText(reservation.getStart());
            ((EditText) findViewById(R.id.Back)).setText(reservation.getEnd());
            ((EditText) findViewById(R.id.Duration)).setText(reservation.getDuration());
            ((EditText) findViewById(R.id.Car_name)).setText(reservation.getCarName());
            // todo 这里的Capability还没有显示出来，因为Reservation中没有保存车的相关信息
//            ((EditText) findViewById(R.id.Capacity)).setText(reservation.getCapability());
            ((EditText) findViewById(R.id.total_price)).setText("$"+reservation.getTotalPrice());
            if (reservation.getGPS().equals("1")){
                ((CheckBox) findViewById(R.id.checkBox_gps)).setChecked(true);
            }
            if (reservation.getOnstar().equals("1")){
                ((CheckBox) findViewById(R.id.checkBox_onstar)).setChecked(true);
            }
            if (reservation.getSiriusxm().equals("1")){
                ((CheckBox) findViewById(R.id.checkBox_siriusXM)).setChecked(true);
            }
        }
        // 定义取消按钮的操作，点击取消之后将对应reservation的状态值置为1
        btn_cancelMyReservation.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                SpecificReservationActivity.this.removeReservation(reservation);
            }
        });
        btn_editMyReservation.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String Start = ((EditText) findViewById(R.id.Start)).getText().toString();
                String Back = ((EditText) findViewById(R.id.Back)).getText().toString();
                // 计算duration程序
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
                Date startdate = null;
                Date backdate = null;
                try {
                    startdate = format.parse(Start);
                    backdate = format.parse(Back);
                }catch (ParseException e){
                    e.printStackTrace();
                }

                float durations = (backdate.getTime() - startdate.getTime())/(1000*24*60*60);
                if(((backdate.getTime() - startdate.getTime())%(1000*24*60*60))==0){

                }else{
                    durations+=1;
                }
                String dur = String.valueOf(durations);
                // 更改Duration的值
                reservation.setDuration(dur);

                // todo 这里计算新的价格，并赋值给price

                String price = "100";
                //这里更改totalPrice
                reservation.setTotalPrice(price);
                reservation.setStart(((EditText) findViewById(R.id.Start)).getText().toString());
                reservation.setEnd(((EditText) findViewById(R.id.Back)).getText().toString());
                if (((CheckBox) findViewById(R.id.checkBox_gps)).isChecked()){
                    reservation.setGPS("1");
                }
                else {
                    reservation.setGPS("0");
                }
                if (((CheckBox) findViewById(R.id.checkBox_onstar)).isChecked()){
                    reservation.setOnstar("1");
                }
                else {
                    reservation.setOnstar("0");
                }
                if (((CheckBox) findViewById(R.id.checkBox_siriusXM)).isChecked()){
                    reservation.setSiriusxm("1");
                }
                else {
                    reservation.setSiriusxm("0");
                }
                SpecificReservationActivity.this.editReservation(reservation);

                ((EditText) findViewById(R.id.Number_of_Reservation)).setText(reservation.getID());
                ((EditText) findViewById(R.id.UserName)).setText(reservation.getUsername());
                ((EditText) findViewById(R.id.Start)).setText(reservation.getStart());
                ((EditText) findViewById(R.id.Back)).setText(reservation.getEnd());
                ((EditText) findViewById(R.id.Duration)).setText(reservation.getDuration());
                ((EditText) findViewById(R.id.Car_name)).setText(reservation.getCarName());
                // todo 这里的Capability还没有显示出来，因为Reservation中没有保存车的相关信息
//            ((EditText) findViewById(R.id.Capacity)).setText(reservation.getCapability());
                ((EditText) findViewById(R.id.total_price)).setText("$"+reservation.getTotalPrice());
            }
        });

    }
}