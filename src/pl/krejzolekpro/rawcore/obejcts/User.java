package pl.krejzolekpro.rawcore.obejcts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User {

    private static List<User> userList = new ArrayList<User>();

    private String name;
    private Boolean vanish;
    private Boolean god;
    private Long turboTime;

    public User(String name){
        this.name = name;
        this.vanish = false;
        this.turboTime = 0L;
        this.god = false;
        add(this);
    }

    public String getName() {
        return name;
    }

    public Boolean getVanish() {
        return vanish;
    }

    public void setVanish(Boolean vanish) {
        this.vanish = vanish;
    }

    public Boolean getGod() {
        return god;
    }

    public void setGod(Boolean god) {
        this.god = god;
    }

    public Long getTurboTime() {
        return turboTime;
    }

    public void setTurboTime(Long turboTime) {
        this.turboTime = turboTime;
    }

    public Boolean hasTurbo(){
        if(this.turboTime >= System.currentTimeMillis()){
            return true;
        }
        return false;
    }

    public Boolean hasVanish(){
        if(this.vanish){
            return true;
        }
        return false;
    }

    public Boolean hasGod(){
        if(this.god){
            return true;
        }
        return false;
    }

    public static void add(User user){
        if(!userList.contains(user)){
            userList.add(user);
        }
    }

    public static User get(String name){
        for(User user : userList){
            if(user.getName().equals(name)){
                return user;
            }
        }
        return new User(name);
    }
}
