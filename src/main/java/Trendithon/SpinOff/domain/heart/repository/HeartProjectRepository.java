package Trendithon.SpinOff.domain.heart.repository;

import Trendithon.SpinOff.domain.board.entity.Board;
import Trendithon.SpinOff.domain.heart.entity.HeartJobPosting;
import Trendithon.SpinOff.domain.heart.entity.HeartProject;
import Trendithon.SpinOff.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HeartProjectRepository extends JpaRepository<HeartProject, Long> {
    Optional<HeartProject> findByMemberAndBoard(Member member, Board board);
    List<HeartProject> findByMemberName(String memberName);

    List<HeartProject> findAllByMemberName(String memberName);
}
