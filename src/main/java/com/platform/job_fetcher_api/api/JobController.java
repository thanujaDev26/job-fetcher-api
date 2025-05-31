package com.platform.job_fetcher_api.api;


import com.platform.job_fetcher_api.service.JobService;
import com.platform.job_fetcher_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
public class JobController {
    private final JobService service;

    @Autowired
    public JobController(JobService service) {
        this.service = service;
    }

    @GetMapping("/cached")
    public ResponseEntity<StandardResponse> getAllCachedJobs(){
        return new ResponseEntity<>(
                new StandardResponse(
                        200 , "Fetched" , service.getAllCachedJobs()
                ), HttpStatus.OK
        );
    }

    @PostMapping("/scrape")
    public ResponseEntity<StandardResponse> scarpeRemoteOKJobs(){
        return new ResponseEntity<>(
                new StandardResponse(
                        200 , "Fetched" , service.fetchRemoteOkJobs()
                ), HttpStatus.OK
        );
    }


}
