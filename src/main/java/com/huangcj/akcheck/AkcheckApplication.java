package com.huangcj.akcheck;

import com.huangcj.akcheck.common.Constant;
import jakarta.annotation.PostConstruct;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AkcheckApplication {
    @Autowired
    private CuratorFramework curator;

    public static void main(String[] args) {
        SpringApplication.run(AkcheckApplication.class, args);
    }

    @PostConstruct
    public void init() throws Exception {
        String path = Constant.REGISTER_NODE;
        curator.create()
                .creatingParentContainersIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath(path);
    }
}
