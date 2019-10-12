package com.example.shirodemo.test.zookeeper.masterZkClient;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.util.ArrayList;
import java.util.List;

public class MasterDemo {

    public static void main(String[] args) {
        List<WorkClient> workClients = new ArrayList<>();
        //
        try {
            for (int i = 0; i < 3; i++) {
                ZkClient zkClient = new ZkClient("172.16.6.111:2181", 5000, 5000, new SerializableSerializer());
                WorkClient workClient = new WorkClient(zkClient, "Client#" + i);
                workClients.add(workClient);
                workClient.start();
            }
            //
            Thread.sleep(22000);
            for (WorkClient workClient : workClients) {
                workClient.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
