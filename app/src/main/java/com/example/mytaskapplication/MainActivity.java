package com.example.mytaskapplication;

import android.os.Bundle;
import android.view.View;

import com.example.mytaskapplication.utils.Prefs;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mytaskapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
        initNavController();

    }

    private void initViews() {
        navView = findViewById(R.id.nav_view);
    }

    private void initNavController() {
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        navController.navigate(R.id.boardFragment);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(R.id.navigation_home);
                list.add(R.id.navigation_dashboard);
                list.add(R.id.navigation_notifications);
                list.add(R.id.navigation_profile);

                if(list.contains(destination.getId())){
                    binding.navView.setVisibility(View.VISIBLE);


                } else{
                    binding.navView.setVisibility(View.GONE);
                }


            }
        });
        // Prefs prefs = new Prefs(requireContext());
        //if (!prefs.isBoardShown()){
        //initViewPager();
        //prefs.saveBordState();

    }
}
