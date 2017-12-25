package com.example.notificationdrawerdemo.activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.notificationdrawerdemo.R;
import com.example.notificationdrawerdemo.fragment.HomeFragment;
import com.example.notificationdrawerdemo.fragment.NotificationFragment;
import com.example.notificationdrawerdemo.fragment.ProfileFragment;
import com.example.notificationdrawerdemo.fragment.SettingFragment;
import com.example.notificationdrawerdemo.other.SessionManager;

public class MainActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtname, txtEmail;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private SessionManager session;

    private static final String urlNavHeaderbg = "https://www.gamedevmarket.net/inc/uploads/ac7a045577fcb440efc841c693fca6f35d3e4b26.png";

    public static int navItemIndex = 0;

    private static final String TAG_HOME = "home";
    private static final String TAG_PROFILE = "profile";
    private static final String TAG_NOTIFICATION = "notification";
    private static final String TAG_SETTING = "setting";
    public static String CURRENT_TAG = TAG_HOME;

    String[] activityTitle = {"Home", "Profile","Notification", "Setting"};

    private boolean ShouldLoadHomeFragOnHomePress = true;

    private Handler mhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        session=new SessionManager(this);

        mhandler = new Handler();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        fab = findViewById(R.id.fab);

        navHeader = navigationView.getHeaderView(0);
        txtname =  navHeader.findViewById(R.id.header_Username);
        txtEmail = navHeader.findViewById(R.id.header_emailId);
        imgNavHeaderBg =  navHeader.findViewById(R.id.headerBG);
        imgProfile =  navHeader.findViewById(R.id.header_profile);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Hello Everyone", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        });
        loadNavHeader();
        setNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragement();
        }

    }

    private void loadNavHeader() {
        txtname.setText(session.getUserDetails());
        txtEmail.setText("Bishopshakya@gmail.com");
        Glide.with(this)
                .load(urlNavHeaderbg)
                .into(imgNavHeaderBg);

        Glide.with(this)
                .load(session.getUserImage())
                .into(imgProfile);





    }

    private void loadHomeFragement() {
        selectNavMenu();
        setToolabarTitle();

        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) !=  null) {
            drawerLayout.closeDrawers();
            toggleFab();
            return;
        }

        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                Fragment fragment = getHomeFragment();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };
        if (mPendingRunnable != null) {
            mhandler.post(mPendingRunnable);
        }
        toggleFab();
        drawerLayout.closeDrawers();
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                //home
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;

            case 1:
                //Profile
                ProfileFragment profileFragment = new ProfileFragment();
                return profileFragment;

            case 2:
                //Notification
                NotificationFragment notificationFragment = new NotificationFragment();
                return notificationFragment;

            case 3:
                //Setting
                SettingFragment settingFragment = new SettingFragment();
                return settingFragment;


            default:
                return new HomeFragment();
        }
    }

    private void setToolabarTitle() {
        getSupportActionBar().setTitle(activityTitle[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    public void setNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;

                    case R.id.nav_profile:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_PROFILE;
                        break;

                    case R.id.nav_notification:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_NOTIFICATION;
                        break;

                    case R.id.nav_setting:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_SETTING;
                        break;

                    case R.id.nav_aboutUs:
                        Intent i = new Intent(MainActivity.this, AboutUsActiivity.class);
                        startActivity(i);
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.nav_policy:
                        Intent j = new Intent(MainActivity.this, PolicyActivity.class);
                        startActivity(j);
                        drawerLayout.closeDrawers();
                        return true;
                    default:
                        navItemIndex = 0;
                }

                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                item.setChecked(true);
                loadHomeFragement();
                return true;
            }
        });
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
            return;
        }
        if (ShouldLoadHomeFragOnHomePress) {

            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragement();
                return;
            }
        }

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (navItemIndex == 0) {
            getMenuInflater().inflate(R.menu.main, menu);
        }

        if (navItemIndex == 2) {
            getMenuInflater().inflate(R.menu.notifications, menu);
        }
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(this, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth);
            } else {
                builder = new AlertDialog.Builder(this);
            }
            builder.setTitle("Log Out")
                    .setMessage("Are you sure you want to logout??")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            session.logoutUser();
                            finish();
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return true;
        }

        if (id == R.id.action_mark_all_read) {
            Toast.makeText(getApplicationContext(), "All notifications marked as read!", Toast.LENGTH_LONG).show();
        }

        if (id == R.id.action_clear_all_notification) {
            Toast.makeText(getApplicationContext(), "Clear all notifications!", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void toggleFab() {
        if (navItemIndex == 0)
            fab.show();
        else
            fab.hide();
    }
}

