package conexaobancodedados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {

    public static Connection getConnection(){
         try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql:clientecontato", "postgres", "1234567");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
