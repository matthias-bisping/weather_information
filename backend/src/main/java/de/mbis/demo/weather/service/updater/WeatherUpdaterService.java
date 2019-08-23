package de.mbis.demo.weather.service.updater;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@Service
public class WeatherUpdaterService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Scheduler scheduler;
    private JobDetail currentJob;

    public WeatherUpdaterService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @PostConstruct
    public void init() throws SchedulerException {
        logger.info("Set updater to default values");
        update("Berlin", 60);
    }

    public void update(String city, int interval) throws SchedulerException {
        logger.info("WeatherUpdaterService: Update current weather to: {}, {} sec", city, interval);

        if(currentJob != null) {
            logger.info("Delete old job");
            scheduler.deleteJob(currentJob.getKey());
        }

        logger.info("Start new job");
        currentJob = jobDetail(city);
        scheduler.scheduleJob(currentJob, trigger(currentJob, interval));
    }

    private JobDetail jobDetail(String city) {
        return JobBuilder.newJob().ofType(WeatherUpdaterJob.class)
                .storeDurably()
                .usingJobData("city", city)
                .build();
    }

    private Trigger trigger(JobDetail jobDetail, int interval) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .startNow()
                .withSchedule(
                        simpleSchedule().repeatForever().withIntervalInSeconds(interval)
                ).build();
    }
}
