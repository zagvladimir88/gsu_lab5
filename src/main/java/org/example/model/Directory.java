package org.example.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "directories")
public class Directory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Directory parent;

    @OneToMany(mappedBy = "parent",fetch = FetchType.EAGER)
    private Set<Directory> subdirectories = new HashSet<>();

    @OneToMany(mappedBy = "directory", fetch = FetchType.EAGER)
    private Set<File> files = new HashSet<>();

    @Column(name = "name", nullable = false)
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