
package com.eams;

import com.eams.dao.AlertDAO;
import com.eams.dao.UserDAO;
import com.eams.model.Alert;
import com.eams.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("EAMS - Demo Start");
        try {
            UserDAO userDAO = new UserDAO();
            User admin = userDAO.findByUsername("admin");
            if (admin == null) {
                System.out.println("Admin user not found. Creating a default admin with username 'admin' and password 'admin123' (hashed)");
                String hashed = BCrypt.hashpw("admin123", BCrypt.gensalt());
                User u = new User("admin", hashed, "ADMIN");
                int id = userDAO.createUser(u);
                System.out.println("Created admin with id: " + id);
                admin = userDAO.findByUsername("admin");
            }

            AlertDAO alertDAO = new AlertDAO();
            Alert alert = new Alert("Roadblock", "Major accident on Highway 7. Avoid area.", "HIGH", admin.getId());
            int id = alertDAO.createAlert(alert);
            System.out.println("Created alert with id: " + id);

            System.out.println("Pending alerts:");
            List<Alert> pending = alertDAO.listPendingAlerts();
            for (Alert a : pending) {
                System.out.println(a);
            }

            boolean ok = alertDAO.markBroadcasted(id);
            System.out.println("Marked broadcasted: " + ok);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
