import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Statement;

public class SQLDatabaseConnection {

	protected static String url = "jdbc:mysql://127.0.0.1:3306/Menzione"; //Indirizzo del mio database
	protected static String user = "root"; //User
	protected static String psw = "michelemenzione";//Pass
	private static String errore = "";



	// Connessione Al tuo database.

	public SQLDatabaseConnection() {

		//Stabilisco la connesione
		try{
			this.con = DriverManager.getConnection(url,user,psw);
			System.out.println("Connesso al database :)\n");

		}
		catch(Exception e){ 
			errore = e.getMessage();
			System.out.println("ERRORE : " + errore);
			System.out.println("Non sono connesso al database");
		}
	}
	public java.sql.Statement getStatment() throws SQLException {
		java.sql.Statement stmt = this.con.createStatement();
		return stmt;
	}
	public Connection getConnection() {
		return this.con;
	}
	private Connection con = null;
}
