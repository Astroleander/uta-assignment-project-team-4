package mavs.uta.team4carental.ui.manager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import mavs.uta.team4carental.R;
import mavs.uta.team4carental.adapter.CarListAdapter;
import mavs.uta.team4carental.adapter.ReservationListAdapter;
import mavs.uta.team4carental.pojo.Car;
import mavs.uta.team4carental.pojo.Rental;
import mavs.uta.team4carental.utils.DBHelper;

public class DisplayCarActivity extends AppCompatActivity {
    private Button reserve;

    private DBHelper dbHelper;

    private String totalprice;
    private Rental rental;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_car);
        Intent i = getIntent();
        Car car = (Car) i.getSerializableExtra(CarListAdapter.CAR_INTENT_TOKEN);
        if (car != null) {
            ((TextView) findViewById(R.id.car_name)).setText(car.getCarname());
            ((TextView) findViewById(R.id.capacity)).setText(car.getCapicity());
            ((TextView) findViewById(R.id.weekday_rate)).setText(car.getWeekday() + "$/day");
            ((TextView) findViewById(R.id.weekend_rate)).setText(car.getWeekend() + "$/day");
            ((TextView) findViewById(R.id.week_rate)).setText(car.getWeel() + "$/week");
            ((TextView) findViewById(R.id.gps_rate)).setText(car.getGps() + "$/day");
            ((TextView) findViewById(R.id.on_star_rate)).setText(car.getOnstar() + "$/day");
            ((TextView) findViewById(R.id.siriusXM_rate)).setText(car.getSiriusxm() + "$/day");
            ((TextView) findViewById(R.id.status)).setText("Available");

        }

    }
}