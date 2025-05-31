package com.platform.job_fetcher_api.service;

import com.platform.job_fetcher_api.entity.Job;

import java.util.List;

public interface JobService {
    List<Job> fetchRemoteOkJobs();

    String classifyJob(String title);

    List<Job> getAllCachedJobs();
}
