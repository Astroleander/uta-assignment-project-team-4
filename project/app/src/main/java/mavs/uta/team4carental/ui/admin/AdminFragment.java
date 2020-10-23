package mavs.uta.team4carental.ui.admin;

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

public class AdminFragment extends Fragment {

    private View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_user, container, false);
        this.initView();
        return root;
    }


    private void initView() {
        Button searchButton = root.findViewById(R.id.Search_For_Users);
        searchButton.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), SearchForUsersActivity.class));
        });
    }
}