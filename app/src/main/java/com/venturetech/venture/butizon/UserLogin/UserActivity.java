package com.venturetech.venture.butizon.UserLogin;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.venturetech.venture.butizon.ClubApp.Register;
import com.venturetech.venture.butizon.Fragments.User.BookingHistory;
import com.venturetech.venture.butizon.Fragments.User.Home;
import com.venturetech.venture.butizon.Fragments.User.ViewUser;
import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.Utilities.Consatnts;
import com.venturetech.venture.butizon.databases.DBTransactionFunctions;

public class UserActivity extends AppCompatActivity {
    private ActionBar toolbar;
    Fragment fragment;
    int[][] state =  new int [] [] {
            new int [] {android.R.attr.state_pressed},
            new int [] {android.R.attr.state_focused},
            new int [] {}
    };

    int[] color = new int [] {
            Color.rgb(255, 255, 255),
            Color.rgb(255, 255, 255),
            Color.rgb(255, 255, 255)
    };
    ColorStateList ColorStateList1 = new ColorStateList(state, color);

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new Consatnts.BottomNavigationViewBehavior());
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setItemIconTintList(ColorStateList1);
        navigation.setItemTextColor(ColorStateList1);
        fragment= Home.newInstance();
        loadFragment(fragment);
        TextView headerText= new TextView(getApplicationContext());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        headerText.setLayoutParams(lp);
        headerText.setText("Butizon");
                headerText.setTextSize(20);
        headerText.setTextColor(Color.parseColor("#FFFFFF"));
        Typeface tf = Typeface.createFromAsset(getAssets(), "baskvill.ttf");
        headerText.setTypeface(tf);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(headerText);

        Menu menu=navigation.getMenu();
        MenuItem menuItem1=menu.findItem(R.id.navigation_profile);
        MenuItem menuItem=menu.findItem(R.id.navigation_gifts);
        if (DBTransactionFunctions.getConfigvalue("user_type").equals("2")){
            menuItem.setTitle("Sign Up");
            menuItem1.setTitle("Exit");
        }
    }

    @Override
    public void onBackPressed() {

        final AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Confirm Exit..!!!");
        adb.setIcon(R.drawable.login);
        adb.setMessage("Are you sure,You want to exit");
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
               dialog.cancel();
            }
        });
        adb.show();

    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_shop:
                    fragment= Home.newInstance();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_gifts:
                    if (DBTransactionFunctions.getConfigvalue("user_type").equals("2")) {

                        Intent intent = new Intent(UserActivity.this, Register.class);
                        startActivity(intent);
                        finish();
                        return true;

                    }else{
                        fragment= BookingHistory.newInstance();
                        loadFragment(fragment);
                        return true;
                    }


                case R.id.navigation_profile:
                    if (DBTransactionFunctions.getConfigvalue("user_type").equals("2")) {

                        final AlertDialog.Builder adb = new AlertDialog.Builder(UserActivity.this);
                        adb.setTitle("Confirm Exit..!!!");
                        adb.setIcon(R.drawable.login);
                        adb.setMessage("Are you sure,You want to exit");
                        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        adb.show();
                        return true;

                    }else{
                        fragment= ViewUser.newInstance();
                        loadFragment(fragment);
                        return true;
                    }

            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.club_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent=new Intent(UserActivity.this,Nearbyshops.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

}
