/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author user
 */
public class CountAge {
     private Map<Integer,Integer> countwithAge;

   
    public CountAge(){
//        this.coughcount=0;
//        this.breathDifficultCount=0;
//        this.soreThroatCount=0;
//        this.headacheCount=0;
//        this.feverCount=0;
//        this.diarrheaCount=0;
//        this.runningNoseCount=0;
        this.countwithAge=new TreeMap<Integer,Integer>();
        setCountByAge();
    }

    public void setCountByAge(){
        countwithAge.put(0, 0);
         countwithAge.put(10, 0);
          countwithAge.put(20, 0);
           countwithAge.put(30, 0);
            countwithAge.put(40, 0);
             countwithAge.put(50, 0);
              countwithAge.put(60, 0);
               countwithAge.put(70, 0);
                countwithAge.put(80, 0);
                 countwithAge.put(90, 0);
       
    }
    public Map<Integer, Integer> getCountwithAge() {
        return countwithAge;
    }
     public void setCountwithAge(Map<Integer, Integer> countwithAge) {
        this.countwithAge = countwithAge;
    }
}
