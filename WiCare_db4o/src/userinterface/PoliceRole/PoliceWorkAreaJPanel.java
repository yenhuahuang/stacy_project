/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.PoliceRole;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.InvestigationOrganization;
import Business.Organization.Organization;
import Business.Organization.PoliceOrganization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.QuarantineRelatedCase;
import Business.WorkQueue.WorkQueue_investigationPolice;
import Business.WorkQueue.WorkRequest_documentDoctor;
import Business.WorkQueue.WorkRequest_investigationPolice;
import Business.WorkQueue.WorkRequest_quarantineList;
import People.People;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author stacyhuang
 */
public class PoliceWorkAreaJPanel extends javax.swing.JPanel {
 private JPanel userProcessContainer;
    private UserAccount account;
    private PoliceOrganization policeOrganization;
    private Enterprise enterprise;
   private EcoSystem business;
   private WorkRequest_investigationPolice request;
   private Network network;
   private  WorkQueue_investigationPolice queue;

    public PoliceWorkAreaJPanel(JPanel userProcessContainer, UserAccount account, PoliceOrganization policeOrganization, Enterprise enterprise, EcoSystem business, Network network) {
       initComponents();
       this.userProcessContainer = userProcessContainer;
        this.business=business;
        this.policeOrganization=policeOrganization;
        this.account=account;
        this.enterprise=enterprise;
        this.network=network;
        //this.queue=queue;
        messaget.setEditable(false);
        queue=policeOrganization.getWorkQueue_investigationPolice();
        ButtonGroup b=new ButtonGroup();
        b.add(ok);b.add(suspect);b.add(missing);
       // this.request=request;
        
     
//         Browser browser = new Browser();
//        BrowserView view = new BrowserView(browser);
//        v
        popRelated();
        
    //    splitPane.setRightComponent(view);
    }
    
    private void popRelated(){
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        for(WorkRequest_investigationPolice request:policeOrganization.getWorkQueue_investigationPolice().getWorkRequestList()){
            Object[] row=new Object[9];
            row[0]=request;
            row[1]=request.getQuarantinePeople().getName();
            row[2]=request.getQuarantinePeople().getAddress()+", "+request.getQuarantinePeople().getCounty()+", "+request.getQuarantinePeople().getState();
            row[3]=request.getQuarantinePeople().getPhone();
            row[4]=request.getEnddate();
            model.addRow(row);
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

        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        replyt = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        messaget = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        searchb = new javax.swing.JButton();
        sendb = new javax.swing.JButton();
        ok = new javax.swing.JRadioButton();
        suspect = new javax.swing.JRadioButton();
        missing = new javax.swing.JRadioButton();
        selectbtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 102, 153));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        table.setForeground(new java.awt.Color(0, 153, 204));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Address", "Phone", "EndDate"
            }
        ));
        table.setGridColor(new java.awt.Color(0, 102, 153));
        table.setSelectionBackground(new java.awt.Color(204, 204, 204));
        table.setSelectionForeground(new java.awt.Color(0, 0, 102));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 1056, 242));

        replyt.setColumns(20);
        replyt.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        replyt.setForeground(new java.awt.Color(0, 153, 204));
        replyt.setRows(5);
        jScrollPane4.setViewportView(replyt);

        add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 370, -1, -1));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 204));
        jLabel1.setText("Reply");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 330, -1, -1));

        messaget.setColumns(20);
        messaget.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        messaget.setForeground(new java.awt.Color(0, 153, 204));
        messaget.setRows(5);
        jScrollPane5.setViewportView(messaget);

        add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 370, -1, -1));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 153, 204));
        jLabel9.setText("Message");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 330, -1, -1));

        searchb.setBackground(new java.awt.Color(204, 204, 204));
        searchb.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        searchb.setForeground(new java.awt.Color(255, 153, 51));
        searchb.setText("Search");
        searchb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbActionPerformed(evt);
            }
        });
        add(searchb, new org.netbeans.lib.awtextra.AbsoluteConstraints(303, 605, 160, 60));

        sendb.setBackground(new java.awt.Color(204, 204, 204));
        sendb.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        sendb.setForeground(new java.awt.Color(255, 153, 51));
        sendb.setText("Send Back");
        sendb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendbActionPerformed(evt);
            }
        });
        add(sendb, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 605, 160, 60));

        ok.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        ok.setForeground(new java.awt.Color(102, 204, 255));
        ok.setText("OK");
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });
        add(ok, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 360, -1, -1));

        suspect.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        suspect.setForeground(new java.awt.Color(102, 204, 255));
        suspect.setText("Suspected(Symptoms)");
        suspect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suspectActionPerformed(evt);
            }
        });
        add(suspect, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 480, -1, -1));

        missing.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        missing.setForeground(new java.awt.Color(102, 204, 255));
        missing.setText("Missing");
        missing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                missingActionPerformed(evt);
            }
        });
        add(missing, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 420, -1, -1));

        selectbtn.setBackground(new java.awt.Color(204, 204, 204));
        selectbtn.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        selectbtn.setForeground(new java.awt.Color(255, 153, 51));
        selectbtn.setText("select");
        selectbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectbtnActionPerformed(evt);
            }
        });
        add(selectbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 300, -1, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
//        int a=table.getSelectedRow();
//        if(a>=0){
//        request=(WorkRequest_investigationPolice) table.getValueAt(a, 0);
//          messaget.setText(request.getMessage());
//          System.out.println(request);
//        }
//        else{JOptionPane.showMessageDialog(null,"Select one row please");}
    }//GEN-LAST:event_tableMouseClicked

    private void searchbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbActionPerformed
        // TODO add your handling code here:
         int a=table.getSelectedRow();
         String address=null;
       
          
         if(a>-1){   
            address= (String) table.getValueAt(a, 2);
              StringSelection stringSelection = new StringSelection (address);
             Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
              clpbrd.setContents (stringSelection, null);
            try{
                String url="www.google.com/maps/";
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
            }catch(Exception e){
            }
         }
         else{JOptionPane.showMessageDialog(null, "Error");}
    }//GEN-LAST:event_searchbActionPerformed

    private void sendbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendbActionPerformed
        // TODO add your handling code here:
   
       int a=table.getSelectedRow();
       String re=null;
       ArrayList<String>detail=new ArrayList();
       request=(WorkRequest_investigationPolice)table.getValueAt(a, 0);
       if(ok.isSelected()){
           re="ok";
        
       }
       if(missing.isSelected()){
         re="Missing report";
       }
       if(suspect.isSelected()){
       re="Sent to hospital";
       }
       
       if(a>=0){  
           request=(WorkRequest_investigationPolice) table.getValueAt(a, 0);
           for(Network n:business.getNetworkList()){
               for(Enterprise en:n.getEnterpriseDirectory().getEnterpriseList()){
           
       for(Organization o:en.getOrganizationDirectory().getOrganizationList()){
           
         if(o instanceof InvestigationOrganization){
           for(WorkRequest_quarantineList req:o.getWorkQueue_quarantineList().getWorkRequestList()){
            for(QuarantineRelatedCase relate:req.getRelatedCase()){
                 if(relate.getQuarantinePeople().getId().equals(request.getQuarantinePeople().getId())){
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date date=new Date();          
                    detail= relate.getDetails();
                    detail.add((formatter.format(date))+":"+re);
                    detail.add((formatter.format(date))+":"+replyt.getText());
                    relate.setDetails(detail);
                    System.out.println(relate);
                    if(ok.isSelected()||suspect.isSelected()){
                     policeOrganization.getWorkQueue_investigationPolice().getWorkRequestList().remove(request);
                    popRelated();
                    }
                   
                 }
              
            }
         }
       }
        
       }}}
       messaget.setText("");replyt.setText("");
       }
       if(detail!=null){JOptionPane.showMessageDialog(null, "Sent to CDC");}else{JOptionPane.showMessageDialog(null, "Error");}
        
    }//GEN-LAST:event_sendbActionPerformed

    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_okActionPerformed

    private void suspectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suspectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_suspectActionPerformed

    private void missingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_missingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_missingActionPerformed

    private void selectbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectbtnActionPerformed
        // TODO add your handling code here:
      int a=table.getSelectedRow();
        if(a>=0){
            request=(WorkRequest_investigationPolice) table.getValueAt(a, 0);
            messaget.setText(request.getMessage());
            PoliceJFrame pf=new PoliceJFrame(request.getQuarantinePeople().getId(), request.getQuarantinePeople().getName(),request.getQuarantinePeople().getAddress(),request.getQuarantinePeople().getPhone(),request.getEnddate(),request.getQuarantinePeople().getPicture());
           pf.setVisible(true);
           
            
        }
        else{JOptionPane.showMessageDialog(null,"Select one row please");}
    }//GEN-LAST:event_selectbtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea messaget;
    private javax.swing.JRadioButton missing;
    private javax.swing.JRadioButton ok;
    private javax.swing.JTextArea replyt;
    private javax.swing.JButton searchb;
    private javax.swing.JButton selectbtn;
    private javax.swing.JButton sendb;
    private javax.swing.JRadioButton suspect;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
