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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import mavs.uta.team4carental.R;
import mavs.uta.team4carental.ui.MainActivity;
import mavs.uta.team4carental.utils.DBHelper;

public class ProfileFragment extends Fragment {
    public static final String PROFILE_TOKEN = "PROFILE";
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
    private Activity activity;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        activity = this.getActivity();
        this.bindViewModel();
        this.bindView(inflater, container);
        return root;
    }

    private void bindView(LayoutInflater inflater, ViewGroup container) {
        this.root = inflater.inflate(R.layout.fragment_profile, container, false);
        this.initLogoutButton();
        this.initProfile();
    }

    private void initProfile() {
        String username = "";
        if (activity != null) {
            username = activity.getIntent().getStringExtra("userName");
            profileViewModel.getUserInfo(activity, username).observe(getViewLifecycleOwner(), u -> {
                Button btn = root.findViewById(R.id.view_profile);
                btn.setOnClickListener(v -> {
                    Intent i = new Intent(activity, ViewProfileActivity.class);
                    i.putExtra(PROFILE_TOKEN, u);
                    this.startActivity(i);
                });
            });
        }
    }

    private void initLogoutButton() {
        Button logoutButton = root.findViewById(R.id.logout);
        logoutButton.setOnClickListener(v -> {
            startActivity(new Intent(activity, MainActivity.class));
            if (activity != null) activity.finish();
        });
    }

    private void bindViewModel() {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
    }
}