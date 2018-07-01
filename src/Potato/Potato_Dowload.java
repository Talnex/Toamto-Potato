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
    public static Connection connection = DBM.getConnection();

    public static List<Potato> select(String now) {
        list = new ArrayList<>();
        String sql = "select *\n" +
                "from 土豆表\n" +
                "where P_Date<DATEADD(dd, (9-CASE when datepart(weekday,'" +
                now +
                "') = 1 Then 8 ELSE datepart(weekday,'" +
                now +
                "')END),'" +
                now +
                "')\n" +
                " and P_Date>=DATEADD(dd, -(CASE when datepart(weekday,'" +
                now +
                "') = 1 Then 8 ELSE datepart(weekday,'" +
                now+
                "')END -2), '" +
                now +
                "')\n" +
                "order by P_Date";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

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


