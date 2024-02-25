// Justin Chipman n01598472
package justin.chipman.n01598472;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> selectedEmail = new MutableLiveData<>();
    private final MutableLiveData<String> toastMessage = new MutableLiveData<>();
    private int counter = 0;

    public void selectEmail(String email) {
        selectedEmail.setValue(email);
    }

    public LiveData<String> getSelectedEmail() {
        return selectedEmail;
    }

    public LiveData<String> getToastMessage() {
        return toastMessage;
    }

    public void updateToastMessage() {
        counter++;
        toastMessage.setValue(counter + " Justin Chipman");
    }
}
