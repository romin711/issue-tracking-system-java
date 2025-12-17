package main;

import service.IssueService;
import repository.UserRepository;

import java.util.Scanner;

public class ConsoleApp {

    private static final Scanner sc = new Scanner(System.in);
    private static final IssueService issueService = new IssueService();
    private static final UserRepository userRepository = new UserRepository();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n==== ISSUE TRACKING SYSTEM ====");
            System.out.println("1. Login as USER");
            System.out.println("2. Login as ADMIN");
            System.out.println("3. Login as STAFF");
            System.out.println("0. Exit");

            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> userMenu();
                case 2 -> adminMenu();
                case 3 -> staffMenu();
                case 0 -> {
                    System.out.println("Exiting system...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private static void userMenu() {
        System.out.print("Enter USER ID: ");
        int userId = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Issue title: ");
        String title = sc.nextLine();

        System.out.print("Description: ");
        String desc = sc.nextLine();

        System.out.print("Category: ");
        String category = sc.nextLine();

        System.out.print("Priority (LOW/MEDIUM/HIGH): ");
        String priority = sc.nextLine();

        var issue = new model.Issue();
        issue.setTitle(title);
        issue.setDescription(desc);
        issue.setCategory(category);
        issue.setPriority(priority);

        int issueId = issueService.createIssue(issue, userId);

        System.out.println("Issue created with ID: " + issueId);
    }


    private static void adminMenu() {
        System.out.print("Enter ADMIN ID: ");
        int adminId = sc.nextInt();

        System.out.print("Issue ID to assign: ");
        int issueId = sc.nextInt();

        System.out.print("STAFF ID: ");
        int staffId = sc.nextInt();

        issueService.assignIssue(issueId, adminId, staffId);
        System.out.println("Issue assigned successfully");
    }


    private static void staffMenu() {
        System.out.println("STAFF menu (next step)");
    }
}
