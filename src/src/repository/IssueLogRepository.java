package repository;

import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class IssueLogRepository {

    public void log(int issueId, String action, int performedBy) {
        String sql = """
            INSERT INTO issue_logs (issue_id, action, performed_by)
            VALUES (?, ?, ?)
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, issueId);
            ps.setString(2, action);
            ps.setInt(3, performedBy);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
