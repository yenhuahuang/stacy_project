/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.ReceptionRole;

import userinterface.InvestigationRole.*;
import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.InvestigationOrganization;
import Business.Organization.Organization;
import Business.Organization.ReceptionOrganization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.RegionData;
import Business.WorkQueue.WorkRequest_quarantineCDC;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import userinterface.DoctorRole.DoctorWorkAreaJPanel;

/**
 *
 * @author stacyhuang
 */
public class AnalysisWorkAreaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form InvestigationWorkAreaJPanel
     */
    private JPanel jpanel;
    private ReceptionOrganization organization;
    private Enterprise enterprise;
    private UserAccount userAccount;
    private EcoSystem system;
    private RegionData regiondata;
    private Network network;
    private HashMap<String,Integer> symtom;
    private Map<Date,Integer>regioncase;
    private Map<Integer,Integer>agedeath;
    private Map<Integer,Integer>age;
    
    ArrayList<String> states;
   

   public AnalysisWorkAreaJPanel(JPanel jpanel, UserAccount userAccount, ReceptionOrganization organization, Enterprise enterprise, EcoSystem system, Network network) {
       initComponents();
         pnChart.setLayout(new BorderLayout());
         jPanel1.setLayout(new BorderLayout());
         jPanel2.setLayout(new BorderLayout());
        
         
         this.jpanel = jpanel;
        this.organization = organization;
        this.enterprise = enterprise;
        this.userAccount = userAccount;
        this.system=system;
        this.network=network;
        this.regiondata=regiondata;
        
        regioncase = new TreeMap< Date, Integer>();
        age=new TreeMap<Integer,Integer>();
        agedeath=system.getCountage().getCountwithAge();
        symtom=system.getCountSymtoms().getCountwithType();
        age.putAll(agedeath);
        piechart();
        populatePie();
        populateBarChart();
        populateLineChart();
           totalt.setText(String.valueOf(total()));
      states=new ArrayList();
      tct.setText(String.valueOf(system.getCaseCount()));
        states.add("Alabama");  
          states.add("Alaska");
            states.add("Arizona");
              states.add("Arkansas");
                states.add("Alabama");
                  states.add("California");
                    states.add("Colorado");
                      states.add("Connecticut");
                        states.add("Delaware");
             states.add("Florida");
               states.add("Georgia");
                 states.add("Hawaii");
                   states.add("Idaho");  states.add("Illinois");states.add("Indiana");states.add("Iowa");
                   states.add( "Kansas");  states.add("Kentucky");  states.add("Louisiana");  states.add("Maine");  states.add("Maryland");
                        states.add("Massachusetts");states.add("Michigan");  states.add("Minnesota");  states.add("Mississippi");  states.add("Missouri");
                         states.add("Montana");  states.add("Nebraska");  states.add("Nevada");  states.add("New Hampshire");  states.add("New Jersey");
         states.add("New Mexico");  states.add("New York");  states.add("North Carolina");  states.add("North Dakota");  states.add("New Jersey");
        states.add("Ohio");  states.add("Oklahoma");  states.add("Oregon");  states.add("Pennsylvania");  states.add("Rhode Island");
        states.add("South Carolina");  states.add("South Dakota");  states.add("Tennessee");  states.add("Texas");  states.add("Utah");
        states.add("Vermont");  states.add("Virginia");  states.add("Washington");  states.add("West Virginia");  states.add("Wisconsin");
        states.add("Wyoming");  
        ComboBox();
       
        
        
    }
     private DefaultCategoryDataset createDataset() {
        // same dataset for line chart and bar chart
        HashMap<Date,Integer> transfer=new HashMap<Date,Integer>();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
     //  DefaultPieDataset dataset = new DefaultPieDataset();
       for(RegionData rd:organization.getRegiondataSet().getRegionDataSet()){ 
           if(transfer.containsKey(rd.getDate())){
             int c= transfer.get(rd.getDate());
             c=c+1;
           transfer.put(rd.getDate(), c);
       }
           else{transfer.put(rd.getDate(), 1);}
       
       
       }
       regioncase.putAll(transfer);
       for(Date s:regioncase.keySet()){
           int y=regioncase.get(s);
          dataset.addValue(y, network.toString(), s);

       }
       
        return dataset;
    }
   
     
     public DefaultPieDataset piechart(){
      DefaultPieDataset dataset = new DefaultPieDataset();
      for(String a:symtom.keySet()){
          System.out.println(symtom);
         int b=symtom.get(a);
         dataset.setValue(a, b);
     }  return dataset;
     }
      private DefaultCategoryDataset createDataset2() {
          DefaultCategoryDataset dataset = new DefaultCategoryDataset();
          for(Integer i:age.keySet()){
              
             int z=age.get(i);
             System.out.println(z);
          dataset.setValue(z, "Nation", i);
          }
          
          
          
        return dataset;
      
      }
     public void populatePie(){
      JFreeChart chart = ChartFactory.createPieChart3D(
            "Symptoms Arrangement", // chart title
            piechart(), // data
            true, // include legend
            true,
            false);
        // set chart properties
        final PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(270);
        plot.setForegroundAlpha(0.60f);
        plot.setInteriorGap(0.02);
        // create chart panel the add it to swing panel in  jframe
        ChartPanel chartPanel = new ChartPanel(chart);
        pnChart.removeAll();        // clear panel before add new chart
        pnChart.add(chartPanel, BorderLayout.CENTER);
        pnChart.validate();         // refresh panel to display new chart
     
     }
     
     public void populateLineChart(){
     JFreeChart lineChart = ChartFactory.createLineChart(
            "Number of Case vs date",
            "Date", "Case",
            createDataset(),
            PlotOrientation.VERTICAL,
            true, true, false);
        // set color
        CategoryPlot plot = (CategoryPlot) lineChart.getPlot();
        plot.getRenderer().setSeriesPaint(0, Color.BLUE);
        ValueAxis valueAxis = plot.getRangeAxis();
        plot.setRangeAxis(0, valueAxis);

        // create chart panel the add it to swing panel in  jframe
        ChartPanel chartPanel = new ChartPanel(lineChart);
        jPanel1.removeAll();    // clear panel before add new chart
        jPanel1.add(chartPanel, BorderLayout.CENTER);
        jPanel1.validate();       // refresh panel to display new chart
                          
     
     }
     public void populateBarChart(){
     JFreeChart barChart = ChartFactory.createBarChart(
            "National Death Number By Age",
            "Age", "Number of Death",
            createDataset2(),
            PlotOrientation.VERTICAL,
            true, true, false);
        // set color
        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
        plot.getRenderer().setSeriesPaint(0, Color.BLUE);
       ValueAxis valueAxis = plot.getRangeAxis();
        plot.setRangeAxis(0, valueAxis);
        // create chart panel the add it to swing panel in  jframe
        ChartPanel chartPanel = new ChartPanel(barChart);
        jPanel2.removeAll();        // clear panel before add new chart
        jPanel2.add(chartPanel, BorderLayout.CENTER);
        jPanel2.validate();          // refresh panel to display new chart
                           
     
     }
     public int total(){
         int tt=0;
         for(Integer a:agedeath.keySet()){
            
       tt+=agedeath.get(a);
      
        }
       return tt;}
    public void ComboBox(){
    stateComboBox.removeAllItems();;
    for(String a:states){
    
     stateComboBox.addItem(a);
    
    }}
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnChart = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        tct = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        totalt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        stateComboBox = new javax.swing.JComboBox();
        statet = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        backbtn = new javax.swing.JButton();

        pnChart.setBackground(new java.awt.Color(0, 102, 153));
        pnChart.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnChart.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));
        jPanel1.setForeground(new java.awt.Color(0, 102, 153));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(0, 102, 153));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 357, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(0, 102, 153));

        tct.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tct.setForeground(new java.awt.Color(0, 153, 204));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 204));
        jLabel1.setText("Total Death:");

        totalt.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        totalt.setForeground(new java.awt.Color(0, 153, 204));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 204));
        jLabel2.setText("Death In:");

        stateComboBox.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        stateComboBox.setForeground(new java.awt.Color(0, 153, 204));
        stateComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        stateComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                stateComboBoxItemStateChanged(evt);
            }
        });
        stateComboBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stateComboBoxMouseClicked(evt);
            }
        });

        statet.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        statet.setForeground(new java.awt.Color(0, 153, 204));

        jSeparator1.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator2.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 204));
        jLabel4.setText("Total Case:");

        backbtn.setBackground(new java.awt.Color(204, 204, 204));
        backbtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        backbtn.setForeground(new java.awt.Color(255, 153, 51));
        backbtn.setText("back");
        backbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addGap(58, 58, 58)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tct, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalt, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(stateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(statet, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(backbtn)))
                .addGap(0, 40, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(8, 8, 8)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
                        .addComponent(jSeparator2))
                    .addGap(8, 8, 8)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backbtn)
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(totalt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(stateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(145, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(101, 101, 101)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(166, 166, 166)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(99, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnChart, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbtnActionPerformed
        // TODO add your handling code here:
        jpanel.remove(this);
        CardLayout layout = (CardLayout) jpanel.getLayout();
        layout.previous(jpanel);
    }//GEN-LAST:event_backbtnActionPerformed

    private void stateComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_stateComboBoxItemStateChanged
        // TODO add your handling code here:
        String a=(String) stateComboBox.getSelectedItem();
        int count=0;
        for(Network nt:system.getNetworkList()){
            if(nt.toString().equals(a)){
                for(Enterprise e:nt.getEnterpriseDirectory().getEnterpriseList()){
                    for(Organization o:e.getOrganizationDirectory().getOrganizationList()){
                        for(WorkRequest_quarantineCDC request:o.getWorkQueue_quarantineCDC().getWorkRequestList()){
                            if(request.getSymptons().contains("DEATH")){
                                count+=1;
                            }
                        }
                    }
                }}
            }
            statet.setText(String.valueOf(count));
    }//GEN-LAST:event_stateComboBoxItemStateChanged

    private void stateComboBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stateComboBoxMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_stateComboBoxMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backbtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel pnChart;
    private javax.swing.JComboBox<String> stateComboBox;
    private javax.swing.JTextField statet;
    private javax.swing.JTextField tct;
    private javax.swing.JTextField totalt;
    // End of variables declaration//GEN-END:variables
}
