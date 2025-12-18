package repository;

import model.Issue;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class IssueRepository {

    public int create(Issue issue) {
        String sql = """
        INSERT INTO issues
        (title, description, category, priority, status,
         created_by, assigned_to, created_at, updated_at)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        RETURNING issue_id
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, issue.getTitle());
            ps.setString(2, issue.getDescription());
            ps.setString(3, issue.getCategory());
            ps.setString(4, issue.getPriority());
            ps.setString(5, issue.getStatus());
            ps.setInt(6, issue.getCreatedBy());

            if (issue.getAssignedTo() > 0) {
                ps.setInt(7, issue.getAssignedTo());
            } else {
                ps.setNull(7, java.sql.Types.INTEGER);
            }

            ps.setTimestamp(8, Timestamp.valueOf(issue.getCreatedAt()));
            ps.setTimestamp(9, Timestamp.valueOf(issue.getUpdatedAt()));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("issue_id");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        throw new RuntimeException("Issue creation failed");
    }


    public List<Issue> findAll() {
        List<Issue> issues = new ArrayList<>();
        String sql = "SELECT * FROM issues";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                issues.add(new Issue(
                        rs.getInt("issue_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("category"),
                        rs.getString("priority"),
                        rs.getString("status"),
                        rs.getInt("created_by"),
                        rs.getInt("assigned_to"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return issues;
    }

    public void assignIssue(int issueId, int staffId) {
        String sql = """
        UPDATE issues
        SET assigned_to = ?, status = 'ASSIGNED', updated_at = CURRENT_TIMESTAMP
        WHERE issue_id = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, staffId);
            ps.setInt(2, issueId);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void updateStatus(int issueId, String status) {
        String sql = """
        UPDATE issues
        SET status = ?, updated_at = CURRENT_TIMESTAMP
        WHERE issue_id = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, issueId);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
