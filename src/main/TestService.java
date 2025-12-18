package main;

import model.Issue;
import service.IssueService;

public class TestService {
    public static void main(String[] args) {

        IssueService service = new IssueService();

        // STEP 1: create issue object
        Issue issue = new Issue();
        issue.setTitle("Pothole near market");
        issue.setDescription("Large pothole causing traffic");
        issue.setCategory("Road");
        issue.setPriority("HIGH");

        // STEP 2: USER (id = 3) creates issue
        int issueId = service.createIssue(issue, 3);

        // STEP 3: ADMIN (id = 1) assigns issue to STAFF (id = 2)
        service.assignIssue(issueId, 1, 2);

        // STEP 4: STAFF updates status
        service.updateStatus(issueId, 2, "IN_PROGRESS");
        service.updateStatus(issueId, 2, "RESOLVED");

        System.out.println("Workflow executed successfully");
    }
}
