//package com.jainva.api.cron;
//
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@Component
//public class ScheduledJob {
//
//
//    @Scheduled(cron = "*/1 * * * * *")
////    @Scheduled(fixedDelay = 1000)
//    public void fixedRateSch() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//
//        Date now = new Date();
//        String strDate = sdf.format(now);
//        System.out.println("Fixed Rate scheduler:: " + strDate);
//    }
//
//
//}
