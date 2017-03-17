
package DesktopApp;

/**
 *Η κλάση User είναι βοηθητική κλάση , μέσω της οποίας αποθηκεύονται 
 * και ανακτώνται τα συγκεκριμένα χαρακτηριστικά του χρήστη αλλα και της
 * "συνεδρίας" του στην Desktop εφαρμογή. Όλες οι μεταβλητές και οι μέθοδοι είναι 
 * static για να διασφαλίσουμε πως τα πεδία και οι μέθοδοι της κλάσης User κατα
 * τη δημιουργία της, θα είναι μοναδικά και προσβάσιμα απο όλες τις υπόλοιπες κλάσεις
 * χωρίς να είναι απαρραίτητη η δημιουργία νέων αντικειμένων ή η παραμετρική αποστολή
 * των references τους απο αντικείμενο σε αντικείμενο.
 * @author Ioulios Tsiko: cs131027
 * @author Theofilos Axiotis: cs131011
 */
public class User {
    private static String username;
    private static String key;
    private static String userRole;
    private static String[] services;

    public static void setKey(String key) {
        User.key = key;
    }

    public static String getKey() {
        return key;
    }

    public static void setUsername(String username) {
        User.username = username;
    }
    
    public static void setRole(String str) {
        User.userRole = str;
    }
    
    public static void setServices(String[] str) {
        User.services = str;
    }
    
    public static String getUsername() {
        return username;
    }
    
    public static String getRole() {
        return userRole;
    }

    public static String[] getServices() {
        return services;
    }
    
    
    
        
}
