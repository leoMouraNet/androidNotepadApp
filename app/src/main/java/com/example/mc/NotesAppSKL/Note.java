package com.example.mc.NotesAppSKL;

public class Note {
    public int id;
    public String title;
    public String location;
    public String message;
    public String voice;
    public String picture;

    public Note() {
        this.id = 0;
    }

    public Note(Integer id, String title, String location, String message, String voice, String picture) {
        super();
        this.id = id;
        this.title = title;
        this.location = location;
        this.message = message;
        this.voice = voice;
        this.picture = picture;
    }

    public Note(String title, String location, String message, String voice, String picture) {
        super();
        this.title = title;
        this.location = location;
        this.message = message;
        this.voice = voice;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Note [id=" + id + ", title=" + title+ ", location=" + location
                + ", message=" + message + ", voice=" + voice + ", picture=" + picture + "]";
    }


}
