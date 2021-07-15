package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class IssueRepositoryTest {
    private IssueRepository repository = new IssueRepository();

    private Issue b0 = new Issue(0, new HashSet<>(Arrays.asList("constructor", "bublik", "ezik")), "Issue 0",
            new HashSet<>(Arrays.asList("component: Kotlin", "component: Jupiter", "status: New")), true,
            2021_02_21, "orl");
    private Issue b1 = new Issue(1, new HashSet<>(Collections.singletonList("denis")), "Issue 1",
            new HashSet<>(Arrays.asList("component: Groovy", "component: Jupiter", "status: New", "theme: build")),
            false, 2021_03_21, "ez");
    private Issue b2 = new Issue(2, new HashSet<>(Arrays.asList("vasya", "kotya")), "Issue 2",
            new HashSet<>(Arrays.asList("component: Vintage", "component: Jupiter", "status: Invalid")), true,
            2021_02_27, "grek");
    private Issue b3 = new Issue(3, new HashSet<>(), "Issue 3", new HashSet<>(), true, 2021_02_10, "orl");

    @BeforeEach
    void setUp() {
        repository.addNew(b0);
        repository.addNew(b1);
        repository.addNew(b2);
    }

    @Test
    void addNew() {
        repository.addNew(b3);
        assertEquals(Arrays.asList(b0, b1, b2, b3), repository.findAll());
    }
}