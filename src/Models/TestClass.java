package Models;

/**
 * Class to test the checkValidUser method in the user class
 *
 * Last Updated: 11/20/2020
 *
 * @author Katelynn Urgitus
 */
public class TestClass {

    public static void main(String[] args) {
// ================================ Edge Cases =================================
        System.out.println("Edge Case:");

        // Valid even though the email cases are incorrect
        System.out.println("Checking the if login credentials "
                + "'ExAmPlE@email.com' and 'Example' are valid.");
        System.out.println(SchlepUser.user.checkValidUser("ExAmPlE@email.com",
                "Example"));

// ================================ Normal Cases ===============================
        System.out.println("Normal Cases:");

        // Valid User Login 1
        System.out.println("Checking the if login credentials "
                + "'example@email.com' and 'Example' are valid.");
        System.out.println(SchlepUser.user.checkValidUser("example@email.com",
                "Example"));

        // Valid User Login 1
        System.out.println("Checking the if login credentials 'IQ@email.com' "
                + "and 'Password' are valid.");
        System.out.println(SchlepUser.user.checkValidUser("IQ@email.com",
                "Password"));

// ================================ Error Cases ================================
        System.out.println("Error Cases:");

        // Invalid because password case is incorrect
        System.out.println("Checking the if login credentials "
                + "'example@email.com' and 'EXAMPLE' are valid.");
        System.out.println(SchlepUser.user.checkValidUser("example@email.com",
                "EXAMPLE"));

        // Invalid because both values are incorrect
        System.out.println("Checking the if login credentials "
                + "'invalid@email.com' and 'invalid' are valid.");
        System.out.println(SchlepUser.user.checkValidUser("invalid@email.com",
                "invalid"));
    }
}
