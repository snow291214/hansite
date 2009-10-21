package ru.sgnhp.sheduling;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class DoSimpleJobQuartzBean extends QuartzJobBean {

    private SimpleWorkService simpleWorkService;

    @Override
    protected void executeInternal(JobExecutionContext ctx)
            throws JobExecutionException {
        simpleWorkService.doSomeJob();
    }

    public void setSimpleWorkService(SimpleWorkService simpleWorkService) {
        this.simpleWorkService = simpleWorkService;
    }
}
