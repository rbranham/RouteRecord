package com.branham.roger.routerecord.models;

/*
    Should user be singleton??
 */
public class LocalUser {


    private String name, uID;
    private int tripsCompleted; // just a test stat to track

   public LocalUser(String name, String uID){
       this.name = name;
       this.uID = uID;
   }


}
