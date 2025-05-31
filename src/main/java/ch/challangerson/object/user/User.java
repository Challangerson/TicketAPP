package ch.challangerson.object.user;


public class User {

    private String login;
    private String password;
    private String firstName, lastName, adress, photo;
    private Job job;
    private long lastLogin;
    private Long id;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.job = Job.USER;
        this.lastLogin = System.currentTimeMillis();
    }

    public User() {

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
        return "Imie: \n " + this.firstName + "\n" +
                "Nazwisko: \n " + this.lastName + "\n" +
                "Adres: \n " + this.adress + "\n" +
                "Praca: \n " + this.job.toString() + "\n";
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
