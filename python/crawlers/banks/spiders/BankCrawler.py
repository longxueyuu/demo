# -*- coding: utf-8 -*-
import scrapy
from scrapy.http.request import Request
from ..items import BanksItem

urls = []

class BankSpider(scrapy.Spider):
    name = "bank"
    allowed_domains = ["zengin.ajtw.net"]
    start_urls = [
        "https://zengin.ajtw.net/"
    ]



    def parse(self, response):
        if response.url == "https://zengin.ajtw.net/":
            result = []
            args = []
            selector = response.xpath('//form[@action="ginkou.php"]/table/tbody')
            for x in selector:
                tds = x.xpath("tr/td[input]")
                for td in tds:
                    value = td.xpath("input/@value").extract()[0]
                    arg = td.xpath("input/@name").extract()[0]
                    print(value, arg)
                    result.append(value)
                    args.append(arg)

                    req = Request(url="https://zengin.ajtw.net/ginkou.php",
                                  method="POST",
                                  headers={"Content-Type": "application/x-www-form-urlencoded"},
                                  body=arg + "=" + value)
                    yield req
                # print(result, len(result))

        elif response.url == "https://zengin.ajtw.net/ginkou.php":
            urls.append(response.url)
            # print(response.url, len(urls))
            trs = response.xpath("//div[@class='c2']/table[@class='j0']/tbody/tr")
            for tr in trs:
                name = tr.xpath('td[1]/text()').extract()
                if name[0].endswith(u"金庫") or name[0].endswith(u"銀行"):
                    value = tr.xpath("td/form/button/@value").extract()[0]
                    arg = tr.xpath("td/form/button/@name").extract()[0]
                    # print name[0], len(urls),  value, arg

                    req = Request(url="https://zengin.ajtw.net/s1.php",
                                  method="POST",
                                  headers={"Content-Type": "application/x-www-form-urlencoded"},
                                  body=arg + "=" + value,
                                  meta={"bank_name": name[0]})
                    yield req

        elif response.url == "https://zengin.ajtw.net/s1.php":
            # print response.url, response.meta
            form_input = response.xpath("//div[@class='g0']/form[@action='shitenmeisai.php']/input[@type='hidden']")
            input_value = form_input.xpath("@value").extract()[0]
            input_arg = form_input.xpath("@name").extract()[0]
            trs = response.xpath("//div[@class='g0']/form[@action='shitenmeisai.php']/table[@class='g1']/tbody/tr")
            for tr in trs:
                tds = tr.xpath("td[input]")
                for td in tds:
                    td.xpath("input")
                    value = td.xpath("input/@value").extract()[0]
                    arg = td.xpath("input/@name").extract()[0]

                    # print(input_value, input_arg, value, arg)
                    req = Request(url="https://zengin.ajtw.net/shitenmeisai.php",
                                  method="POST",
                                  headers={"Content-Type": "application/x-www-form-urlencoded"},
                                  body=input_arg + "=" + input_value + "&" + arg + "=" + value,
                                  meta={"bank_name": response.meta["bank_name"]})
                    yield req
        elif response.url == "https://zengin.ajtw.net/shitenmeisai.php":
            # print response.url, response.meta
            trs = response.xpath("//div[@class='c2']/table[@class='j0']/tbody/tr[count(td)=4]")
            bank_name = response.meta["bank_name"]
            for tr in trs:
                branch_name = tr.xpath("td[1]/text()").extract()[0]
                branch_id = tr.xpath("td[3]/text()").extract()[0]
                # print
                item = BanksItem()
                item["bank_name"] = bank_name
                item["bank_branch_id"] = branch_id
                item["bank_branch_name"] = branch_name
                yield item







