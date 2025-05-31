package com.platform.job_fetcher_api.dto.response;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobResponseDto {
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
