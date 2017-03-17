package com.axicik;


import java.math.BigInteger;
import java.sql.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import org.json.*;


/**
 * Η κλάση DBManager είναι μια βοηθητική κλάση για τα web services που κάνει αυθεντικοποίηση του χρήστη
 * και περιέχει όλες τις μεθόδους που καλούνται απο τα Web Services.
 * Επιτρέπετε μόνο ένα αντικείμενο της κλάσης και οι εξωτερικές κλάσεις μπορούν να το χρησιμοποιούν 
 * καλόντας την static μέθοδο getInstance.
 *
 * @author Ioulios Tsiko: cs131027
 * @author Theofilos Axiotis: cs131011
 */
public class DBManager {
    
   
SessionIdentifierGenerator si=new SessionIdentifierGenerator();


String myDatabase="jdbc:mysql://localhost:3306/dikt?user=dikt&password=dikt";
Connection myConnection;
Statement myStatement;
Statement myStatement2;
static DBManager Inst=null;





 






    /**
     * Δημιουρεί αντεικήμενο της κλάσης DBManager
     * και αρχικοποιεί την κλάση com.mysql.jdbc.Driver
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    private DBManager() throws ClassNotFoundException, SQLException {

        deleteKeys();
        Class.forName("com.mysql.jdbc.Driver");        
    }
    
    /**
     * Επιστρέφει το αντικείμενο της DBManager, αν δεν υπάρχει το δημιουργεί.
     * @return Επιστρέφει το αντικείμενο της DBManager.
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static DBManager getInstance() throws ClassNotFoundException, SQLException
    {
        if (Inst == null)
            Inst = new DBManager();
        return Inst;
    }
    
    
    
    
/**
 * Επιστρέφει τον ρόλο του χρήστη που το Όνομα Χρηστη δίνετε σαν όρισμα.
 * @param username Όνομα χρήστη
 * @return Ένα String με τον ρόλο του χρήστη.
 * @throws SQLException 
 */
public String getRole(String username) throws SQLException
{
    String res="test";
    String result="nulls";
    
    
    myConnection = DriverManager.getConnection(myDatabase);
    myStatement = myConnection.createStatement();
    
    //Πέρνει τον κωδικό του ρόλου απο την βάση.
    String sql1="select roles_FK from Users where cred_FK='"+username+"'";
    ResultSet rs2=myStatement.executeQuery(sql1);
    
    while(rs2.next()){
    result=rs2.getString("roles_FK");
    }
    
    
    //Περνει το όνομα του ρόλου.
    String sql="select R_name from Roles where R_id='"+result+"'";
    ResultSet rs = myStatement.executeQuery(sql);
    while(rs.next()){
    res=rs.getString("R_name");
    }
    
    myStatement.close();
    myConnection.close();
   
    return res;
}

/**
 * dexetai username/roleName(Admin/Secretary/Student)
 * kai afou ektelesei to sqlQuery epistrefei ta services(onomata)
 * pou antistoixoun ston sugkekrimeno xristi
 * @param username
 * @param roleName
 * @return
 * @throws SQLException 
 */
public String getServices(String username,String roleName) throws SQLException{
    
    
    ArrayList <String> myList=new ArrayList<String>();
    String test = null;
    myConnection = DriverManager.getConnection(myDatabase);
    myStatement = myConnection.createStatement();
       
    // εκτελείτε το sql query και επιστρεφει τα ονόματα των services. 
    String mysql="select S_name from Services where S_id in (select Services_FK from JNCT_Roles_Services,Roles where Roles_FK=Roles.R_id and R_name='"+roleName+"')";

    ResultSet rs2=myStatement.executeQuery(mysql);
    while(rs2.next()){
        
        myList.add(rs2.getString("S_name"));
    }
    
    myStatement.close();
    myConnection.close();
   
    return myList.toString();
    
}


/**
 * Κάνει την σύνδεση του χρήστη και δημιουργεί το κλειδί τις συνεδρίας του αν ο Χρήστης υπάρχει.
 * @param n1 Όνομα Χρήστη
 * @param n2 Κωδικός Χρήστη
 * @return String - Κλειδί Χρηστη η "0".
 * @throws ClassNotFoundException
 * @throws SQLException 
 */
public String login(String n1,String n2,String n3) throws ClassNotFoundException, SQLException{
    
    myConnection = DriverManager.getConnection(myDatabase);
    myStatement = myConnection.createStatement();
   
    String user=n1 ;
    String pass=n2 ;
    String logedfrom=n3;
    int flag1=0;
    String sql;
    
    //Ελέγχει αν ο χρήστης υπάρχει και αν έχει δώσει σωστό κωδικό.
    sql = "SELECT * FROM Credentials";
    ResultSet rs = myStatement.executeQuery(sql);
    
    try{
       while(rs.next()){
           
         
            if(user.equals(rs.getString("Username")) && pass.equals(rs.getString("Password"))){
                
                flag1=1;
            }
       }
    }
    catch(SQLException exception1){
        flag1=0;
    }
    
    
    //Αν ο χρήστης υπάρχει δημιουργεί κλειδι, το εισάγει στον πίνακα LoginKeys και το επιστρέφει
    //Αλλιώς επιστρέφει "0".
    if (flag1==1)
    {
        String key=si.nextSessionId();
        String sqlString = "insert into LoginKeys(User_fk,Login_Key,logedfrom)"
                + " values('"+user+"','"+key+"','"+logedfrom+"')";
        myStatement.executeUpdate(sqlString);
        myStatement.close();
        myConnection.close(); 
        return key;
    }
    else
    {
        myStatement.close();
        myConnection.close(); 
        return "0";
    }

}

/**
 * Κάνει την αποσύνδεση του χρήστη και διαγράφει το κλειδί τις συνεδρίας του.
 * @param n1 Όνομα Χρήστη
 * @param n2 Κωδικός Χρήστη
 * @return String - Κλειδί Χρηστη η "0".
 * @throws ClassNotFoundException
 * @throws SQLException 
 */
 public void logout (String usr,String key) throws SQLException
    {
        myConnection = DriverManager.getConnection(myDatabase);
        myStatement = myConnection.createStatement();
    
        //Διαγράφει το κλειδί της συνεδρίας απο την βάση.
        String sql;
        sql = "DELETE FROM LoginKeys WHERE LoginKeys.User_FK = '"+usr+"'";
        myStatement.executeUpdate(sql);
        myStatement.close();
        myConnection.close();
        
    }


 /**
  * Ελέγχει αν ο χρήστης είναι συνδεδεμένος και δημιουργεί ένα αντικείμενο User.
  * Αν ο χρήστης υπάρχει το αντικείμενο της κλάσης User θα περιέχει στοιχεία του,
  * αλλιώς Θα έχει μονο ενα flag UsrExist=false;
  * @param username Όνομα Χρήστη
  * @param key Κωδικός Χρήστη
  * @return Επιστρέφει ένα αντικείμενο User 
  * @throws SQLException 
  */
public User checkKey (String username,String key) throws SQLException
{
    myConnection = DriverManager.getConnection(myDatabase);
    myStatement = myConnection.createStatement();
    
    //Ψάχνει το κλειδί στην βάση.Αν υπάρχει φτιάχνει ένα αντικείμενο User 
    //με τα στοιχεία του χρήστη και το επιστρέφει
    //Αλλιώς ενα κένο User.
    String sql;
    sql = "SELECT * FROM LoginKeys where Login_Key = '"+key+"'"
            + "AND User_FK ='"+username+"'";
    ResultSet rs = myStatement.executeQuery(sql);
   
    if(rs.next())
    {
        if(rs.getString("Login_Key").equals(key) && rs.getString("User_FK").equals(username))
        {
            String sqlString = "UPDATE LoginKeys SET timestamp = CURRENT_TIMESTAMP "
                + "WHERE Login_Key = '"+key+"'";
            myStatement.executeUpdate(sqlString);
            
            
            User u=getUser(username);
            System.out.println("class user test ---- >"+u.getName()+" "+u.getLast_name());
            return u;
        }
    }
    
    myStatement.close();
    myConnection.close();
    return new User(false);
}

/**
 * Δημιουργεί ένα αντικείμενο User που περιέχει στοιχεία του χρήστη, που δίνετε 
 * παραμετρικάμ, και τα δικαιώματα του.
 * @param username Όνομα Χρήστη
 * @return ένα αντικείμενο User.
 * @throws SQLException 
 */
public User getUser(String username) throws SQLException
{
    myConnection = DriverManager.getConnection(myDatabase);
    myStatement = myConnection.createStatement();
    
    String sql;
    
    //Πέρνει τα δεδομένα του χρήστη απο την βάση.
    sql = "SELECT * FROM Users where Cred_FK='"+username+"'";
    ResultSet rs = myStatement.executeQuery(sql);
    
    User usr=new User();
    int role=0;
    
    //Εισάγει τις τιμές απο την βάση στο αντικείμενο User.
    if(rs.next())
    {
        if(rs.getString("Cred_FK").equals(username))
        {
             usr.setName(rs.getString("name"));
             usr.setLast_name(rs.getString("last_name"));
             usr.setUsername(username);
             role=rs.getInt("roles_FK");
             usr.setRole(role);
             usr.setUsrExist(true);
             
        }
    }
    
    //Πέρνει τους κωδικούς των webservice, που έχει δικαίωμα να καλέσει ο χρήστης, απο την βάση.
    sql = "SELECT * FROM JNCT_Roles_Services where Roles_FK='"+role+"'";
    rs = myStatement.executeQuery(sql);
    
    //Εισάγει τους κωδικούς στο αντικείμενο User.
    while(rs.next())
    {
        if(rs.getInt("Roles_FK")==role)
        {
            usr.srvList.add(rs.getInt("Services_FK"));
            
        }
    }
   
    myStatement.close();
    myConnection.close();
    
    return usr;
   
}


/**
 * Πέρνει τα στοιχεία του φοιτητή, τα μαθήματα του και τους βαθμούς απο την βάση,
 * Τα εισάγει σε ένα αντίκείμενο json και τα μετατρέπει σε String και τα επιστρέφει.
 * @param usr Όνομα Χρήστη προς αναζήτηση.
 * @return ένα String που περιέχει τα στοιχεία σε μορφη json array. 
 * Σε περίπτωση σφάλματος ένα μήνυμα που αναφέρει τι πήγε στραβά.
 * 
 * Μορφή json :
* { "PersonalInfo": 
*      { "name": Ονομα χρήστη, 
*        "last_name": Επίθετο χρήστη,
*        "phone": αριθμός τηλεφόνου,
*        "username":  Όνομα Χρήστη,
*        "registrationNumber": Αριθμό μητρόου,
*        "email": email,
*        "role": κωδικός ρόλου
*      },
*   "lessons": 
*      [{ "name": Ονομα μαθήματος, 
*        "lessid": κωδικός μαθήματος,
*        "grade": βαθμός},
*        ... ,
*       { "name": Ονομα μαθήματος, 
*        "lessid": κωδικός μαθήματος,
*        "grade": βαθμός}
*      ]
* }
 * 
 * @throws SQLException 
 */
public String getStudInfo(String usr) throws SQLException
{
    myConnection = DriverManager.getConnection(myDatabase);
    myStatement = myConnection.createStatement();
    
    String sql;
    JsonObject myJson;
    int id=2;
    String grade;
    
    //Αρχικοποιεί το json.
    JsonBuilderFactory factory=Json.createBuilderFactory(null);
    JsonObjectBuilder jsonB = factory.createObjectBuilder()
                    .addNull("Initializer");
    JsonArrayBuilder jsonA = factory.createArrayBuilder();
    
    //Πέρνει τα δεδομένα του χρήστη απο την βάση.
    sql = "SELECT * FROM Users where Cred_FK='"+usr+"'";
    ResultSet rs = myStatement.executeQuery(sql);
    
    //Εισάγει τις τιμές απο την βάση στο αντικείμενο  Json.
    if(rs.next())
    {
        if(rs.getString("Cred_FK").equals(usr))
        {
            String role=rs.getString("roles_FK");
            //Αν ο χρήστης προς αναζήτηση δεν είναι φοιτιτής κάνει return "Student Doesn't Exists"
            if(!role.equals("3"))
            {
                myStatement.close();
                myConnection.close();
                return "Student Doesn't Exists";
            }
             jsonB= jsonB.add("PersonnalInfo", factory.createObjectBuilder()
                        .add("name", rs.getString("name"))
                        .add("last_name", rs.getString("last_name"))
                        .add("email", rs.getString("email"))
                        .add("phone", rs.getString("phone"))
                        .add("registrationNumber", rs.getString("RegistrationNo"))
                        .add("role",role)
                        .add("username",rs.getString("Cred_FK")));

            id=rs.getInt("RegistrationNo");
        }
        
    }
    else //Αν το rs είναι κενό δεν υπαρχεί ο χρηστης οποτε επιστρέφει καταληλο μήνυμα.
    {
        myStatement.close();
        myConnection.close();
        return "Student Doesn't Exists";
    }
    
    //Αν ο χρηστης υπάρχει εξάγει απο την βάση τα μαθήματα του και τους βαθμούς του και τα βάζει στο json.
    sql = "SELECT * FROM JNCT_Students_Lessons,Lessons WHERE stud_FK='"+id+"' AND les_fk=LessonID";
    rs = myStatement.executeQuery(sql);
    while(rs.next())
    {
        if(rs.getString("LessonID")!=null)
        {
         if (rs.getString("grade")==null)  
             grade="-";
         else
             grade=rs.getString("grade");
         
         jsonA= jsonA.add(factory.createObjectBuilder()
                            .add("name", rs.getString("name"))
                            .add("lessid", rs.getString("LessonID"))
                            .add("grade", grade));
        }
    }
   jsonB= jsonB.add("Grades", jsonA);
           
    myJson=jsonB.build();
    myStatement.close();
    myConnection.close();
    return myJson.toString();
   
}

/**
 * Επιστρέφει τα στοιχεία του μαθήματος με κωδικό lessID που δίνετε παραμετρικά.
 * 
 * @param lesID Κωδικός μαθήματος
 * @return ένα String που περιέχει τα στοιχεία του μαθήματος σε μορφη json. 
 * Σε περίπτωση σφάλματος ένα μήνυμα που αναφέρει τι πήγε στραβά
 * Μορφή json :
 * { "lesson": 
 *      { "name": Ονομα μαθήματος, 
 *        "semester": αριθμός εξαμήνου,
 *        "lessid": κωδικός μαθήματος,
 *        "description": περιγραφή
 *      }
 * }
 * @throws SQLException 
 */
public String getLesson(String lesID) throws SQLException
{    
    myConnection = DriverManager.getConnection(myDatabase);
    myStatement = myConnection.createStatement();
    String sql;
    
    // Πέρνει τα δεδομένα του μαθήματος απο την βάση.
    sql = "SELECT * FROM Lessons WHERE LessonID='"+lesID+"'";
    ResultSet rs = myStatement.executeQuery(sql);
    
    //Αρχικοποιεί το json.
    JsonBuilderFactory factory=Json.createBuilderFactory(null);
    JsonObjectBuilder jsonB = factory.createObjectBuilder()
                    .addNull("Initializer");
    
    //Αν το μάθημα υπάρχει εισάγει τις τιμές απο την βάση στο αντικείμενο  Json και το επιστρέφει.
   if(rs.next())
    {
        if(rs.getString("LessonID").equals(lesID))
        {
            jsonB= jsonB.add("lesson",factory.createObjectBuilder()
                               .add("name", rs.getString("Name"))
                               .add("lessid", rs.getString("LessonID"))
                               .add("semester", rs.getString("Semester"))
                               .add("description", rs.getString("Description")));
            myStatement.close();
            myConnection.close();
            return jsonB.build().toString();
        }
        
    }
   
    //Αν το μάθημα δεν υπάρχει επιστρέφει ενα String.       
    
    myStatement.close();
    myConnection.close();
    return "Lesson doesn't exist";
   
}

/**
 * Επιστρέφει όλα τα μαθήματα ή μόνο τα μαθήματα ενός φοιτητή. 
 * @param usr Όνομα χρήστη προς αναζήτηση "0" για όλα τα μαθήματα.
 * @return ένα String που περιέχει τα μαθήματα σε μορφη json array. Σε περίπτωση σφάλματος ένα μήνυμα που αναφέρει τι πήγε στραβά.
* Μορφή json :
* { "lessons": 
*      [{ "name": Ονομα μαθήματος, 
*        "lessid": κωδικός μαθήματος},
*        ... ,
*       { "name": Ονομα μαθήματος, 
*        "lessid": κωδικός μαθήματος}
*      ]
* }
 * @throws SQLException 
 */
public String getLessons(String usr) throws SQLException
{
    myConnection = DriverManager.getConnection(myDatabase);
    myStatement = myConnection.createStatement();
    
    String sql;
    //Αν το usr είναι "0" επιστρέφει όλα τα μαθήματα.
    //Αλλιώς μονο τα μαθήματα που έχει ο χρήστης usr.
    if(usr.equals("0"))
    sql = "SELECT * FROM Lessons";
    else
    sql = "SELECT * FROM Lessons,JNCT_Students_Lessons where Les_FK=LessonID AND Stud_FK='"+usr+"' ";    
    ResultSet rs = myStatement.executeQuery(sql);
    
    //Αρχικοποιεί το json.
    JsonBuilderFactory factory=Json.createBuilderFactory(null);
    JsonObjectBuilder jsonB = factory.createObjectBuilder()
                    .addNull("Initializer");
    JsonArrayBuilder jsonA = factory.createArrayBuilder();
    
        
    
   while(rs.next())
    {
        if(rs.getString("LessonID")!=null)
         jsonA= jsonA.add(factory.createObjectBuilder()
                            .add("name", rs.getString("Name"))
                            .add("lessid", rs.getString("LessonID")));
        
        
        
    }
   
           
    jsonB= jsonB.add("lessons", jsonA);
           
        myStatement.close();
        myConnection.close();
        return jsonB.build().toString();
   
}

/**
 * Προσθέτει έναν νέο φοιτητή στην βάση δεδομένων. 
 * @param json Json Κώδικας με τα στοιχεία του φοιτητή
     * μορφή json:
     * { "student": 
     *      { "name": Ονομα Φοιτητή, 
     *        "last_name": Επίθετο Φοιτητή,
     *        "phone": "αριθμός τηλεφόνου",
     *        "username": "Αριθμό μητρόου",
     *        "password": "Κωδικός" 
     *      }
     * }
 * @return String - "User Added Successfully"
 * @throws SQLException 
 */
public String addStudent(String json) throws SQLException
{
    myConnection = DriverManager.getConnection(myDatabase);
    myStatement = myConnection.createStatement();
    
    JSONObject js=new JSONObject(json);
    String name =js.getJSONObject("student").getString("name");
    String last_name =js.getJSONObject("student").getString("last_name");
    String phone =js.getJSONObject("student").getString("phone");
    String regNum =js.getJSONObject("student").getString("username");
    String pass =js.getJSONObject("student").getString("password");
    
    String username="cs"+regNum;
    String email = username+"@teiath.gr";
    String sqlString = "insert into Credentials(username,password)"
                + " values('"+username+"','"+pass+"')";
        myStatement.executeUpdate(sqlString);
        
        
    sqlString = "insert into Users(name,RegistrationNo,last_name,roles_FK,cred_FK,email,phone)"
                + " values('"+name+"','"+regNum+"','"+last_name+"',3,'"+username+"','"+email+"','"+phone+"')";
        myStatement.executeUpdate(sqlString);
        
        
    
        
        myStatement.close();
        myConnection.close();
        return "User Added Successfully";
} 

/**
 * Προσθέτει έναν νέο χρήστη(Διαχειριστή η Γραμματέα) στην βάση δεδομένων.
 * @param json Json Κώδικας με τα στοιχεία του νέου χρήστη 
     * Μορφή json:
     * { "student": 
     *      { "name": Ονομα χρήστη, 
     *        "last_name": Επίθετο χρήστη,
     *        "phone": αριθμός τηλεφόνου,
     *        "username": Αριθμό μητρόου,
     *        "password": Κωδικός, 
     *        "role": κωδικός ρόλου
     *      }
     * }
 * @return  String - "User Added Successfully"
 * @throws SQLException 
 */
 public String addUser(String json) throws SQLException
{
    myConnection = DriverManager.getConnection(myDatabase);
    myStatement = myConnection.createStatement();
    
    JSONObject js=new JSONObject(json);
    String name =js.getJSONObject("student").getString("name");
    String last_name =js.getJSONObject("student").getString("last_name");
    String role =js.getJSONObject("student").getString("role");
    String phone =js.getJSONObject("student").getString("phone");
    String regNum =js.getJSONObject("student").getString("username");
    String pass =js.getJSONObject("student").getString("password");
    
    String username="cs"+regNum;
    String email = username+"@teiath.gr";
    String sqlString = "insert into Credentials(username,password)"
                + " values('"+username+"','"+pass+"')";
        myStatement.executeUpdate(sqlString);
        
        
    sqlString = "insert into Users(name,RegistrationNo,last_name,roles_FK,cred_FK,email,phone)"
                + " values('"+name+"','"+regNum+"','"+last_name+"','"+role+"','"+username+"','"+email+"','"+phone+"')";
        myStatement.executeUpdate(sqlString);
        
        
    
        
        myStatement.close();
        myConnection.close();
        return "User Added Successfully";
} 
  
 
  
 


 /**
  * Προσθέτει η αλλάζει τον βαθμό σε έναν ή περισότερουw φοιτητές.
  * @param str Κώδικας με τα στοιχεία του νέου χρήστη 
     * Μορφή json:
     * { "lessonID": κωδικός μαθήματος,
     *   "Grades": 
     *      [{ "am": Αριθμός μητρώου , 
     *        "grade": βαθμός},
     *        ... ,
     *       { "am": Αριθμός μητρώου, 
     *        "grade": βαθμός}
     *      ]
     * } 
  * @return String - "Grades Added Successfully"
  * @throws SQLException 
  */
public String putGrades(String str) throws SQLException
{
    myConnection = DriverManager.getConnection(myDatabase);
    myStatement = myConnection.createStatement();
    
    JSONObject userJson=new JSONObject(str);

    JSONArray gradesJson=userJson.getJSONArray("Grades");
    String lessID=userJson.getString("lessonID");

    for (int i=0;i<gradesJson.length();i++)
    {
        int am=gradesJson.getJSONObject(i).getInt("am");
        double grade=gradesJson.getJSONObject(i).getDouble("grade");
                
            
        String sqlString = "UPDATE JNCT_Students_Lessons SET Grade = '"+grade+"' "
                + "WHERE JNCT_Students_Lessons.Stud_FK = '"+am+"' "
                + "AND JNCT_Students_Lessons.Les_FK = '"+lessID+"'";
        myStatement.executeUpdate(sqlString);
    }

   

    myStatement.close();
    myConnection.close();
    return "Grades Added Successfully";
   
}

/**
 * Προσθέτει νέα μαθήματα που επέλεξε ο φοιτητης στα μαθήματα του,
 * και αρχικοποιεί τον βαθμό με "-".
 * @param jsonA
 * @param usr
 * @throws SQLException 
 */
public void putClasses(String jsonA,String usr) throws SQLException
{
    myConnection = DriverManager.getConnection(myDatabase);
    myStatement = myConnection.createStatement();
    
    JSONArray json=new JSONArray(jsonA);

    

    String usr2=usr.substring(2,8);
   
    for (int i=0;i<json.length();i++)
    {
        String lessid=json.getJSONObject(i).getString("lessid");
        
        //Αν το το κύριο κλειδί υπάρχει είδη(δηλαδη το μάθημα υπάρχει) το κάνει update χωρίς να αλλακσει τον βαθμό.
        String sqlString = "INSERT INTO `JNCT_Students_Lessons` (`Stud_FK`, `Les_FK`, `Grade`)"
                + " VALUES ('"+usr2+"', '"+lessid+"', NULL)\n"
                +"ON DUPLICATE KEY UPDATE Grade=Grade;";
        myStatement.executeUpdate(sqlString);
    }

   

    myStatement.close();
    myConnection.close();
   
  
}

private final ScheduledExecutorService scheduler =
       Executors.newScheduledThreadPool(1);

    public void deleteKeys() throws SQLException,ClassNotFoundException {
        final Runnable keyCleaner = new Runnable() {
            
            
                        
                public void run() 
                {  
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    System.out.println("driver exeption");
                }
                    
                    String sql;
                    Timestamp date;
                    
                    Calendar dt=Calendar.getInstance();
                    System.out.println("timer test ----> beep ");
                    dt.add(Calendar.MINUTE, -10);
                    
                    Calendar dt2=Calendar.getInstance();
                    
                            
                    try {
                        
                        String myDatabase="jdbc:mysql://localhost:3306/dikt?user=dikt&password=dikt";
                        Connection myConnection1 = DriverManager.getConnection(myDatabase);
                        Statement myStatement1 = myConnection1.createStatement();
                                                
                        sql = "SELECT * FROM LoginKeys";
                        ResultSet rs = myStatement1.executeQuery(sql);
                        
                        
                        while(rs.next())
                        {
                            if(rs.getString("logedfrom").equals("1"))
                            {
                                date=rs.getTimestamp("TimeStamp");
                                System.out.println("time test ---> "+date.toString());
                                dt2.setTime(date);
                                
                                if(dt.after(dt2))
                                {
                                    sql = "Delete FROM LoginKeys Where Login_Key='"+rs.getString("Login_Key")+"'";
                                    myStatement1.executeUpdate(sql);
                                }
                                else
                                    System.out.println("timer test ---->  to kleidi yparxei");
                            }
                        }
                        
                        
                        
                        myStatement1.close();
                        myConnection1.close(); 
                        
                    } catch (SQLException ex) {
                        System.out.println("den uparxoun kleidia apo web efarmogh");
                    }
                }
            };
        
        final ScheduledFuture<?> keyCleanerHandle =
            scheduler.scheduleAtFixedRate(keyCleaner, 60*2, 60*2, SECONDS);
        
        /*
        scheduler.schedule(new Runnable() {
                public void run() { System.out.println("scheduler test ----> beep");  keyCleanerHandle.cancel(true); }
            }, 60, SECONDS);
        */
    }





}

/**
 * Περιέχει μια μέθοδο που δειμιουργεί τυχαίους κλειδαρίθμους.
 * @author ioulios
 */
final class SessionIdentifierGenerator {
  private SecureRandom random = new SecureRandom();

  public String nextSessionId() {
    return new BigInteger(130, random).toString(32);
  }
}