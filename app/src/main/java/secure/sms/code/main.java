
/**
 * 	Project : Peer to Peer Secure Communication in Mobile Envoirnment 
 * 	
 * 	@Author: Naman Pahwa
 * 
 * 	File Name : main.java
 * 
 */

package secure.sms.code;

import java.io.FileInputStream;
import java.io.InputStreamReader;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

public class main extends Activity {
	
	/**	Store Password after reading from the file	*/
	public String data;
	/**	Text View displaying username	*/
	TextView user;
	/**	Text View displaying password	*/
	TextView passwd;
	/**	EditText for entering username	*/
	EditText user_name ;
	/**	EditText for entering password	*/
	EditText password;
	/**	Button for checking username and password and login	*/
	Button Ok;
	/**	Button for changing password	*/
	Button ChangePassword;
	/**	variable to store length of password	*/
	public static int passwd_length = 5;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		if(ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS ) != PackageManager.PERMISSION_GRANTED){
			ActivityCompat.requestPermissions(this, new String[]{
					Manifest.permission.RECEIVE_SMS,
					Manifest.permission.SEND_SMS,
			Manifest.permission.READ_SMS,
			Manifest.permission.ACCESS_COARSE_LOCATION}, 111);
			System.out.println("Granted the relevant permissions");
		}

		/**	Creating Linear Layout for the current activity	*/
        LinearLayout layout = new LinearLayout(this);
        /**	Setting orientation of activity	*/
        layout.setOrientation(LinearLayout.VERTICAL); 
        
        /**	adding TextView user to activity	*/
        user = new TextView(this);
        user.setText("Username");
        layout.addView(user , new LinearLayout.LayoutParams( 
        		ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0));
        
        /**	adding EditText user_name to activity	*/
        user_name = new EditText(this);
        user_name.setText("");
        layout.addView(user_name , new LinearLayout.LayoutParams( 
        		ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0));
        

        /**	adding EditText passwd to activity */
        passwd = new TextView(this);
        passwd.setText("password");
        layout.addView(passwd , new LinearLayout.LayoutParams( 
        		ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0));
        
        /** adding EditText password to activity */
        password = new EditText(this);
        password.setTransformationMethod(new android.text.method.PasswordTransformationMethod()); 
        password.setText("");
        layout.addView(password, new LinearLayout.LayoutParams( 
        		ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0));
        
        /** Adding Button Ok to Activity */
        Ok = new Button(this);
        Ok.setText("LOG IN");
      
        /** Defining ClickEvent of  Button Ok */
        Ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	boolean fg  = true;
            	FileInputStream fIn = null;	/** InputStream for reading file */
         	   InputStreamReader isr = null;/** InputStreamReader for reading file */

         	   /**
         	    * 	Reading File for password
         	    */
         	   try{
         		   //System.out.println("File writing done");
         	   char[] inputBuffer = new char[passwd_length];

         	   data = null;
         	   System.out.println("File reading ");
         	   fIn = openFileInput("public.dat");	/** Opening File */
         	   System.out.println("File present");
         	   isr = new InputStreamReader(fIn);

         	   isr.read(inputBuffer);
         	   System.out.println("File reading done");

         	   data = new String(inputBuffer); /** Reading file */
         	   System.out.println("Data in the file :: \n"+ data);
         	   isr.close();

         	   fIn.close();
         	   
         	   /**
         	    * 	File Read
         	    */
         	   //Toast.makeText(HelloAndroid.this, "File Read " + data, Toast.LENGTH_SHORT).show();
         	   }
         	   catch(Exception e){
         		   
         		   fg = false;
         		   e.printStackTrace(System.err);
         		   System.out.println("tehere");
         	   }
         	   if(!fg)
         	   {
         		   /**
         		    * 	Default passsowrd
         		    */
            	//System.out.println("check "+user_name.getText().toString());
                if ( user_name.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                	System.out.println("User Confirmed");
                	Toast.makeText(getBaseContext(), "Login Successfully" , Toast.LENGTH_SHORT).show();
                	
                	user_name.setText("");
                	password.setText("");
                
      
                	
                	/**
                	 * Call send_recieve Activity
                	 */
                	
                	Intent i = new Intent();
					i.setClass(getBaseContext(), send_sms.class);
                	startActivity(i);
                	
                	/**
                	 *  Send_Recive Activity started
                	 */
                	
                	
                }else{
                	System.out.println(" Security attack or Login unsucessfull ");
                	Toast.makeText(getBaseContext(), "Login Unsuccessfull" , Toast.LENGTH_SHORT).show();
                }
         	   }
         	   else
         	   {
         		   /**
         		    * 	Checking for password
         		    */
         		  if ( user_name.getText().toString().equals("admin") && password.getText().toString().equals(data)){
                  	System.out.println("User Confirmed");
                  	Toast.makeText(getBaseContext(), "Login Successfully" , Toast.LENGTH_SHORT).show();
                  	
                  	user_name.setText("");
                  	password.setText("");
                  
        
                  	
                  	/**
                  	 * Call send_recieve Activity
                  	 */
                  	
                  	Intent i = new Intent();
                  	i.setClassName("secure.sms.code", "send_sms");
                  	startActivity(i);
                  	
                  	/**
                  	 *  Send_Recive Activity started
                  	 */
                  	
                  	
                  }else{
                  	System.out.println(" Security attack or Login unsucessfull ");
                  	Toast.makeText(getBaseContext(), "Login Unsuccessfull" , Toast.LENGTH_SHORT).show();
                  }
        	
         	   }
            }
        });

        
        layout.addView(Ok, new LinearLayout.LayoutParams( 
        		ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0));
        
        /** Adding Button ChangePassword in activity */
        ChangePassword = new Button(this);
        ChangePassword.setText("Change Password");
       
        /** Setting ClickEvent of Button ChangePassword in activity */
        ChangePassword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	boolean fg  = true;
            	FileInputStream fIn = null;	/** InputStream to Read file */
         	    InputStreamReader isr = null;	/** InputStreamReader to Read file */

         	    
         	   try{
         		  // System.out.println("File writing done");
         	   char[] inputBuffer = new char[passwd_length];

         	   data = null;
         	   System.out.println("File reading ");
         	   fIn = openFileInput("public.dat");
         	   System.out.println("File present");
         	   isr = new InputStreamReader(fIn);

         	   isr.read(inputBuffer);
         	   System.out.println("File reading done");

         	   data = new String(inputBuffer);
         	   System.out.println("Data in the file :: \n"+ data);
         	   isr.close();

         	   fIn.close();
         	   //Toast.makeText(HelloAndroid.this, "File Read " + data, Toast.LENGTH_SHORT).show();
         	   }
         	   catch(Exception e){
         	   
         		   fg = false;
         		   e.printStackTrace(System.err);
         		   System.out.println("tehere");
         	   }
         	   if(!fg)
         	   {
            	//System.out.println("check "+user_name.getText().toString());
                if ( user_name.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                	System.out.println("User Confirmed");
                	Toast.makeText(getBaseContext(), "" + "clicked Successfully" , Toast.LENGTH_SHORT).show();
                	
                	user_name.setText("");
                	password.setText("");
                
                	/**
                	 * 		Going to call Activity to change password
                	 */
                	Intent i1 = new Intent();
                	i1.setClassName("secure.sms.code", "passwd_change");
                	startActivity(i1);
                }
                else
                {
                	System.out.println(" Security attack or Login unsucessfull ");
                  	Toast.makeText(getBaseContext(), "Please Enter Correct Username & Password" , Toast.LENGTH_SHORT).show();
                }
                
              	
         	   }
         	   else
         	   {
         		  if ( user_name.getText().toString().equals("admin") && password.getText().toString().equals(data)){
                  	System.out.println("User Confirmed");
                  	Toast.makeText(getBaseContext(), "" + "clicked Successfully" , Toast.LENGTH_SHORT).show();
                  	
                  	user_name.setText("");
                  	password.setText("");
                  
                  	Intent i1 = new Intent();
                  	i1.setClassName("secure.sms.code", "passwd_change");
                  	startActivity(i1);
                  }
                  else
                  {
                  	System.out.println(" Security attack or Login unsucessfull ");
                    	Toast.makeText(getBaseContext(), "Please Enter Correct Username & Password" , Toast.LENGTH_SHORT).show();
                  }
         	   }
            }
        });

        
        layout.addView(ChangePassword, new LinearLayout.LayoutParams( 
        		ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0));
        

        setContentView(layout);
        
    }
}