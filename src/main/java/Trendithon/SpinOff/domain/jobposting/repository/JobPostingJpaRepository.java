package Trendithon.SpinOff.domain.jobposting.repository;

import Trendithon.SpinOff.domain.jobposting.entity.JobPosting;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostingJpaRepository extends JpaRepository<JobPosting, Long> {

    List<JobPosting> findByDeadLineAfterAndJobTitleContainingOrCompanyNameContaining(LocalDateTime deadLine,
                                                                                     String jobTitle,
                                                                                     String name);

    List<JobPosting> findByDeadLineAfter(LocalDateTime localDateTime);
}
