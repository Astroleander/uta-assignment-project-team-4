package mavs.uta.team4carental.ui.user.requestCar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mavs.uta.team4carental.R;
import mavs.uta.team4carental.pojo.Car;
import mavs.uta.team4carental.ui.user.viewReservation.Reservations;
import mavs.uta.team4carental.utils.DBHelper;

public class requestCarActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    Fragment datePickerFragment;
    List<Car> carList;

    private TextView dateTextView;
    private DatePickerDialog dpd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_car);
        dbHelper = new DBHelper(this);

        Car[] carList = dbHelper.queryCar();



        




//        Car[] result = dbHelper.queryCar();
//        Log.d(result.toString(), "onCreate: result");
//        LinearLayout list = (LinearLayout)findViewById(R.id.list);
//        for(Car a:carList){
//            TextView tv = new TextView(this);
//            tv.setText(a.toString());
//            list.addView(tv);
//        }
    }


}