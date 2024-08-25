/**Project : Peer to Peer Secure Communication in Mobile Envoirnment 
 * 	
 * 	@Author: Naman Pahwa
 * 
 * 	File Name : display_sender.java
 * 
 */

package secure.sms.code;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


/**
 * 	Class to display list of all senders to application in database
 * 
 */

public class display_sender extends ListActivity {
	
	/** Sender phone  number to pass on*/
	public static String sender;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  
		System.out.println("Entered display senders ");
		
		super.onCreate(savedInstanceState);
	  
	  /** Create an array of Strings, that will be put to our ListActivity 
	   * 	and getting it from database
	   */
		List<String> list_senders = send_sms.DB.selectAll_sender();
		
		
		/**
		 * displaying senders in a list in activity
		 */
		
		String[] senders = new String[list_senders.size()];
		
		for ( int i = 0 ; i < list_senders.size() ; i++){
			senders[i] = list_senders.get(i);
		}
		
		System.out.println("Got the list of senders");
		
		// Create an ArrayAdapter, that will actually make the Strings above
		// appear in the ListView
		this.setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, senders));
		
		System.out.println("Displayed List");
	}

	/**
	 * 	Click Event of a item in list
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		// Get the item that was clicked
		Object o = this.getListAdapter().getItem(position);
		String keyword = o.toString();
		
		System.out.println("Sender Selected " + keyword);
	
		
		/**
		 * storing selected sender
		 */
		this.sender = keyword;
		
		Toast.makeText(this, "You selected: " + keyword, Toast.LENGTH_LONG)
				.show();
		
		/**
    	 * Call sender message  Activity
    	 */
		
    		Intent i = new Intent();
    		i.setClassName("secure.sms.code", "secure.sms.code.sender_message");
    		startActivity(i);
    	
    	/**
    	 *  sender message activity started
    	 */
    	
		
	}

}
