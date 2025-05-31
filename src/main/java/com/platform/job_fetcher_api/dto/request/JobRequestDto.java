package com.platform.job_fetcher_api.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobRequestDto {
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
