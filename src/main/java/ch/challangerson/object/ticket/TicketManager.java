package ch.challangerson.object.ticket;

import ch.challangerson.object.user.User;
import ch.challangerson.object.user.UserManager;
import ch.challangerson.sql.SQLExecute;

import java.sql.SQLException;

public class TicketManager {

    private final SQLExecute sqlExecute;
    private final UserManager userManager;


    public TicketManager(SQLExecute sqlExecute, UserManager userManager) {
        this.sqlExecute = sqlExecute;
        this.userManager = userManager;

        this.userManager.getUsers().values().forEach(this::setTickets);
    }

    public void updateResponseTicket(Ticket ticket) {
        this.sqlExecute.executeUpdateSync("UPDATE zazalenia SET sprawdzone = '" + (ticket.isResponse() ? 1 : 0) + "', powod_odrzutu = '" + ticket.getResponse_text() + "', odpowiedz ='" + (ticket.isChecked() ? 1 : 0) + "' WHERE id_zazalenia = '" + ticket.getId_ticket() + "';");
    }


    private void setTickets(User user) {
        this.sqlExecute.executeQuery("SELECT * FROM zazalenia WHERE id_uzytkownika = '" + user.getId() + "';", resultSet -> {
            try {
                while (resultSet.next()) {
                    Ticket ticket = new Ticket(
                            resultSet.getInt("id_zazalenia"),
                            resultSet.getInt("id_uzytkownika"),
                            resultSet.getBoolean("sprawdzone"),
                            resultSet.getBoolean("odpowiedz"),
                            resultSet.getString("powod_odrzutu")
                    );

                    user.getTicketsList().add(ticket);
                }
            } catch (SQLException e) {

            }
        });
    }
}
