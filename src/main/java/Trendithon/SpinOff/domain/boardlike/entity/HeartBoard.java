package Trendithon.SpinOff.domain.boardlike.entity;

import Trendithon.SpinOff.domain.board.entity.Board;
import Trendithon.SpinOff.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity

public class HeartBoard {
    @Id
    @GeneratedValue
    private Long heart_id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name ="member_id")
    private Member member;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="board_id")
    private Board board;

    @Builder
    public HeartBoard(Long heart_id, Member member, Board board) {
        this.heart_id = heart_id;
        this.member = member;
        this.board = board;
    }
}
