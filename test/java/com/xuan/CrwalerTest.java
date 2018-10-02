package com.xuan;

import com.company.Crawler;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private List<String> provinceListA = null;
    private List<String> provinceList = null;

    private List<Selectable> toCityA = null;
    private List<Selectable> toCityList = null;

    private List<String> districtleveleListA = null;
    private List<String> districtlevelList = null;


    @Override
    public void process(Page page) {
        Html html = page.getHtml();


        if (html.xpath("//tr[@class=\"provincetr\"]/td/a/@href").match()) {
            System.out.println("开始爬取省份数据：");
            provinceListA = html.xpath("//tr[@class=\"provincetr\"]/td/a/@href").all();
            provinceList = html.xpath("//tr[@class=\"provincetr\"]/td/a/text()").all();

            for (int i = 0; i < provinceList.size(); i++) {

                System.out.println("名字：" + provinceList.get(i).toString());
                System.out.println("链接：" + provinceListA.get(i).toString());
            }

//            page.addTargetRequests(provinceListA);

        } else if (html.xpath("//tr[@class=\"citytr\"]/td/a/text()").match()) {
            System.out.println("开始爬取市级数据:");

            toCityList = html.xpath("//tr[@class=\"citytr\"]/td//a/text()").nodes();
            toCityA = html.xpath("//tr[@class=\"citytr\"]/td/a/@href").nodes();

            for (int i = 0; i < toCityA.size(); i++) {
                for (int j = toCityA.size() - 1; j > i; j--) {
                    if (toCityA.get(j).toString().equals(toCityA.get(i).toString())) {
                        toCityA.remove(j);
                    }
                }
            }


            for (int i = 0; i < toCityList.size(); i++) {

                    System.out.println("名字：" + toCityList.get(i).toString());
                    System.out.println("链接：" + toCityA.get(i).toString());
//                    toCityString.add(toCityA.get(i).toString());
//                    toCityName.add(toCityList.get(i).toString());

            }
//            System.out.println(toCityString.size());
//            System.out.println(toCityName.size());
//            page.addTargetRequests(toCityString);
        }


    }

    @Override
    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {

        System.out.println("爬虫开始");
        Spider.create(new CrwalerTest()).addUrl("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2016/43.html").thread(5).run();
//        Spider.create(new CrwalerTest()).addUrl("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2016/").run();
        System.out.println("爬虫结束");
    }


}
