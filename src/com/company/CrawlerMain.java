package com.company;

import com.company.dao.DaoImpl;
import com.company.pojo.Districtlevel;
import com.company.pojo.Province;
import com.company.pojo.ToCity;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrawlerMain implements PageProcessor {

    private Site site = Site.me().setCharset("GBK").setRetryTimes(1000).setSleepTime(1000);

    private Province province = new Province();
    private ToCity toCity = new ToCity();
    private Districtlevel districtlevel = new Districtlevel();

    private List<Selectable> listA = null;

    private Map<String, String> map = new HashMap<>();


    private DaoImpl dao = new DaoImpl();

    public static void main(String[] args) {

        System.out.println("爬虫开始");
        Spider.create(new CrawlerMain()).addUrl("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2016/").run();
        System.out.println("爬虫结束");
    }

    @Override
    public void process(Page page) {
       /* Html html = page.getHtml();

        List<String> list = html.xpath("//tr[@class=\"provincetr\"]/td/a/@href").all();


        if (html.xpath("//table[@class=\"citytable\"]/tbody/tr[@class=\"citytr\"]/td/a/text()").match()) {

            List<String> all = html.xpath("//table[@class=\"citytable\"]/tbody/tr[@class=\"citytr\"]/td/a/text()").all();
            List<String> list2 = html.xpath("//table[@class=\"citytable\"]/tbody/tr[@class=\"citytr\"]/td/a/@href").all();

            for (int i = 0; i < list2.size(); i++) {
//                if (!all.get(i).contains("0")) map.put(list2.get(i), all.get(i));
                System.out.println(all.get(i));
            }

//            String name = map.get(page.getUrl().toString());
//
//            int provinceId = dao.getProvinceId(name);
//            System.out.println(name + ":" + provinceId);
            page.addTargetRequests(list2);
//            toCity.setProvinceId(provinceId);
            *//*for (String i : all) {
                if (!i.contains("0")){
                toCity.setToCityName(i);
                dao.getInsertToCity(toCity);
//                System.out.println(i);

                }
            }*//*


        } else if (html.xpath("//table[@class=\"towntable\"]/tbody/tr[@class=\"towntr\"]/td/a/text()").match()) {
            List<String> all = html.xpath("//table[@class=\"towntable\"]/tbody/tr[@class=\"towntr\"]/td/a/text()").all();

            String name = map.get(page.getUrl().toString());

            int toCityId = dao.getToCityId(name);
            System.out.println(name + ":" + toCityId);
            districtlevel.setToCityId(toCityId);
            for (String i : all) {
               *//* if (!i.contains("0")) {
                    districtlevel.setDistrictLevelName(i);
                    dao.getInsertDis(districtlevel);
                    System.out.println(i);

                }*//*
                System.out.println(i);
            }


        } else {
            listA =
            for (int i = 0; i < list.size(); i++) {
                map.put(list.get(i), listA.get(i).toString());
            }

            System.out.println("开始爬取市级数据：-----------------------------");
            page.addTargetRequests(list);
        }*/


//        System.out.println(nodes.size());
    }

    @Override
    public Site getSite() {
        return site;
    }
}
