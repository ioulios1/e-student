package DesktopApp;


/**
 * Η loginForm αποτελεί το GUI της βασικής οθόνης login της εφαρμογής.Εδώ ελέγχονται
 * μέσω συγκεκριμένων μεθόδων τα credentials του χρήστη που θέλει να κανει  login
 * και εφόσον αυτά υπάρχουν στη βάση δίνεται πρόσβαση στην κεντρική οθόνη επιλογών.
 * Δεν δινεται η δυνατότητα σε φοιτητές (ρόλος=3) να κάνουν login.
 * @author Ioulios Tsiko: cs131027
 * @author Theofilos Axiotis: cs131011
 */
public class loginForm extends javax.swing.JFrame {

    private String username;
    private String password;
    private String userRole;
    int clicked=0;
   
    public void setUsername(String username){
        this.username=username;
    }
    
    public String getUser(){
        return username;
    }
    
    public String getPass(){
        return password;
    }
    
    public loginForm() {
        this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        init();
        initComponents();
        setResizable(false);
    }

     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Username:");

        jLabel2.setText("Password:");

        jLabel3.setText("   ");

        jLabel4.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPasswordField1)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(201, 201, 201))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(17, 17, 17)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        username=jTextField1.getText();
        password=jPasswordField1.getText();
        
        clicked++;
        
        //κλήση της loginClient και αποθήκευση της επιστρεφόμενης τιμής απο τη μέθοδο
        //getLoginKey στο response
        loginClient client = new loginClient();
        String response = client.getLoginKey (username,password);
        
        //set στα keys/username της κλάσης User
        User.setUsername(username);
        User.setKey(response);
        
        //κλήση της getRole και αποθήκευση του ρόλου του χρήστη στη μεταβλητή userRole
        roleClient role = new roleClient();
        userRole = role.getRole(username);
        User.setRole(userRole);
        
        //κλήση της getServices ώστε να ελεγχθούν οι επιτρεπόμενες ενέργειες
        //που μπορεί να κάνει ο χρήστης.
        ServicesClient serv = new ServicesClient();
        String services = serv.getServicies(username,userRole);
                
        int i=0;
               
        /*Ο έλεγχος των ενεργειών που μπορεί να εκτελέσει ο χρήστης γίνεται βάσει των
        ονομάτων των services και των αντιστοιχων buttons. 
        ---------Reminder(!)Προχειρο,να γίνει με ServiceCodes-------------
        Παρακάτω είναι τα βήματα αποδόμησης του αρχικού string που επιστρέφει το 
        service getServices , και η απαλοιφή των περιττών χαρακτήρων όπως είναι 
        [ ] , κ.ο.κ . Η αποθήκευση του τελικού string γίνεται στον πίνακα finalParts*/
        String my_new_str = services.replace('[', ' ');
        String my_new_str2 = my_new_str.replace(']', ' ');
                
        String[] finalParts = null;
        String[] parts = my_new_str2.split(",");
        finalParts=parts;
        
        while(i<parts.length){
            
            finalParts[i]=parts[i].trim();
            i++;
        }
        
        i=0;
        while(i<parts.length){
            System.out.println("Service "+i+" :"+finalParts[i]);
            i++;
        }
        
        //έλεγχος επιτρεπτών τιμών για να πληρούνται οι προυποθέσεις επιτυχημένου 
        //login. Το response απο το webService είναι 0 σε περίπτωση σφάλματος
        //και σε περίπτωση που το role είανι φοιτητής επίσης δεν επιτρέπει την είσοδο.
        if((!response.equals("0")) && (!userRole.equals("Student")))
        {
            //set στο key της User
            User.setKey(response);
         
            this.setVisible(false);
            
            attachShutDownHook();
         
        
        //set των τελικώς μορφοποιημένων services για τον συγκεκριμένο χρήστη
         User.setServices(finalParts);
         
         adminMain adMain = new adminMain();
         adMain.setVisible(true);    
         
         System.out.println("main "+User.getKey());
         System.out.println("Info: "+userRole+"  "+services);
        }    
        else
        {
            jLabel3.setText("Wrong Credentials");
            
            
        }
        System.out.println(response+"  "+clicked);
        
        if(clicked>3)
        {
            client.close();
            this.setVisible(false);
            
        }
        
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
        
        username=jTextField1.getText();
        
        
    }//GEN-LAST:event_jTextField1ActionPerformed

    /**
     * Καλείται ώστε να σβήνεται το login Key ακόμα και εάν δεν γίνει κανονικά
     * logout απο το χρήστη.
     */
    public void attachShutDownHook(){
    Runtime.getRuntime().addShutdownHook(new Thread() {
     @Override
     public void run() {
      System.out.println("Inside Add Shutdown Hook");
       logoutClient lgOut = new logoutClient();
       lgOut.logout(getUser());
     }
    });
    System.out.println("Shut Down Hook Attached.");
   }
    
   
    public void init() {
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
            java.util.logging.Logger.getLogger(loginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(loginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(loginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(loginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

     
    }
    
    
    public static void main(String[] args){
         
         
         loginForm login = new loginForm();
         login.setVisible(true);
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
