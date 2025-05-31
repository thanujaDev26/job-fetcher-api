package com.platform.job_fetcher_api.repo;


import com.platform.job_fetcher_api.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface JobRepo extends JpaRepository<Job,String> {

}
