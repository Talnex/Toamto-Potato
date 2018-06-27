package Potato;

import Beans.Potato;
import Utils.DB.DBM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Potato_Dowload {
    public static List<Potato> select() {
        List<Potato> list = new ArrayList<>();
        String sql = "select * from 土豆表";

        Connection connection = DBM.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            int col = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                list.add(new Potato(new Date(resultSet.getDate(1).getTime()),
                        resultSet.getBoolean(4),
                        resultSet.getInt(5),
                        resultSet.getString(6).trim(),
                        resultSet.getString(2).trim(),
                        resultSet.getString(3).trim()
                ));
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
