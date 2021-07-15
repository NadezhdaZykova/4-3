package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Issue {
    private int id;
    private HashSet<String> assignee = new HashSet<>();
    private String name;
    private HashSet<String> label = new HashSet<>();
    private boolean isOpen;
    private int date;
    private String author;
    public void addAssignee(String whom) {
        assignee.add(whom);
    }

    public void addLabel(String tag) {
        label.add(tag);
    }
}
