package ru.itmo.kirpichev.model;

/**
 * @author ilyakirpichev
 */
public class Task {
    public int id;
    public String name;
    public boolean isDone;
    public int position;

    public Task() {
    }

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
