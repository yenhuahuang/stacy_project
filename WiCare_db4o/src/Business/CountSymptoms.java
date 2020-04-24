/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.HashMap;

/**
 *
 * @author stacyhuang
 */
public class CountSymptoms {
    private int coughcount;
    private int soreThroatCount;
    private int headacheCount;
    private int feverCount;
    private int diarrheaCount;
    private int runningNoseCount;
    private int breathDifficultCount;
    private int no;
    private int deathCount;

  

   
    
    private HashMap<String,Integer> countwithType;
    public CountSymptoms(){
//        this.coughcount=0;
//        this.breathDifficultCount=0;
//        this.soreThroatCount=0;
//        this.headacheCount=0;
//        this.feverCount=0;
//        this.diarrheaCount=0;
//        this.runningNoseCount=0;
        this.countwithType=new HashMap<String,Integer>();
        setCountAndType();
    }

    public void setCountAndType(){
        countwithType.put("cough", 0);
        countwithType.put("sore throat", 0);
        countwithType.put("headache", 0);
        countwithType.put("fever", 0);
        countwithType.put("diarrhea", 0);
        countwithType.put("running nose", 0);
        countwithType.put("breath difficult", 0);
        countwithType.put("noSymptoms", 0);
        countwithType.put("death", 0);
    }
    public HashMap<String, Integer> getCountwithType() {
        return countwithType;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setCountwithType(HashMap<String, Integer> countwithType) {
        this.countwithType = countwithType;
    }
    
    public int getCoughcount() {
        return coughcount;
    }

    public void setCoughcount(int coughcount) {
        this.coughcount = coughcount;
    }

    public int getSoreThroatCount() {
        return soreThroatCount;
    }

    public void setSoreThroatCount(int soreThroatCount) {
        this.soreThroatCount = soreThroatCount;
    }

    public int getHeadacheCount() {
        return headacheCount;
    }

    public void setHeadacheCount(int headacheCount) {
        this.headacheCount = headacheCount;
    }

    public int getFeverCount() {
        return feverCount;
    }

    public void setFeverCount(int feverCount) {
        this.feverCount = feverCount;
    }

    public int getDiarrheaCount() {
        return diarrheaCount;
    }

    public void setDiarrheaCount(int diarrheaCount) {
        this.diarrheaCount = diarrheaCount;
    }

    public int getRunningNoseCount() {
        return runningNoseCount;
    }

    public void setRunningNoseCount(int runningNoseCount) {
        this.runningNoseCount = runningNoseCount;
    }

    public int getBreathDifficultCount() {
        return breathDifficultCount;
    }
    

    public void setBreathDifficultCount(int breathDifficultCount) {
        this.breathDifficultCount = breathDifficultCount;
    }
      public int getDeathCount() {
        return deathCount;
    }

    public void setDeathCount(int deathCount) {
        this.deathCount = deathCount;
    }
    
}
