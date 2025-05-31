package com.platform.job_fetcher_api.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Job {
    @Id
    private String id;

    private String title;
    private String company;
    private String link;
    private String source;

    private String logoUrl;
    private String jobType;
    private String location;

    @Column(length = 3000)
    private String description;

    private LocalDateTime fetchedAt;
}
