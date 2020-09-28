package mavs.uta.team4carental.ui.admin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdminViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AdminViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is admin fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}