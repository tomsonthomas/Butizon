package com.venturetech.venture.butizon.Utilities.RetrofitService;

import android.content.ContentValues;
import android.content.Context;
import android.widget.Toast;

import com.venturetech.venture.butizon.Fragments.Club.EditEmployee;
import com.venturetech.venture.butizon.Fragments.Club.EditService;
import com.venturetech.venture.butizon.Model.List.ClubList;
import com.venturetech.venture.butizon.Model.List.EmployeeFullList;
import com.venturetech.venture.butizon.Model.List.ServiceFullList;
import com.venturetech.venture.butizon.Model.List.SheduleFullList;
import com.venturetech.venture.butizon.Model.List.UserList;
import com.venturetech.venture.butizon.Model.ServiceClub;
import com.venturetech.venture.butizon.Model.ServiceEmployee;
import com.venturetech.venture.butizon.Model.ServiceShedule;
import com.venturetech.venture.butizon.Model.Update.UpdateClub;
import com.venturetech.venture.butizon.Model.Update.UpdateEmployee;
import com.venturetech.venture.butizon.Model.Update.UpdateService;
import com.venturetech.venture.butizon.Model.Update.UpdateShedule;
import com.venturetech.venture.butizon.Model.UserApp.List.AppoinmentList;
import com.venturetech.venture.butizon.Model.UserApp.List.ServiceFeedbackList;
import com.venturetech.venture.butizon.databases.DBTransactionFunctions;

import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RetrofitInterface {
    public static boolean flag = false;


    // to set the club table

    public static void SetUserTables(final Context context) {

try {
    HashMap<String, String> hashMap = new HashMap<>();
    hashMap.put("table", "club");

    RetroInterface retro = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);
    retro.clublist(hashMap).enqueue(new Callback<ClubList>() {
        @Override
        public void onResponse(Call<ClubList> call, Response<ClubList> response) {
            try {
                if (response.body().getInfo().size() > 0) {
                    DBTransactionFunctions.DB_ClearTable("tb_club");
                    try {
                        for (int i = 0; i < response.body().getInfo().size(); i++) {
                            ContentValues cv = new ContentValues();
                            cv.put("id", response.body().getInfo().get(i).getId());
                            cv.put("club_name", response.body().getInfo().get(i).getName());
                            cv.put("category", response.body().getInfo().get(i).getCategory());
                            cv.put("mobile", response.body().getInfo().get(i).getMobile());
                            cv.put("email", response.body().getInfo().get(i).getEmail());
                            cv.put("website", response.body().getInfo().get(i).getWebsite());
                            cv.put("street", response.body().getInfo().get(i).getStreet());
                            cv.put("city", response.body().getInfo().get(i).getCity());
                            cv.put("district", response.body().getInfo().get(i).getDistrict());
                            cv.put("state", response.body().getInfo().get(i).getState());
                            cv.put("country", response.body().getInfo().get(i).getCountry());
                            cv.put("password", response.body().getInfo().get(i).getPassword());
                            cv.put("updatedtime", System.currentTimeMillis());
                            cv.put("p_image",response.body().getInfo().get(i).getP_image() );
                            DBTransactionFunctions.DB_InsertRow("tb_club", cv);
                        }
                    } catch (Exception e) {

                    }
                    SetUsrData(context);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Call<ClubList> call, Throwable t) {


        }
    });
}catch (Exception e){
    e.printStackTrace();
}

    }


    //to insert service data

    public static boolean InsertShopService(HashMap<String, String> hashMap, final Context context) {
        flag = false;
        try {

            RetroInterface retro = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);
            retro.serviceclub(hashMap).enqueue(new Callback<ServiceClub>() {
                @Override
                public void onResponse(Call<ServiceClub> call, Response<ServiceClub> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(context, "Success", Toast.LENGTH_LONG).show();
                        flag = true;
                    }


                    SetServiveTable();
                }


                @Override
                public void onFailure(Call<ServiceClub> call, Throwable t) {
                    flag = false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

//to  insert service data to local

    public static void SetServiveTable() {
try {
    HashMap<String, String> hashmap = new HashMap<>();
    hashmap.put("table", "shop_service");

    RetroInterface retr = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);

    retr.shopservice(hashmap).enqueue(new Callback<ServiceFullList>() {
        @Override
        public void onResponse(Call<ServiceFullList> call, Response<ServiceFullList> response) {
            if (response.body().getInfo().size() > 0) {
                DBTransactionFunctions.DB_ClearTable("tb_shop_service");
                try {
                    for (int i = 0; i < response.body().getInfo().size(); i++) {
                        ContentValues cv = new ContentValues();
                        cv.put("id", response.body().getInfo().get(i).getId());
                        cv.put("shopid", response.body().getInfo().get(i).getShopId());
                        cv.put("service_name", response.body().getInfo().get(i).getServiceName());
                        cv.put("gender", " ");
                        cv.put("rate", response.body().getInfo().get(i).getRate());
                        cv.put("updatedtime", System.currentTimeMillis());
                        DBTransactionFunctions.DB_InsertRow("tb_shop_service", cv);
                    }
                } catch (Exception e) {

                }

            }
            setAppoinment();
        }

        @Override
        public void onFailure(Call<ServiceFullList> call, Throwable t) {

        }
    });
}catch (Exception e){
    e.printStackTrace();
}
    }

    //to insert employee data

    public static boolean InsertEmployee(HashMap<String, String> hashMap, final Context context) {
        flag = false;
        RetroInterface retro = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);


        retro.serviceemployee(hashMap).enqueue(new Callback<ServiceEmployee>() {
            @Override
            public void onResponse(Call<ServiceEmployee> call, Response<ServiceEmployee> response) {
                if (response.isSuccessful()) {

                    Toast.makeText(context, "Employee Added", Toast.LENGTH_LONG).show();
                    flag = true;


                }
                setEmployyee();
            }

            @Override
            public void onFailure(Call<ServiceEmployee> call, Throwable t) {
                flag = false;
            }
        });
        return flag;

    }

    //to set employee data to the local

    public static void setEmployyee() {
try {
    HashMap<String, String> hashmap = new HashMap<>();
    hashmap.put("table", "employee");

    RetroInterface retr = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);
    retr.employee(hashmap).enqueue(new Callback<EmployeeFullList>() {
        @Override
        public void onResponse(Call<EmployeeFullList> call, Response<EmployeeFullList> response) {
            if (response.body().getInfo().size() > 0) {
                DBTransactionFunctions.DB_ClearTable("tb_employee");
                try {
                    for (int i = 0; i < response.body().getInfo().size(); i++) {
                        ContentValues cv = new ContentValues();
                        cv.put("id", response.body().getInfo().get(i).getId());
                        cv.put("shopid", response.body().getInfo().get(i).getShopId());
                        cv.put("serviceid", response.body().getInfo().get(i).getServiceId());
                        cv.put("emp_name", response.body().getInfo().get(i).getEmpName());
                        cv.put("phonenumber", response.body().getInfo().get(i).getMobile());
                        cv.put("updatedtime", System.currentTimeMillis());
                        DBTransactionFunctions.DB_InsertRow("tb_employee", cv);

                    }
                } catch (Exception e) {

                }
            }
        }

        @Override
        public void onFailure(Call<EmployeeFullList> call, Throwable t) {

        }
    });
}catch (Exception  e){
    e.printStackTrace();
}

    }

    //to insert schedule data to the server

    public static boolean InsertShedule(HashMap<String, String> hashMap, Context context) {
        flag = false;

        RetroInterface retroInterface = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);

        retroInterface.updateshedule(hashMap).enqueue(new Callback<UpdateShedule>() {
            @Override
            public void onResponse(Call<UpdateShedule> call, Response<UpdateShedule> response) {
                if (response.isSuccessful()) {
                    flag = true;


                }

            }

            @Override
            public void onFailure(Call<UpdateShedule> call, Throwable t) {
                flag = false;

            }
        });
        return flag;

    }

    //to get all  schedule data
    public static void SheduleData() {
       try {
           HashMap<String, String> hashmap = new HashMap<>();
           hashmap.put("table", "schedule");
           RetroInterface retr = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);
           retr.shedule(hashmap).enqueue(new Callback<SheduleFullList>() {
               @Override
               public void onResponse(Call<SheduleFullList> call, Response<SheduleFullList> response) {
                   if (response.body().getInfo().size() > 0) {
                       DBTransactionFunctions.DB_ClearTable("tb_schedule");
                       try {
                           for (int i = 0; i < response.body().getInfo().size(); i++) {
                               ContentValues cv = new ContentValues();
                               cv.put("id", response.body().getInfo().get(i).getId());
                               cv.put("shopid", response.body().getInfo().get(i).getShopId());
                               cv.put("week_day", response.body().getInfo().get(i).getDay().trim());
                               cv.put("openingtime", response.body().getInfo().get(i).getOpenTime().trim());
                               cv.put("closingtime", response.body().getInfo().get(i).getCloseTime().trim());
                               cv.put("updatedtime", System.currentTimeMillis());
                               DBTransactionFunctions.DB_InsertRow("tb_schedule", cv);
                           }
                       } catch (Exception e) {

                       }
                   }
               }

               @Override
               public void onFailure(Call<SheduleFullList> call, Throwable t) {

               }
           });
       }catch (Exception e){
           e.printStackTrace();
       }
    }
// to edit schedule data

    public static boolean EditShedule(HashMap<String, String> hashMap, Context context) {


        RetroInterface retroInterface = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);
        retroInterface.serviceshedule(hashMap).enqueue(new Callback<ServiceShedule>() {
            @Override
            public void onResponse(Call<ServiceShedule> call, Response<ServiceShedule> response) {
                if(response.isSuccessful())
                flag = true;
            }

            @Override
            public void onFailure(Call<ServiceShedule> call, Throwable t) {
                flag = false;
            }
        });
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        return flag;
    }

    public static boolean Updateclubb(HashMap<String, String> hashMap, final Context context) {
        RetroInterface retro = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);
        retro.updateclub(hashMap).enqueue(new Callback<UpdateClub>() {
            @Override
            public void onResponse(Call<UpdateClub> call, Response<UpdateClub> response) {
                if (response.isSuccessful()) {
                    flag = true;
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                    SetData();
                }
            }

            @Override
            public void onFailure(Call<UpdateClub> call, Throwable t) {

            }
        });


        return flag;

    }
// to  get club data
    public static void SetData() {

try {
    HashMap<String, String> hashMap = new HashMap<>();
    hashMap.put("table", "club");

    RetroInterface retro = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);
    retro.clublist(hashMap).enqueue(new Callback<ClubList>() {
        @Override
        public void onResponse(Call<ClubList> call, Response<ClubList> response) {
            if (response.body().getInfo().size() > 0) {
                DBTransactionFunctions.DB_ClearTable("tb_club");
                try {
                    for (int i = 0; i < response.body().getInfo().size(); i++) {
                        ContentValues cv = new ContentValues();
                        cv.put("id", response.body().getInfo().get(i).getId());
                        cv.put("club_name", response.body().getInfo().get(i).getName());
                        cv.put( "category",response.body().getInfo().get( i ).getCategory() );
                        cv.put("mobile", response.body().getInfo().get(i).getMobile());
                        cv.put("email", response.body().getInfo().get(i).getEmail());
                        cv.put("website", response.body().getInfo().get(i).getWebsite());
                        cv.put("street", response.body().getInfo().get(i).getStreet());
                        cv.put("city", response.body().getInfo().get(i).getCity());
                        cv.put("district", response.body().getInfo().get(i).getDistrict());
                        cv.put("state", response.body().getInfo().get(i).getState());
                        cv.put("country", response.body().getInfo().get(i).getCountry());
                        cv.put("password", response.body().getInfo().get(i).getPassword());
                        cv.put("updatedtime", System.currentTimeMillis());
                       cv.put("p_image",response.body().getInfo().get(i).getP_image());
                        DBTransactionFunctions.DB_InsertRow("tb_club", cv);
                    }
                } catch (Exception e) {

                }

            }
        }

        @Override
        public void onFailure(Call<ClubList> call, Throwable t) {


        }
    });
}catch (Exception e){
    e.printStackTrace();
}
    }

    //to add employee

    public static boolean AdEmployee(HashMap<String, String> hashMap, final Context context) {
        flag = false;
        RetroInterface retro = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);

        retro.updateemp(hashMap).enqueue(new Callback<UpdateEmployee>() {
            @Override
            public void onResponse(Call<UpdateEmployee> call, Response<UpdateEmployee> response) {
                Toast.makeText(context,"Employee Added",Toast.LENGTH_LONG).show();
                flag = true;
                EditEmployee.refresh(context);
                setData(context);
            }

            @Override
            public void onFailure(Call<UpdateEmployee> call, Throwable t) {
                flag = false;

            }
        });
        return flag;
    }

    //to get employee
    public static void setData(final Context context) {

try {
    HashMap<String, String> hashmap = new HashMap<>();
    hashmap.put("table", "employee");

    RetroInterface retr = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);
    retr.employee(hashmap).enqueue(new Callback<EmployeeFullList>() {
        @Override
        public void onResponse(Call<EmployeeFullList> call, Response<EmployeeFullList> response) {
            if (response.body().getInfo().size() > 0) {
                DBTransactionFunctions.DB_ClearTable("tb_employee");
                try {
                    for (int i = 0; i < response.body().getInfo().size(); i++) {
                        ContentValues cv = new ContentValues();
                        cv.put("id", response.body().getInfo().get(i).getId());
                        cv.put("shopid", response.body().getInfo().get(i).getShopId());
                        cv.put("serviceid", response.body().getInfo().get(i).getServiceId());
                        cv.put("emp_name", response.body().getInfo().get(i).getEmpName());
                        cv.put("phonenumber", response.body().getInfo().get(i).getMobile());
                        cv.put("updatedtime", System.currentTimeMillis());
                        DBTransactionFunctions.DB_InsertRow("tb_employee", cv);

                    }
                } catch (Exception e) {
                }
                EditEmployee.refresh(context);
                setShedule();
            }
        }

        @Override
        public void onFailure(Call<EmployeeFullList> call, Throwable t) {

        }
    });
}catch (Exception e){
    e.printStackTrace();
}

    }

    //to add service

    public static boolean AdService(HashMap<String, String> hashMap, final Context context) {
flag=false;
        RetroInterface retro = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);

        retro.updateservice(hashMap).enqueue(new Callback<UpdateService>() {
            @Override
            public void onResponse(Call<UpdateService> call, Response<UpdateService> response) {
                if (response.isSuccessful()){
                    Toast.makeText(context,"Service Updated",Toast.LENGTH_LONG).show();
                    SetServiveTable();
                    EditService.refresh(context);
                    flag=true;
                }

            }

            @Override
            public void onFailure(Call<UpdateService> call, Throwable t) {

            }
        });
       return flag;
    }

    //to set user data
    public static void SetUsrData(final Context context) {
try {
    HashMap<String, String> hashmap = new HashMap<>();
    hashmap.put("table", "user");
    RetroInterface retr = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);

    retr.userlist(hashmap).enqueue(new Callback<UserList>() {
        @Override
        public void onResponse(Call<UserList> call, Response<UserList> response) {
            if (response.body().getInfo().size() > 0) {

                DBTransactionFunctions.DB_ClearTable("tb_user");
                try {
                    for (int i = 0; i < response.body().getInfo().size(); i++) {
                        ContentValues cv = new ContentValues();
                        cv.put("id", response.body().getInfo().get(i).getId());
                        cv.put("name", response.body().getInfo().get(i).getName());
                        cv.put("mobile", response.body().getInfo().get(i).getMobile());
                        cv.put("gender", response.body().getInfo().get(i).getGender());
                        cv.put("age", response.body().getInfo().get(i).getAge());
                        cv.put("address", response.body().getInfo().get(i).getAddress());
                        cv.put("email", response.body().getInfo().get(i).getEmail());
                        cv.put("password", response.body().getInfo().get(i).getPassword());
                        cv.put("updatedtime", System.currentTimeMillis());
                        cv.put("p_image", response.body().getInfo().get(i).getP_image());
                        DBTransactionFunctions.DB_InsertRow("tb_user", cv);
                    }
                } catch (Exception e) {

                }
                setData(context);
            }

        }

        @Override
        public void onFailure(Call<UserList> call, Throwable t) {

        }
    });

}catch (Exception e){
    e.printStackTrace();
}

    }

    //to set schedule

    public static void setShedule() {
try {
    HashMap<String, String> hashmap = new HashMap<>();
    hashmap.put("table", "schedule");
    RetroInterface retr = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);
    retr.shedule(hashmap).enqueue(new Callback<SheduleFullList>() {
        @Override
        public void onResponse(Call<SheduleFullList> call, Response<SheduleFullList> response) {
            if (response.body().getInfo().size() > 0) {
                DBTransactionFunctions.DB_ClearTable("tb_schedule");
                try {
                    for (int i = 0; i < response.body().getInfo().size(); i++) {

                        ContentValues cv = new ContentValues();
                        cv.put("id", response.body().getInfo().get(i).getId());
                        cv.put("shopid", response.body().getInfo().get(i).getShopId());
                        cv.put("week_day", response.body().getInfo().get(i).getDay().trim());
                        cv.put("openingtime", response.body().getInfo().get(i).getOpenTime().trim());
                        cv.put("closingtime", response.body().getInfo().get(i).getCloseTime().trim());
                        cv.put("updatedtime", System.currentTimeMillis());
                        DBTransactionFunctions.DB_InsertRow("tb_schedule", cv);
                    }
                } catch (Exception e) {

                }

                SetServiveTable();
            }
        }

        @Override
        public void onFailure(Call<SheduleFullList> call, Throwable t) {

        }
    });
}catch (Exception e){
    e.printStackTrace();
}

    }

    //to set appointments

    public static void setAppoinment(){
try {
    HashMap<String, String> hashmap = new HashMap<>();
    hashmap.put("table", "appointment");
    RetroInterface retr = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);
    retr.set_apmnt(hashmap).enqueue(new Callback<AppoinmentList>() {
        @Override
        public void onResponse(Call<AppoinmentList> call, Response<AppoinmentList> response) {
            if (response.body().getInfo().size() > 0) {
                DBTransactionFunctions.DB_ClearTable("tb_appoinments");
                try {
                    for (int i = 0; i < response.body().getInfo().size(); i++) {
                        ContentValues cv = new ContentValues();
                        cv.put("id", response.body().getInfo().get(i).getId());
                        cv.put("userid", response.body().getInfo().get(i).getUserId());
                        cv.put("shopid", response.body().getInfo().get(i).getShopId());
                        cv.put("service_id", response.body().getInfo().get(i).getServiceId());
                        cv.put("empid", response.body().getInfo().get(i).getEmpId());
                        cv.put("appoinmenttime", response.body().getInfo().get(i).getAppTime());
                        cv.put("updatedtime", response.body().getInfo().get(i).getUpdatedTime());
                        cv.put("status", response.body().getInfo().get(i).getStatus());
                        DBTransactionFunctions.DB_InsertRow("tb_appoinments", cv);

                    }
                    sentFeedback();
                } catch (Exception e) {

                }
            }
        }

        @Override
        public void onFailure(Call<AppoinmentList> call, Throwable t) {

        }
    });
}catch (Exception e){
    e.printStackTrace();
}
    }

    //to add feedback

    public static void sentFeedback() {
        try {
            HashMap<String, String> hashmap = new HashMap<>();
            hashmap.put("table", "feedback");
            RetroInterface retr = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);
            retr.set_listfeed(hashmap).enqueue(new Callback<ServiceFeedbackList>() {
                @Override
                public void onResponse(Call<ServiceFeedbackList> call, Response<ServiceFeedbackList> response) {
                    if (response.body().getInfo().size() > 0) {
                        DBTransactionFunctions.DB_ClearTable("tb_feedback");
                        for (int i = 0; i < response.body().getInfo().size(); i++) {
                            ContentValues cv = new ContentValues();
                            cv.put("id", response.body().getInfo().get(i).getId());
                            cv.put("userid", response.body().getInfo().get(i).getUserId());
                            cv.put("shopid", response.body().getInfo().get(i).getShopId());
                            cv.put("serviceid", response.body().getInfo().get(i).getServiceId());
                            cv.put("message", response.body().getInfo().get(i).getMessage());
                            cv.put("status", response.body().getInfo().get(i).getStatus());
                            DBTransactionFunctions.DB_InsertRow("tb_feedback", cv);
                        }
                    }
                }

                @Override
                public void onFailure(Call<ServiceFeedbackList> call, Throwable t) {

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}




