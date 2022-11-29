package com.pgd.recruitingplatformservice.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Process")
public class ProcessEntity {
    /**
     * Date/Time of Cronjob execution.
     */
    @NotNull
    @Id
    @Column(nullable = false)
    private LocalDateTime dateTime;
}
