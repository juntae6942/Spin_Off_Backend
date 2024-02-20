package Trendithon.SpinOff.domain.heart.repository;

import Trendithon.SpinOff.domain.heart.entity.HeartJobPosting;
import Trendithon.SpinOff.domain.jobposting.entity.JobPosting;
import Trendithon.SpinOff.domain.member.entity.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeartJobPostingRepository extends JpaRepository<HeartJobPosting, Long> {
    Optional<HeartJobPosting> findByMemberAndJobPosting(Member member, JobPosting jobPosting);

    Optional<List<HeartJobPosting>> findByMember(Member member);
}
