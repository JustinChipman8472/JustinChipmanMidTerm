// Justin Chipman n01598472
package justin.chipman.n01598472;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ChipmanActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.Justintabs);
        viewPager = findViewById(R.id.Justinview_pager);

        adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        String[] tabTitles = new String[]{getString(R.string.justin), getString(R.string.chipman), getString(R.string.n01598472)};

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(tabTitles[position])
        ).attach();

        // Set initial tab colors
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                // Unselected tabs
                tab.view.setBackgroundColor(getResources().getColor(R.color.unselected_tab));
            }
        }

        // Listen for tab selection to change colors
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Selected tab
                tab.view.setBackgroundColor(getResources().getColor(R.color.selected_tab));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Unselected tab
                tab.view.setBackgroundColor(getResources().getColor(R.color.unselected_tab));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Optionally handle reselection
            }
        });

        // Manually setting the first tab as selected to apply the correct color
        TabLayout.Tab initialTab = tabLayout.getTabAt(0);
        if (initialTab != null) {
            initialTab.view.setBackgroundColor(getResources().getColor(R.color.selected_tab));
        }
    }
}

