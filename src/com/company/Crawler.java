package com.company;

import com.company.dao.DaoImpl;
import com.company.pojo.Districtlevel;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Crawler implements PageProcessor {

    private Site site = Site.me().setCharset("GBK").setRetryTimes(1000).setSleepTime(1000);
    private List<String> listA = new ArrayList<>();
    private Map<String, String> map = new HashMap<>();
    private DaoImpl dao = new DaoImpl();
    private Districtlevel districtlevel = new Districtlevel();

    public static void main(String[] args) {

        System.out.println("爬虫开始");
        Spider.create(new Crawler()).addUrl("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2016/14.html").thread(10).run();
        System.out.println("爬虫结束");
    }

    @Override
    public void process(Page page) {
        Html html = page.getHtml();

        /*if (!html.xpath("//table[@class=\"countytable\"]/tbody/tr[@class=\"countytr\"]/td/a/text()").match()) {
            List<String> all = html.xpath("//table[@class=\"citytable\"]/tbody/tr[@class=\"citytr\"]/td/a/text()").all();
            List<String> list2 = html.xpath("//table[@class=\"citytable\"]/tbody/tr[@class=\"citytr\"]/td/a/@href").all();

            System.out.println(all.size());
            System.out.println(list2.size());

            for (int i = 0; i < all.size(); i++) {
                if (!all.get(i).contains("0")) {
                    listA.add(list2.get(i));
                    map.put(list2.get(i), all.get(i));
                }
            }
            System.out.println("开始区级数据爬取：");
            page.addTargetRequests(listA);
        } else {
            List<String> all = html.xpath("//table[@class=\"countytable\"]/tbody/tr[@class=\"countytr\"]/td/a/text()").all();

            String name = map.get(page.getUrl().toString());

            int toCityId = dao.getToCityId(name);
            System.out.println(name + ":" + toCityId);
            districtlevel.setToCityId(toCityId);
            for (String i : all) {
                if (!i.contains("0")) {
                    districtlevel.setDistrictLevelName(i);
                    dao.getInsertDis(districtlevel);
                    System.out.println(i);

                }
            }

        }*/

    }

    @Override
    public Site getSite() {
        return site;
    }
}
