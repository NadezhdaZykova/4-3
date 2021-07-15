package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.exception.NotFoundException;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class IssueManager {
    private IssueRepository issueRepository;

    public IssueManager(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public void add(Issue issue) {
        issueRepository.addNew(issue);
    }

    public List<Issue> findAll() {
        return issueRepository.findAll();
    }

    public List<Issue> findOpened() {
        List<Issue> tmp = new ArrayList<>();
        for (Issue issue : issueRepository.findAll()) {
            if (issue.isOpen()) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

    public List<Issue> findClosed() {
        List<Issue> tmp = new ArrayList<>();
        for (Issue issue : issueRepository.findAll()) {
            if (!issue.isOpen()) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

    public List<Issue> filterByAuthor(String author) {
        return filterBy(issue -> issue.getAuthor().equalsIgnoreCase(author));
    }

    public List<Issue> filterByLabel(String label) {
        return filterBy(issue -> issue.getLabel().contains(label));
    }

    public List<Issue> filterByAssignee(String assignee) {
        return filterBy(issue -> issue.getAssignee().contains(assignee));
    }

    private List<Issue> filterBy(Predicate<Issue> predicate) {
        List<Issue> tmp = new ArrayList<>();
        for (Issue issue : issueRepository.findAll()) {
            if (predicate.test(issue)) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

    public List<Issue> sortNewest() {
        issueRepository.findAll().sort(Comparator.comparingInt(Issue::getDate));
        return issueRepository.findAll();
    }

    public List<Issue> sortOldest() {
        issueRepository.findAll().sort((a, b) -> b.getDate() - a.getDate());
        return issueRepository.findAll();
    }

    public void openIssue(int id) {
        boolean flag = false;
        for (Issue issue : issueRepository.findAll()) {
            if (issue.getId() == id && !issue.isOpen()) {
                issue.setOpen(true);
                flag = true;
            }
        }
        if (!flag) {
            System.out.printf("No issue with id = %s or it's opened\n", id);
        }

    }

    public void closeIssue(int id) {
        boolean flag = false;
        for (Issue issue : issueRepository.findAll()) {
            if (issue.getId() == id && issue.isOpen()) {
                issue.setOpen(false);
                flag = true;
            }
        }
        if (!flag) {
            System.out.printf("No issue with id = %s or it's closed\n", id);
        }
    }

    public boolean getIssueStatus(int id) {
        for (Issue issue : issueRepository.findAll()) {
            if (issue.getId() == id) {
                return issue.isOpen();
            }
        }
        throw new NotFoundException("Элемент с id = " + id + " не найден!");
    }
}
