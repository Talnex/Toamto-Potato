package Potato;

import Beans.Potato;
import Utils.DB.DBM;
import sun.security.pkcs11.Secmod;

import java.sql.*;
import java.time.LocalDateTime;

public class Potato_Upload {
    public static Connection connection = Potato_Dowload.connection;
    public static int insert(Potato potato) {
        String sql = "insert " +
                "into 土豆表(P_Date,P_Name,P_note,P_ok,F_color,F_ca) " +
                "values(?,?,?,?,?,?)";
        return go(sql,potato);
    }

    public static int update(Potato potato) throws SQLException {
        String sql = "update "+"土豆表\n" +
                "set " +
                "P_Date='" + potato.getDateString()+" "+potato.getTimeString()+":00.000'"+
                ",P_Name='" + potato.getName()+"'"+
                ",P_note='" + potato.getInfo()+"'"+
                ",P_ok=" + (potato.isIs_ok()?1:0)+
                ",F_color='" + potato.getColor()+"'"+
                ",F_ca='" + potato.getCa()+"'\n"+
                "where " +
                "IDENTITYCOL="+potato.getNo()
                ;
        return go(sql);
    }

    public static int delete(Potato potato){
        String sql = "delete from 土豆表\n" +
                "where IDENTITYCOL="+potato.getNo();
        int i = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            i = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static int go(String sql,Potato potato){
        int i = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setTimestamp(1,new Timestamp(potato.getDate().getTime()));
            preparedStatement.setString(2, potato.getName());
            preparedStatement.setString(3, potato.getInfo());
            preparedStatement.setString(4, String.valueOf(potato.isIs_ok() ? 1 : 0));
            preparedStatement.setString(5, String.valueOf(potato.getColor()));
            preparedStatement.setString(6, potato.getCa());
            i = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static int go(String sql) throws SQLException {
        int i = 0;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        i = preparedStatement.executeUpdate();
        preparedStatement.close();
        return i;
    }

}
