package Tomato;

import Beans.Runtime_Info;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Tomato_upload {


    public static void upload(Runtime_Info info, Connection connection) {
        String sql = "select *\n" +
                "from 软件使用记录表\n" +
                "where T_end is null and App_Name = '" + info.getApp() + "'" +
                " and (T_start BETWEEN CONVERT(datetime, LEFT(GETDATE(), 10) + ' 00:00:00.000')    \n" +
                "      AND CONVERT(datetime, LEFT(GETDATE(), 10) + ' 00:00:00.000') + 1)   ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                sql = "insert into 软件使用记录表(App_Name, T_start,T_time) values (?,?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, info.getApp());
                preparedStatement.setString(2, info.getStart());
                preparedStatement.setString(3, info.getTime());
                System.out.println(info.getApp() + "已添加");
                preparedStatement.executeUpdate();
            } else {
                sql = "update 软件使用记录表 set T_time = '" + info.getTime() + "' " +
                        "where T_end is null and App_Name = '" + info.getApp() + "'" +
                        " and (T_start BETWEEN CONVERT(datetime, LEFT(GETDATE(), 10) + ' 00:00:00.000')    \n" +
                        "      AND CONVERT(datetime, LEFT(GETDATE(), 10) + ' 00:00:00.000') + 1)   ";
                preparedStatement = connection.prepareStatement(sql);
                System.out.println("已更新"+info.getApp()+"的时间");
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void check(Connection connection, List<String> info_name) throws SQLException {
        System.out.println("正在执行检查");
        String sql = "select *\n" +
                "from 软件使用记录表\n" +
                "where T_end is null and (T_start BETWEEN CONVERT(datetime, LEFT(GETDATE(), 10) + ' 00:00:00.000')    \n" +
                "      AND CONVERT(datetime, LEFT(GETDATE(), 10) + ' 00:00:00.000') + 1)   ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            if (!info_name.contains(resultSet.getString(1).trim())) {
                sql = "update 软件使用记录表 set T_end = getdate() where App_Name='" + resultSet.getString(1).trim() + "'";
                System.out.println(resultSet.getString(1) + "已结束");
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();
            }
        }
    }
}

