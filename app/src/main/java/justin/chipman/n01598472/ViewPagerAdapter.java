package justin.chipman.n01598472;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import justin.chipman.n01598472.ChipmanFragment2;
import justin.chipman.n01598472.JustinFragment1;
import justin.chipman.n01598472.n01598472Fragment3;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new JustinFragment1();
            case 1:
                return new ChipmanFragment2();
            case 2:
                return new n01598472Fragment3();
            default:
                return new JustinFragment1();// Fallback
        }
    }

    @Override
    public int getItemCount() {
        return 3; // Three fixed tabs
    }
}

