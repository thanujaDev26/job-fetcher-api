package com.platform.job_fetcher_api.service.impl;

import com.platform.job_fetcher_api.entity.Job;
import com.platform.job_fetcher_api.repo.JobRepo;
import com.platform.job_fetcher_api.service.JobService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepo _repo;

    @Autowired
    public JobServiceImpl(JobRepo _repo) {
        this._repo = _repo;
    }

    @Override
    public List<Job> fetchRemoteOkJobs() {
        List<Job> jobs = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://remoteok.com").userAgent("Mozilla").get();
            for (Element row : doc.select("tr.job")) {
                String title = row.attr("data-position");
                String company = row.attr("data-company");
                String link = "https://remoteok.com" + row.attr("data-href");
                String logo = row.selectFirst("img").absUrl("src");

                String id = UUID.nameUUIDFromBytes((title + company + link).getBytes()).toString();

                Job job = Job.builder()
                        .id(id)
                        .title(title)
                        .company(company)
                        .link(link)
                        .logoUrl(logo)
                        .source("RemoteOK")
                        .jobType(classifyJob(title))
                        .location("Remote")
                        .fetchedAt(LocalDateTime.now())
                        .build();

                if (!_repo.existsById(id)) {
                    _repo.save(job);
                }

                jobs.add(job);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobs;
    }

    @Override
    public String classifyJob(String title) {
        title = title.toLowerCase();
        if (title.contains("intern")) return "Intern";
        if (title.contains("associate")) return "Associate";
        if (title.contains("junior")) return "Junior";
        if (title.contains("senior")) return "Senior";
        if (title.contains("lead") || title.contains("tech lead")) return "Tech Lead";
        return "Unknown";
    }

    @Override
    public List<Job> getAllCachedJobs() {
        return _repo.findAll();
    }
}
