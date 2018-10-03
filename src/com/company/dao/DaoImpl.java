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

/**
 * @author ASUS
 */
public class DaoImpl extends BaseDao implements Dao {


    /**
     * 添加区县表数据
     * @param districtlevel
     * @return
     */
    @Override
    public int districtlevel(Districtlevel districtlevel) {
        String sql = "INSERT INTO districtlevel(districtLevelName,toCityId) VALUES(?,?)";

        List<Object> list = new ArrayList<>();
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

    /**
     * 添加省份表数据
     * @param province
     * @return
     */
    @Override
    public int province(Province province) {

        String sql = "INSERT INTO province(provinceName) VALUES(?)";

        List<Object> list = new ArrayList<>();
        list.add(province.getProvinceName());

        try {
            intsert(sql, list);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * 添加市级表数据
     * @param toCity
     * @return
     */
    @Override
    public int tocity(ToCity toCity) {
        String sql = "INSERT INTO tocity(toCityName,provinceId) VALUES(?,?)";

        List<Object> list = new ArrayList<>();
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

    /**
     * 通过名称获取该名字对应的省份ID或市区ID
     * @param sql
     * @param name
     * @return
     */
    @Override
    public int getID(String sql, String name) {
        try {
            conn = Open();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);

            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Close();
        }

        return 0;
    }


    /**
     * 公共添加方法
     * @param sql
     * @param lists
     * @throws SQLException
     */
    private void intsert(String sql, List<Object> lists) throws SQLException {
        try {
            conn = Open();
            ps = conn.prepareStatement(sql);
            if (lists != null && lists.size() > 0) {
                for (int i = 0; i < lists.size(); i++) {

                    ps.setObject(i + 1, lists.get(i));
                }
                ps.executeUpdate();
            }
        } finally {

            Close();

        }
    }


}
