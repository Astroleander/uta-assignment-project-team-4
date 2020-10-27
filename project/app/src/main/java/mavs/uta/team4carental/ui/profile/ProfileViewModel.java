package mavs.uta.team4carental.ui.profile;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import mavs.uta.team4carental.pojo.Car;
import mavs.uta.team4carental.pojo.User;
import mavs.uta.team4carental.utils.DBHelper;
import mavs.uta.team4carental.utils.EnumTable;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<User> mUser;

    public ProfileViewModel() {
        mUser = new MutableLiveData<>();
        mText = new MutableLiveData<>();
        mText.setValue("This is Profile fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<User> getUserInfo(Context context, String username) {
        String[] q = new String[]{username};
        DBHelper dbHelper = new DBHelper(context.getApplicationContext());
//        User[] user_list = dbHelper.queryUser(EnumTable.User.USERNAME + "?=\"", q);
//        User result = user_list[0];
        User result = new User(
                "alice",
                "123456",
                "admin",
                "13331110",
                "alicece",
                "alice",
                "1331331133",
                "a@b.com",
                "da 10 no 10",
                "ALT",
                "Teaxs",
                "a103",
                "",
                ""
                );
        mUser.setValue(result);
        return mUser;
    }

}