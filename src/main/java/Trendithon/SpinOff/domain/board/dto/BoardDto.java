package Trendithon.SpinOff.domain.board.dto;

import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
public class BoardDto {
    private Long boardId;
    private String boardTitle; // 제목
    private String boardContext; // 내용
    private Integer boardLike; // 좋아요
    private List<String> imageUrl; // 이미지 URL
    private Long writer_id; // 작성자
}
