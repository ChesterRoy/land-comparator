package com.roy.landcomparator.datasource;

import com.roy.landcomparator.beans.Land;
import static com.roy.landcomparator.datasource.IProperties.DB_URL;
import static com.roy.landcomparator.datasource.IProperties.JDBC_DRIVER;
import static com.roy.landcomparator.datasource.IProperties.PASSWORD;
import static com.roy.landcomparator.datasource.IProperties.USER;
import com.roy.landcomparator.xlsx.ExcelFileManager;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DataBaseManager implements IQuery {

    private final String JDBC_DRIVER;
    private final String DB_URL;
    private final String USER;
    private final String PASSWORD;

    public DataBaseManager(String JDBC_DRIVER, String DB_URL, String USER, String PASSWORD) {
        this.JDBC_DRIVER = JDBC_DRIVER;
        this.DB_URL = DB_URL;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
    }

    public String getDB_URL() {
        return DB_URL;
    }

    public String getUSER() {
        return USER;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }
    
    

    public Connection getConnection() {

        Connection connection = null;

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }

    public Map<Double, Land> getAllLandsFromDB() throws SQLException {

        Map<Double, Land> landsFromDB = new HashMap<>();
        Connection connection = null;
        Statement statement = null;

        try {
            connection = getConnection();

            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SELECT_ALL_SQL);

            while (resultSet.next()) {

                Land land = new Land(
                        resultSet.getDouble("NKMSTART"),
                        resultSet.getDouble("NKMEND"),
                        resultSet.getDouble("NKRPLANYEAR"),
                        resultSet.getString("CPIPENAME").trim());

                landsFromDB.put(resultSet.getDouble("CIDNUM"), land);
            }
        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
        return landsFromDB;
    }

    public int KmUpdate(double CIDNUM, Land land) throws SQLException, IOException {

        int rowCount = 1;
        Connection connection = null;
        PreparedStatement statement = null;
        String cidnum = CharacterFilter.getCorrectCidnum(CIDNUM);

        try {
            connection = getConnection();

            statement = connection.prepareStatement(UPDATE_KM_SQL);
            statement.setDouble(1, land.getNKMSTART());
            statement.setDouble(2, land.getNKMEND());
            statement.setString(3, cidnum);

//            rowCount = statement.executeUpdate();

            if (rowCount > 0) {
                System.out.println(cidnum + "|| record is updated ||" + UPDATE_KM_SQL);
                ExcelFileManager.setCellColoreIfUpdated(CIDNUM, 5);
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
        return rowCount;
    }

    public int YearUpdate(double CIDNUM, Land land) throws SQLException, IOException {

        int rowCount = 1;
        Connection connection = null;
        PreparedStatement statement = null;
        String cidnum = CharacterFilter.getCorrectCidnum(CIDNUM);

        try {
            connection = getConnection();

            statement = connection.prepareStatement(UPDATE_YEAR_SQL);
            statement.setDouble(1, land.getNKRPLANYEAR());
            statement.setString(2, cidnum);

//            rowCount = statement.executeUpdate();

            if (rowCount > 0) {
                System.out.println(cidnum + "|| record is updated ||" + UPDATE_YEAR_SQL);
                ExcelFileManager.setCellColoreIfUpdated(CIDNUM, 9);
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
        return rowCount;
    }

    public int NameUpdate(double CIDNUM, Land land) throws SQLException, IOException {

        int rowCount = 1;
        Connection connection = null;
        PreparedStatement statement = null;
        String cidnum = CharacterFilter.getCorrectCidnum(CIDNUM);

        try {
            connection = getConnection();

            statement = connection.prepareStatement(UPDATE_CPIPENAME_SQL);
            statement.setString(1, land.getCPIPENAME());
            statement.setString(2, cidnum);

//            rowCount = statement.executeUpdate();

            if (rowCount > 0) {
                System.out.println(cidnum + "|| record is updated ||" + UPDATE_CPIPENAME_SQL);
                ExcelFileManager.setCellColoreIfUpdated(CIDNUM, 18);
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
        return rowCount;

    }
}
