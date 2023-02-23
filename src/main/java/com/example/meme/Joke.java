package com.example.meme;

// import java.util.Objects;

public class Joke {

    int id;
    String joke;

    public Joke() {
    }

    public Joke(int id, String joke) {
        this.id = id;
        this.joke = joke;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    // @Override
    // public boolean equals(Object o) {
    //     if (this == o)
    //         return true;
    //     if (o == null || getClass() != o.getClass())
    //         return false;
    //     Joke joke = (Joke) o;
    //     return joke.joke == joke && id == joke.id;
    // }

    // @Override
    // public int hashCode() {
    //     return Objects.hash(userId, id, title, completed);
    // }

    // @Override
    // public String toString() {
    //     return "{" + "userId=" + userId + ", id=" + id + ", title='" + title + '\'' + ", completed=" + completed + '}';
    // }
}
