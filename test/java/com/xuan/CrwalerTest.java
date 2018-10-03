package com.xuan;

import com.company.Crawler;
import com.company.dao.DaoImpl;
import com.company.pojo.Districtlevel;
import com.company.pojo.Province;
import com.company.pojo.ToCity;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.*;

/**
 * <p>Description: 描述 </p>
 *
 * @Created with IDEA
 * @author: Yi-Xuan
 * @Date: 2018/10/3
 * @Time: 1:02
 */
public class CrwalerTest implements PageProcessor {
    private Site site = Site.me().setCharset("GBK").setRetryTimes(1000).setSleepTime(1000);

    private String name, sql;
    private Province province = null;
    private ToCity toCity = null;
    private Districtlevel districtlevel = null;
    private DaoImpl dao = new DaoImpl();

    private List<String> provinceListA = null;
    private List<String> provinceList = null;

    private List<String> toCityListA = null;
    private List<String> toCityList = null;

    private List<String> districtleveleListA = null;
    private List<String> districtleveleList = null;

    private Map<String, String> map = new HashMap<>();


    @Override
    public void process(Page page) {
        Html html = page.getHtml();


        if (html.xpath("//tr[@class=\"provincetr\"]/td/a/@href").match()) {
            System.out.println("开始爬取省份数据：");
            provinceListA = html.xpath("//tr[@class=\"provincetr\"]/td/a/@href").all();
            provinceList = html.xpath("//tr[@class=\"provincetr\"]/td/a/text()").all();

            for (int i = 0; i < provinceList.size(); i++) {

                System.out.println("名字：" + provinceList.get(i));
                System.out.println("链接：" + provinceListA.get(i));
                map.put(provinceListA.get(i), provinceList.get(i));

                province = new Province();
                province.setProvinceName(provinceList.get(i));

                dao.province(province);
            }


            page.addTargetRequests(provinceListA);

        } else if (html.xpath("//tr[@class=\"citytr\"]/td/a/text()").match()) {
            System.out.println("开始爬取市级数据:");

            toCityList = html.xpath("//tr[@class=\"citytr\"]/td//a/text()").all();
            toCityListA = html.xpath("//tr[@class=\"citytr\"]/td/a/@href").all();

            for (int i = 0; i < toCityListA.size(); i++) {
                for (int j = toCityListA.size() - 1; j > i; j--) {
                    if (toCityListA.get(j).equals(toCityListA.get(i))) {
                        toCityListA.remove(j);
                    }
                }
            }

            //通过for循环删除 集合类不需要的数据
            for (int i = 0; i < toCityList.size(); i++) {
                if (toCityList.get(i).contains("0")) {
                    toCityList.remove(i);
                }
            }

            name = map.get(page.getUrl().toString());
            System.out.println(name);

            System.out.println(toCityList.size());
            System.out.println(toCityListA.size());

            sql = "SELECT id from province where provinceName=?";

            int id = dao.getID(sql, name);
            for (int i = 0; i < toCityList.size(); i++) {

                System.out.println("名字：" + toCityList.get(i));
                System.out.println("链接：" + toCityListA.get(i));
                map.put(toCityListA.get(i), toCityList.get(i));


                toCity = new ToCity();
                toCity.setProvinceId(id);
                toCity.setToCityName(toCityList.get(i));

                dao.tocity(toCity);

            }

            page.addTargetRequests(toCityListA);

        } else if (html.xpath("//tr[@class=\"countytr\"]/td/a/text()").match()) {
            System.out.println("开始爬取区级数据:");

            districtleveleList = html.xpath("//tr[@class=\"countytr\"]/td//a/text()").all();
            districtleveleListA = html.xpath("//tr[@class=\"countytr\"]/td/a/@href").all();

            for (int i = 0; i < districtleveleListA.size(); i++) {
                for (int j = districtleveleListA.size() - 1; j > i; j--) {
                    if (districtleveleListA.get(j).equals(districtleveleListA.get(i))) {
                        districtleveleListA.remove(j);
                    }
                }
            }

            //通过for循环删除 集合类不需要的数据
            for (int i = 0; i < districtleveleList.size(); i++) {
                if (districtleveleList.get(i).contains("0")) {
                    districtleveleList.remove(i);
                }
            }


            name = map.get(page.getUrl().toString());
            System.out.println(name);

            System.out.println(districtleveleList.size());
            System.out.println(districtleveleListA.size());


            sql = "SELECT id from tocity where toCityName=?";

            int id = dao.getID(sql, name);

            for (int i = 0; i < districtleveleList.size(); i++) {

                System.out.println("名字：" + districtleveleList.get(i));
                System.out.println("链接：" + districtleveleListA.get(i));

                districtlevel = new Districtlevel();
                districtlevel.setToCityId(id);
                districtlevel.setDistrictLevelName(districtleveleList.get(i));

                dao.districtlevel(districtlevel);

            }
        }


    }

    @Override
    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {

        System.out.println("爬虫开始");
        Spider.create(new CrwalerTest()).addUrl("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2016/index.html").run();
//        Spider.create(new CrwalerTest()).addUrl("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2016/").run();
        System.out.println("爬虫结束");
    }


}
