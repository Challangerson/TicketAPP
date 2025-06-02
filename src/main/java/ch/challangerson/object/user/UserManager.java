package ch.challangerson.object.user;

import ch.challangerson.object.role.Role;
import ch.challangerson.sql.SQLExecute;

import java.util.HashMap;

public class UserManager {

    private final HashMap<String, Role> roles = new HashMap<>();
    private final HashMap<Integer, Role> users = new HashMap<>();

    private final SQLExecute sqlExecute;

    public UserManager(SQLExecute sqlExecute) {
        this.sqlExecute = sqlExecute;
        this.loadUsers();
    }


    private void loadUsers() {
        sqlExecute.executeQuery("SELECT * FROM uzytkownicy", resultSet -> {
            try {
                while (resultSet.next()) {
                    String login = resultSet.getString("login");
                    String password = resultSet.getString("password");
                    Role user = new Role(login, password);
                    user.setFirstName(resultSet.getString("imie"));
                    user.setLastName(resultSet.getString("nazwisko"));
                    user.setId(resultSet.getInt("id_uzytkownika"));
                    user.setAdress(resultSet.getString("adres"));
//                    user.setPhoto(resultSet.getString("photo"));
                    user.setPhoto("/ch/challangerson/view/image/avatar_user.jpg");
                    user.setJob(Job.getJob(resultSet.getString("inne")));
                    roles.put(login, user);
                    users.put(user.getId(), user);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public HashMap<Integer, Role> getUsers() {
        return this.users;
    }

    public User getUser(String login) {
        return roles.get(login);
    }
}
