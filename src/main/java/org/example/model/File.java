package org.example.model;

public class File {

    private int id;
    private Directory directory;
    private String name;
    private int size;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Directory getDirectory() {
        return directory;
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public File() {}

    public File(Directory directory, String name, int size) {
        this.directory = directory;
        this.name = name;
        this.size = size;
    }
}
