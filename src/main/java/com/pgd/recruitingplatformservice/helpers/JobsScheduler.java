package com.pgd.recruitingplatformservice.helpers;

import com.pgd.recruitingplatformservice.entities.JobEntity;
import com.pgd.recruitingplatformservice.entities.ProcessEntity;
import com.pgd.recruitingplatformservice.models.responses.Job;
import com.pgd.recruitingplatformservice.repositories.JobEntityRepository;
import com.pgd.recruitingplatformservice.repositories.ProcessEntityRepository;
import com.pgd.recruitingplatformservice.services.CacheJobService;
import com.pgd.recruitingplatformservice.services.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Component
public class JobsScheduler {

    private static final Long TIME_HOURS_REFRESH_DATABASE = 11L;

    /**
     * Dependency injection of CacheJobService to get list of jobs from SR.
     */
    @Autowired
    private CacheJobService cacheJobService;
    /**
     * Dependency injection of JobEntityRepository to
     * refresh data stored in database.
     */
    @Autowired
    private JobEntityRepository jobEntityRepository;
    /**
     * Dependency injection of JobMapper to map data
     * names from SR to Careers format.
     */
    @Autowired
    private JobMapper jobMapper;
    /**
     * Dependency injection of CacheHandler to update cache object.
     */
    @Autowired
    private CacheHandler cacheHandler;
    /**
     * Logger object for JobsScheduler.class.getName().
     */
    static final Logger LOGGER =
            Logger
                    .getLogger(JobsScheduler.class.getName());
    /**
     * Dependency injection of JobEntityRepository to
     * refresh data stored in database.
     */
    @Autowired
    private ProcessEntityRepository processEntityRepository;
    /**
     * Get jobs from SR and save data on database and update cache.
     */
    //Run cron job 3 seconds after the server is started and every 11 hours
    @Scheduled(fixedDelayString = "PT11H", initialDelayString = "PT3S")
    public void getJobsFromSmartRecruiters() {
        String cronJobUUID = UUID.randomUUID().toString();
        String logMessage = "";
        LocalDateTime nowTime  = LocalDateTime.now();
        LocalDateTime lastExecutedTimeAdjusted =
                this.getLastExecutedTime()==null?
                        null:
                        this.getLastExecutedTime().plusHours( TIME_HOURS_REFRESH_DATABASE );

        logMessage = "------lastExecutedTimeAdjusted:[" + lastExecutedTimeAdjusted +
                "], nowtime:["+nowTime+"], " +
                TIME_HOURS_REFRESH_DATABASE + " hours pased?:[" +
                (lastExecutedTimeAdjusted!=null?nowTime.isAfter(lastExecutedTimeAdjusted):"first Time")+"]";
        LOGGER.info(logMessage);

        //-- this case is the first time and every [TIME_HOURS_REFRESH_DATABASE] hours this jobs is executed
        if ( lastExecutedTimeAdjusted == null || nowTime.isAfter( lastExecutedTimeAdjusted )){
            List<Job> jobs =
                    cacheJobService
                            .getJobListSr();

            List<JobEntity> jobEntities =
                    jobMapper
                            .jobsToJobsEntity(jobs);

            if (!jobEntities.isEmpty()) {
                logMessage = "------Start executing CronJob:[" +cronJobUUID +"]";
                LOGGER.info(logMessage);

                jobEntityRepository.deleteAll();

                List<JobEntity> jobEntitiesSaved =
                        jobEntityRepository
                                .saveAll(jobEntities);

                cacheHandler.updateCache(jobEntities);

                //-- save this date/time
                processEntityRepository.deleteAll();
                processEntityRepository.save(new ProcessEntity(LocalDateTime.now()));

                logMessage = "------End executing CronJob:[" + cronJobUUID + "]";
                LOGGER.info(logMessage);
            }
        }
        else {
            LOGGER.info("------just reload Cache!" );
            List<JobEntity> jobEntitiesDB =
                    jobEntityRepository.findAll();

            cacheHandler.updateCache(jobEntitiesDB);
        }
    }

    /**
     * Get last time were executed process
     */
    private LocalDateTime getLastExecutedTime(){
        Optional<ProcessEntity> opLastTime = processEntityRepository
                .findAll()
                .stream()
                .findFirst();
        if ( opLastTime.isPresent() ){
            return opLastTime.get().getDateTime();
        }
        return null;
    }
}
