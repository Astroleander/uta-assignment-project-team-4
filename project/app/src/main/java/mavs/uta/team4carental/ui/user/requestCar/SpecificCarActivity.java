package mavs.uta.team4carental.ui.user.requestCar;

import androidx.appcompat.app.AppCompatActivity;
import mavs.uta.team4carental.R;
import mavs.uta.team4carental.adapter.CarListAdapter;
import mavs.uta.team4carental.pojo.Car;
import mavs.uta.team4carental.pojo.Rental;
import mavs.uta.team4carental.utils.DBHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SpecificCarActivity extends AppCompatActivity {
    private Button reserve;

    private DBHelper dbHelper;

    private String totalprice;
    private Rental rental;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_car);
        Intent i = getIntent();
        Car car = (Car) i.getSerializableExtra(CarListAdapter.CAR_INTENT_TOKEN);
        String user = getIntent().getStringExtra("user");
        String start = getIntent().getStringExtra("start");
        String back = getIntent().getStringExtra("back");
        String occupants = getIntent().getStringExtra("occupants");
//        Rental[] reservations=dbHelper.queryAllReservations("0000-00-00-00:00","3000-00-00-00:00");






        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        Date startdate = null;
        Date backdate = null;
        try {
            startdate = format.parse(start);
            backdate = format.parse(back);
        }catch (ParseException e){
            e.printStackTrace();
        }

        long durations = (backdate.getTime() - startdate.getTime())/(1000*24*60*60);
        String dur = String.valueOf(durations);

        Calendar cal = Calendar.getInstance();
        cal.setTime(startdate);
        int start_week = cal.get(Calendar.DAY_OF_WEEK)-1;
        cal.setTime(backdate);
        int back_week = cal.get(Calendar.DAY_OF_WEEK)-1;
//
//
////        TextView test = findViewById(R.id.for_test);
////        if (car != null) {
////            test.setText(car.toString());
////        } else {
////            test.setText("error token");
////        }
////        Intent i = getIntent();
////        Car car = (Car) i.getSerializableExtra(CarListAdapter.CAR_INTENT_TOKEN);
        if (car != null) {
            ((TextView) findViewById(R.id.car_name)).setText(car.getCarname());
            ((TextView) findViewById(R.id.capacity)).setText(car.getCapicity());
            ((TextView) findViewById(R.id.Start)).setText(start);
            ((TextView) findViewById(R.id.Back)).setText(back+String.valueOf(back_week));
            ((TextView) findViewById(R.id.duration)).setText(dur);
            ((TextView) findViewById(R.id.number_of_occupants)).setText(occupants+" occupant(s)");

            ((TextView) findViewById(R.id.total_price)).setText("32.99$");

        }

        reserve = findViewById(R.id.reserve);
        reserve.setOnClickListener(v -> {
//            dbHelper.addReservation(rental);

            Toast.makeText(this, "Reservation number 1 ,price: $32.99,no extra" ,Toast.LENGTH_LONG).show();
            finish();

        });
    }
}