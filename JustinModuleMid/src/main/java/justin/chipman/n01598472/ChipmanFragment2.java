// Justin Chipman n01598472
package justin.chipman.n01598472;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChipmanFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChipmanFragment2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView tvEmail;
    private RatingBar ratingBar;
    private Button btnSubmitRating;

    public ChipmanFragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChipmanFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static ChipmanFragment2 newInstance(String param1, String param2) {
        ChipmanFragment2 fragment = new ChipmanFragment2();
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
        View view = inflater.inflate(R.layout.fragment_chipman2, container, false);

        tvEmail = view.findViewById(R.id.JustvEmail);
        ratingBar = view.findViewById(R.id.JusratingBar);
        btnSubmitRating = view.findViewById(R.id.JusbtnSubmitRating);

        // Retrieve and display email if exists
        SharedViewModel model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        model.getSelectedEmail().observe(getViewLifecycleOwner(), email -> {
            tvEmail.setText(email != null ? email : getString(R.string.no_data));
        });

        // When submit button clicked get rating and display on snackbar
        btnSubmitRating.setOnClickListener(v -> {
            float rating = ratingBar.getRating();
            Snackbar.make(view, getString(R.string.rating) + rating, Snackbar.LENGTH_LONG).show();
        });

        return view;
    }
}
