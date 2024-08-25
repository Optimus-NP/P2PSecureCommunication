/**Project : Peer to Peer Secure Communication in Mobile Envoirnment 
 * 	
 * 	@Author: Naman Pahwa
 * 
 * 	File Name : show_message.java
 * 
 */
package secure.sms.code;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class show_message extends Activity
{
	TextView message ;
	Button delete ;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   
        /** 
         * 	Creating layout and adding elements to it ...
         */
        
        LinearLayout layout = new LinearLayout(this);
        
        layout.setOrientation(LinearLayout.VERTICAL);
        
        message = new TextView(this);
 
        message.setText(sender_message.snd_msg);
        
        layout.addView(message, new LinearLayout.LayoutParams( 
        		ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0));
        
        
        delete = new Button(this);
        delete.setText("Delete Message");
        
        /**
         * deleting message from database
         */
        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	send_sms.DB.TABLE_NAME = "Table_" + display_sender.sender;
            	if(send_sms.DB.deleterow(sender_message.msg_id))
            		Toast.makeText(getBaseContext(), 
                    " Message Deleted Successfully ", 
                    Toast.LENGTH_SHORT).show();
            	show_message.this.finish();
       
            	
            	
            }
        });

        
        layout.addView(delete, new LinearLayout.LayoutParams( 
        		ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0));
        
        setContentView(layout);
        
    }
}
