package ch.challangerson.object.car;

public class Car {
    private int id, user_id, year;
    private String vin, brand, model, registration;
    private boolean ubezpieczony;

    public Car(int id, int user_id, int year, String vin, String brand, String model, String registration, boolean ubezpieczony) {
        this.id = id;
        this.user_id = user_id;
        this.year = year;
        this.vin = vin;
        this.brand = brand;
        this.model = model;
        this.registration = registration;
        this.ubezpieczony = ubezpieczony;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return this.user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getVin() {
        return this.vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistration() {
        return this.registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public boolean isUbezpieczony() {
        return this.ubezpieczony;
    }

    public void setUbezpieczony(boolean ubezpieczony) {
        this.ubezpieczony = ubezpieczony;
    }
}
