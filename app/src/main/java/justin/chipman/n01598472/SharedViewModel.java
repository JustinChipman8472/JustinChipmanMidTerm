package justin.chipman.n01598472;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> selectedEmail = new MutableLiveData<>();

    public void selectEmail(String email) {
        selectedEmail.setValue(email);
    }

    public LiveData<String> getSelectedEmail() {
        return selectedEmail;
    }
}
