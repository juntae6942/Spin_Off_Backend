package Trendithon.SpinOff.domain.heart.entity;

import Trendithon.SpinOff.domain.board.entity.Board;
import Trendithon.SpinOff.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class HeartProject {
    @Id
    @GeneratedValue
    private Long heart_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bno")
    private Board board;

    @Builder
    public HeartProject(Long heart_id, Member member, Board board) {
        this.heart_id = heart_id;
        this.member = member;
        this.board = board;
    }
}
