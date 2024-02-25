// Justin Chipman n01598472
package justin.chipman.n01598472;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JustinFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JustinFragment1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private AutoCompleteTextView autoCompleteEmail;
    private Button btnSubmit;

    public JustinFragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JustinFragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static JustinFragment1 newInstance(String param1, String param2) {
        JustinFragment1 fragment = new JustinFragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_justin1, container, false);

        autoCompleteEmail = view.findViewById(R.id.autoCompleteEmail);
        btnSubmit = view.findViewById(R.id.btnSubmit);

        // Set up the AutoCompleteTextView
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.Justin, android.R.layout.simple_dropdown_item_1line);
        autoCompleteEmail.setAdapter(adapter);

        // Button click listener
        btnSubmit.setOnClickListener(v -> validateEmail(autoCompleteEmail.getText().toString()));

        return view;
    }

    private void validateEmail(String email) {
        if (email.isEmpty()) {
            autoCompleteEmail.setError(getString(R.string.cannot_be_empty));
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            autoCompleteEmail.setError(getString(R.string.invalid_email));
            return;
        }

        // Pass email to other fragment
        SharedViewModel model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        model.selectEmail(email);

        // Clear the user input
        autoCompleteEmail.setText("");
    }
}