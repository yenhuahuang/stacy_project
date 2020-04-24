/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.PopulationRole;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.DoctorOrganization;
import Business.Organization.DocumentOrganization;
import Business.Organization.Organization;
import Business.Organization.PopulationOrganization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.WorkRequest_documentDoctor;
import People.Diagnosis;
import People.People;
import java.awt.CardLayout;
import java.awt.Image;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author user
 */
public class PopulationJPanel extends javax.swing.JPanel {

    /**
     * Creates new form PopulationJPanel
     */
 

   private JPanel jpanel;
    private UserAccount userAccount;
    private PopulationOrganization organization;
    private Enterprise enterprise;
    private EcoSystem system;
    private Network network;
    private People thispeople;
    private Boolean b;
    public PopulationJPanel(JPanel userProcessContainer, UserAccount account, PopulationOrganization populationOrganization, Enterprise enterprise, EcoSystem business, Network network,EcoSystem system) {
        initComponents();
        this.jpanel=jpanel;
        this.userAccount=userAccount;
        this.organization=organization;
        this.enterprise=enterprise;
        this.system=system;
        this.network=network;
        System.out.println(system.getPeopleDirectory().getPeoples());
        populateComboBox();
                populateComboBox2();
                jButton3.setEnabled(false);
//        SimpleDateFormat formate=new SimpleDateFormat("mm-dd-yyyy");
//        Date tryit=null;
//       try {
//           tryit=formate.parse("12-12-1995");
//       } catch (ParseException ex) {
//           Logger.getLogger(PopulationJPanel.class.getName()).log(Level.SEVERE, null, ex);
//       }
//        Date input = new Date();
//         LocalDate date = tryit.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//          LocalDate bday = LocalDate.of(1955, Month.MAY, 19); LocalDate today = LocalDate.now(); 
//          Period age = Period.between(date, today); 
//          int years = age.getYears();
//          System.out.print(years);
agefield.setEnabled(false);


    }
     private void setField(String id,String name, String birthday,int age,String phone,String address,String county,String state,String picture,String gender){
        idfield.setText(id);
        namefield.setText(name);
        birthdayfield.setText(birthday);
        agefield.setText(String.valueOf(age));
        phonefield.setText(phone);
        addressfield.setText(address);
        statefield.setText(state);
        countyfield.setText(county);
        ImageIcon c=new ImageIcon(picture);
        ImageIcon imageIcon = new ImageIcon(c.getImage().getScaledInstance(570, 470, Image.SCALE_DEFAULT));
        imageLabel.setIcon(imageIcon);
        genderfield.setEnabled(false);
        statefield.setEnabled(false);
        genderfield.setText(gender);
        
         b =true;
       
         
    }
     
     public boolean test(){
       
       if(idfield.getText().equals("")){
       b=false;
       return b;
       }if(namefield.getText().equals("")){
       b=false;
       return b;
       }
       if(countyfield.getText().equals("")){
       b=false;
       return b;
       }
        if(statefield.getText().equals("")){
       b=false;
       return b;
       }
         if(addressfield.getText().equals("")){
       b=false;
       return b;
       }
          if(birthdayfield.getText().equals("")){
       b=false;
       return b;
       }
           if(genderfield.getText().equals("")){
       b=false;
       return b;
       }
            if(phonefield.getText().equals("")){
       b=false;
       return b;
       }
            
        if(imageLabel.getIcon()==null){
       b=false;
       return b;
       }
        else{  return true;
        }
     
     
     }
     public void setBU(Boolean r){
     birthdayfield.setEnabled(r);
        genderfield.setEnabled(r);
        idfield.setEnabled(r);
         phonefield.setEnabled(r);
         birthdayfield.setEnabled(r);
         addressfield.setEnabled(r);
         statefield.setEnabled(r);
         countyfield.setEnabled(r);
         namefield.setEnabled(r);
         idfield.setEnabled(r);
         picturebtn.setEnabled(r);
         jButton2.setEnabled(r);
         
                            

     
     
     }
      private void populateComboBox() {
        networkJComboBox1.removeAllItems();
      

        for (Network network : system.getNetworkList()) {
            networkJComboBox1.addItem(network);
        }}
       private void populateComboBox2() {
           genderCombox.removeAllItems();
           genderCombox.addItem("Female");
           genderCombox.addItem("Male");
        }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jLabel20 = new javax.swing.JLabel();
        genderfield = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        idfield = new javax.swing.JTextField();
        namefield = new javax.swing.JTextField();
        agefield = new javax.swing.JTextField();
        birthdayfield = new javax.swing.JTextField();
        phonefield = new javax.swing.JTextField();
        addressfield = new javax.swing.JTextField();
        countyfield = new javax.swing.JTextField();
        imageLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        patientIdfield = new javax.swing.JTextField();
        searchbtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        statefield = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        createbtn = new javax.swing.JButton();
        picturebtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        genderCombox = new javax.swing.JComboBox();
        networkJComboBox1 = new javax.swing.JComboBox();
        deathl = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 102, 153));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 153, 204));
        jLabel20.setText("gender");
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 370, -1, -1));

        genderfield.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        genderfield.setForeground(new java.awt.Color(0, 153, 204));
        add(genderfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 370, 220, -1));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 204));
        jLabel7.setText("address");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 250, -1, -1));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 153, 204));
        jLabel8.setText("county");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 690, -1, -1));

        idfield.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        idfield.setForeground(new java.awt.Color(0, 153, 204));
        add(idfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 560, 220, -1));

        namefield.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        namefield.setForeground(new java.awt.Color(0, 153, 204));
        add(namefield, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 430, 220, -1));

        agefield.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        agefield.setForeground(new java.awt.Color(0, 153, 204));
        agefield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agefieldActionPerformed(evt);
            }
        });
        add(agefield, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 490, 220, -1));

        birthdayfield.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        birthdayfield.setForeground(new java.awt.Color(0, 153, 204));
        birthdayfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                birthdayfieldActionPerformed(evt);
            }
        });
        add(birthdayfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 620, 220, -1));

        phonefield.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        phonefield.setForeground(new java.awt.Color(0, 153, 204));
        add(phonefield, new org.netbeans.lib.awtextra.AbsoluteConstraints(903, 188, 228, -1));

        addressfield.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        addressfield.setForeground(new java.awt.Color(0, 153, 204));
        add(addressfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(903, 248, 228, -1));

        countyfield.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        countyfield.setForeground(new java.awt.Color(0, 153, 204));
        add(countyfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 690, 220, -1));

        imageLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        imageLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 153, 51)));
        add(imageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 570, 470));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 204));
        jLabel1.setText("search patient id:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(273, 121, -1, -1));

        patientIdfield.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        patientIdfield.setForeground(new java.awt.Color(0, 153, 204));
        add(patientIdfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(455, 118, 163, -1));

        searchbtn.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        searchbtn.setForeground(new java.awt.Color(255, 153, 51));
        searchbtn.setText("search");
        searchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbtnActionPerformed(evt);
            }
        });
        add(searchbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 110, 160, 50));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 204));
        jLabel2.setText("id");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 560, -1, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 204));
        jLabel3.setText("name");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 430, -1, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 204));
        jLabel4.setText("age");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 490, -1, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 204));
        jLabel5.setText("birthday");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 620, -1, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 204));
        jLabel6.setText("phone");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(825, 191, -1, -1));

        statefield.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        statefield.setForeground(new java.awt.Color(0, 153, 204));
        add(statefield, new org.netbeans.lib.awtextra.AbsoluteConstraints(903, 311, 228, -1));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 153, 204));
        jLabel9.setText("state");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(837, 315, -1, -1));

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 153, 204));
        jLabel19.setText("photo");
        add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 156, -1, -1));

        createbtn.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        createbtn.setForeground(new java.awt.Color(255, 153, 51));
        createbtn.setText("Create");
        createbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createbtnActionPerformed(evt);
            }
        });
        add(createbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 30, 160, 50));

        picturebtn.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        picturebtn.setForeground(new java.awt.Color(255, 153, 51));
        picturebtn.setText("Shot");
        picturebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                picturebtnActionPerformed(evt);
            }
        });
        add(picturebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 671, 240, 70));

        jButton1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 153, 51));
        jButton1.setText("Clean");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 30, 160, 50));

        jButton2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 153, 51));
        jButton2.setText("Find");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 671, 260, 70));

        jButton3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 153, 51));
        jButton3.setText("Update");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 110, 160, 50));

        genderCombox.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        genderCombox.setForeground(new java.awt.Color(0, 153, 204));
        genderCombox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        genderCombox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                genderComboxItemStateChanged(evt);
            }
        });
        genderCombox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                genderComboxMouseClicked(evt);
            }
        });
        genderCombox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderComboxActionPerformed(evt);
            }
        });
        add(genderCombox, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 370, 130, -1));

        networkJComboBox1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        networkJComboBox1.setForeground(new java.awt.Color(0, 153, 204));
        networkJComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        networkJComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                networkJComboBox1ItemStateChanged(evt);
            }
        });
        networkJComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                networkJComboBox1MouseClicked(evt);
            }
        });
        networkJComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                networkJComboBox1ActionPerformed(evt);
            }
        });
        add(networkJComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 310, 130, -1));

        deathl.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        deathl.setForeground(new java.awt.Color(0, 204, 204));
        add(deathl, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 440, 130, 50));
    }// </editor-fold>//GEN-END:initComponents

    private void searchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbtnActionPerformed
        // TODO add your handling code here:
        networkJComboBox1.setEnabled(false);
        genderCombox.setEnabled(false);
       deathl.setText("");
        String searchId=patientIdfield.getText();
        thispeople=null;
        if(searchId.equals("")){
            JOptionPane.showMessageDialog(null, "please enter patient id to search");
            return;
        }else{
            for(People peopleFound:system.getPeopleDirectory().getPeoples()){
                if(peopleFound.getId().equals(searchId)){
                    thispeople=peopleFound;
                    break;
                }
            }
        }
            boolean death=false;
        for(Network n:system.getNetworkList()){
        for(Enterprise e:n.getEnterpriseDirectory().getEnterpriseList()){
             for(Organization o:e.getOrganizationDirectory().getOrganizationList()){
             if(o instanceof DoctorOrganization){
               for(WorkRequest_documentDoctor w:o.getWorkQueue_documentDoctor().getWorkRequestList()){
                    if(w.getPeople().getId().equals(thispeople.getId())){
                         for(Diagnosis ds:thispeople.getMedicalRecord().getDoctorNote()){
            if(ds.getDiagnosis().contains("Death")){
             death=true;
             break;
            }}
                    }
               }
             }
             }        
        
        }}
       
        if(thispeople!=null){
             if(death==false){
            setField(thispeople.getId(),thispeople.getName(),thispeople.getBirthday(),thispeople.getAge(),thispeople.getPhone(),thispeople.getAddress(),thispeople.getCounty(),thispeople.getState(),thispeople.getPicture(),thispeople.getGender());
            
             deathl.setText("");
            setBU(false);
            jButton3.setEnabled(true);}else{
             setField(thispeople.getId(),thispeople.getName(),thispeople.getBirthday(),thispeople.getAge(),thispeople.getPhone(),thispeople.getAddress(),thispeople.getCounty(),thispeople.getState(),thispeople.getPicture(),thispeople.getGender());
            
            
            setBU(false);
            deathl.setText("Death");
            jButton3.setEnabled(false);}
        }else{
            JOptionPane.showMessageDialog(null, "cannot find this patient, please make sure the id you enter is correct");
            patientIdfield.setText("");
            return;
        }

    }//GEN-LAST:event_searchbtnActionPerformed

    private void birthdayfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_birthdayfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_birthdayfieldActionPerformed

    private void picturebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_picturebtnActionPerformed
        // TODO add your handling code here:
       
        new CamFrame().setVisible(true);
        
    }//GEN-LAST:event_picturebtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        statefield.setText("");
        idfield.setText("");
        namefield.setText("");
        birthdayfield.setText("");
        countyfield.setText("");
        imageLabel.removeAll();
        phonefield.setText("");
        addressfield.setText("");
        agefield.setText("");
        genderfield.setText("");
        patientIdfield.setText("");
         networkJComboBox1.setEnabled(true);
          genderCombox.setEnabled(true);
          statefield.setEnabled(false);
          genderfield.setEnabled(false);
          deathl.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void createbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createbtnActionPerformed
        // TODO add your handling code here:
        Boolean h=false;
        setBU(true);
        ArrayList<Integer>num=new ArrayList();
        for(int i=100;i<1000;i++){
            for(People p:system.getPeopleDirectory().getPeoples()){
                if(!p.getId().equals(i)){
            num.add(i);
            
            }}
        }
        Collections.shuffle(num);
        
        for(People p:system.getPeopleDirectory().getPeoples()){
            if(p.getId().equals(idfield.getText())){
                h=true;
                JOptionPane.showMessageDialog(null, "Id already used, try a new one. "+"Suggested Id Number: "+ num.get(1));
               
                
            }} 
        
            if(test()==true && h==false){
           
              People n=new People();
              n.setAddress(addressfield.getText());
                try{
                  SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
               Date date1= sdf.parse(birthdayfield.getText());
              n.setBirthday( birthdayfield.getText());
               LocalDate d = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
           LocalDate today = LocalDate.now(); 
          Period age = Period.between(d, today); 
          int years = age.getYears();
          System.out.print(years);
          n.setAge(years);
              }
              catch(Exception e){JOptionPane.showMessageDialog(null, "Invalid form MM-dd-yyyy");}
              
                  SimpleDateFormat formate=new SimpleDateFormat("mm-dd-yyyy");
        Date tryit=null;
       try {
           tryit=formate.parse(birthdayfield.getText());
          } catch (ParseException ex) {
           Logger.getLogger(PopulationJPanel.class.getName()).log(Level.SEVERE, null, ex);
       }
        //Date input = new Date();
        
            
              n.setCounty(countyfield.getText());
              if(genderfield.getText().equals("Female")||genderfield.getText().equals("Male")){
              n.setGender(genderfield.getText());}else{JOptionPane.showMessageDialog(null, "Can be only Female or Male");}
              n.setId(idfield.getText());
              n.setName(namefield.getText());
              n.setPhone(phonefield.getText());
              n.setPicture(imageLabel.getText());
              n.setState(statefield.getText());
              n.setPicture(filename);
              system.getPeopleDirectory().getPeoples().add(n);
              JOptionPane.showMessageDialog(null, "Success");
           }
            else if(test()==false && h==false){
           JOptionPane.showMessageDialog(null, "Make sure you fill in all information in a correct format");
           
           }
            
        
    }//GEN-LAST:event_createbtnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
         JFileChooser chooser=new JFileChooser();
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        filename=f.getAbsolutePath();
        ImageIcon c=new ImageIcon(filename.toString());
        ImageIcon imageIcon = new ImageIcon(c.getImage().getScaledInstance(570, 420, Image.SCALE_DEFAULT));
        imageLabel.setIcon(imageIcon);
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void agefieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agefieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_agefieldActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        setBU(true);
         genderCombox.setEnabled(false);
         networkJComboBox1.setEnabled(true);
        birthdayfield.setEnabled(false);
        statefield.setEnabled(false);
        genderfield.setEnabled(false);
        idfield.setEnabled(false);
         if(!phonefield.getText().equals("")&&!countyfield.getText().equals("")&&!addressfield.getText().equals("")){
         for(People p:system.getPeopleDirectory().getPeoples()){
             if(p.getId().equals(idfield.getText())){
                 p.setAddress(addressfield.getText());
                 p.setName(namefield.getText());
                 p.setPhone(phonefield.getText());
                 p.setState(statefield.getText());
                 p.setPicture(filename);
                 p.setCounty(countyfield.getText());
                 JOptionPane.showMessageDialog(null, "Success");
                 
                 
                 
             }
    }}
        else{
         JOptionPane.showMessageDialog(null, "No blank");}
        
     
        
      
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void genderComboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderComboxActionPerformed
        // TODO add your handling code here:
//        genderfield.setEnabled(false);
//        genderfield.setText(genderCombox.getSelectedItem().toString());
    }//GEN-LAST:event_genderComboxActionPerformed

    private void networkJComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_networkJComboBox1ActionPerformed
        // TODO add your handling code here:
//        statefield.setEnabled(false);
//        statefield.setText(networkJComboBox1.getSelectedItem().toString());
        
    }//GEN-LAST:event_networkJComboBox1ActionPerformed

    private void networkJComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_networkJComboBox1ItemStateChanged
        // TODO add your handling code here:
      //  statefield.setText(networkJComboBox1.getSelectedItem().toString());
    }//GEN-LAST:event_networkJComboBox1ItemStateChanged

    private void genderComboxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_genderComboxItemStateChanged
        // TODO add your handling code here:
        //genderfield.setText(genderCombox.getSelectedItem().toString());
    }//GEN-LAST:event_genderComboxItemStateChanged

    private void networkJComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_networkJComboBox1MouseClicked
        // TODO add your handling code here:
         statefield.setText(networkJComboBox1.getSelectedItem().toString());
    }//GEN-LAST:event_networkJComboBox1MouseClicked

    private void genderComboxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_genderComboxMouseClicked
        // TODO add your handling code here:
        genderfield.setText(genderCombox.getSelectedItem().toString());
    }//GEN-LAST:event_genderComboxMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressfield;
    private javax.swing.JTextField agefield;
    private javax.swing.JTextField birthdayfield;
    private javax.swing.JTextField countyfield;
    private javax.swing.JButton createbtn;
    private javax.swing.JLabel deathl;
    private javax.swing.JComboBox genderCombox;
    private javax.swing.JTextField genderfield;
    private javax.swing.JTextField idfield;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField namefield;
    private javax.swing.JComboBox networkJComboBox1;
    private javax.swing.JTextField patientIdfield;
    private javax.swing.JTextField phonefield;
    private javax.swing.JButton picturebtn;
    private javax.swing.JButton searchbtn;
    private javax.swing.JTextField statefield;
    // End of variables declaration//GEN-END:variables
byte[]photo=null;
String filename=null;
}
