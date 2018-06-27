package Potato;

import Beans.Potato;
import Utils.DB.DBM;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Potato_Upload {
    public static int insert(Potato potato) {
        Connection connection = DBM.getConnection();
        int i = 0;
        String sql = "insert " +
                "into 土豆表(P_Date,P_Name,P_note,P_ok,F_rate,F_ca) " +
                "values(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, new Date(potato.getDate().getTime()));
            preparedStatement.setString(2, potato.getName());
            preparedStatement.setString(3, potato.getInfo());
            preparedStatement.setString(4, String.valueOf(potato.isIs_ok() ? 1 : 0));
            preparedStatement.setString(5, String.valueOf(potato.getRank()));
            preparedStatement.setString(6, potato.getCa());
            i = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }

}
