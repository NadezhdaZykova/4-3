package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class IssueManagerTest {
    private IssueRepository repository = new IssueRepository();
    private IssueManager manager = new IssueManager(repository);

    private Issue i0 = new Issue(0, new HashSet<>(Arrays.asList("constructor", "bublik", "Cidorovik")), "Issue 0",
            new HashSet<>(Arrays.asList("Kotlin", " Jupiter", "status: New")), true,
            2021_02_21, "Zykova");
    private Issue i1 = new Issue(1, new HashSet<>(Collections.singletonList("denis")), "Issue 1",
            new HashSet<>(Arrays.asList("Groovy", "Jupiter", "status: New", "theme: build")),
            false, 2021_03_21, "Cidorov");
    private Issue i2 = new Issue(2, new HashSet<>(Arrays.asList("Kolya", "kotya")), "Issue 2",
            new HashSet<>(Arrays.asList("component: Vintage", " Jupiter", "status: Invalid")), true,
            2021_02_27, "Butre");
    private Issue i3 = new Issue(3, new HashSet<>(), "Issue 3", new HashSet<>(), true, 2021_02_10, "Zykova");

    @BeforeEach
    void setUp() {
        manager.add(i0);
        manager.add(i1);
        manager.add(i2);
    }

    @Test
    void addNew() {
        manager.add(i3);
        assertEquals(Arrays.asList(i0, i1, i2, i3), manager.findAll());
    }

    @Test
    void findOpened() {
        assertEquals(Arrays.asList(i0, i2), manager.findOpened());
    }

    @Test
    void findClosed() {
        assertEquals(Collections.singletonList(i1), manager.findClosed());
    }

    @Test
    void filterByAuthor() {
        manager.add(i3);
        manager.add(i0);

        assertEquals(Arrays.asList(i0, i3, i0), manager.filterByAuthor("Zykova"));
    }

    @Test
    void filterByLabel() {
        manager.add(i3);
        manager.add(i0);

        assertEquals(Arrays.asList(i0, i0), manager.filterByLabel("Kotlin"));
    }

    @Test
    void filterByAssignee() {
        manager.add(i3);
        manager.add(i0);

        assertEquals(Collections.singletonList(i2), manager.filterByAssignee("Kolya"));
    }

    @Test
    void sortNewest() {
        manager.add(i3);
        manager.add(i0);
        assertEquals(Arrays.asList(i3, i0, i0, i2, i1), manager.sortNewest());
    }

    @Test
    void sortOldest() {
        manager.add(i3);
        manager.add(i0);
        manager.sortOldest();
        assertEquals(Arrays.asList(i1, i2, i0, i0, i3), manager.sortOldest());
    }

    @Test
    void canCloseThis() {
        manager.closeIssue(2);
        assertFalse(manager.getIssueStatus(2));
    }

    @Test
    void cantCloseThis() {
        manager.closeIssue(1);
        assertFalse(manager.getIssueStatus(1));
    }

    @Test
    void canOpenThis() {
        manager.openIssue(1);
        assertTrue(manager.getIssueStatus(1));
    }

    @Test
    void cantOpenThis() {
        manager.openIssue(2);
        assertTrue(manager.getIssueStatus(2));
    }

}