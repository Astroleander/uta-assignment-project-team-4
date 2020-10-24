package mavs.uta.team4carental.ui.user.viewReservation;

import androidx.appcompat.app.AppCompatActivity;
import mavs.uta.team4carental.R;
import mavs.uta.team4carental.adapter.CarListAdapter;
import mavs.uta.team4carental.adapter.ReservationListAdapter;
import mavs.uta.team4carental.pojo.Car;
import mavs.uta.team4carental.pojo.Rental;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SpecificReservationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_reservation);
        Intent i = getIntent();
        Rental reservation = (Rental) i.getSerializableExtra(ReservationListAdapter.RESERVATION_INTENT_TOKEN);
        TextView test = findViewById(R.id.for_test_reservation);
        if (reservation != null) {
            test.setText(reservation.toString());
        } else {
            test.setText("error token");
        }
    }
}