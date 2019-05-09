Create table tb_configuration(configName text,configValue text);
Create table tb_club(id integer PRIMARY KEY AUTOINCREMENT,user_type text,club_name text,category text,mobile text,email text,website text,street text,city text,district text,state text,country text,password text,updatedtime text,p_image text);
Create table tb_user(id integer PRIMARY KEY AUTOINCREMENT,name text,mobile text,gender text,age text,address text,email text,password text,updatedtime text,p_image text);
Create table tb_login(id integer PRIMARY KEY AUTOINCREMENT,userid text,logintime text,status text);
Create table tb_schedule(id integer PRIMARY KEY AUTOINCREMENT,shopid text,week_day text,openingtime text,closingtime text,updatedtime text);
Create table tb_employee(id integer PRIMARY KEY AUTOINCREMENT,shopid text,serviceid text,emp_name text,phonenumber text,updatedtime text);
Create table tb_shop_service(id integer PRIMARY KEY AUTOINCREMENT,shopid text,service_name text,gender text,rate text,updatedtime text);
Create table tb_appoinments(id integer PRIMARY KEY AUTOINCREMENT,userid text,shopid text,service_id text,empid text,appoinmenttime text,updatedtime text,status text);
Create table tb_feedback(id integer PRIMARY KEY AUTOINCREMENT,userid text,shopid text,serviceid text,message text,status text);
