package mavs.uta.team4carental.ui.profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import mavs.uta.team4carental.ui.MainActivity;

public class ProfileFragment extends Fragment {
// ↓ elegant but complex way
//    /* 与 activity 通信 */
//    private ProfileListener activityCallback;
//    public interface ProfileListener {
//        public void onLogout();
//    }
//    public void setProfileListener(ProfileListener callback) {
//        this.activityCallback = callback;
//    }

    private ProfileViewModel profileViewModel;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        this.bindView(inflater, container);
        this.bindViewModel();
        return root;
    }

    private void bindView(LayoutInflater inflater, ViewGroup container) {
        this.root = inflater.inflate(R.layout.fragment_profile, container, false);

        Button logoutButton = root.findViewById(R.id.logout);
        Log.d("logoutButton", logoutButton.toString());
        logoutButton.setOnClickListener(v -> {
            startActivity(new Intent(this.getContext(), MainActivity.class));
            Activity activity = getActivity();
            if (activity != null) activity.finish();
        });
    }

    private void bindViewModel() {
        final TextView textView = root.findViewById(R.id.text_dashboard);
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        profileViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
    }
}