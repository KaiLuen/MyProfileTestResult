==INTRO

This app is a service for the other app can get user's data easily, and  we catch easily form this app.

There are three parts of an content object, "Key" , "User Define Name" and "Content".

App needs to require "Key" to get "Content" and user can using "User Define Name" to select the content which they want to give required app.


==HOW CAN WE GET DATA FORM THIS APP

= Send Request = 

4 steps to query data:

1.  Intent myProfileIntent = new Intent("cityforfun.myprofile.query");

2. Using "ArrayList<String>" to put the key which you want ask, 
    for example: 
     ArrayList<String> queryList = new ArrayList<>();
     queryList.add("Name");
     queryList.add("Address");
     queryList.add("CreditCard");

3. Put list to intent:
            myProfileIntent.putExtra("DataQuery", queryList);
    
     Please note that key is "DataQuery"

4. Start activity for result:
    startActivityForResult(myProfileIntent, 0);



= Get Result = 
   
We return data in HashMap no other struct. Maybe will have, but in future.

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        HashMap<String, String> resultMap = (HashMap<String, String>)data.getSerializableExtra("Result");
        String name = resultMap.get("Name");
    }



==RESULT COLD MEAN

RESULT_SUCCESS = 0;
RESULT_USER_DENIED = 1;
RESULT_WRONG_REQUEST_DATA_TYPE = 2;
RESULT_NO_REQUEST_DATA = 3;
RESULT_USER_NOT_LOGIN = 4;


==DEFAULT KEYS

Name
EngName
Birthday (Example:1999-01-01)
BankCode
BankAccount
CheckCode
Email
Address
EngAddress
CellPhoneNumber
ShippingAddress
DefaultUserName
