/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.QuarantineRole;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.Organization.QuarantineOrganization;
import Business.Organization.ReceptionOrganization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.LabTest_doctorQuarantine;
import Business.WorkQueue.RegionData;
import Business.WorkQueue.WorkQueue_doctorQuarantine;
import Business.WorkQueue.WorkQueue_quarantineCDC;
import Business.WorkQueue.WorkRequest_doctorQuarantine;
import Business.WorkQueue.WorkRequest_quarantineCDC;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import userinterface.DoctorRole.DoctorWorkAreaJPanel;

/**
 *
 * @author stacyhuang
 */
public class QuarantineWorkAreaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form QuarantineWorkAreaJPanel
     */
    private JPanel jpanel;
    private QuarantineOrganization organization;
    private Enterprise enterprise;
    private UserAccount userAccount;
    private EcoSystem system;
    //private int caseNumber=0;
    private WorkRequest_doctorQuarantine thisrequest;
    private HashMap<String, Integer>symtom;
    private Network network;
    private RegionData regiondata;
    public QuarantineWorkAreaJPanel(JPanel jpanel, UserAccount userAccount, QuarantineOrganization organization, Enterprise enterprise, EcoSystem system, Network network) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        initComponents();
        this.jpanel = jpanel;
        this.organization = organization;
        this.enterprise = enterprise;
        this.userAccount = userAccount;
        this.system=system;
        this.network=network;
        //this.request=null;
        ButtonGroup b=new ButtonGroup();
        b.add(positive);
        b.add(negative);
        setField(false);
        regiondata=new RegionData();
        messagearea.setEditable(false);
        messagearea.setText("");
        popQuarantineTable();
        setCombo();
        symtom=system.getCountSymtoms().getCountwithType();
    }
    //Emily Edition
    public void populateSymtomCount(ArrayList<String> a){
          for(String s:a){
              if(symtom.containsKey(s)){
                  int b=symtom.get(s);
                  symtom.put(s, ++b);
              }
              else{
                 symtom.put(s, 1);
              }
              
              
              
              //Eimly Edition
//              if(s.equals("noSymptoms")){
//                int nocount=symtom.get("noSymptoms"); //Emily Edition
//           symtom.put("noSymptoms", ++nocount);
//              }if(s.equals("sore throat")){
//                 int nocount=symtom.get("sore throat"); //Emily Edition
//           symtom.put("sore throat", ++nocount);
//              }
//              if(s.equals("headache")){
//                 int nocount=symtom.get("headache"); //Emily Edition
//           symtom.put("headache", ++nocount);
//              }
//                if(s.equals("fever")){
//                 int nocount=symtom.get("fever"); //Emily Edition
//           symtom.put("fever", ++nocount);
//              }
//                  if(s.equals("diarrhea")){
//                 int nocount=symtom.get("diarrhea"); //Emily Edition
//           symtom.put("diarrhea", ++nocount);
//              }
//                    if(s.equals("running nose")){
//                 int nocount=symtom.get("running nose"); //Emily Edition
//           symtom.put("running nose", ++nocount);
//              }
//                      if(s.equals("breath difficult")){
//                 int nocount=symtom.get("breath difficult"); //Emily Edition
//           symtom.put("breath difficult", ++nocount);
//              }
//                        if(s.equals("death")){
//                 int nocount=symtom.get("death"); //Emily Edition
//           symtom.put("death", ++nocount);
//              }

          
          }   
     
    
    }
    //Emily Edition
     public void populateRegionData(){
       regiondata.setState(network.toString());
       Date date=new Date();
       SimpleDateFormat formatte = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = formatte.parse("2020-04-19");
            regiondata.setDate(date1);
            
        } catch (ParseException ex) {
            Logger.getLogger(DoctorWorkAreaJPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
           // Date date2 = sdf.parse("2012-10-4");
          
     
      for(Enterprise en:network.getEnterpriseDirectory().getEnterpriseList()){
          for(Organization o:en.getOrganizationDirectory().getOrganizationList()){
              if(o instanceof ReceptionOrganization){
                  o.getRegiondataSet().getRegionDataSet().add(regiondata);
                  
              }
    }
      }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
      // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        quarantineTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        symptonfield = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        diagnosisfield = new javax.swing.JTextArea();
        positive = new javax.swing.JRadioButton();
        negative = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        savebtn = new javax.swing.JButton();
        reportbtn = new javax.swing.JButton();
        selectbtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        typefield = new javax.swing.JTextField();
        resultfield = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        doctorfield = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        networkJComboBox = new javax.swing.JComboBox();
        enterpriseTypeJComboBox = new javax.swing.JComboBox();
        inspectorfield = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        messagearea = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(0, 102, 153));

        quarantineTable.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        quarantineTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Quarantin ID", "date", "ID", "First Name", "Last Name", "test type", "status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        quarantineTable.setGridColor(new java.awt.Color(0, 102, 153));
        quarantineTable.setSelectionBackground(new java.awt.Color(204, 204, 204));
        quarantineTable.setSelectionForeground(new java.awt.Color(0, 0, 102));
        jScrollPane1.setViewportView(quarantineTable);
        if (quarantineTable.getColumnModel().getColumnCount() > 0) {
            quarantineTable.getColumnModel().getColumn(0).setResizable(false);
            quarantineTable.getColumnModel().getColumn(1).setResizable(false);
            quarantineTable.getColumnModel().getColumn(2).setResizable(false);
            quarantineTable.getColumnModel().getColumn(3).setResizable(false);
            quarantineTable.getColumnModel().getColumn(4).setResizable(false);
            quarantineTable.getColumnModel().getColumn(5).setResizable(false);
            quarantineTable.getColumnModel().getColumn(6).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 204));
        jLabel1.setText("Symptons:");

        symptonfield.setColumns(20);
        symptonfield.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        symptonfield.setForeground(new java.awt.Color(0, 153, 204));
        symptonfield.setRows(5);
        jScrollPane2.setViewportView(symptonfield);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 204));
        jLabel2.setText("Diagnosis:");

        diagnosisfield.setColumns(20);
        diagnosisfield.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        diagnosisfield.setForeground(new java.awt.Color(0, 153, 204));
        diagnosisfield.setRows(5);
        jScrollPane3.setViewportView(diagnosisfield);

        positive.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        positive.setForeground(new java.awt.Color(0, 153, 204));
        positive.setText("Positive");
        positive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                positiveActionPerformed(evt);
            }
        });

        negative.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        negative.setForeground(new java.awt.Color(0, 153, 204));
        negative.setText("Negative");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 204));
        jLabel3.setText("Result:");

        savebtn.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        savebtn.setForeground(new java.awt.Color(255, 153, 51));
        savebtn.setText("Save to Document");
        savebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebtnActionPerformed(evt);
            }
        });

        reportbtn.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        reportbtn.setForeground(new java.awt.Color(0, 204, 204));
        reportbtn.setText("Report it to CDC");
        reportbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportbtnActionPerformed(evt);
            }
        });

        selectbtn.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        selectbtn.setForeground(new java.awt.Color(255, 153, 51));
        selectbtn.setText("select");
        selectbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectbtnActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 204));
        jLabel5.setText("testing type");

        typefield.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        typefield.setForeground(new java.awt.Color(0, 153, 204));

        resultfield.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        resultfield.setForeground(new java.awt.Color(0, 153, 204));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 153, 204));
        jLabel8.setText("result");

        doctorfield.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        doctorfield.setForeground(new java.awt.Color(0, 153, 204));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 153, 204));
        jLabel9.setText("responsible doctor");

        networkJComboBox.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        networkJComboBox.setForeground(new java.awt.Color(0, 153, 204));
        networkJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        networkJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                networkJComboBoxActionPerformed(evt);
            }
        });

        enterpriseTypeJComboBox.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        enterpriseTypeJComboBox.setForeground(new java.awt.Color(0, 153, 204));
        enterpriseTypeJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        inspectorfield.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        inspectorfield.setForeground(new java.awt.Color(0, 153, 204));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 153, 204));
        jLabel10.setText("responsible inspector");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 204));
        jLabel4.setText("message to CDC");

        messagearea.setColumns(20);
        messagearea.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        messagearea.setForeground(new java.awt.Color(0, 153, 204));
        messagearea.setRows(5);
        jScrollPane4.setViewportView(messagearea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(223, 223, 223)
                .addComponent(savebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5))
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(typefield, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(doctorfield, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(resultfield, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                        .addComponent(inspectorfield, javax.swing.GroupLayout.Alignment.LEADING)))
                                .addGap(56, 56, 56)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel3))
                                    .addComponent(positive)
                                    .addComponent(negative))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(networkJComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1035, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(reportbtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(enterpriseTypeJComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(selectbtn)
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectbtn)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(3, 3, 3)
                                .addComponent(positive)
                                .addGap(5, 5, 5)
                                .addComponent(negative)))
                        .addGap(32, 32, 32)
                        .addComponent(networkJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(typefield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(doctorfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inspectorfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(resultfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addComponent(savebtn)))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(enterpriseTypeJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(reportbtn))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel2)
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void setField(boolean boo){
        negative.setEnabled(boo);positive.setEnabled(boo);
        savebtn.setEnabled(boo);reportbtn.setEnabled(boo);
        typefield.setEditable(boo);doctorfield.setEditable(boo);
        inspectorfield.setEditable(boo);diagnosisfield.setEditable(boo);
        resultfield.setEditable(boo);symptonfield.setEditable(boo);doctorfield.setEditable(boo);
        
    }
    public void setCombo(){
        networkJComboBox.removeAllItems();
        for (Network network : system.getNetworkList()) {
            networkJComboBox.addItem(network);
        }
    }
    public void setEnterprise(Network network){
        enterpriseTypeJComboBox.removeAllItems();
        for(Enterprise e:network.getEnterpriseDirectory().getEnterpriseList()){
            if(e.getEnterpriseType().getValue().equals("CDC")){
                enterpriseTypeJComboBox.addItem(e);
            }
        }
    }
   
    public void popQuarantineTable(){
        DefaultTableModel model = (DefaultTableModel) quarantineTable.getModel();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        model.setRowCount(0);
        for(WorkRequest_doctorQuarantine request:organization.getWorkQueue_doctorQuarantine().getWorkRequestList()){
            Object row[]=new Object[7];
            row[0]=request;
            row[1]=formatter.format(request.getDate());
            row[2]=request.getPatient().getId();
            row[3]=request.getPatient().getName().split(" ")[0];
            row[4]=request.getPatient().getName().split(" ")[1];
            row[5]=request.getTestType();
            row[6]=request.getStatus();
           
            model.addRow(row);
        }
        
    }
    private void positiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_positiveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_positiveActionPerformed

    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed
        // TODO add your handling code here:
        Date date=new Date();
        if(positive.isSelected()==false&&negative.isSelected()==false){
            JOptionPane.showMessageDialog(null, "please update result");
            return;
        }else{
            if(positive.isSelected()){
                (thisrequest).setResult("positive");
            }else{
                (thisrequest).setResult("negative");
            }
            thisrequest.setDate(date);
            thisrequest.setStatus("finish test");
            thisrequest.setInspector(userAccount);
            JOptionPane.showMessageDialog(null, "save success");
            resultfield.setText(thisrequest.getResult());
            inspectorfield.setText(thisrequest.getInspector().getEmployee().getName());
            negative.setEnabled(false);positive.setEnabled(false);
            thisrequest.setMessage(messagearea.getText());
            popQuarantineTable();
        }
    }//GEN-LAST:event_savebtnActionPerformed

    private void selectbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectbtnActionPerformed
        // TODO add your handling code here:
        int select=quarantineTable.getSelectedRow();
        if(select<0){
            JOptionPane.showMessageDialog(null, "please select");
            return;
        }else{
            negative.setEnabled(true);positive.setEnabled(true);
            savebtn.setEnabled(true);reportbtn.setEnabled(true);
            messagearea.setEditable(true);
            WorkRequest_doctorQuarantine request=(WorkRequest_doctorQuarantine) quarantineTable.getValueAt(select, 0);
           
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            this.thisrequest=request;
           
            typefield.setText(request.getTestType());
            doctorfield.setText(request.getDoctor().getEmployee().getName());
            resultfield.setText(request.getResult());
//            if(request.getResult().equals("positive")){
//                    positive.setSelected(true);
//                }else if(request.getResult().equals("negative")){
//                    negative.setSelected(true);
//                }
            diagnosisfield.setText(request.getDiagnosis());
            String sym="";
            for(String str:request.getSymptoms()){
                sym+=(str+",");
            }
            symptonfield.setText(sym);
            messagearea.setText(request.getMessage());
            if(request.getInspector()!=null){
                inspectorfield.setText(request.getInspector().getEmployee().getName());
            }else{
                inspectorfield.setText("");
            }
        }
    }//GEN-LAST:event_selectbtnActionPerformed

    private void reportbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportbtnActionPerformed
        // TODO add your handling code here:
        ReceptionOrganization org=null;
        Enterprise en=(Enterprise) enterpriseTypeJComboBox.getSelectedItem();
        
        //Emily Edition
        ArrayList<String>checking=new ArrayList();
        WorkRequest_quarantineCDC toCDC=new WorkRequest_quarantineCDC();
         for(Organization o:en.getOrganizationDirectory().getOrganizationList()){
                    if(o instanceof ReceptionOrganization){
                        org=(ReceptionOrganization) o;
                        break;
                    }
                }
         for(WorkRequest_quarantineCDC w:org.getWorkQueue_quarantineCDC().getWorkRequestList()){
            checking.add(w.getPatient().getId());
           
         }
       
        //int select=quarantineTable.getSelectedRow();
        if(en==null){
            JOptionPane.showMessageDialog(null, "please select");
            return;
        }else if(checking.indexOf(thisrequest.getPatient().getId())>-1)
                {
                    JOptionPane.showMessageDialog(null, "This case already reported before, can not do the request");
                 return;
        }else{
            //WorkRequest_doctorQuarantine request = (WorkRequest_doctorQuarantine)quarantineTable.getValueAt(select, 0);
            if(thisrequest.getStatus().equals("finish test")){
                thisrequest.setStatus("sent to CDC");
                popQuarantineTable();
                
                int c=system.getCaseCount()+1;
                system.setCaseCount(c);
                String message=messagearea.getText();
                toCDC.setMessage(message);
                toCDC.setCaseNumber(c);
                toCDC.setDate(thisrequest.getDate());
                toCDC.setPatient(thisrequest.getPatient());
                toCDC.setStatus("sent to CDC");
                toCDC.setEnterprise(enterprise);
                toCDC.setSymptons(symptonfield.getText());
                populateSymtomCount(thisrequest.getSymptoms());
                toCDC.setSendtoEnterprise(en);
                for(Organization o:en.getOrganizationDirectory().getOrganizationList()){
                    if(o instanceof ReceptionOrganization){
                        org=(ReceptionOrganization) o;
                        break;
                    }
                }
                org.getWorkQueue_quarantineCDC().getWorkRequestList().add(toCDC);
                System.out.println("sent to:"+toCDC.getSendtoEnterprise().getName());
                JOptionPane.showMessageDialog(null, "send to CDC reception organization");
                populateRegionData();
            }else{
                JOptionPane.showMessageDialog(null, "need to update result");
                return;
            }
        }
    }//GEN-LAST:event_reportbtnActionPerformed

    private void networkJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_networkJComboBoxActionPerformed
        // TODO add your handling code here:
        Network network=(Network)networkJComboBox.getSelectedItem();
        if(network!=null){
            setEnterprise(network);
        }
    }//GEN-LAST:event_networkJComboBoxActionPerformed


      // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea diagnosisfield;
    private javax.swing.JTextField doctorfield;
    private javax.swing.JComboBox enterpriseTypeJComboBox;
    private javax.swing.JTextField inspectorfield;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea messagearea;
    private javax.swing.JRadioButton negative;
    private javax.swing.JComboBox networkJComboBox;
    private javax.swing.JRadioButton positive;
    private javax.swing.JTable quarantineTable;
    private javax.swing.JButton reportbtn;
    private javax.swing.JTextField resultfield;
    private javax.swing.JButton savebtn;
    private javax.swing.JButton selectbtn;
    private javax.swing.JTextArea symptonfield;
    private javax.swing.JTextField typefield;
    // End of variables declaration//GEN-END:variables
}
