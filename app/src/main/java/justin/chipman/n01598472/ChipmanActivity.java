// Justin Chipman n01598472
package justin.chipman.n01598472;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.Manifest;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ChipmanActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        TabLayout tabLayout = findViewById(R.id.Justintabs);
        ViewPager2 viewPager = findViewById(R.id.Justinview_pager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
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

            }
        });

        // Manually setting the first tab as selected to apply the correct color
        TabLayout.Tab initialTab = tabLayout.getTabAt(0);
        if (initialTab != null) {
            initialTab.view.setBackgroundColor(getResources().getColor(R.color.selected_tab));
        }


    }

    // Menu Bar Creation and logic

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.Justinaction_map) {
            checkLocationPermissionAndOpenMap();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void checkLocationPermissionAndOpenMap() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            getLastLocationAndOpenMap();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocationAndOpenMap();
            } else {
                Toast.makeText(this, getString(R.string.access_denied), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void getLastLocationAndOpenMap() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Permission check for ACCESS_FINE_LOCATION
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    if (location != null) {
                        Log.d(getString(R.string.location), getString(R.string.got_location) + location);
                        Uri gmmIntentUri = Uri.parse(getString(R.string.geo) + location.getLatitude() + "," + location.getLongitude());
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage(getString(R.string.google_local));
                        startActivity(mapIntent);
                    } else {
                        Log.d(getString(R.string.location), getString(R.string.location_not_available));
                        Toast.makeText(ChipmanActivity.this, R.string.location_not_available, Toast.LENGTH_LONG).show();
                    }
                });
    }



/*
    private void getLastLocationAndOpenMap() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Permission check for ACCESS_FINE_LOCATION
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    Intent mapIntent = null;
                    if (location != null) {
                        Log.d(getString(R.string.location), getString(R.string.got_location) + location);
                        Uri gmmIntentUri = Uri.parse(getString(R.string.geo) + location.getLatitude() + "," + location.getLongitude());
                        mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage(getString(R.string.google_local));
                        if (mapIntent.resolveActivity(getPackageManager()) != null) {
                            startActivity(mapIntent);
                        }else if (mapIntent.resolveActivity(getPackageManager()) == null) {
                            // Fallback to web browser if Google Maps app is not available
                            Uri webIntentUri = Uri.parse(getString(R.string.google_url) + location.getLatitude() + "," + location.getLongitude());
                            Intent webIntent = new Intent(Intent.ACTION_VIEW, webIntentUri);
                            startActivity(webIntent);
                        } else {
                            Log.d(getString(R.string.location), getString(R.string.could_not_resolve_map_intent));
                            Toast.makeText(ChipmanActivity.this, R.string.could_not_launch_maps, Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Log.d(getString(R.string.location), getString(R.string.location_not_available));
                        Toast.makeText(ChipmanActivity.this, R.string.location_not_available, Toast.LENGTH_LONG).show();
                    }
                });


    }
    */
}




