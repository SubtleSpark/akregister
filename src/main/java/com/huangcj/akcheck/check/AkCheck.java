package com.huangcj.akcheck.check;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * @Author: HuangChengJun
 * @Date: 2023/7/19 10:17
 * @Description:
 */
@Service
@Slf4j
public class AkCheck {
    @Value("${aktool-address}")
    private String akAddress;
    @Autowired
    private CuratorFramework curator;
    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(cron = "0/2 * * * * ?")
    public void checkAkStatus() {
        try {
            ResponseEntity<Object> entity = restTemplate.getForEntity(akAddress + "/version", Object.class, Collections.emptyMap());
            if (entity.getStatusCode().is2xxSuccessful()) {
                log.info("ak is running");
                return;
            }
        } catch (Exception e) {

        }

        // todo 异常处理
        log.error("ak service error");
//        curator.delete()

    }
}
