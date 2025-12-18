package service;

import repository.IssueLogRepository;
import model.Issue;
import model.User;
import repository.IssueRepository;
import repository.UserRepository;

import java.time.LocalDateTime;

public class IssueService {

    private final IssueRepository issueRepository = new IssueRepository();
    private final UserRepository userRepository = new UserRepository();

    // USER creates issue
    public int createIssue(Issue issue, int userId) {
        User user = userRepository.findById(userId);

        if (user == null || !"USER".equals(user.getRole())) {
            throw new RuntimeException("Only USER can create issues");
        }

        issue.setStatus("OPEN");
        issue.setCreatedBy(userId);
        issue.setCreatedAt(LocalDateTime.now());
        issue.setUpdatedAt(LocalDateTime.now());

        int issueId = issueRepository.create(issue);
        logRepository.log(issueId, "ISSUE CREATED", userId);
        return issueId;

    }

    // ADMIN assigns issue to STAFF
    public void assignIssue(int issueId, int adminId, int staffId) {
        User admin = userRepository.findById(adminId);
        User staff = userRepository.findById(staffId);

        if (admin == null || !"ADMIN".equals(admin.getRole())) {
            throw new RuntimeException("Only ADMIN can assign issues");
        }

        if (staff == null || !"STAFF".equals(staff.getRole())) {
            throw new RuntimeException("Issue can only be assigned to STAFF");
        }

        issueRepository.assignIssue(issueId, staffId);
        logRepository.log(issueId, "ASSIGNED to staff " + staffId, adminId);
    }


    private final IssueLogRepository logRepository = new IssueLogRepository();

    public void updateStatus(int issueId, int staffId, String status) {
        User staff = userRepository.findById(staffId);

        if (staff == null || !"STAFF".equals(staff.getRole())) {
            throw new RuntimeException("Only STAFF can update issue status");
        }

        issueRepository.updateStatus(issueId, status);
        logRepository.log(issueId, "STATUS changed to " + status, staffId);
    }


}
