package ch.challangerson.object.session;

public class Session {

    private int parking_id;
    private Boolean isPaid;
    private String session_start;
    private String address;
    private int user;

    public Session(int parking_id, boolean isPaid, String session_start, String address, int user) {
        this.parking_id = parking_id;
        this.isPaid = isPaid;
        this.session_start = session_start;
        this.address = address;
        this.user = user;
    }

    public int getParking_id() {
        return this.parking_id;
    }

    public void setParking_id(int parking_id) {
        this.parking_id = parking_id;
    }

    public boolean isPaid() {
        return this.isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public String getSession_start() {
        return this.session_start;
    }

    public void setSession_start(String session_start) {
        this.session_start = session_start;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUser() {
        return this.user;
    }

    public void setUser(int user) {
        this.user = user;
    }
}
