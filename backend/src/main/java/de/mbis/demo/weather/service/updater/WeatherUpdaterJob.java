package de.mbis.demo.weather.service.updater;

import de.mbis.demo.weather.service.provider.OpenWeatherProvider;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class WeatherUpdaterJob implements Job {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private OpenWeatherProvider provider;
    private WeatherUpdateRepository repository;

    public WeatherUpdaterJob(OpenWeatherProvider provider, WeatherUpdateRepository repository) {
        this.provider = provider;
        this.repository = repository;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("Start Weather Updater Job");

        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String city = jobDataMap.getString("city");

        try {
            repository.setCurrentWeather(provider.getCurrentWeatherByCityName(city));
        } catch (Exception ex) {
            throw new JobExecutionException("Update Job is canceled", ex);
        }
        logger.info("Finish Weather Updater Job");
    }
}
