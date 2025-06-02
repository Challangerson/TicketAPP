package ch.challangerson.object.punishment;

public class Punishment {
    private int id, id_user, price, ticket;
    private String response;
    private boolean paid, recidivism;

    public Punishment(int id, int id_user, int price, int ticket, String response, boolean paid, boolean recidivism) {
        this.id = id;
        this.id_user = id_user;
        this.price = price;
        this.ticket = ticket;
        this.response = response;
        this.paid = paid;
        this.recidivism = recidivism;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return this.id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTicket() {
        return this.ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public String getResponse() {
        return this.response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isPaid() {
        return this.paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isRecidivism() {
        return this.recidivism;
    }

    public void setRecidivism(boolean recidivism) {
        this.recidivism = recidivism;
    }
}
