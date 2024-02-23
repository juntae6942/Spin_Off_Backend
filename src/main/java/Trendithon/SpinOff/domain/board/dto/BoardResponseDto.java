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
    private Long bno;// id
    private String projectName;// 제목
    private String projectDescription;// 한줄 소개
    private String projectBackground;//배경
    private String projectFeatures;//주요기능
    private String projectImage;// 이미지 URL
    private String distribution;//배포 URL
    private String github;//깃허브 링크
    private String member1;
    private String member2;
    private String member3;
    private String member4;
    private String member5;
    private String category;//프로젝트 카테고리
    private String content;// 내용
    private Integer boardLike;// 좋아요
    private String writer;

    public static BoardResponseDto toDTO(Board board) throws JsonProcessingException {
        BoardResponseDto boardResponseDto = new BoardResponseDto();
        boardResponseDto.setBno(board.getBno());
        boardResponseDto.setProjectName(board.getProjectName());
        boardResponseDto.setProjectDescription(board.getProjectDescription());
        boardResponseDto.setDistribution(board.getDistribution());
        boardResponseDto.setProjectBackground(board.getProjectBackground());
        boardResponseDto.setProjectFeatures(board.getProjectFeatures());
        boardResponseDto.setProjectImage(board.getProjectImage());// 이미지 URL// 이미지 URL
        boardResponseDto.setMember1(board.getMember1());
        boardResponseDto.setMember2(board.getMember2());
        boardResponseDto.setMember3(board.getMember3());
        boardResponseDto.setMember4(board.getMember4());
        boardResponseDto.setMember5(board.getMember5());
        boardResponseDto.setGithub(board.getGithub());
        boardResponseDto.setCategory(board.getCategory());
        boardResponseDto.setWriter(board.getWriter());
        boardResponseDto.setBoardLike(board.getBoardLike());
        return boardResponseDto;
    }

    public static List<String> toList(String imageUrl) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(imageUrl, new TypeReference<List<String>>() {});
    }
}
