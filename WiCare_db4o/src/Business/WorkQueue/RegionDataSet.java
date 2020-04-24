/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;
import java.util.ArrayList;
/**
 *
 * @author user
 */
public class RegionDataSet {
     private ArrayList<RegionData> dataList;

    public RegionDataSet() {
     dataList = new ArrayList();
    }

    public ArrayList<RegionData> getRegionDataSet() {
        return dataList;
    }
}
