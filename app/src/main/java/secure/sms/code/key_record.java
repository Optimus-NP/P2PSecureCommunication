/**Project : Peer to Peer Secure Communication in Mobile Envoirnment 
 * 	
 * 	@Author: Naman Pahwa
 * 
 * 	File Name : key_record.java
 * 
 */


package secure.sms.code;

import java.math.BigInteger;

/**
 * 	class for storing a phone number key pair
 * 
 */
class key_record {
	/** phone number */
	public String ph_no;
	/** Key */
	public BigInteger key;
}

/**
 * 	class to store message key id and tag from database in a tupple
 * 
 */
class message_record {
	/** message */
	public String message;
	/** key */
	public String key;
	/** id of tuppel i.e. primary key */
	public int id;
	/** tag */
	public int voice_tag;
}