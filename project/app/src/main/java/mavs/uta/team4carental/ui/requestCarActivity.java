package mavs.uta.team4carental.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mavs.uta.team4carental.R;
import mavs.uta.team4carental.pojo.Car;
import mavs.uta.team4carental.utils.DBHelper;

public class requestCarActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    List<Car> carList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_car);
        dbHelper = new DBHelper(this);

        Car[] carList = dbHelper.queryCar();



//        Car[] result = dbHelper.queryCar();
//        Log.d(result.toString(), "onCreate: result");
        LinearLayout list = (LinearLayout)findViewById(R.id.list);
        for(Car a:carList){
            TextView tv = new TextView(this);
            tv.setText(a.toString());
            list.addView(tv);
        }
    }
}