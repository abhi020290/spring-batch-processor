package com.springboot.springbatchprocessor.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;

@RestController
@Slf4j
public class SalesResource {

    @Autowired
    public JobLauncher jobLauncher;

    @Autowired
    public Job job;

    @GetMapping("/launcher")
    public String launchJob() {
        String jobStatus = null;
        HashMap<String, JobParameter> hashMap = new HashMap<>();
        hashMap.put("Job", new JobParameter("Started"));
        JobParameters jobParameters = new JobParameters(hashMap);
        try {
            JobExecution run = jobLauncher.run(job, jobParameters);
            while(run.isRunning()){
                log.info("................");
            }
            log.info("Job status "+run.getStatus());
            jobStatus = run.getEndTime().toString();
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            e.printStackTrace();
        }

        return jobStatus;
    }

}
