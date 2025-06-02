package ch.challangerson.object.user;


import ch.challangerson.object.car.Car;
import ch.challangerson.object.punishment.Punishment;
import ch.challangerson.object.session.Session;
import ch.challangerson.object.ticket.Ticket;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final String login;
    private String password;
    private String firstName, lastName, adress, photo;
    private Job job;
    private long lastLogin;
    private int id;
    private List<Session> sessionList;
    private List<Punishment> punishmentList;
    private List<Ticket> ticketsList;
    private List<Car> cars;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.job = Job.USER;
        this.lastLogin = System.currentTimeMillis();
        this.sessionList = new ArrayList<>();
        this.punishmentList = new ArrayList<>();
        this.ticketsList = new ArrayList<>();
        this.cars = new ArrayList<>();
    }

    public List<Car> getCars() {
        return this.cars;
    }



    public List<Session> getSessionList() {
        return this.sessionList;
    }

    public void setSessionList(List<Session> sessionList) {
        this.sessionList = sessionList;
    }

    public List<Punishment> getPunishmentList() {
        return this.punishmentList;
    }

    public void setPunishmentList(List<Punishment> punishmentList) {
        this.punishmentList = punishmentList;
    }

    public List<Ticket> getTicketsList() {
        return this.ticketsList;
    }

    public void setTicketsList(List<Ticket> ticketsList) {
        this.ticketsList = ticketsList;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Job getJob() {
        return this.job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public long getLastLogin() {
        return this.lastLogin;
    }

    public void setLastLogin(long lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        return "User: " + this.firstName + " " + this.lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAdress() {
        return this.adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
