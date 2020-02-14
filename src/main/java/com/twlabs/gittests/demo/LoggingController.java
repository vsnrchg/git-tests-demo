package com.twlabs.gittests.demo;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

//import org.apache.logging.log4j.LogManager;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.LogManager;
import org.slf4j.MDC;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
public class LoggingController {

    static int counter =0;

//    Logger log = LoggerFactory.getLogger(LoggingController.class);
//    Logger log = LogManager.getLogger(LoggingController.class);

    @RequestMapping("/")
    public String index() throws ExecutionException, InterruptedException {
        MDC.put("request.id", "REQ-" + String.valueOf(counter++));
        log.trace("A TRACE Message");
        log.debug("A DEBUG Message");
        log.info("An INFO Message");
        log.warn("A WARN Message");
        log.error("An ERROR Message");

        runAway();
        CompletableFuture<Integer> cf = logAsync();
        log.info("after calling logAsync");
        log.info("logAsync returned " + cf.get());

        MDC.clear();
        return "Howdy! Check out the Logs to see the output...";
    }

    @Async
    public  void runAway() throws InterruptedException {
        log.info("Entering runAway and sleeping");
        Thread.sleep(2000);
        log.info("Waking up...runAway");
        log.error("An ERROR Message from runAway function");
    }

    @Async
    public CompletableFuture<Integer> logAsync() throws InterruptedException {

        log.info("Entering logAsync and sleeping");
        Thread.sleep(2000);
        log.info("Waking up...");
        log.error("An ERROR Message from async function");
            return CompletableFuture.completedFuture(1);
    }
}

