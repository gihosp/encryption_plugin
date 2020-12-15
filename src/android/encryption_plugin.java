package com.gi.encryp_decript;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import android.util.Log;

/**
 * This class echoes a string called from JavaScript.
 */
public class encryption_plugin extends CordovaPlugin {
    
    private String launchSuccessMsg = "App Launched";
    private String launchFailMsg = "Package Not Installed";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("encrypt")) {
            String message = args.getString(0);
            callbackContext.success(AES.encrypt(message));
            return true;
        }else if (action.equals("encrypt_no_url_encode")) {
            String message = args.getString(0);
            callbackContext.success(AES.encrypt_no_url_encode(message));
            return true;
        }else if (action.equals("decrypt")) {
            String message = args.getString(0);
            callbackContext.success(AES.decrypt(message));
            return true;
        }
        else if (action.equals("launchapp")) {
            Log.i("applaunch_plugin_package",message);
            callbackContext.success(this.LaunchApp(message));
            return true;
        }
        return false;
    }
    
        public String LaunchApp(String packageName) {
            
        try {
            // Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(packageName);
            PackageManager manager = cordova.getActivity().getApplicationContext().getPackageManager();
            Intent LaunchIntent = manager.getLaunchIntentForPackage(packageName);
            if (LaunchIntent != null) {
              this.cordova.getActivity().startActivity(LaunchIntent);
                return launchSuccessMsg;
            } else {
                return launchFailMsg;
                // Toast.makeText(MainActivity.this, "There is no package available in android", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Log.i("applaunch_plugin","launchIntent"+e.getMessage());

        }
        return null;
    }

    // private void coolMethod(String message, CallbackContext callbackContext) {
    //     if (message != null && message.length() > 0) {
    //         callbackContext.success(message);
    //     } else {
    //         callbackContext.error("Expected one non-empty string argument.");
    //     }
    // }
}
