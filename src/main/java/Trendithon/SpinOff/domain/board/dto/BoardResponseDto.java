package Trendithon.SpinOff.domain.board.dto;

import Trendithon.SpinOff.domain.board.entity.Board;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class BoardResponseDto {
    private Long boardId;
    private String boardTitle;
    private String boardContext;
    private Integer boardLike;
    private List<String> imageUrl;
    private String writer;

    public static BoardResponseDto toDTO(Board board) throws JsonProcessingException {
        BoardResponseDto boardResponseDto = new BoardResponseDto();
        boardResponseDto.setBoardId(board.getBno());
        boardResponseDto.setBoardTitle(board.getTitle());
        boardResponseDto.setBoardContext(board.getContent());
        boardResponseDto.setBoardLike(board.getBoardLike());
        boardResponseDto.setImageUrl(toList(board.getImageUrl()));
        boardResponseDto.setWriter(board.getWriter());
        return boardResponseDto;
    }

    public static List<String> toList(String imageUrl) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(imageUrl, new TypeReference<List<String>>() {});
    }
}
