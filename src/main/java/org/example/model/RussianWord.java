package org.example.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "russian_words")
public class RussianWord {

    public RussianWord() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "word", nullable = false)
    private String word;

    @OneToMany(mappedBy = "russianWord", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Translation> translations;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Set<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(Set<Translation> translations) {
        this.translations = translations;
    }


    @Override
    public String toString() {
        return word;
    }
}
