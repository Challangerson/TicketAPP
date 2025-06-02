package ch.challangerson.object;

import ch.challangerson.object.car.CarManager;
import ch.challangerson.object.parking.ParkingManager;
import ch.challangerson.object.punishment.PunishmentManager;
import ch.challangerson.object.session.SessionManager;
import ch.challangerson.object.ticket.TicketManager;
import ch.challangerson.object.user.UserManager;
import ch.challangerson.sql.MySQL;
import ch.challangerson.sql.SQLExecute;
import javafx.fxml.Initializable;

public abstract class BaseImplentation implements Initializable {

    protected UserManager userManager;
    protected TicketManager ticketManager;
    protected PunishmentManager punishmentManager;
    protected SQLExecute sqlExecute;
    protected SessionManager sessionManager;
    protected ParkingManager parkingManager;
    protected CarManager carManager;

    public BaseImplentation() {

        this.sqlExecute = new SQLExecute(
                "mysql.titanaxe.com",
                3306,
                "srv310626",
                "srv310626",
                "ezjV3sX8");


        this.sessionManager = new SessionManager(this.sqlExecute);
        this.userManager = new UserManager(this.sqlExecute);
        this.ticketManager = new TicketManager(this.sqlExecute, this.userManager);
        this.punishmentManager = new PunishmentManager(this.sqlExecute, this.userManager);
        this.parkingManager = new ParkingManager(this.sqlExecute);
        this.carManager = new CarManager(this.sqlExecute, this.userManager);


    }

    public void setter() {

        if(this.sqlExecute == null) {
            this.sqlExecute = new SQLExecute(
                    "mysql.titanaxe.com",
                    3306,
                    "srv310626",
                    "srv310626",
                    "ezjV3sX8");
        }

        this.userManager = new UserManager(this.sqlExecute);
        this.sessionManager = new SessionManager(this.sqlExecute);
        this.ticketManager = new TicketManager(this.sqlExecute, this.userManager);
        this.punishmentManager = new PunishmentManager(this.sqlExecute, this.userManager);
        this.parkingManager = new ParkingManager(this.sqlExecute);
        this.carManager = new CarManager(this.sqlExecute, this.userManager);
    }

    public UserManager getUserManager() {
        return this.userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public TicketManager getTicketManager() {
        return this.ticketManager;
    }

    public void setTicketManager(TicketManager ticketManager) {
        this.ticketManager = ticketManager;
    }

    public PunishmentManager getPunishmentManager() {
        return this.punishmentManager;
    }

    public void setPunishmentManager(PunishmentManager punishmentManager) {
        this.punishmentManager = punishmentManager;
    }

    public SQLExecute getSqlExecute() {
        return this.sqlExecute;
    }

    public SessionManager getSessionManager() {
        return this.sessionManager;
    }

    public ParkingManager getParkingManager() {
        return this.parkingManager;
    }

    public CarManager getCarManager() {
        return this.carManager;
    }
}
