package Trendithon.SpinOff.domain.board.repository;

import Trendithon.SpinOff.domain.board.entity.Board;
import Trendithon.SpinOff.domain.board.entity.Heart;
import Trendithon.SpinOff.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart,Long> {
    Optional<Heart> findByMemberAndBoard(Member member, Board board);
}
