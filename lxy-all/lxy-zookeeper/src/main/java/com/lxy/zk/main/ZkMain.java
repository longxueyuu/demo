package com.lxy.zk.main;

import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.io.FileUtils;
import org.apache.qpid.transport.util.Logger;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

/**
 * Created by lxy on 2016/12/17.
 */
public class ZkMain {

    public static final Logger log = Logger.get(ZkMain.class);

    public static void main(String[] args)
    {
        String filePath = "E:/home/localsftp/flume-conf-a1.properties";
        String fileContent = getFileString(filePath);
        System.out.print(fileContent);
        log.info(fileContent);
        if(fileContent != null && !"".equals(fileContent)) {
            ZkClient zkClient = new ZkClient("192.168.1.201:2181,192.168.1.202:2181,192.168.1.203:2181,192.168.1.204:2181", 10000);
            zkClient.setZkSerializer(new MyZkSerializer());
            String path = "/flume/producer1";
            boolean exist = zkClient.exists(path);
            if(exist)
            {
                Stat state = new Stat();
                zkClient.readData(path, state);
                int version = state.getVersion();
                zkClient.writeData(path, fileContent, version);
            } else {
                zkClient.createPersistent(path, true);
                zkClient.writeData(path,fileContent, -1);
            }

        } else {
            log.error("file content not exists or the content is empty!");
        }

    }

    public static String getFileString(String path)
    {
        File file = new File(path);
        if(!file.exists())
        {
            return null;
        }
        String fileContent = null;
        try {
            fileContent = FileUtils.readFileToString(file);
        } catch (IOException e) {
            log.error("fail to read file [" + path + "]:", e);
            return null;
        }
        return fileContent;
    }
}
