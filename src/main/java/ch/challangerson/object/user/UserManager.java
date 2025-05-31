package ch.challangerson.object.user;

import ch.challangerson.object.role.Role;

import java.util.HashMap;

public class UserManager {

    private final HashMap<String, Role> roles = new HashMap<>();

    public UserManager() {
        roles.put("admin", new Role("admin", "admin"));
        roles.put("user", new Role("user", "user"));

        roles.get("admin").setFirstName("Wacław");
        roles.get("admin").setLastName("Koziróg");
        roles.get("admin").setAdress("Legionów 13/25");
        roles.get("admin").setPhoto("admin");
        roles.get("admin").setJob(Job.ADMIN);
    }

    public User getUser(String login) {
        return roles.get(login);
    }
}
