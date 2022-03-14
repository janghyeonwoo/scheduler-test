package com.example.scheduler.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class DateTime {
    @Column(name = "stdt")
    private LocalDateTime startDateTime;
    @Column(name = "eddt")
    private LocalDateTime endDateTime;
}
