
package DesktopApp;

import static java.net.URLEncoder.encode;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Η κλάση modifyGrade παρέχει το βασικό GUI της σελίδας και εκτελεί τις βασικές
 * μεθόδους για να είναι λειτουργική η δυνατότητα αλλαγής ενός βαθμού. Η λειτουργία της
 * είναι όμοια με την κλάση modifyFromFile με μόνη ειδοποιό διαφορά ότι εδώ δεν εμπεριέχεται
 * η χρήση αρχείου.Αντ'αυτού η μορφοποίηση ενός βαθμού γίνεται προσωπικά για κάποιον χρήστη
 * @author Ioulios Tsiko: cs131027
 * @author Theofilos Axiotis: cs131011
 */
public class modifyGrade extends javax.swing.JFrame {

   
    String key;
    adminMain adm;
    public modifyGrade(adminMain obj) {
        adm=obj;
        initComponents();
        setResizable(false);
    }
    
    public modifyGrade() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.setText(" ");

        jTextField3.setText(" ");

        jButton1.setText("Modify");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Student:");

        jLabel2.setText("Lesson:");

        jLabel3.setText("Grade:");

        jButton3.setText("Πίσω");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        initComboBox("0");

        jButton4.setText("OK");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel4.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addGap(27, 27, 27)
                .addComponent(jLabel4)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        
            
            JsonBuilderFactory factory=Json.createBuilderFactory(null);
            JsonObjectBuilder jsonB = factory.createObjectBuilder()
                            .addNull("Initializer");
            JsonArrayBuilder jsonA = factory.createArrayBuilder();

            JsonObject myJson;
        
           
            //json που κρατά ΑΜ και βαθμό προς αλλαγή  
            jsonA= jsonA.add(factory.createObjectBuilder()
                .add("am", Integer.parseInt(jTextField1.getText().trim()))
                .add("grade", Integer.parseInt(jTextField3.getText().trim())));
            
            //debugging 
            System.out.println("am : "+jTextField1.getText()+" grade : "+ jTextField3.getText());
                
            

            String lesson=(String) jComboBox2.getSelectedItem();
            //προσθήκη επιλεγμένου μαθήματος απο jComboBoc
            jsonB.add("lessonID",lesson.substring(0,1));
            //προσθήκη πίνακα jsonA με το key Grades στο αντικείμενο jsonB
            jsonB= jsonB.add("Grades", jsonA);

            myJson=jsonB.build();
            
            //debugging
            System.out.println(myJson.toString());
                
            String BASE_URI = "http://localhost:8080/WebServices/webresources";
            Client client=javax.ws.rs.client.ClientBuilder.newClient();
            WebTarget webTarget= client.target(BASE_URI);
            WebTarget resource = webTarget.path("modifyFromFile");
            
            //encoding για μεταφορά μέσω URI
            String url=encode(myJson.toString());
            
            resource = resource.queryParam("usr", User.getUsername());
            resource = resource.queryParam("key",User.getKey());
            resource = resource.queryParam("info", url);//δεδομένα προς αλλαγή

            Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN);
            
            System.out.println(build.get(String.class));
            
            client.close();
                
            jLabel4.setText("Grade modified succesfully");

        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        adm.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        initComboBox(jTextField1.getText().trim());
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * Η initComboBox κανει τις απαρραιτητες ενεργειες ωστε να κληθει το service
     * getLessons και να επιστρεψει τα μαθηματα που υπαρχουν. Αφου γινει αυτο , 
     * επομενο βημα ειναι η διαμορφωση του επιστρεφομενου json kai η προσηθκη
     * των μαθηματων στο comboBox
     */
    public void initComboBox(String usr)
    {
       String BASE_URI = "http://localhost:8080/WebServices/webresources";
       Client client=javax.ws.rs.client.ClientBuilder.newClient();
       WebTarget webTarget= client.target(BASE_URI);
       
       
       
       WebTarget resource = webTarget.path("getLessons");
         
        resource = resource.queryParam("usr",User.getUsername());
        resource = resource.queryParam("key",User.getKey());
        //εδώ δίνεται ως παράμετρος το username του φοιτητή και επιστρέφονται με αυτόν τον τρόπο
        //μόνο τα δικά του μαθήματα. Αν η τιμή info ήταν 0 θα επιστρέφονταν όλα τα μαθήματα.
        resource = resource.queryParam("info",usr); 
        
        Invocation.Builder build = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
        
        //αποθήκευση της επιστρεφόμενης τιμής      
        String js=build.get(String.class);
        //αποδόμηση json
        if(js.startsWith("{"))
        {
        jComboBox2.removeAllItems();
        JSONObject json=new JSONObject(js);
        
        JSONArray lessonsArray=json.getJSONArray("lessons");
        //προσθήκη των δεδομένων στο comboBox
        for (int i=0;i<lessonsArray.length();i++)
            {
                String lessName=lessonsArray.getJSONObject(i).getString("name");
                String lessID=lessonsArray.getJSONObject(i).getString("lessid");
                jComboBox2.addItem(lessID+" "+lessName);
                
            }
        }
            
        client.close();
    }
    
    
    /**
     * @param args the command line arguments
     */
    public void init(){
   // public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(modifyGrade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(modifyGrade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(modifyGrade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(modifyGrade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
