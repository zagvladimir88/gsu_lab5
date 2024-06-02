package org.example.model;

import java.util.HashSet;
import java.util.Set;

public class Directory {

    private int id;
    private Directory parent;
    private Set<Directory> subdirectories = new HashSet<>();
    private Set<File> files = new HashSet<>();
    private String name;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Directory getParent() {
        return parent;
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }

    public Set<Directory> getSubdirectories() {
        return subdirectories;
    }

    public void setSubdirectories(Set<Directory> subdirectories) {
        this.subdirectories = subdirectories;
    }

    public Set<File> getFiles() {
        return files;
    }

    public void setFiles(Set<File> files) {
        this.files = files;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Directory() {}

    public Directory(Directory parent, String name) {
        this.parent = parent;
        this.name = name;
    }
}
