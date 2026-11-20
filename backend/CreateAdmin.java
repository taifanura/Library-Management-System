
import java.io.Console;
import java.util.Scanner;

public class CreateAdmin {

    public static void main(String[] args) {

        Console console = System.console();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("===== CREATE ADMIN ACCOUNT =====");

            System.out.print("Enter username: ");
            String username = scanner.nextLine().trim();

            System.out.print("Enter full name: ");
            String fullName = scanner.nextLine().trim();

            System.out.print("Enter email: ");
            String email = scanner.nextLine().trim();

            String password;

            if (console != null) {
                char[] passwordChars = console.readPassword("Enter password: ");
                password = new String(passwordChars);
                java.util.Arrays.fill(passwordChars, ' ');
            } else {
                System.out.println("Warning: Password will be visible while typing.");
                System.out.print("Enter password: ");
                password = scanner.nextLine();
            }

            if (username.isEmpty() || fullName.isEmpty()
                    || password.length() < 8) {
                System.out.println(
                        "Username and name are required. Password must be at least 8 characters."
                );
                return;
            }

            AdminDAO adminDAO = new AdminDAO();

            if (adminDAO.usernameExists(username)) {
                System.out.println("Username already exists!");
                return;
            }

            String hashedPassword =
                    PasswordUtil.hashPassword(password);

            adminDAO.createAdmin(
                    username,
                    hashedPassword,
                    fullName,
                    email.isEmpty() ? null : email
            );

            System.out.println("Admin account created successfully!");

        } catch (Exception e) {
            System.out.println("Admin creation failed!");
            System.out.println(e.getMessage());
        }
    }
}
