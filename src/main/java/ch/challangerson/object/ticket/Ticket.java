package ch.challangerson.object.ticket;

public class Ticket {
    private int id_ticket, id_user;
    private boolean checked, response;
    private String response_text;

    public Ticket(int id_ticket, int id_user, boolean checked, boolean response, String response_text) {
        this.id_ticket = id_ticket;
        this.id_user = id_user;
        this.checked = checked;
        this.response = response;
        this.response_text = response_text;
    }

    public int getId_ticket() {
        return this.id_ticket;
    }

    public void setId_ticket(int id_ticket) {
        this.id_ticket = id_ticket;
    }

    public int getId_user() {
        return this.id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isResponse() {
        return this.response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    public String getResponse_text() {
        return this.response_text;
    }

    public void setResponse_text(String response_text) {
        this.response_text = response_text;
    }
}
