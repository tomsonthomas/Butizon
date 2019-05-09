package com.venturetech.venture.butizon.ClubApp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.UserLogin.UserActivity;
import com.venturetech.venture.butizon.Utilities.RetrofitService.RetrofitInterface;
import com.venturetech.venture.butizon.databases.DBTransactionFunctions;

import static com.venturetech.venture.butizon.databases.DBTransactionFunctions.isClubFound;
import static com.venturetech.venture.butizon.databases.DBTransactionFunctions.isUserFound;

public class ClubLogin extends AppCompatActivity {
    EditText username,password;
    Button login;
    Dialog progressBar;
    TextView register,skiplogin;
    String email;
    RadioButton radioButton;
    RadioGroup radioGroup;
    String usertype =" ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_login);

        username=findViewById(R.id.name);
        skiplogin=findViewById(R.id.skip);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        register=findViewById(R.id.register);
        skiplogin.setVisibility(View.GONE);

        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        setTitle("Club Login");
        RetrofitInterface.SetUserTables(getApplicationContext());
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.radiobuton1) {
                    usertype ="1";
                } else if(checkedId == R.id.radiobuton2) {

                    usertype ="0";

                }
            }

        });
        skiplogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBTransactionFunctions.updateConfigvalues("user_name", "");
                DBTransactionFunctions.updateConfigvalues("password", "");
                DBTransactionFunctions.updateConfigvalues("login_status", "1");
                DBTransactionFunctions.updateConfigvalues("userid","");
                DBTransactionFunctions.updateConfigvalues("gender","");
                DBTransactionFunctions.updateConfigvalues("user_type","2");
                Intent intent1 = new Intent(ClubLogin.this, UserActivity.class);
                startActivity(intent1);
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = true;
                String uemail = username.getText().toString();
                String upass = password.getText().toString();
                progressBar = new Dialog(ClubLogin.this);
                progressBar.setContentView(R.layout.progressdialogue);
                progressBar.show();
                if (!Register.isValidPassword( upass )) {
                    password.setError("password contain atleast one special character,one integer,upper and lower case letters");
                    flag = false;
                    progressBar.cancel();
                }
                if (isValidMail(username.getText().toString())) {
                    email = username.getText().toString();

                } else {
                    username.setError("Invalid Email Id");
                    flag = false;
                    progressBar.cancel();

                }
                if(usertype.equals(" ")){
                    Toast.makeText(getApplicationContext(),"Please Select User Type",Toast.LENGTH_LONG).show();
                    flag = false;
                    progressBar.cancel();

                }


                if (flag) {
                    if(usertype.equals("1"))
                    {
                        if(isClubFound(uemail,upass)){
                            DBTransactionFunctions.updateConfigvalues("user_name",uemail);
                            DBTransactionFunctions.updateConfigvalues("password", upass);
                            DBTransactionFunctions.updateConfigvalues("userid",DBTransactionFunctions.getClubid(uemail,upass));
                            DBTransactionFunctions.updateConfigvalues("login_status","1");
                            DBTransactionFunctions.updateConfigvalues("user_type","1");
                            Intent intent=new Intent(ClubLogin.this,ClubMain.class);
                            startActivity(intent);
                            finish();
                            progressBar.cancel();

                        }else {
                            progressBar.cancel();
                            Toast.makeText(getApplicationContext(),"No registered user found,Please check the username and password",Toast.LENGTH_LONG).show();
                        }

                    }else {
                        if(isUserFound(uemail,upass)) {
                            DBTransactionFunctions.updateConfigvalues("user_name", uemail);
                            DBTransactionFunctions.updateConfigvalues("password", upass);
                            DBTransactionFunctions.updateConfigvalues("login_status", "1");
                            DBTransactionFunctions.updateConfigvalues("userid",DBTransactionFunctions.getUserid(uemail,upass));
                            String gender=DBTransactionFunctions.getUserGender( uemail,upass );
                            if (gender.equals( "Male" ))
                            {
                                DBTransactionFunctions.updateConfigvalues("gender","Men");

                            }
                            else if (gender.equals( "Female" ))
                            {
                                DBTransactionFunctions.updateConfigvalues("gender","Women and kids");

                            }
                            else if (gender.equals( "Other" ))
                            {
                                DBTransactionFunctions.updateConfigvalues("gender","Both");

                            }
                            DBTransactionFunctions.updateConfigvalues("user_type","0");
                            Intent intent1 = new Intent(ClubLogin.this, UserActivity.class);
                            startActivity(intent1);
                            finish();
                            progressBar.cancel();

                        }
                        else {
                            Toast.makeText(getApplicationContext(),"No registered user found,Please check the username and password",Toast.LENGTH_LONG).show();
                            progressBar.cancel();
                        }
                    }


                        }

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ClubLogin.this,Register.class);
                startActivity(intent);
            }
        });


    }
    private boolean isValidMail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    private Boolean exit = false;

    @Override
    public void onBackPressed() {
        if (exit)
            System.exit(0);
        else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }
}
