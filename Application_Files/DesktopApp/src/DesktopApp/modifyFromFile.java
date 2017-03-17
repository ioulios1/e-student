package DesktopApp;


import static java.net.URLEncoder.encode;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
//import org.gmele.general.sheets.XlsxSheet;
//import org.gmele.general.sheets.exception.SheetExc;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Η κλάση modifyFromFile παρέχει το βασικό GUI και τις απαρραίτητες λειτουργίες
 * ωστε να γίνεται η μορφοποίηση των βαθμών κατευθείαν μέσω αρχείου xl.
 * @author Ioulios Tsiko: cs131027
 * @author Theofilos Axiotis: cs131011
 */
public class modifyFromFile extends javax.swing.JFrame {

    adminMain adm;
    public modifyFromFile(adminMain obj) {
        adm=obj;
        //init();
        initComponents();
        setResizable(false);
    }
    
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fc = new javax.swing.JFileChooser();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Όνομα αρχείου:  ");

        jButton1.setText("Τροποποίηση");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Πίσω");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Αναζήτηση");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setText("Μάθημα");

        initComboBox();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 131, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jButton3)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jLabel3.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        
        
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       /*try {
            XlsxSheet xl=new XlsxSheet(jTextField1.getText());
            
            JsonBuilderFactory factory=Json.createBuilderFactory(null);
            JsonObjectBuilder jsonB = factory.createObjectBuilder()
                            .addNull("Initializer");
            JsonArrayBuilder jsonA = factory.createArrayBuilder();

            JsonObject myJson;
        
            //αποθήκευση των τιμών του αρχείου σε json
            for (int i=0;i<=xl.GetLastRow ();i++){
               try{
                     jsonA= jsonA.add(factory.createObjectBuilder()
                                        .add("am", (int) xl.GetCellNumeric(i, 0))
                                        .add("grade", xl.GetCellNumeric(i, 1)));
                     System.out.println("am : "+xl.GetCellNumeric(i, 0)+" grade : "+ xl.GetCellNumeric(i, 1));
               }catch(Exception e)
               { 
                   //προσπερνάει τις κενές γραμμές
               }   
            }

            String lesson=(String) jComboBox1.getSelectedItem();
            jsonB.add("lessonID",lesson.substring(0,1));
            jsonB= jsonB.add("Grades", jsonA);

            myJson=jsonB.build();
            System.out.println(myJson.toString());
                
            //κληση του αντιστοιχου web service
            String BASE_URI = "http://localhost:8080/WebServices/webresources";
            Client client=javax.ws.rs.client.ClientBuilder.newClient();
            WebTarget webTarget= client.target(BASE_URI);
            WebTarget resource = webTarget.path("modifyFromFile");
            
            String url=encode(myJson.toString());
            
            resource = resource.queryParam("usr", User.getUsername());
            resource = resource.queryParam("key",User.getKey());
            resource = resource.queryParam("info", url); //τα δεδομένα των βαθμών κ.ο.κ
        

            Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
            
            System.out.println(build.get(String.class));
            
              client.close();
                
            jLabel2.setText("File uploaded correctly");

        } catch (SheetExc ex) {
           jLabel2.setText("File upload failed");
        }*/
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        adm.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //ανοιγμα του αρχειου
        FileFilter ft = new FileNameExtensionFilter("Spreadsheet(xls,xlsx)", "xls","xlsx");
        fc.addChoosableFileFilter( ft );
        
        int returnVal = fc.showOpenDialog( this );
        
        if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
            java.io.File file = fc.getSelectedFile();
            jTextField1.setText(file.toString());


        }



       //FileChooser fl = new FileChooser();
       //fl.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * Η initComboBox κανει τις απαρραιτητες ενεργειες ωστε να κληθει το service
     * getLessons και να επιστρεψει τα μαθηματα που υπαρχουν. Αφου γινει αυτο , 
     * επομενο βημα ειναι η διαμορφωση του επιστρεφομενου json kai η προσηθκη
     * των μαθηματων στο comboBox
     */
    public void initComboBox()
    {
       String BASE_URI = "http://localhost:8080/WebServices/webresources";
       Client client=javax.ws.rs.client.ClientBuilder.newClient();
       WebTarget webTarget= client.target(BASE_URI);
       
       
       
       WebTarget resource = webTarget.path("getLessons");
         
        resource = resource.queryParam("usr",User.getUsername());
        resource = resource.queryParam("key",User.getKey());
        resource = resource.queryParam("info","0"); //Η παραμετρος αυτη αφορα τον αριθμο μητρωου του χρηστη
        //οταν ειναι =0 τα μαθηματα που επιστρεφονται ειναι ολα τα μαθηματα, ενω αλλιως επιστρεφονται 
        //μονο αυτα τα οποια παρακολουθεί ο χρήστης
        
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
              
        String js=build.get(String.class);
        //έλεγχος αν η επιστρεφόμενη τιμή είναι τύπου json ή απλώς ένα string
        //που σηματοδοτεί κάποιο error.
        if(js.startsWith("{"))
        {
        
        JSONObject json=new JSONObject(js); 
        
        JSONArray lessonsArray=json.getJSONArray("lessons");
        
        for (int i=0;i<lessonsArray.length();i++)
            {
                String lessName=lessonsArray.getJSONObject(i).getString("name");
                String lessID=lessonsArray.getJSONObject(i).getString("lessid");
                jComboBox1.addItem(lessID+" "+lessName);
                
            }
        }
            
        client.close();
    }
    
    
    public void init(){
    //public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(addStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

      
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser fc;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
