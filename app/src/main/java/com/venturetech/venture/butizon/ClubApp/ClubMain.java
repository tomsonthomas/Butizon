package com.venturetech.venture.butizon.ClubApp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.venturetech.venture.butizon.ClubApp.Typeface.CustomTypefaceSpan;
import com.venturetech.venture.butizon.Fragments.Club.ActivityAddService;
import com.venturetech.venture.butizon.Fragments.Club.AddEmployee;
import com.venturetech.venture.butizon.Fragments.Club.AddShedule;
import com.venturetech.venture.butizon.Fragments.Club.Bookings;
import com.venturetech.venture.butizon.Fragments.Club.EditClub;
import com.venturetech.venture.butizon.Fragments.Club.EditEmployee;
import com.venturetech.venture.butizon.Fragments.Club.EditService;
import com.venturetech.venture.butizon.Fragments.Club.ViewFeedBack;
import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.databases.DBTransactionFunctions;

public class ClubMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    int[][] state =  new int [] [] {
            new int [] {android.R.attr.state_pressed},
            new int [] {android.R.attr.state_focused},
            new int [] {}
    };

    int[] color = new int [] {
            Color.rgb(60, 76, 226),
            Color.rgb(0, 0, 0),
            Color.rgb(224, 180, 0)
    };
    int[] color1 = new int [] {
            Color.rgb(255, 255, 255),
            Color.rgb(0, 0, 0),
            Color.rgb(255, 255, 255)
    };
    private Handler mHandler;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    Fragment fm=null;
    ColorStateList ColorStateList1 = new ColorStateList(state, color);
    ColorStateList ColorStateList2 = new ColorStateList(state, color1);
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_main);
        checkRunTimePermission();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation1);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setItemIconTintList(ColorStateList2);
        navigation.setItemTextColor(ColorStateList2);
        mHandler= new Handler();
        setPage(0);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        setToolbar(toolbar,"ButiZon");
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



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Menu menu = navigationView.getMenu();
        try {
            MenuItem edit, viewdata, logout, add;
            edit = menu.findItem(R.id.edit);

            logout = menu.findItem(R.id.logoutm);
            add = menu.findItem(R.id.addclub);
            SpannableString s = new SpannableString(edit.getTitle());
            s.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance44), 0, s.length(), 0);
            edit.setTitle(s);

            s = new SpannableString(logout.getTitle());
            s.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance44), 0, s.length(), 0);
            logout.setTitle(s);
            s = new SpannableString(add.getTitle());
            s.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance44), 0, s.length(), 0);
            add.setTitle(s);
            navigationView.setNavigationItemSelectedListener(this);
            navigationView.setItemIconTintList(ColorStateList1);
            navigationView.setItemTextColor(ColorStateList1);
            Menu m = navigationView.getMenu();
            for (int i = 0; i < m.size(); i++) {
                MenuItem mi = m.getItem(i);

                //for aapplying a font to subMenu ...
                SubMenu subMenu = mi.getSubMenu();
                if (subMenu != null && subMenu.size() > 0) {
                    for (int j = 0; j < subMenu.size(); j++) {
                        MenuItem subMenuItem = subMenu.getItem(j);
                        applyFontToMenuItem(subMenuItem);
                    }
                }

                //the method we have create in activity
                applyFontToMenuItem(mi);
            }
        }catch (Exception e){e.printStackTrace();}
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.name);
        ImageView navimage = (ImageView) headerView.findViewById(R.id.imageView);
        byte[] imageBytes = Base64.decode(DBTransactionFunctions.getClubImage(DBTransactionFunctions.getConfigvalue("userid")), Base64.DEFAULT);
        Bitmap mbitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        //  Bitmap mbitmap = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.tc)).getBitmap();
        Bitmap imageRounded = Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getConfig());
        Canvas canvas = new Canvas(imageRounded);
        Paint mpaint = new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), 100, 100, mpaint);
        navimage.setImageBitmap(mbitmap);
        try {
            navUsername.setText(DBTransactionFunctions.getUsername(DBTransactionFunctions.getConfigvalue("userid")));
            TextView navUsername1 = (TextView) headerView.findViewById(R.id.textView);
            navUsername1.setText(DBTransactionFunctions.getConfigvalue("user_name"));
        }
        catch (Exception e){
            e.printStackTrace();
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




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);

 if (id == R.id.editclubdata) {
            setToolbar(toolbar,"Edit Club Profile");
            setPage(3);
        } else if (id == R.id.editempdata) {
            setToolbar(toolbar,"Edit Employee Data");
            setPage(4);
        } else if (id == R.id.editservdata) {
            setToolbar(toolbar,"Edit Service");
            setPage(5);

        }else if (id == R.id.editschoduledata) {
            setToolbar(toolbar,"Edit Schedule");
            setPage(6);
        }
        else if (id == R.id.addschedule ){
            setToolbar(toolbar,"Add Schedule");
            setPage(7);
        }else if (id == R.id.addservice) {
            setToolbar(toolbar,"Add Service");
            setPage(8);
        }else if (id == R.id.addemployee) {
            setToolbar(toolbar,"Add Employee");
            setPage(9);
        }else if (id == R.id.logout) {
            DBTransactionFunctions.updateConfigvalues("login_status","0");
            Intent intent = new Intent(getApplicationContext(),ClubLogin.class);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }

    private void setPage(int i) {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        Bundle bundle = new Bundle();
        switch (i) {
            case 0:
                fm =  Bookings.newInstance();
                commitFragment(fm);
                break;
                case 1:
                fm =  ViewFeedBack.newInstance();
                commitFragment(fm);
                break;
            case 2:
                fm=ClubProfile.newInstance();
                commitFragment( fm );
                break;
                case 3:
                fm =  EditClub.newInstance();
                commitFragment(fm);
                break;
                case 4:
                fm =  EditEmployee.newInstance();
                commitFragment(fm);
                break;
                case 5:
                fm =  EditService.newInstance();
                commitFragment(fm);
                break;
                case 6:
                    bundle.putString("edit","0");
                    fm =  AddShedule.newInstance();
                    fm.setArguments(bundle);
                commitFragment(fm);
                break;
                case 7:
                    bundle.putString("edit","1");
                    fm =  AddShedule.newInstance();
                    fm.setArguments(bundle);
                commitFragment(fm);
                break;
                case 8:
                    fm =  ActivityAddService.newInstance();
                commitFragment(fm);
                break;
                case 9:
                    fm =  AddEmployee.newInstance();
                commitFragment(fm);
                break;
        }
    }

    class CommitFragmentRunnable implements Runnable {

        private Fragment fragment;

        public CommitFragmentRunnable(Fragment fragment) {
            this.fragment = fragment;
        }

        @Override
        public void run() {
            try {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment).commit();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public void commitFragment(Fragment fragment) {
        mHandler.post(new CommitFragmentRunnable(fragment));
    }
    private void applyFontToMenuItem(MenuItem mi) {
        try {
            Typeface font = Typeface.createFromAsset(getAssets(), "baskvill.ttf");
            SpannableString mNewTitle = new SpannableString(mi.getTitle());
            mNewTitle.setSpan(new CustomTypefaceSpan("", font), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            mi.setTitle(mNewTitle);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setToolbar(Toolbar toolbar, String title){
        AppCompatActivity actionBar = ClubMain.this;
        actionBar.setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout)actionBar.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toogle);
        drawer.setScrimColor(Color.WHITE);
        toogle.setDrawerIndicatorEnabled(true);
        toogle.syncState();
        if(toolbar!=null)
            toolbar.setTitle(title);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_club:
                    setPage( 0 );
                    return true;
                case R.id.navigation_feedback:

                    setPage( 1 );
                    return true;

                case R.id.navigation_clubprofile:

                    setPage( 2 );
                    return true;
            }

            return false;

        }
    };

    private void checkRunTimePermission() {
        String[] permissionArrays = new String[]{Manifest.permission.SEND_SMS
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissionArrays, 11111);
        } else {
            // if already permition granted
            // PUT YOUR ACTION (Like Open cemara etc..)
        }
    }
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean openActivityOnce = true;
        boolean openDialogOnce = true;
        if (requestCode == 11111) {
            boolean isPermitted=false;
            for (int i = 0; i < grantResults.length; i++) {
                String permission = permissions[i];

                isPermitted = grantResults[i] == PackageManager.PERMISSION_GRANTED;

                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    // user rejected the permission
                    boolean showRationale = shouldShowRequestPermissionRationale(permission);
                    if (!showRationale) {
                        //execute when 'never Ask Again' tick and permission dialog not show
                    } else {
                        if (openDialogOnce) {
                            alertView();
                        }
                    }
                }
            }

            if (isPermitted){

            }

        }
    }

    private void alertView() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(ClubMain.this);

        dialog.setTitle("Permission Denied")
                .setInverseBackgroundForced(true)
                //.setIcon(R.drawable.ic_info_black_24dp)
                .setMessage("Without those permission the app is unable to save your profile. App needs to save profile image in your external storage and also need to get profile image from camera or external storage.Are you sure you want to deny this permission?")

                .setNegativeButton("I'M SURE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.dismiss();
                    }
                })
                .setPositiveButton("RE-TRY", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.dismiss();
                        checkRunTimePermission();

                    }
                }).show();
    }





}
