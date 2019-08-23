package section;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import androidx.navigation.NavController;

import com.hubertyoung.component_section.R;

import static androidx.navigation.Navigation.findNavController;
import static androidx.navigation.ui.NavigationUI.navigateUp;
import static androidx.navigation.ui.NavigationUI.setupActionBarWithNavController;
import static androidx.navigation.ui.NavigationUI.setupWithNavController;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_home);

        bindViews();

        setUpToolbar();

        setUpNavigation();

        if (savedInstanceState == null) {
            drawerLayout.openDrawer( GravityCompat.START);
        }
    }

    private void bindViews() {
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navController = findNavController(this, R.id.nav_host_fragment);
        navigationView = findViewById(R.id.nav_view);
    }

    private void setUpToolbar() {
        setSupportActionBar(toolbar);
//        ActionBar supportActionBar = getSupportActionBar();
//        if (supportActionBar != null) {
//            supportActionBar.setDisplayHomeAsUpEnabled(true);
//            supportActionBar.setDisplayShowHomeEnabled(true);
//        }
    }

    private void setUpNavigation() {
        setupActionBarWithNavController(this, navController, drawerLayout);
        setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navigateUp(navController, drawerLayout);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
