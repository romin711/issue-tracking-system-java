/*
  Frontend Simulation for Issue Tracking System
  Uses localStorage to persist data across pages
*/

// Load issues from localStorage or initialize
let issues = JSON.parse(localStorage.getItem("issues")) || [];
let issueIdCounter = JSON.parse(localStorage.getItem("issueIdCounter")) || 1;

// Save to localStorage
function saveData() {
    localStorage.setItem("issues", JSON.stringify(issues));
    localStorage.setItem("issueIdCounter", JSON.stringify(issueIdCounter));
}

/* =========================
   USER: Create Issue
   ========================= */
function createIssue(issue) {
    issue.id = issueIdCounter++;
    issue.status = "OPEN";
    issue.assignedTo = null;

    issues.push(issue);
    saveData();

    console.log("Issue Created:", issue);
    return issue;
}

/* =========================
   ADMIN: Assign Issue
   ========================= */
function assignIssue(issueId, staffId) {
    const issue = issues.find(i => i.id === issueId);

    if (!issue) {
        console.error("Assign failed: Issue not found");
        return null;
    }

    issue.assignedTo = staffId;
    issue.status = "ASSIGNED";
    saveData();

    console.log("Issue Assigned:", issue);
    return issue;
}

/* =========================
   STAFF: Update Status
   ========================= */
function updateStatus(issueId, status) {
    const issue = issues.find(i => i.id === issueId);

    if (!issue) {
        console.error("Status update failed: Issue not found");
        return null;
    }

    issue.status = status;
    saveData();

    console.log("Status Updated:", issue);
    return issue;
}

/* =========================
   DEBUG
   ========================= */
function getAllIssues() {
    return issues;
}
