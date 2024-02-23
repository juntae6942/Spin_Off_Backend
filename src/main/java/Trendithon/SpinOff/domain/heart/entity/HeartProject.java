package Trendithon.SpinOff.domain.heart.entity;

import Trendithon.SpinOff.domain.board.entity.Board;
import Trendithon.SpinOff.domain.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class HeartProject {
    @Id
    @GeneratedValue
    private Long heart_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String memberName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bno")
    private Board board;

    @Builder
    public HeartProject(Long heart_id, Member member, Board board) {
        this.heart_id = heart_id;
        this.member = member;
        this.memberName = member.getMemberId();
        this.board = board;
    }
}
