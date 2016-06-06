package traceback;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("taskJob")  
public class TimerTask {  
    @Scheduled(cron = "5/10 * * * * ?")  
    public void run() {  
        System.out.println("任务成功运行。。。");  
    }  
}