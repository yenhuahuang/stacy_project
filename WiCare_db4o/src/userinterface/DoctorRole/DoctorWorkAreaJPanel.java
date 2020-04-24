/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.DoctorRole;




import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.DoctorOrganization;
import Business.Organization.Organization;
import Business.Organization.PharmacyOrganization;
import Business.Organization.QuarantineOrganization;
import Business.Organization.ReceptionOrganization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.LabTestWorkRequest;
import Business.WorkQueue.LabTest_doctorQuarantine;
import Business.WorkQueue.LabTest_documentDoctor;
import Business.WorkQueue.RegionData;
import Business.WorkQueue.WorkRequest;
import Business.WorkQueue.WorkRequest_doctorPharmacy;
import Business.WorkQueue.WorkRequest_doctorQuarantine;
import Business.WorkQueue.WorkRequest_documentDoctor;
import Business.WorkQueue.WorkRequest_quarantineCDC;
import MedicineCatelog.Medicine;
import MedicineCatelog.MedicineDetail;
import MedicineCatelog.MedicineDirectory;
import People.Diagnosis;
import People.People;
import java.awt.CardLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.PieChart.Data;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.eclipse.swt.widgets.DateTime;
import userinterface.AdministrativeRole.ManageOrganizationJPanel;
import userinterface.DocumentRole.SendPatientJPanel;

/**
 *
 * @author raunak
 */
public class DoctorWorkAreaJPanel extends javax.swing.JPanel {

    private JPanel jpanel;
    private DoctorOrganization organization;
    private Enterprise enterprise;
    private UserAccount userAccount;
    private EcoSystem system;
    private People thispeople;
    private ArrayList<String> medicineArr;
    private WorkRequest_documentDoctor thisrequest;
    private MedicineDirectory medicineDir;
    private ArrayList<String> othersymptoms;
    private ArrayList<String> symptoms;
    private ArrayList<String> finalSymptoms;
    private HashMap<String,Integer>setSymtomCount;
    private Map<Integer, Integer> setAgeCount;
    private RegionData regiondata;
    private Medicine thismedicine;
    private Diagnosis diagnosis;
    private Network network;
    private Date datesss;
    
   
    //private int quarantine_id=0;
    /**
     * Creates new form DoctorWorkAreaJPanel
     */
    public DoctorWorkAreaJPanel(JPanel jpanel, UserAccount userAccount, DoctorOrganization organization, Enterprise enterprise,EcoSystem system, Network network) {
       //Emily Edition
            initComponents();
            this.network=network;
            this.jpanel = jpanel;
            this.organization = organization;
            this.enterprise = enterprise;
            this.userAccount = userAccount;
            this.system=system;
            this.thispeople=null;
            this.thisrequest=null;
            this.medicineDir=enterprise.getMedicineDir();
            this.thismedicine=new Medicine();
            regiondata=new RegionData();
            Date date=new Date();
            System.out.println(date);
            symptoms=new ArrayList();
            valueLabel.setText(userAccount.getUsername());
            setSymtomCount=system.getCountSymtoms().getCountwithType();
            setAgeCount=system.getCountage().getCountwithAge();
            reportbtn.setEnabled(false);
            deathbtn.setEnabled(false);
            hospitalizedbtn.setEnabled(false);
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            Date date1 = sdf.parse("2012-10-4");
//            Date date2 = sdf.parse("2012-10-4");
            
           
           
            System.out.println(setSymtomCount);
            System.out.println(setAgeCount);
            setBtn(false);
            showother.setEditable(false);
            setField(false);
            setfieldtext("","","","");
            setCombo();
            populateRequestTable();
        
       
    }
    
    private void setBtn(boolean boo){
        finishbtn.setEnabled(boo);
        sendbtn.setEnabled(boo);
        addmedicinebtn.setEnabled(boo);
        addbtn.setEnabled(boo);
    }
    private void setField(boolean boo){
        namefield.setEditable(boo);
        notefield.setEditable(boo);
        agefield.setEditable(boo);
    }
    private void setfieldtext(String name,String age,String note,String pic){
        namefield.setText(name);
        agefield.setText(age);
        notefield.setText(note);
        ImageIcon im=new ImageIcon(pic);
        
    }
    private String convertArrayToString(ArrayList<String> arr){
        String toReturn="";
        if(arr.size()!=0){
            toReturn=arr.get(0);
            for(int i=1;i<arr.size(); i++){
                toReturn=toReturn+","+arr.get(i);
            }
        }else{
            toReturn="";
        }
        
        return toReturn;
    }
        
    public void setCombo(){
        medicineCatelogcombo.removeAllItems();
        for(Medicine m:medicineDir.getMedicines()){
            medicineCatelogcombo.addItem(m.getMedicineCatelog());
        }
    }
    public void populateRequestTable(){
        DefaultTableModel model = (DefaultTableModel) workRequestJTable.getModel();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        model.setRowCount(0);
        for (WorkRequest_documentDoctor request : organization.getWorkQueue_documentDoctor().getWorkRequestList()){
            Object[] row = new Object[7];
            row[0]=request;
            row[1]=request.getPeople().getGender();
            row[2]=request.getPeople().getAge();
            row[3]=request.getStatus();
            row[4]=formatter.format(request.getDate());
            row[5]=request.getAssignedDoctor()==null?"pending":request.getAssignedDoctor().getEmployee().getName();
            row[6]=((LabTest_documentDoctor)request).getMessage();
            
            model.addRow(row);
        }
    }

    
    public void popTestTable(People p){
        DefaultTableModel model = (DefaultTableModel) testTable.getModel();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        model.setRowCount(0);
        for(WorkRequest_doctorQuarantine quarantine:p.getQuarantineTesting().getWorkRequestList()){
            Object row[]=new Object[4];
            row[0]=quarantine.getTestType();
            row[1]=quarantine.getResult();           
            row[2]=formatter.format(quarantine.getDate());
            row[3]=quarantine.getStatus();
            model.addRow(row);
        }
    }
    private void popMedicineTable(){
        DefaultTableModel model = (DefaultTableModel) medicineTable.getModel();
        model.setRowCount(0);
        for (String str:medicineArr){
            Object[] row = new Object[2];
            row[0]=str.split(":")[0];
            row[1]=str.split(":")[1];
            model.addRow(row);
        }
    }
        
    private boolean checkDoctor(){
        boolean boo=true;
        for (WorkRequest_documentDoctor request : organization.getWorkQueue_documentDoctor().getWorkRequestList()){
            if(request.getAssignedDoctor()!=null){
                if(request.getAssignedDoctor().getEmployee().getName().equals(userAccount.getEmployee().getName())){
                    if(request.getStatus().equals("checking")){
                        boo=false;
                    }
                }
            }
        }
        return boo;
    }
    public void setSymptoms(){
        
        if(nocheck.isSelected()){
     //       int nocount=setCount.get("noSymptoms"); //Emily Edition
  //          setCount.put("noSymptoms", ++nocount);
            symptoms.add("no symptom");
            deathbtn.setEnabled(false);
        }
        if(coughcheck.isSelected()){
     //       int coughcount=setCount.get("cough");
     //       setCount.put("cough", ++coughcount);
            symptoms.add("cough");
             deathbtn.setEnabled(false);
        }
        if(sorethroatcheck.isSelected()){
     //       int sorecount=setCount.get("sore throat");
     //       setCount.put("sore throat", ++sorecount);
            symptoms.add("sore throat");
             deathbtn.setEnabled(false);
        }
        if(headachecheck.isSelected()){
    //        int headachecount=setCount.get("headache");
     //       setCount.put("headache", ++headachecount);
            symptoms.add("headache");
             deathbtn.setEnabled(false);
        }
        if(fevercheck.isSelected()){
    //        int fevercount=setCount.get("fever");
    //        setCount.put("fever", ++fevercount);
            symptoms.add("fever");
             deathbtn.setEnabled(false);
        }
        if(diarrheacheck.isSelected()){
     //       int diacount=setCount.get("diarrhea");
       //     setCount.put("diarrhea", ++diacount);
            symptoms.add("diarrhea");
             deathbtn.setEnabled(false);
        }
        if(nosecheck.isSelected()){
   //         int nosecount=setCount.get("running nose");
     //       setCount.put("running nose", ++nosecount);
            symptoms.add("running nose");
             deathbtn.setEnabled(false);
        }
        if(breathcheck.isSelected()){
    //        int breathcount=setCount.get("breath difficult");
      //      setCount.put("breath difficult", ++breathcount);
            symptoms.add("breath difficult");
             deathbtn.setEnabled(false);
        }
        
    }
    public void setSymptomsField(boolean boo){
        showother.setText("");
        nocheck.setSelected(boo);
        nosecheck.setSelected(boo);
        diarrheacheck.setSelected(boo);
        fevercheck.setSelected(boo);
        headachecheck.setSelected(boo);
        sorethroatcheck.setSelected(boo);
        coughcheck.setSelected(boo);
        nocheck.setSelected(boo);
        breathcheck.setSelected(boo);
    }
    
    public void deathReporttoDocu(){
        setBtn(false);
        setField(false);
        Diagnosis diagnosis=new Diagnosis();
        thisrequest.getPeople().getMedicalRecord().getDoctorNote();
        Date date = new Date();
        thisrequest.setStatus("finish");
        thisrequest.setDate(date);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String d=formatter.format(date);
        diagnosis.setDate(d);
        diagnosis.setDiagnosis("Death  "+"Note:"+diagnosisArea.getText());
        diagnosis.setMedicine(null);
        diagnosis.setSymptoms(null);
        thispeople.getMedicalRecord().getDoctorNote().add(diagnosis);
        populateRequestTable();
        completebtn.setEnabled(false);
        datesss=date;
        hospitalizedbtn.setEnabled(false);
        reportbtn.setEnabled(true);
        if(setSymtomCount.containsKey("death".toLowerCase())){
          int b=setSymtomCount.get("death".toLowerCase())+1;
         setSymtomCount.put("death".toLowerCase(), b);
        }
        else{setSymtomCount.put("death".toLowerCase(), 1);}
    }
    
    public void deathAge(People p){
        int a=p.getAge();
        switch (Math.round(a/10)) {
    case 0:
        int b=setAgeCount.get(0);
        setAgeCount.put(0,++b);
        break;
    case 1:
         int c=setAgeCount.get(10);
        setAgeCount.put(10,++c);
    case 2:
         int d=setAgeCount.get(20);
        setAgeCount.put(20,++d);
        break;
    case 3:
         int e=setAgeCount.get(30);
        setAgeCount.put(30,++e);
        break;
   case 4:
         int f=setAgeCount.get(40);
        setAgeCount.put(40,++f);
        break;
   case 5:
        int g=setAgeCount.get(50);
        setAgeCount.put(50,++g);
       break;
   case 6:
        int h=setAgeCount.get(60);
        setAgeCount.put(60,++h);
       break;
   case 7:
       int i=setAgeCount.get(70);
        setAgeCount.put(70,++i);
       break;
   case 8:
       int j=setAgeCount.get(80);
        setAgeCount.put(80,++j);
       break;
   case 9:
        int k=setAgeCount.get(90);
        setAgeCount.put(90,++k);
       break;
     
}
    }
//    public void populateRegionData(){
//       regiondata.setState(network.toString());
//       SimpleDateFormat formatte = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date date1 = formatte.parse("2020-4-18");
//            regiondata.setDate(date1);
//            
//        } catch (ParseException ex) {
//            Logger.getLogger(DoctorWorkAreaJPanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//           // Date date2 = sdf.parse("2012-10-4");
//          
//     
//      for(Enterprise en:network.getEnterpriseDirectory().getEnterpriseList()){
//          for(Organization o:en.getOrganizationDirectory().getOrganizationList()){
//              if(o instanceof ReceptionOrganization){
//                  o.getRegiondataSet().getRegionDataSet().add(regiondata);
//                  
//              }
//    }
//      }
//    }
//    public void HOSReporttoDocu(){
//        
//        Diagnosis diagnosis=new Diagnosis();
//        thisrequest.getPeople().getMedicalRecord().getDoctorNote();
//        Date date = new Date();
//        thisrequest.setStatus("finish");
//        thisrequest.setDate(date);
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        String d=formatter.format(date);
//        diagnosis.setDate(d);
//        diagnosis.setDiagnosis(diagnosisArea.getText());
//        diagnosis.setMedicine(medicineArr);
//        diagnosis.setSymptoms(finalSymptoms);
//        thispeople.getMedicalRecord().getDoctorNote().add(diagnosis);
//        populateRequestTable();
//        completebtn.setEnabled(false);
//        datesss=date;
//    }
    
    
    
    
    
  // }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        workRequestJTable = new javax.swing.JTable();
        refreshTestJButton = new javax.swing.JButton();
        enterpriseLabel = new javax.swing.JLabel();
        valueLabel = new javax.swing.JLabel();
        assignbtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        diagnosisArea = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        medicineTable = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        amountfield = new javax.swing.JTextField();
        addmedicinebtn = new javax.swing.JButton();
        finishbtn = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        testTable = new javax.swing.JTable();
        sendbtn = new javax.swing.JButton();
        testingfield = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        patientbtn = new javax.swing.JButton();
        completebtn = new javax.swing.JButton();
        medicineCatelogcombo = new javax.swing.JComboBox();
        medicinescombo = new javax.swing.JComboBox();
        namefield = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        agefield = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        notefield = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        coughcheck = new javax.swing.JCheckBox();
        headachecheck = new javax.swing.JCheckBox();
        fevercheck = new javax.swing.JCheckBox();
        diarrheacheck = new javax.swing.JCheckBox();
        sorethroatcheck = new javax.swing.JCheckBox();
        nosecheck = new javax.swing.JCheckBox();
        nocheck = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        otherfield = new javax.swing.JTextField();
        addbtn = new javax.swing.JButton();
        showother = new javax.swing.JTextField();
        breathcheck = new javax.swing.JCheckBox();
        reportbtn = new javax.swing.JButton();
        deathbtn = new javax.swing.JButton();
        hospitalizedbtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 102, 153));
        setForeground(new java.awt.Color(0, 102, 153));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        workRequestJTable.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        workRequestJTable.setForeground(new java.awt.Color(0, 51, 102));
        workRequestJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "patient", "gender", "age", "status", "date", "doctor", "note"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        workRequestJTable.setGridColor(new java.awt.Color(0, 102, 153));
        workRequestJTable.setSelectionBackground(new java.awt.Color(204, 204, 204));
        workRequestJTable.setSelectionForeground(new java.awt.Color(0, 51, 102));
        jScrollPane1.setViewportView(workRequestJTable);
        if (workRequestJTable.getColumnModel().getColumnCount() > 0) {
            workRequestJTable.getColumnModel().getColumn(0).setResizable(false);
            workRequestJTable.getColumnModel().getColumn(1).setResizable(false);
            workRequestJTable.getColumnModel().getColumn(2).setResizable(false);
            workRequestJTable.getColumnModel().getColumn(3).setResizable(false);
            workRequestJTable.getColumnModel().getColumn(4).setResizable(false);
            workRequestJTable.getColumnModel().getColumn(5).setResizable(false);
            workRequestJTable.getColumnModel().getColumn(6).setResizable(false);
        }

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 1210, 144));

        refreshTestJButton.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        refreshTestJButton.setForeground(new java.awt.Color(255, 153, 51));
        refreshTestJButton.setText("Refresh");
        refreshTestJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshTestJButtonActionPerformed(evt);
            }
        });
        add(refreshTestJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 10, -1, -1));

        enterpriseLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        enterpriseLabel.setForeground(new java.awt.Color(0, 153, 204));
        enterpriseLabel.setText("Doctor");
        add(enterpriseLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 73, 30));

        valueLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        valueLabel.setForeground(new java.awt.Color(0, 153, 204));
        valueLabel.setText("<value>");
        add(valueLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 158, 26));

        assignbtn.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        assignbtn.setForeground(new java.awt.Color(255, 153, 51));
        assignbtn.setText("assign to me");
        assignbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assignbtnActionPerformed(evt);
            }
        });
        add(assignbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        diagnosisArea.setColumns(20);
        diagnosisArea.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        diagnosisArea.setForeground(new java.awt.Color(0, 153, 204));
        diagnosisArea.setRows(5);
        jScrollPane2.setViewportView(diagnosisArea);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 390, -1, 104));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 204));
        jLabel7.setText("diagnosis");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 390, -1, 20));

        medicineTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "type", "amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(medicineTable);
        if (medicineTable.getColumnModel().getColumnCount() > 0) {
            medicineTable.getColumnModel().getColumn(0).setResizable(false);
            medicineTable.getColumnModel().getColumn(1).setResizable(false);
        }

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 510, 500, 140));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 153, 204));
        jLabel8.setText("type");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 660, -1, 20));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 153, 204));
        jLabel10.setText("amount");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 660, -1, 20));

        amountfield.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        amountfield.setForeground(new java.awt.Color(0, 153, 204));
        add(amountfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 680, 170, 30));

        addmedicinebtn.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        addmedicinebtn.setForeground(new java.awt.Color(255, 153, 51));
        addmedicinebtn.setText("add");
        addmedicinebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addmedicinebtnActionPerformed(evt);
            }
        });
        add(addmedicinebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 680, 70, 30));

        finishbtn.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        finishbtn.setForeground(new java.awt.Color(255, 153, 51));
        finishbtn.setText("finish and send to pharmacy");
        finishbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishbtnActionPerformed(evt);
            }
        });
        add(finishbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 730, 310, 40));

        testTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "quarantine type", "result", "date", "status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(testTable);
        if (testTable.getColumnModel().getColumnCount() > 0) {
            testTable.getColumnModel().getColumn(0).setResizable(false);
            testTable.getColumnModel().getColumn(1).setResizable(false);
            testTable.getColumnModel().getColumn(2).setResizable(false);
            testTable.getColumnModel().getColumn(3).setResizable(false);
        }

        add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 510, 530, 140));

        sendbtn.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        sendbtn.setForeground(new java.awt.Color(255, 153, 51));
        sendbtn.setText("send to quarantine organization");
        sendbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendbtnActionPerformed(evt);
            }
        });
        add(sendbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 730, 310, -1));

        testingfield.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        testingfield.setForeground(new java.awt.Color(0, 153, 204));
        add(testingfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 660, 207, -1));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 153, 204));
        jLabel9.setText("testing type");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 660, -1, 20));

        patientbtn.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        patientbtn.setForeground(new java.awt.Color(255, 153, 51));
        patientbtn.setText("patient detail");
        patientbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientbtnActionPerformed(evt);
            }
        });
        add(patientbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 150, -1));

        completebtn.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        completebtn.setForeground(new java.awt.Color(255, 153, 51));
        completebtn.setText("complete");
        completebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                completebtnActionPerformed(evt);
            }
        });
        add(completebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 277, 150, -1));

        medicineCatelogcombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        medicineCatelogcombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medicineCatelogcomboActionPerformed(evt);
            }
        });
        add(medicineCatelogcombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 660, 160, -1));

        medicinescombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        medicinescombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medicinescomboActionPerformed(evt);
            }
        });
        add(medicinescombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 690, 160, -1));

        namefield.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        namefield.setForeground(new java.awt.Color(0, 153, 204));
        add(namefield, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 259, 210, -1));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 204));
        jLabel1.setText("name");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(284, 260, 70, 30));

        agefield.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        agefield.setForeground(new java.awt.Color(0, 153, 204));
        add(agefield, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 299, 210, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 204));
        jLabel2.setText("age");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(304, 300, -1, 30));

        notefield.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        notefield.setForeground(new java.awt.Color(0, 153, 204));
        add(notefield, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 340, 210, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 204));
        jLabel3.setText("note");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 340, 70, 30));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 204));
        jLabel4.setText("symptoms");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 220, -1, -1));

        coughcheck.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        coughcheck.setForeground(new java.awt.Color(0, 153, 204));
        coughcheck.setText("Cough");
        coughcheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coughcheckActionPerformed(evt);
            }
        });
        add(coughcheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 260, -1, -1));

        headachecheck.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        headachecheck.setForeground(new java.awt.Color(0, 153, 204));
        headachecheck.setText("Headache");
        headachecheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                headachecheckActionPerformed(evt);
            }
        });
        add(headachecheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 300, -1, -1));

        fevercheck.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        fevercheck.setForeground(new java.awt.Color(0, 153, 204));
        fevercheck.setText("Fever");
        add(fevercheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 300, -1, -1));

        diarrheacheck.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        diarrheacheck.setForeground(new java.awt.Color(0, 153, 204));
        diarrheacheck.setText("Diarrhea");
        add(diarrheacheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 300, -1, -1));

        sorethroatcheck.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        sorethroatcheck.setForeground(new java.awt.Color(0, 153, 204));
        sorethroatcheck.setText("sore throat");
        add(sorethroatcheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 260, -1, -1));

        nosecheck.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        nosecheck.setForeground(new java.awt.Color(0, 153, 204));
        nosecheck.setText("Running Nose");
        add(nosecheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 260, -1, -1));

        nocheck.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        nocheck.setForeground(new java.awt.Color(0, 153, 204));
        nocheck.setText("no symptom");
        nocheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nocheckActionPerformed(evt);
            }
        });
        add(nocheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 300, -1, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 204));
        jLabel5.setText("others:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 340, -1, -1));

        otherfield.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        otherfield.setForeground(new java.awt.Color(0, 153, 204));
        add(otherfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 340, 160, -1));

        addbtn.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        addbtn.setForeground(new java.awt.Color(255, 153, 51));
        addbtn.setText("add");
        addbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbtnActionPerformed(evt);
            }
        });
        add(addbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 340, -1, -1));

        showother.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        showother.setForeground(new java.awt.Color(0, 153, 204));
        add(showother, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 340, 270, -1));

        breathcheck.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        breathcheck.setForeground(new java.awt.Color(0, 153, 204));
        breathcheck.setText("Breath Difficult");
        add(breathcheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 260, -1, -1));

        reportbtn.setBackground(new java.awt.Color(204, 204, 204));
        reportbtn.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        reportbtn.setForeground(new java.awt.Color(0, 204, 204));
        reportbtn.setText("Death Report to CDC");
        reportbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportbtnActionPerformed(evt);
            }
        });
        add(reportbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 390, 290, 30));

        deathbtn.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        deathbtn.setForeground(new java.awt.Color(255, 153, 51));
        deathbtn.setText("Death Announce");
        deathbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deathbtnActionPerformed(evt);
            }
        });
        add(deathbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 383, 190, -1));

        hospitalizedbtn.setBackground(new java.awt.Color(204, 204, 204));
        hospitalizedbtn.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        hospitalizedbtn.setForeground(new java.awt.Color(0, 204, 204));
        hospitalizedbtn.setText("Hospitalized Report to CDC");
        hospitalizedbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hospitalizedbtnActionPerformed(evt);
            }
        });
        add(hospitalizedbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 430, 290, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void refreshTestJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshTestJButtonActionPerformed

        populateRequestTable();
        
    }//GEN-LAST:event_refreshTestJButtonActionPerformed

    private void assignbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assignbtnActionPerformed
        // TODO add your handling code here:
        if(namefield.getText()==null){
          deathbtn.setEnabled(false);
          reportbtn.setEnabled(false);
        }
      if(checkDoctor()==false){
          JOptionPane.showMessageDialog(null, "you have one patient ot hand, please complete it");
          return;
      }
        int select=workRequestJTable.getSelectedRow();
        if(select<0){
            JOptionPane.showMessageDialog(null, "please select");
            return;
        }else{
            WorkRequest_documentDoctor request=(WorkRequest_documentDoctor) workRequestJTable.getValueAt(select, 0);
            if(request.getAssignedDoctor()==null){
                this.medicineArr=new ArrayList();
                this.othersymptoms=new ArrayList();
                finalSymptoms=new ArrayList();
                setBtn(true);
                request.setAssignedDoctor(userAccount);
                request.setStatus("checking");
                thispeople=request.getPeople();
                thisrequest=request;
                //populatePatientMedicalRecord(thispeople,thisrequest);
                setfieldtext(thispeople.getName(),String.valueOf(thispeople.getAge()),((LabTest_documentDoctor)thisrequest).getMessage(),thispeople.getPicture());
                populateRequestTable();
                popMedicineTable();
                popTestTable(thispeople);
                diagnosisArea.setText("");
                deathbtn.setEnabled(true);
                hospitalizedbtn.setEnabled(true);
            }else{
                JOptionPane.showMessageDialog(null, "not allowed to take this patient");
                return;
            }
                    
        }
        if(namefield.getText()==null){
           deathbtn.setEnabled(false);
           reportbtn.setEnabled(false);
        }
    
    }//GEN-LAST:event_assignbtnActionPerformed

    private void addmedicinebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addmedicinebtnActionPerformed
        // TODO add your handling code here:
        String addMedicine="";
        String type=(String)medicinescombo.getSelectedItem();
        String amount=amountfield.getText();
        if(type.equals("")||amount.equals("")){
            JOptionPane.showMessageDialog(null, "type or amount cannot be null");
            return;
        }
        
        addMedicine=type+":"+amount;
        medicineArr.add(addMedicine);
        popMedicineTable();
        //typefield.setText("");
        amountfield.setText("");
        
    }//GEN-LAST:event_addmedicinebtnActionPerformed

    private void finishbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishbtnActionPerformed
        // TODO add your handling code here:
        Date date = new Date();
        Diagnosis diagnosis=new Diagnosis();
        //finalSymptoms=new ArrayList();
        ArrayList<String>medicinetoAdd=new ArrayList();
        thisrequest.setStatus("finish");
        thisrequest.setDate(date);
        //add diagnosis, medicine record
        String doctorNote=diagnosisArea.getText();
        //String medicine="";
        if(medicineArr.size()!=0){
            for(String str:medicineArr){
                medicinetoAdd.add(str);
            }
        }else{
            JOptionPane.showMessageDialog(null, "please give medicine");
            return;
        }
        if(doctorNote.equals("")){
            JOptionPane.showMessageDialog(null, "please enter your diagnosis");
            return;
        }
        if(finalSymptoms.isEmpty()){
            setSymptoms(); 
            if(!othersymptoms.isEmpty()){
                finalSymptoms.addAll(othersymptoms);
            }
            if(!symptoms.isEmpty()){
                finalSymptoms.addAll(symptoms);
            }
        }
        System.out.println("in finish btn:"+finalSymptoms);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String d=formatter.format(date);
        diagnosis.setDate(d);
        diagnosis.setDiagnosis(doctorNote);
        diagnosis.setMedicine(medicinetoAdd);
        diagnosis.setSymptoms(finalSymptoms);
        thispeople.getMedicalRecord().getDoctorNote().add(diagnosis);
        populateRequestTable();
        
        WorkRequest_doctorPharmacy docPharmacy=new WorkRequest_doctorPharmacy();
        docPharmacy.setDoctor(userAccount);
        docPharmacy.setSendDate(date);
        docPharmacy.setMedicineArr(medicineArr);
        docPharmacy.setPatient(thispeople);
        docPharmacy.setStatus("pending");
        docPharmacy.setDiagnosis(doctorNote);
        PharmacyOrganization org=null;
        for(Organization o:enterprise.getOrganizationDirectory().getOrganizationList()){
            if(o instanceof PharmacyOrganization){
                org=(PharmacyOrganization)o;
                break;
            }
        }
        org.getWorkQueue_doctorPharmacy().getWorkRequestList().add(docPharmacy);
        
        medicineArr=new ArrayList();
        othersymptoms= new ArrayList();
        popMedicineTable();
        diagnosisArea.setText("");
        setfieldtext("","","","");
        setSymptomsField(false);
        setBtn(false);
        System.out.println("count:"+system.getCountSymtoms().getCountwithType());
    }//GEN-LAST:event_finishbtnActionPerformed

    private void sendbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendbtnActionPerformed
        // TODO add your handling code here:
        //send to Quarantine or;
//        this.quarantine_id++;
        //finalSymptoms=new ArrayList();
        Date date=new Date();
        String type=testingfield.getText();
        if(type.equals("")){
            JOptionPane.showMessageDialog(null, "please enter testing type");
            return;
        }else{
            
            WorkRequest_doctorQuarantine toQuarantine =new WorkRequest_doctorQuarantine();
            String diagnosis=diagnosisArea.getText();
            if(diagnosis.equals("")){
                JOptionPane.showMessageDialog(null, "please enter your diagnosis");
                return;
            }
            setSymptoms();
            System.out.println("sym in sendbtn:"+symptoms);
            if(symptoms.isEmpty()&&othersymptoms.isEmpty()){
                JOptionPane.showMessageDialog(null, "please choose symptoms");
                return;
            }
            if(!othersymptoms.isEmpty()){
                finalSymptoms.addAll(othersymptoms);
            }
            if(!symptoms.isEmpty()){
                finalSymptoms.addAll(symptoms);
            }
            System.out.println("in sendbtn"+finalSymptoms);
           
            toQuarantine.setDiagnosis(diagnosis);
            toQuarantine.setSymptoms(finalSymptoms);
            toQuarantine.setDate(date);
            toQuarantine.setDoctor(userAccount);
            toQuarantine.setStatus("pending");
            toQuarantine.setTestType(type);
            toQuarantine.setPatient(thispeople);
            int c=system.getQuarantineCount()+1;
            system.setQuarantineCount(c);
            toQuarantine.setId(c);
            QuarantineOrganization org=null;
            for(Organization o:enterprise.getOrganizationDirectory().getOrganizationList()){
                if(o instanceof QuarantineOrganization){
                    org=(QuarantineOrganization) o;
                    break;
                }
            }
            org.getWorkQueue_doctorQuarantine().getWorkRequestList().add(toQuarantine);
            thispeople.getQuarantineTesting().getWorkRequestList().add(toQuarantine);
            testingfield.setText("");
            popTestTable(thispeople);
            JOptionPane.showMessageDialog(null, "success");
        }
        
    }//GEN-LAST:event_sendbtnActionPerformed

    private void patientbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientbtnActionPerformed
        // TODO add your handling code here:
        int select=workRequestJTable.getSelectedRow();
        if(select<0){
            JOptionPane.showMessageDialog(null, "please select");
            return;
        }else{
            WorkRequest_documentDoctor request=(WorkRequest_documentDoctor) workRequestJTable.getValueAt(select, 0);
            thispeople=request.getPeople();
            jpanel.add("about patient JPanel", new AboutPatientJPanel(jpanel,thispeople));
            System.out.println(thispeople.getMedicalRecord().getDoctorNote());
            CardLayout layout = (CardLayout) jpanel.getLayout();
            layout.next(jpanel);
        }
    }//GEN-LAST:event_patientbtnActionPerformed

    private void completebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_completebtnActionPerformed
        // TODO add your handling code here:
        int select=workRequestJTable.getSelectedRow();
        if(select<0){
            JOptionPane.showMessageDialog(null, "please select");
            return;
        }else{
            WorkRequest_documentDoctor request=(WorkRequest_documentDoctor) workRequestJTable.getValueAt(select, 0);
            if((request.getStatus().equals("pending"))){
                JOptionPane.showMessageDialog(null, "you need to assign to you first");
                return;
            }
            else if((request.getAssignedDoctor().equals(userAccount)&&request.getStatus().equals("checking"))){
                this.medicineArr=new ArrayList();
                this.othersymptoms=new ArrayList();
                finalSymptoms=new ArrayList();
                setBtn(true);
                hospitalizedbtn.setEnabled(true);
                deathbtn.setEnabled(true);
                request.setAssignedDoctor(userAccount);
                request.setStatus("checking");
                thispeople=request.getPeople();
                thisrequest=request;
                //populateRequestTable();
                setfieldtext(thispeople.getName(),String.valueOf(thispeople.getAge()),((LabTest_documentDoctor)thisrequest).getMessage(),thispeople.getPicture());
                populateRequestTable();
                popMedicineTable();
                popTestTable(thispeople);
                diagnosisArea.setText("");
            }
        }
        if(namefield.getText()==null){
           deathbtn.setEnabled(false);
           reportbtn.setEnabled(false);
        }
    }//GEN-LAST:event_completebtnActionPerformed

    private void medicineCatelogcomboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medicineCatelogcomboActionPerformed
        // TODO add your handling code here:
        String medicine=(String)medicineCatelogcombo.getSelectedItem();
        for(Medicine m:medicineDir.getMedicines()){
            if(m.getMedicineCatelog().equals(medicine)){
                thismedicine=m;
                break;
            }
        }
        setSecondcombo(thismedicine);
    }//GEN-LAST:event_medicineCatelogcomboActionPerformed

    public void setSecondcombo(Medicine m){
        medicinescombo.removeAllItems();
        for(MedicineDetail medicineDetail:m.getMedicinearr()){
            medicinescombo.addItem(medicineDetail.getName());
        }
    }
    private void medicinescomboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medicinescomboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_medicinescomboActionPerformed

    private void coughcheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coughcheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_coughcheckActionPerformed

    private void headachecheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_headachecheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_headachecheckActionPerformed

    private void addbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbtnActionPerformed
        // TODO add your handling code here:
        String other=otherfield.getText().toLowerCase();
        if(other.equals("")){
            JOptionPane.showMessageDialog(null, "please enter other field to add");
            return;
        }else{
            int c=setSymtomCount.getOrDefault(other, 0)+1;
            othersymptoms.add(other);
            setSymtomCount.put(other,c);
            System.out.println("put:"+other+","+c);
            otherField(othersymptoms);
            otherfield.setText("");
            deathbtn.setEnabled(false);
        }
        
    }//GEN-LAST:event_addbtnActionPerformed

    private void nocheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nocheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nocheckActionPerformed

    private void deathbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deathbtnActionPerformed
        // TODO add your handling code here:
        if(!diagnosisArea.getText().equals("")){
        deathReporttoDocu();
        deathbtn.setEnabled(false);}else{JOptionPane.showMessageDialog(null, "Must write the diagnosis");}
    }//GEN-LAST:event_deathbtnActionPerformed

    private void reportbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportbtnActionPerformed
        // TODO add your handling code here:
        String diagnos;
        String test=null;
        WorkRequest_quarantineCDC req=null;
        
        for(Network nt:system.getNetworkList()){
         for(Enterprise et:nt.getEnterpriseDirectory().getEnterpriseList()){
             for(Organization og:et.getOrganizationDirectory().getOrganizationList()){
               if(og instanceof ReceptionOrganization){
                   for(WorkRequest_quarantineCDC request:og.getWorkQueue_quarantineCDC().getWorkRequestList()){
                   if(request.getPatient().getId().equals(thispeople.getId())){
                       req=request;
                   }
               
               }
             }
         }
        
        }}
        if(req!=null){
         diagnos=req.getSymptons()+("\n death").toUpperCase();
               req.setSymptons(diagnos);
                        JOptionPane.showMessageDialog(null, "Send to CDC already");
                        reportbtn.setEnabled(false);
                        deathAge(req.getPatient()); 
                       
                        SimpleDateFormat formatte = new SimpleDateFormat("yyyy-MM-dd");
                        String e=formatte.format(datesss);
                        
                        
        }
        else{
        JOptionPane.showMessageDialog(null, "No record found, contact Quaratine");
        
        
        }
    }//GEN-LAST:event_reportbtnActionPerformed

    private void hospitalizedbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hospitalizedbtnActionPerformed
        // TODO add your handling code here:
          String diagnos;
        String test=null;
        WorkRequest_quarantineCDC req=null;
        
        for(Network nt:system.getNetworkList()){
         for(Enterprise et:nt.getEnterpriseDirectory().getEnterpriseList()){
             for(Organization og:et.getOrganizationDirectory().getOrganizationList()){
               if(og instanceof ReceptionOrganization){
                   for(WorkRequest_quarantineCDC request:og.getWorkQueue_quarantineCDC().getWorkRequestList()){
                   if(request.getPatient().getId().equals(thispeople.getId())){
                       req=request;
                   }
               
               }
             }
         }
        
        }}
        if(req!=null){
         diagnos=req.getSymptons()+("\n hospitalized").toUpperCase();
               req.setSymptons(diagnos);
                        JOptionPane.showMessageDialog(null, "Send to CDC already");
                        reportbtn.setEnabled(false);    
                        deathbtn.setEnabled(false);
                        hospitalizedbtn.setEnabled(false);
                        symptoms.add("Hospitalized");
        }
        else{
        JOptionPane.showMessageDialog(null, "No record found, contact Quaratine");
        }
    }//GEN-LAST:event_hospitalizedbtnActionPerformed
    private void otherField(ArrayList<String> arr){
        String show="";
        for(String str:arr){
            show+=(str+",") ;
        }
        showother.setText(show);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addbtn;
    private javax.swing.JButton addmedicinebtn;
    private javax.swing.JTextField agefield;
    private javax.swing.JTextField amountfield;
    private javax.swing.JButton assignbtn;
    private javax.swing.JCheckBox breathcheck;
    private javax.swing.JButton completebtn;
    private javax.swing.JCheckBox coughcheck;
    private javax.swing.JButton deathbtn;
    private javax.swing.JTextArea diagnosisArea;
    private javax.swing.JCheckBox diarrheacheck;
    private javax.swing.JLabel enterpriseLabel;
    private javax.swing.JCheckBox fevercheck;
    private javax.swing.JButton finishbtn;
    private javax.swing.JCheckBox headachecheck;
    private javax.swing.JButton hospitalizedbtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JComboBox<String> medicineCatelogcombo;
    private javax.swing.JTable medicineTable;
    private javax.swing.JComboBox<String> medicinescombo;
    private javax.swing.JTextField namefield;
    private javax.swing.JCheckBox nocheck;
    private javax.swing.JCheckBox nosecheck;
    private javax.swing.JTextField notefield;
    private javax.swing.JTextField otherfield;
    private javax.swing.JButton patientbtn;
    private javax.swing.JButton refreshTestJButton;
    private javax.swing.JButton reportbtn;
    private javax.swing.JButton sendbtn;
    private javax.swing.JTextField showother;
    private javax.swing.JCheckBox sorethroatcheck;
    private javax.swing.JTable testTable;
    private javax.swing.JTextField testingfield;
    private javax.swing.JLabel valueLabel;
    private javax.swing.JTable workRequestJTable;
    // End of variables declaration//GEN-END:variables
}
