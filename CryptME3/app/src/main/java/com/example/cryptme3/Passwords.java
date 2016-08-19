package com.example.cryptme3;

/**
 * Created by breck on 7/7/2016.
 */
public class Passwords {
    private long id;
    private String name;
    private String password;
    //empty constructor

    public Passwords(){

    }

    public Passwords(long id, String name, String password){
        this.id=id;
        this.name = name;
        this.password=password;
    }

    public long getID(){
        return id;
    }
    public void setId(long id){

        this.id = id;
    }
    public String getPassword(){
            return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    //Will be used used to display the String in the
    // ArrayAdapter in the LastView
    @Override
    public String toString(){
        return id + " " + name + " " + password;
    }
}
