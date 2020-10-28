package mavs.uta.team4carental.ui.manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import mavs.uta.team4carental.R;

public class ManagerFragment extends Fragment {
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_manager, container, false);
        view.findViewById(R.id.view_all_reservations).setOnClickListener(v -> {
            startActivity(new Intent(this.getContext(), ViewReservations.class));
        });
        view.findViewById(R.id.view_cars).setOnClickListener(v -> {
            startActivity(new Intent(this.getContext(), ViewCars.class));
        });
        view.findViewById(R.id.search_cars).setOnClickListener(v -> {
            startActivity(new Intent(this.getContext(), SearchCars.class));
        });
        return view;
    }
}