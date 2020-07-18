# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html

import json

class BanksPipeline(object):
    def __init__(self):
        self.bank2Branches = {}
        self.bank_lists = []
        self.file = open('banks.json', 'wb')
        self.filetxt = open('banks.txt', 'wb')

    def process_item(self, item, spider):
        bank_name = item["bank_name"]
        branch_name = item["bank_branch_name"]
        branch_id = item["bank_branch_id"]

        s = u"\t".join([bank_name, branch_name, branch_id])
        print (s)
        s = s + u"\n"
        self.filetxt.write(s.encode("utf-8"))


        if len(branch_name) > 0 and len(branch_id) == 3:
            if bank_name in self.bank2Branches:
                bs = self.bank2Branches[bank_name]
                bs.append({"bank_name": bank_name, "branch_name": branch_name, "branch_id": branch_id})
            else:
                ls = [{"bank_name": bank_name, "branch_name": branch_name, "branch_id": branch_id}]
                self.bank2Branches[bank_name] = ls
        return item

    def close_spider(self, spider):
        count = 0
        bank_obj = {}
        for k, v in self.bank2Branches.items():
            bank_obj["bank_name"] = k
            bank_obj["branches"] = v
            self.bank_lists.append(bank_obj)
            count = count + len(v)

        print(self.bank2Branches)

        banks_json_str = json.dumps(self.bank_lists)
        print("-" * 200, len(self.bank2Branches), count)
        print (banks_json_str)
        self.file.write(banks_json_str)
        self.file.close()
        print("-" * 200, len(self.bank2Branches), count)
