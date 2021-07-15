package ru.netology.repository;

import ru.netology.domain.Issue;

import java.util.*;

public class IssueRepository {
    private List<Issue> issues = new ArrayList<>();

    public void addNew(Issue issue) {
        issues.add(issue);
    }

    public List<Issue> findAll() {
        return issues;
    }
}
