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

    public static Job getJob(String name) {
        for (Job job : Job.values()) {
            if (job.getName().equalsIgnoreCase(name)) {
                return job;
            }
        }
        return USER; // Default to USER if no match found
    }

    public String getName() {
        return this.name;
    }

    public int getPrivilege() {
        return this.privilege;
    }
}
