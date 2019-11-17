package utilities;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

public class JDBCUtils {

	private static String jdbcURL = "jdbc:mysql://localhost:3306/scheduler";
	private static String jdbcUsername = "root";
	private static String jdbcPassword = "";

	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

//	public static Date getSQLDate(LocalDate date) {
//		return java.sql.Date.valueOf(date);
//	}

	public static String getDateString(Calendar date){
		SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");

		return sdf.format(date.getTime());
	}

	public static Calendar getDateCalendar(String dateString) throws ParseException {
		Calendar cal = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
		Date date = sdf.parse(dateString);

		cal.setTime(date);

		return cal;
	}

//	public static LocalDate getUtilDate(Date sqlDate) {
//		return sqlDate.toLocalDate();
//	}
}
