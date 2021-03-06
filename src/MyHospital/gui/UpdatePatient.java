/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyHospital.gui;

import MyHospital.Dao.DoctorDao;
import MyHospital.Dao.PatientDao;
import MyHospital.pojo.PatientPojo;
import MyHospital.pojo.userprofile;
import com.sun.glass.events.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class UpdatePatient extends javax.swing.JFrame {
    private int refs;
     private String opd;
      private String f_name;
       private String s_name;
        private int age;
         private String p_id;
          private String gender;
           private String m_status;
            private String address;
             private String relationname;
              private String mo;
             private String BedNo;
            private String WardNo;
           private String doctor_id;
          private java.sql.Date date;
         private java.util.Date d;
        private ArrayList<String> doctor,wardNo,bed;
       private String active;
      private String tag;
     private String relation;
    private String SYW;
    /**
     * Creates new form AddPatientFrame
     */
    public UpdatePatient() {
        initComponents();
        this.setLocationRelativeTo(null);
        loadDoctorId();
    }

    private void loadDoctorId()
    {
        try {
            d= new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat();
        String src = sdf.format(d);
        txtDate.setText(src);
            doctor = DoctorDao.getDoctorId();
            for(String s:doctor)
            {
                jcDocId.addItem(s);
            }
            wardNo = PatientDao.getWardNo();
             
             wardNo.stream().forEach((s) -> {
                 jcWard.addItem(s);
           });
        }
            catch(SQLException e)
                    {
                        e.printStackTrace();
                    JOptionPane.showMessageDialog(null,"Some Problem Occured Please try Again Later!");
                    }
            
        }
      private  void accessDenied()
     {
         String name=userprofile.getUsername();
         boolean qname=true;
         if(qname==true) //name==null
         {
             JOptionPane.showMessageDialog(null,"Access Denied Please Login First!","Error",JOptionPane.ERROR_MESSAGE);
             Login frame = new Login();
             frame.setVisible(true);
             this.dispose();
         }   
     }
      private boolean validateInputs()
      {
          f_name = txtFname.getText();
          opd = txtOpd.getText();
          doctor_id =(String) jcDocId.getSelectedItem();
          s_name = txtSname.getText();
          relationname = txtSW.getText();
          age =Integer.parseInt( txtAge.getText());
          p_id = txtPid.getText();
          gender =(String) jcGender.getSelectedItem();
          m_status =(String) jcStatus.getSelectedItem();
          address = txtAddress.getText();
          mo = txtPhone.getText();
          WardNo = jcWard.getSelectedItem().toString();
          BedNo = jcBed.getSelectedItem().toString();
          date = new java.sql.Date(d.getTime());
          if(f_name.isEmpty()||opd.isEmpty()||doctor_id.isEmpty()||s_name.isEmpty()||age==0||p_id.isEmpty()||gender.isEmpty()||m_status.isEmpty()||address.isEmpty()||mo.isEmpty()||relationname.isEmpty())
              return false;
          return true;
              }
    public void clear()
    {
        txtFname.setText("");
          txtOpd.setText("");
          txtSname.setText("");
          txtAge.setText("");
          txtPid.setText("");
          txtAddress.setText("");
          txtPhone.setText("");
         // date = new java.sql.Date(d.getTime());
    }
      
      public String sendSms()
         {
             try
             {

                 
                 String apiKey = "apikey="+"gfx6ajtU9ag-HJG6yvpa4NafeSl8c2EU4UHhFaWtRb";
                 String message = "&message="+" Your OTP for Update is "+ refs+" And Patient Id:"+p_id+" from My Hospital APP";

                 String sender = "& sender=" + "TXTLCL";
                 String numbers = "&numbers=" + mo;

                 URL url=new URL("https://api.textlocal.in/send/?");
                 HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                String data = apiKey + numbers + message + sender;
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                OutputStream out= conn.getOutputStream();
                out.write(data.getBytes("UTF-8"));

                InputStreamReader isr = new InputStreamReader(conn.getInputStream());
                BufferedReader rd = new BufferedReader (isr);
                StringBuffer stringBuffer = new StringBuffer();
                
                String line;
                while((line = rd.readLine())!=null)
                {
                    stringBuffer.append(line);
                }
                rd.close();
                 System.out.println("in send sms"+stringBuffer);;
                 return stringBuffer.toString();
                 
                
             }
             catch(Exception e)
             {
                 System.out.println("Error SMS"+e);
                 return "Error"+0;
             }
         }
      public void updatePatientDetails()
      {
          int ans;
          int count=3;
          refs = 1000+(int)(Math.random()*28);
          String message = sendSms();
          if(message.contains("Invalid number"))
          {
              JOptionPane.showMessageDialog(null,"Please enter valid mobile number","Wrong",JOptionPane.ERROR_MESSAGE);
              return;
          }
          do
          {
              ans = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter one time Password","OTP",JOptionPane.INFORMATION_MESSAGE));
              if(refs==ans)
              {
                  PatientPojo p = new PatientPojo(p_id,f_name,s_name,age,BedNo,WardNo,opd,gender,m_status,date,address,mo,doctor_id,active,tag,relation,SYW);
                  try
                  {
                      boolean result = PatientDao.updatePatient(p);
                      if(result)
                      {
                          JOptionPane.showMessageDialog(null,"Success!","Patient Details successfully Updated",JOptionPane.INFORMATION_MESSAGE);
                          clear();
                          RecieptionistOptions r = new RecieptionistOptions();
                          r.setVisible(true);
                          this.dispose();
                          break;
                      }
                      else
                      {
                          JOptionPane.showMessageDialog(null,"Failed","Something went Wrong while update data",JOptionPane.ERROR_MESSAGE);
                      }
                  }
                  catch(SQLException e)
                  {
                      e.printStackTrace();
                      JOptionPane.showMessageDialog(null,"Something went Wrong Exception in DB","Error",JOptionPane.ERROR_MESSAGE);
                      
                  }
            
            
                  
              }
              else
              {
                  JOptionPane.showMessageDialog(null,"Please Enter Valid OTP","Error",JOptionPane.ERROR_MESSAGE);
                  count--;
              }
              
          }
          while(count!=0);
      }
      
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAddPatient1 = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jrMr = new javax.swing.JRadioButton();
        jcBed = new javax.swing.JComboBox();
        jrMrs = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtSW = new javax.swing.JTextField();
        jcGender = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jrFather = new javax.swing.JRadioButton();
        jcStatus = new javax.swing.JComboBox();
        jrHusband = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAddress = new javax.swing.JTextArea();
        txtPhone = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtFname = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPid = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtAge = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jcDocId = new javax.swing.JComboBox();
        txtOpd = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jcWard = new javax.swing.JComboBox();
        btnAddPatient2 = new javax.swing.JButton();
        btnHome1 = new javax.swing.JButton();
        btnBack1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 204, 255));
        setUndecorated(true);

        btnAddPatient1.setFont(new java.awt.Font("Sitka Text", 1, 30)); // NOI18N
        btnAddPatient1.setText("Add Patient");
        btnAddPatient1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnAddPatient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPatient1ActionPerformed(evt);
            }
        });

        btnHome.setFont(new java.awt.Font("Sitka Text", 1, 30)); // NOI18N
        btnHome.setText("Home");
        btnHome.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N
        jLabel7.setText("Bed No");

        jrMr.setFont(new java.awt.Font("Gabriola", 1, 18)); // NOI18N
        jrMr.setText("Mr");
        jrMr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrMrActionPerformed(evt);
            }
        });

        jcBed.setBackground(new java.awt.Color(0, 204, 204));
        jcBed.setFont(new java.awt.Font("Sitka Text", 1, 22)); // NOI18N
        jcBed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcBedActionPerformed(evt);
            }
        });

        jrMrs.setFont(new java.awt.Font("Gabriola", 1, 18)); // NOI18N
        jrMrs.setText("Mrs");

        jLabel8.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N
        jLabel8.setText("Second Name");

        jLabel13.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N
        jLabel13.setText("Name");

        jLabel9.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N
        jLabel9.setText("Gender");

        txtSW.setFont(new java.awt.Font("Sitka Text", 1, 22)); // NOI18N

        jcGender.setFont(new java.awt.Font("Sitka Text", 1, 22)); // NOI18N
        jcGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Male", "Female", "TransGender" }));

        jLabel15.setFont(new java.awt.Font("Gabriola", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 0, 0));
        jLabel15.setText("Please fields all the fields properly");

        jLabel10.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N
        jLabel10.setText("Marial Status");

        jrFather.setFont(new java.awt.Font("Gabriola", 1, 18)); // NOI18N
        jrFather.setText("Father");

        jcStatus.setFont(new java.awt.Font("Sitka Text", 1, 22)); // NOI18N
        jcStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Married", "UnMarried" }));

        jrHusband.setFont(new java.awt.Font("Gabriola", 1, 18)); // NOI18N
        jrHusband.setText("Husband");

        jLabel11.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N
        jLabel11.setText("Date");

        jLabel12.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N
        jLabel12.setText("Address");

        jLabel14.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N
        jLabel14.setText("Phone No");

        btnBack.setFont(new java.awt.Font("Sitka Text", 1, 30)); // NOI18N
        btnBack.setText("Back");
        btnBack.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        txtAddress.setColumns(20);
        txtAddress.setFont(new java.awt.Font("Sitka Small", 1, 20)); // NOI18N
        txtAddress.setRows(5);
        jScrollPane1.setViewportView(txtAddress);

        txtPhone.setFont(new java.awt.Font("Sitka Text", 1, 22)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N
        jLabel1.setText("OPD");

        txtFname.setFont(new java.awt.Font("Sitka Text", 1, 22)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N
        jLabel2.setText("Doctor Id");

        txtPid.setEditable(false);
        txtPid.setFont(new java.awt.Font("Sitka Text", 1, 22)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N
        jLabel3.setText("Patient Id");

        txtSname.setFont(new java.awt.Font("Sitka Text", 1, 22)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N
        jLabel4.setText("First Name");

        txtAge.setFont(new java.awt.Font("Sitka Text", 1, 22)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N
        jLabel5.setText("Age");

        txtDate.setFont(new java.awt.Font("Sitka Text", 1, 22)); // NOI18N

        jcDocId.setFont(new java.awt.Font("Sitka Text", 1, 22)); // NOI18N
        jcDocId.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));

        txtOpd.setFont(new java.awt.Font("Sitka Text", 1, 22)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N
        jLabel6.setText("Ward No");

        jcWard.setFont(new java.awt.Font("Sitka Text", 1, 22)); // NOI18N
        jcWard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcWardActionPerformed(evt);
            }
        });

        btnAddPatient2.setFont(new java.awt.Font("Sitka Text", 1, 30)); // NOI18N
        btnAddPatient2.setText("Update Patient");
        btnAddPatient2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnAddPatient2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPatient2ActionPerformed(evt);
            }
        });

        btnHome1.setFont(new java.awt.Font("Sitka Text", 1, 30)); // NOI18N
        btnHome1.setText("Home");
        btnHome1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnHome1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHome1ActionPerformed(evt);
            }
        });

        btnBack1.setFont(new java.awt.Font("Sitka Text", 1, 30)); // NOI18N
        btnBack1.setText("Back");
        btnBack1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        btnBack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel10))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(83, 83, 83)
                                    .addComponent(jLabel5))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(69, 69, 69)
                                    .addComponent(jLabel12)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtSname, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jcGender, 0, 200, Short.MAX_VALUE)
                                            .addComponent(jcStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtAge))
                                        .addComponent(txtSW, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtFname, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel15)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(25, 25, 25)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel8)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(48, 48, 48)
                                            .addComponent(jrMr)))
                                    .addGap(8, 8, 8)
                                    .addComponent(jrMrs)))
                            .addGap(155, 155, 155)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(jrFather))
                        .addGap(53, 53, 53)
                        .addComponent(jrHusband))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel9))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jLabel1))
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel2))
                        .addGap(97, 97, 97)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(txtPid, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(531, 531, 531))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtOpd)
                                    .addGap(531, 531, 531))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jcBed, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcWard, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jcDocId, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAddPatient2)
                                .addGap(18, 18, 18)
                                .addComponent(btnBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnHome1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnAddPatient1))
                        .addGap(18, 18, 18)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jrMrs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jrMr, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtFname, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPid, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSname, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtOpd, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrFather, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jrHusband, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSW, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jcWard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jcStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcBed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel2)
                    .addComponent(jcDocId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel11)
                        .addGap(46, 46, 46)
                        .addComponent(jLabel12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 185, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack1)
                    .addComponent(btnHome1)
                    .addComponent(btnAddPatient2))
                .addGap(101, 101, 101)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(btnHome)
                    .addComponent(btnAddPatient1))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddPatient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPatient1ActionPerformed
        // TODO add your handling code here:
        try {
            if(validateInputs()==false)
            {
                JOptionPane.showMessageDialog(null,"Error","Please fill all fields first",JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(age<0)
            {
                JOptionPane.showMessageDialog(null,"Error","Please input Valid Age",JOptionPane.ERROR_MESSAGE);
                return;
            }
            //       addPatientDetails();
            PatientPojo p = new PatientPojo(p_id,f_name,s_name,age,BedNo,WardNo,opd,gender,m_status,date,address,mo,doctor_id,active,tag,relation,SYW);
            try
            {
                boolean result = PatientDao.addPatient(p);
                if(result)
                {
                    JOptionPane.showMessageDialog(null,"Success!","Appointment book successfully",JOptionPane.INFORMATION_MESSAGE);
                    clear();
                    RecieptionistOptions r = new RecieptionistOptions();
                    r.setVisible(true);
                    this.dispose();
                    //   break;
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Failed","Something went Wrong while inserting data",JOptionPane.ERROR_MESSAGE);
                }
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null,"Something went Wrong Exception in DB","Error",JOptionPane.ERROR_MESSAGE);

            }

        }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null,"Error","please input Valid Age",JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnAddPatient1ActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        // TODO add your handling code here:
        RecieptionistOptions l= new RecieptionistOptions();
        l.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHomeActionPerformed

    private void jrMrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrMrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrMrActionPerformed

    private void jcBedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcBedActionPerformed

    }//GEN-LAST:event_jcBedActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        RecieptionistOptions p = new RecieptionistOptions();
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void jcWardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcWardActionPerformed
        String bedid=jcWard.getSelectedItem().toString();
        jcBed.removeAllItems();
        try {
            bed = PatientDao.getBedNo(bedid);
            for(String s:bed)
            {
                jcBed.addItem(s);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("Some error while loading bed!");
        }
    }//GEN-LAST:event_jcWardActionPerformed

    private void btnAddPatient2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPatient2ActionPerformed
        // TODO add your handling code here:
        try {
            if(validateInputs()==false)
            {
                JOptionPane.showMessageDialog(null,"Error","Please fill all fields first",JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(age<0)
            {
                JOptionPane.showMessageDialog(null,"Error","Please input Valid Age",JOptionPane.ERROR_MESSAGE);
                return;
            }
            //       addPatientDetails();
            PatientPojo p = new PatientPojo(p_id,f_name,s_name,age,BedNo,WardNo,opd,gender,m_status,date,address,mo,doctor_id,active,tag,relation,SYW);
            try
            {
                boolean result = PatientDao.addPatient(p);
                if(result)
                {
                    JOptionPane.showMessageDialog(null,"Success!","Appointment book successfully",JOptionPane.INFORMATION_MESSAGE);
                    clear();
                    RecieptionistOptions r = new RecieptionistOptions();
                    r.setVisible(true);
                    this.dispose();
                    //   break;
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Failed","Something went Wrong while inserting data",JOptionPane.ERROR_MESSAGE);
                }
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null,"Something went Wrong Exception in DB","Error",JOptionPane.ERROR_MESSAGE);

            }

        }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null,"Error","please input Valid Age",JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnAddPatient2ActionPerformed

    private void btnHome1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHome1ActionPerformed
        // TODO add your handling code here:
        RecieptionistOptions l= new RecieptionistOptions();
        l.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHome1ActionPerformed

    private void btnBack1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack1ActionPerformed
        // TODO add your handling code here:
        RecieptionistOptions p = new RecieptionistOptions();
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBack1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UpdatePatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdatePatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdatePatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdatePatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UpdatePatient f =new UpdatePatient();
                f.setVisible(true);
                f.accessDenied();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddPatient1;
    private javax.swing.JButton btnAddPatient2;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnBack1;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnHome1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox jcBed;
    private javax.swing.JComboBox jcDocId;
    private javax.swing.JComboBox jcGender;
    private javax.swing.JComboBox jcStatus;
    private javax.swing.JComboBox jcWard;
    private javax.swing.JRadioButton jrFather;
    private javax.swing.JRadioButton jrHusband;
    private javax.swing.JRadioButton jrMr;
    private javax.swing.JRadioButton jrMrs;
    private javax.swing.JTextArea txtAddress;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtFname;
    private javax.swing.JTextField txtOpd;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtPid;
    private javax.swing.JTextField txtSW;
    private javax.swing.JTextField txtSname;
    // End of variables declaration//GEN-END:variables
}
