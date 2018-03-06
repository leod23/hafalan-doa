package uinbdg.id.doa.Util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import uinbdg.id.doa.Activities.LoginActivity;
import uinbdg.id.doa.Activities.MainActivity;

/**
 * Created by Comp on 2/11/2018.
 */

public class Session {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "name_app";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoged";

    // User ID (make variable public to access from outside)
    public static final String KEY_ID = "id";

    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    public static final String KEY_ACCESS_TOKEN = "access_token";
    public static final String KEY_CREDENTIAL = "credential";



    // Constructor
    public Session(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String userName, String email, String access_token,String credential,String nip,Integer id){

        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_NAME, userName);

        editor.putString(KEY_EMAIL, email);

        editor.putString(KEY_ACCESS_TOKEN, access_token);

        editor.putString(KEY_CREDENTIAL, credential);
        editor.putInt(KEY_ID,id);

        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(isLoggedIn()){
            // user is not logged in redirect him to Login Aktifitas
            Intent i = new Intent(_context, MainActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Aktifitas
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Aktifitas
            _context.startActivity(i);

        }

    }

    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        editor.putBoolean(IS_LOGIN,false);
        // After logout redirect user to Loing Aktifitas
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Aktifitas
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Aktifitas
        _context.startActivity(i);
    }



    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_ID, pref.getString(KEY_ID, null));
        user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));
        user.put(KEY_CREDENTIAL, pref.getString(KEY_CREDENTIAL, null));

        return user;
    }

    public String getFullname() {
        return pref.getString(KEY_NAME, null);
    }

    public void setFullName(String v) {
        editor.putString(KEY_NAME, v);
        editor.commit();
    }

    public String getEmail() {
        return pref.getString(KEY_EMAIL, null);
    }

    public void setEmail(String v) {
        editor.putString(KEY_EMAIL, v);
        editor.commit();
    }
    public void setKeyApiKey(String v) {
        editor.putString(KEY_ACCESS_TOKEN, v);
        editor.commit();
    }

    public String getAccessToken() {
        return pref.getString(KEY_ACCESS_TOKEN, null);
    }

    public String getKeyCredential() {
        return pref.getString(KEY_CREDENTIAL, null);
    }

    public String getUserName() {
        return pref.getString(KEY_NAME, null);
    }

    public int getID() {
        return pref.getInt(KEY_ID, 0);
    }

    public int getIntID() {
        return Integer.parseInt(pref.getString(KEY_ID, "0"));
    }
    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }


    public void setIsLogin(Boolean v) {
        editor.putBoolean(KEY_PASSWORD, v);
        editor.commit();
    }

    /**
     * Clear session details
     * */
    public void setPassword(String v) {
        editor.putString(KEY_PASSWORD, v);
        editor.commit();
    }

    public String getPassword() {
        return pref.getString(KEY_PASSWORD, "");
    }

    public void setKeyCreateAt(String v) {
        editor.putString(KEY_CREDENTIAL, v);
        editor.commit();
    }

}
