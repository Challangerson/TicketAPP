package ch.challangerson.object.parking;

public class Parking {
    private int id,places_amount,taken_amount;
    private boolean paid, card;
    private double parking_price;
    private String adress;

    public Parking(int id, int places_amount, int taken_amount, boolean paid, boolean card, double parking_price, String adress) {
        this.id = id;
        this.places_amount = places_amount;
        this.taken_amount = taken_amount;
        this.paid = paid;
        this.card = card;
        this.parking_price = parking_price;
        this.adress = adress;
    }


    public String getAdress() {
        return this.adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlaces_amount() {
        return this.places_amount;
    }

    public void setPlaces_amount(int places_amount) {
        this.places_amount = places_amount;
    }

    public int getTaken_amount() {
        return this.taken_amount;
    }

    public void setTaken_amount(int taken_amount) {
        this.taken_amount = taken_amount;
    }

    public boolean isPaid() {
        return this.paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isCard() {
        return this.card;
    }

    public void setCard(boolean card) {
        this.card = card;
    }

    public double getParking_price() {
        return this.parking_price;
    }

    public void setParking_price(double parking_price) {
        this.parking_price = parking_price;
    }
}
