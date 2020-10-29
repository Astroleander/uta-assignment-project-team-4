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
        String user = getIntent().getStringExtra("userName");
        String start = getIntent().getStringExtra("start");
        String back = getIntent().getStringExtra("back");
        //计算价格



//        TextView test = findViewById(R.id.for_test);
//        if (car != null) {
//            test.setText(car.toString());
//        } else {
//            test.setText("error token");
//        }
//        Intent i = getIntent();
//        Car car = (Car) i.getSerializableExtra(CarListAdapter.CAR_INTENT_TOKEN);
        if (car != null) {
            ((TextView) findViewById(R.id.car_name)).setText(car.getCarname());
            ((TextView) findViewById(R.id.capacity)).setText(car.getCapicity());
            ((TextView) findViewById(R.id.Start)).setText("2020-10-28-15:00");
            ((TextView) findViewById(R.id.Back)).setText("2020-10-28-15:00");
            ((TextView) findViewById(R.id.duration)).setText("1 weekday");
            ((TextView) findViewById(R.id.number_of_occupants)).setText("1 occupant(s)");

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