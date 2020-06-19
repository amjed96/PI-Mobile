/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author zakib
 */
public class Participation {
    private int id ;
    static User users;
    private Events events ;

    public Participation() {
    }

    public Participation(int id, Events events) {
        this.id = id;
        this.events = events;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static User getUsers() {
        return users;
    }

    public static void setUsers(User users) {
        Participation.users = users;
    }

    public Events getEvents() {
        return events;
    }

    public void setEvents(Events events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "Participation{" + "id=" + id + ", events=" + events + '}';
    }
    
    
}
