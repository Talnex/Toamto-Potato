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
    public static List<Potato> list;
    public static List<Potato> select(String now) {
        list = new ArrayList<>();
        String sql = "select *\n" +
                "        from 土豆表\n" +
                "        where ( P_Date)<=(\n" +
                "        SELECT DATEADD(wk, DATEDIFF(wk,0,'" +
                now +
                "'), 6)\n" +
                "        ) and P_Date>=(\n" +
                "        SELECT DATEADD(wk, DATEDIFF(wk,0,'" +
                now +
                "'), 0)\n" +
                "        )";

        Connection connection = DBM.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            int col = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                list.add(new Potato(new Date(resultSet.getTimestamp(1).getTime()),
                        resultSet.getBoolean(4),
                        resultSet.getString(5).trim(),
                        resultSet.getString(6).trim(),
                        resultSet.getString(2).trim(),
                        resultSet.getString(3).trim(),
                        resultSet.getInt(7)
                ));
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}


