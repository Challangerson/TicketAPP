package ch.challangerson.object.user;

public enum Job {

    ADMIN("Admin" ,0),
    USER("User", 0),
    POLICEMAN("Policeman", 0),
    ATTENDANT("Attendant", 0);

    private final String name;
    private final int privilege;
    Job(String name, int privilege) {
        this.privilege = 0;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getPrivilege() {
        return this.privilege;
    }
}
