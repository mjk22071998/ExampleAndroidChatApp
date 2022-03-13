package com.example.dmr.examplechatapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

public class SessionManager {
    public static final String NAME ="Name";
    public static final String CITY="City";
    public static final String ADDRESS="Address";
    public static final String EMAIL="Email";
    public static final String PASSWORD="Password";
    public static final String PHONE_NUMBER="Contact No.";

    public static void saveUser(@NonNull Context context, @NonNull Map<String,Object> map){
        SharedPreferences sharepref=context.getSharedPreferences("MyFile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharepref.edit();
        editor.putString(NAME,map.get(NAME).toString());
        editor.putString(CITY,map.get(CITY).toString());
        editor.putString(ADDRESS,map.get(ADDRESS).toString());
        editor.putString(EMAIL,map.get(EMAIL).toString());
        editor.putString(PHONE_NUMBER,map.get(PHONE_NUMBER).toString());
        editor.apply();
    }

    @NonNull
    public static Map<String,Object> getUser(@NonNull Context context){
        SharedPreferences sharepref=context.getSharedPreferences("MyFile", Context.MODE_PRIVATE);
        Map<String,Object> map=new HashMap<>();
        map.put(NAME,sharepref.getString(NAME,""));
        map.put(CITY,sharepref.getString(CITY,""));
        map.put(ADDRESS,sharepref.getString(ADDRESS,""));
        map.put(EMAIL,sharepref.getString(EMAIL,""));
        map.put(PHONE_NUMBER,sharepref.getString(PHONE_NUMBER,""));
        return map;
    }

    public static void deleteUser(@NonNull Context context){
        SharedPreferences sharepref=context.getSharedPreferences("MyFile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharepref.edit();
        editor.putString(NAME,"");
        editor.putString(CITY,"");
        editor.putString(ADDRESS,"");
        editor.putString(EMAIL,"");
        editor.putString(PHONE_NUMBER,"");
        editor.apply();
    }
}
