/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package People;

import java.util.ArrayList;

/**
 *
 * @author stacyhuang
 */
public class PeopleDirectory {
    private ArrayList<People> peoples;

    public PeopleDirectory(){
        peoples=new ArrayList<People>();
    }
    public ArrayList<People> getPeoples() {
        return peoples;
    }
    public void deletePeople(People p){
        peoples.remove(p);
    }
    public People createPeople(String id,String name, String birthday,int age,String phone,String address,String county,String state,String gender,String picture){
        People p=new People(id,name,birthday,age,phone,address,county,state,gender,picture);
        peoples.add(p);
        return p;
    }
    
}
