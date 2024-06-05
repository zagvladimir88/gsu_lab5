package org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "translations")
public class Translation {

    public Translation() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "english_id", nullable = false)
    private EnglishWord englishWord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "russian_id", nullable = false)
    private RussianWord russianWord;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnglishWord getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(EnglishWord englishWord) {
        this.englishWord = englishWord;
    }

    public RussianWord getRussianWord() {
        return russianWord;
    }

    public void setRussianWord(RussianWord russianWord) {
        this.russianWord = russianWord;
    }

    @Override
    public String toString() {
        return "Translation{" +
                "id=" + id +
                ", englishWord=" + englishWord.getWord() +
                ", russianWord=" + russianWord.getWord() +
                '}';
    }
}
