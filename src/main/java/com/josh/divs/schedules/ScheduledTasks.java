package com.josh.divs.schedules;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ScheduledTasks {
    
    
    @Scheduled(fixedRate = 20)
    public void scheduleTaskWithFixedRate() {
    	//UPDATE BEHAVIORS
    }

    public void scheduleTaskWithFixedDelay() {}

    public void scheduleTaskWithInitialDelay() {}

    public void scheduleTaskWithCronExpression() {}
}
