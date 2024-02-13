package Trendithon.SpinOff.domain.board.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class BoardDto {
    private Long boardId;
    private String boardTitle; // 제목
    private String boardContext; // 내용
    private Integer boardLike; // 좋아요
    private List<String> imageUrl; // 이미지 URL
    private Long memberId; // 작성자
}
