package com.company.dao;


import com.company.pojo.Districtlevel;
import com.company.pojo.Province;
import com.company.pojo.ToCity;

public interface Dao {

    /**
     * 添加县级数据
     *
     * @param districtlevel
     * @return
     */
    int districtlevel(Districtlevel districtlevel);

    /**
     * 添加省级数据
     *
     * @param province
     * @return
     */
    int province(Province province);

    /**
     * 添加市级数据
     *
     * @param toCity
     * @return
     */
    int tocity(ToCity toCity);


    /**
     * 通过名字ID
     *
     * @param name
     * @return
     */
    int getID(String sql, String name);


}
