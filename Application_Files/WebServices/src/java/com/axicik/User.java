/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.axicik;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Βοηθιτική κλάση για την αποθήκευση των βασικών στοιχίων του χρηστη.
 *
 * @author Ioulios Tsiko: cs131027
 * @author Theofilos Axiotis: cs131011
 */
public class User {
    
    private boolean usrExist;
    private int role=0;
    private String name;
    private String last_name;
    private String username;
    public ArrayList srvList=new ArrayList();

    public User(boolean x) {
        usrExist=x;
    }

    public User() {
    }
    
    
    public boolean hasService(int s)
    {
        for (int i=0;i<srvList.size();i++)
            if((int)srvList.get(i)==s)
                return true;
        return false;
    }
    
    
    
    public void setUsrExist(boolean usrExist) {
        this.usrExist = usrExist;
    }

    public boolean getUsrExist() {
        return usrExist;
    }
   
     public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
    
       
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getRole() {
        return role;
    }
    
    
    
    
}
