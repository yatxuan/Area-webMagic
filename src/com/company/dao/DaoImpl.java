package com.company.dao;


import com.company.pojo.Districtlevel;
import com.company.pojo.Province;
import com.company.pojo.ToCity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoImpl implements Dao {

    private static BaseDao baseDao = BaseDao.getBaseDao();
    private static PreparedStatement ps = null;
    private static Connection connection = null;
    private static ResultSet rs = null;
    private List<Object> list = new ArrayList<>();


    @Override
    public int districtlevel(Districtlevel districtlevel) {
        String sql = "INSERT INTO districtlevel(districtLevelName,toCityId) VALUES(?,?)";

        list.add(districtlevel.getDistrictLevelName());
        list.add(districtlevel.getToCityId());

        try {
            intsert(sql, list);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int province(Province province) {

        String sql = "INSERT INTO province(provinceName) VALUES(?)";

        list.add(province.getProvinceName());

        try {
            intsert(sql, list);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int tocity(ToCity toCity) {
        String sql = "INSERT INTO tocity(toCityName,provinceId) VALUES(?,?)";

        list.add(toCity.getToCityName());
        list.add(toCity.getProvinceId());

        try {
            intsert(sql, list);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int getID(String sql, String name) {
        try {
            PreparedStatement ps = baseDao.getConnection().prepareStatement(sql);
            ps.setString(1, name);

            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return 0;
    }


    private void intsert(String sql, List<Object> lists) throws SQLException {
        try {
            ps = baseDao.getConnection().prepareStatement(sql);

            if (lists != null && lists.size() > 0) {
                for (int i = 0; i < lists.size(); i++) {

                    ps.setObject(i + 1, lists.get(i));
                }
                ps.executeUpdate();
            }
        } finally {

            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }

        }
    }


}
