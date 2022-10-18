import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mysql.cj.jdbc.result.ResultSetMetaData;


public class Query {
	public static String[] toString(ResultSet rs) throws SQLException {
		String st = "";
		String TypeT = "";
		String NameT = "";
		java.sql.ResultSetMetaData resultSetMetaData = rs.getMetaData();
		for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {            
			NameT+=resultSetMetaData.getColumnName(i);   // Nome colonne
			TypeT+= resultSetMetaData.getColumnTypeName(i);  // Tipi colonne 
			if(i <= (resultSetMetaData.getColumnCount() - 1)) {
				NameT+= ",";
				TypeT += ",";
			}
		}

		String[] Type = TypeT.split(",");
		String[] Name = NameT.split(",");


		while(rs.next()) {
			for (int i = 0 ; i<Type.length ; i++) {
				if(Type[i].equalsIgnoreCase("CHAR") || Type[i].equalsIgnoreCase("DATE") || Type[i].equalsIgnoreCase("DATETIME"))
					st += rs.getString(Name[i]) + "&";
				else if(Type[i].equalsIgnoreCase("INT") ||  Type[i].equalsIgnoreCase("BIGINT"))
					st += rs.getInt(Name[i]) + "&";
				else if(Type[i].equalsIgnoreCase("DECIMAL"))
					st += rs.getFloat(Name[i]) + "&";
			}
			st+= "\n";
		}
		return st.split("\n");
	}


	public static String getNameColonne(ResultSet rs) throws SQLException {
		String NameT = "";
		java.sql.ResultSetMetaData resultSetMetaData = rs.getMetaData();
		for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {            
			NameT+=resultSetMetaData.getColumnName(i);   // Nome colonne
			if(i <= (resultSetMetaData.getColumnCount() - 1)) {
				NameT+= ",";
			}
		}
		return NameT;
	}
}



