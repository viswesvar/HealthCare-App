/*
* Copyright 2015 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.assignment.diabetesrecords.common.permission_management_android6;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.assignment.diabetesrecords.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class that wraps access to the runtime permissions API in M and provides basic helper
 * methods.
 */
public abstract class PermissionUtil {
    private static final int REQUEST_CODE_PERMISSIONS_NEEDED_ALL=111;

    /**
     * Check that all given permissions have been granted by verifying that each entry in the
     * given array is of the value {@link PackageManager#PERMISSION_GRANTED}.
     *
     * @see Activity#onRequestPermissionsResult(int, String[], int[])
     */
    public static boolean verifyPermissions(int[] grantResults)
    {
        // At least one result must be checked.
        if(grantResults.length < 1){
            return false;
        }

        // Verify that each required permission has been granted, otherwise return false.
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }


    public static boolean AllPermissionGranted(List<String> ALL_APP_PERMISSIONS_IN, Context ctx)
    {
        List<String> ALL_APP_PERMISSIONS= new ArrayList<String>();
        List<String> PERMISSIONS_NEEDED_ALL= new ArrayList<String>();

        ALL_APP_PERMISSIONS = ALL_APP_PERMISSIONS_IN;
        boolean result=true;


        PERMISSIONS_NEEDED_ALL.clear();

        for(int i=0; i<ALL_APP_PERMISSIONS.size(); i++)
        {

            if (ActivityCompat.checkSelfPermission(ctx, ALL_APP_PERMISSIONS.get(i))
                    != PackageManager.PERMISSION_GRANTED)
            {
                PERMISSIONS_NEEDED_ALL.add(ALL_APP_PERMISSIONS.get(i));


            }
        }

        if (PERMISSIONS_NEEDED_ALL.size() >0)
        {
            result=false;
        }

        return  result;
    }




    public static List<String>  checkPermissionsNeeded(List<String> ALL_APP_PERMISSIONS_IN, Context ctx, AppCompatActivity my_activity)
    {
        List<String> ALL_APP_PERMISSIONS= new ArrayList<String>();
        List<String> PERMISSIONS_NEEDED_ALL= new ArrayList<String>();
        List<String> SHOW_RATIONALE_FOR_PERMISSIONS_ALL= new ArrayList<String>();

        ALL_APP_PERMISSIONS = ALL_APP_PERMISSIONS_IN;
        PERMISSIONS_NEEDED_ALL.clear();

        for(int i=0; i<ALL_APP_PERMISSIONS.size(); i++)
        {

            if (ActivityCompat.checkSelfPermission(ctx, ALL_APP_PERMISSIONS.get(i))
                    != PackageManager.PERMISSION_GRANTED)
            {
                PERMISSIONS_NEEDED_ALL.add(ALL_APP_PERMISSIONS.get(i));


            }
        }
        return PERMISSIONS_NEEDED_ALL;

    }


    public static List<String>  checkPermissionsToShowRationale(List<String> ALL_APP_PERMISSIONS_IN, Context ctx, AppCompatActivity my_activity)
    {
        List<String> ALL_APP_PERMISSIONS= new ArrayList<String>();
        List<String> PERMISSIONS_NEEDED_ALL= new ArrayList<String>();
        List<String> SHOW_RATIONALE_FOR_PERMISSIONS_ALL= new ArrayList<String>();

        ALL_APP_PERMISSIONS = ALL_APP_PERMISSIONS_IN;
        PERMISSIONS_NEEDED_ALL.clear();

        for(int i=0; i<ALL_APP_PERMISSIONS.size(); i++)
        {

            if (ActivityCompat.checkSelfPermission(ctx, ALL_APP_PERMISSIONS.get(i))
                    != PackageManager.PERMISSION_GRANTED)
            {
                PERMISSIONS_NEEDED_ALL.add(ALL_APP_PERMISSIONS.get(i));


            }
        }


        SHOW_RATIONALE_FOR_PERMISSIONS_ALL.clear();

        if (PERMISSIONS_NEEDED_ALL.size() >0)
        {
            for(int i=0; i<PERMISSIONS_NEEDED_ALL.size(); i++)
            {
                if (ActivityCompat.shouldShowRequestPermissionRationale(my_activity,PERMISSIONS_NEEDED_ALL.get(i)))
                {
                    SHOW_RATIONALE_FOR_PERMISSIONS_ALL.add(PERMISSIONS_NEEDED_ALL.get(i));

                }
            }

        }

        return SHOW_RATIONALE_FOR_PERMISSIONS_ALL;

    }




    public static void checkAndRequestPermissions(List<String> ALL_APP_PERMISSIONS_IN, final Context ctx, final AppCompatActivity my_activity, View mLayout)
    {

        List<String> ALL_APP_PERMISSIONS= new ArrayList<String>();
        //List<String> PERMISSIONS_NEEDED_ALL= new ArrayList<String>();
        List<String> SHOW_RATIONALE_FOR_PERMISSIONS_ALL= new ArrayList<String>();
        ALL_APP_PERMISSIONS = ALL_APP_PERMISSIONS_IN;

        final List<String> PERMISSIONS_NEEDED_ALL= PermissionUtil.checkPermissionsNeeded(ALL_APP_PERMISSIONS, ctx, my_activity);
        SHOW_RATIONALE_FOR_PERMISSIONS_ALL = PermissionUtil.checkPermissionsToShowRationale(ALL_APP_PERMISSIONS, ctx, my_activity);

        //================================

        if(SHOW_RATIONALE_FOR_PERMISSIONS_ALL.size()>0)
        {
            Snackbar.make(mLayout, R.string.permission_message_rationale_all,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ActivityCompat.requestPermissions(my_activity,
                                    PERMISSIONS_NEEDED_ALL.toArray(new String[PERMISSIONS_NEEDED_ALL.size()-1]),
                                    REQUEST_CODE_PERMISSIONS_NEEDED_ALL);
                        }
                    })
                    .show();
        }
        else
        {
            ActivityCompat.requestPermissions(my_activity,
                    PERMISSIONS_NEEDED_ALL.toArray(new String[PERMISSIONS_NEEDED_ALL.size()-1]),
                    REQUEST_CODE_PERMISSIONS_NEEDED_ALL);
        }
        //======================================

    }





}  //End of class
