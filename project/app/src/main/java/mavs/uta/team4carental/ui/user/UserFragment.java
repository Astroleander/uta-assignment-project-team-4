package mavs.uta.team4carental.ui.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import mavs.uta.team4carental.R;
import mavs.uta.team4carental.ui.UserActivity;

public class UserFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user, container, false);
        final Button mBtnViewReservation = (Button) root.findViewById(R.id.btn_ViewReservation);
        final Button requestCarButton = (Button) root.findViewById(R.id.btn_RequestCar);
        final Activity activity = getActivity();
        mBtnViewReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName =  activity.getIntent().getStringExtra("userName");
                Intent intent = new Intent(activity, mavs.uta.team4carental.ui.user.viewReservation.ViewReservations.class);
                intent.putExtra("userName", userName);
                startActivity(intent);
            }
        });

        requestCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = activity.getIntent().getStringExtra("userName");
                Intent intent = new Intent(activity, mavs.uta.team4carental.ui.user.requestCar.requestCarActivity.class);
                intent.putExtra("userName", userName);
                startActivity(intent);
            }
        });

        return root;
    }
}