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
    private String title;// 제목
    private String description;// 한줄 소개
    private String projBackground;//배경
    private String mainFeature;//주요기능
    private String imageUrl;// 이미지 URL
    private String projUrl;//배포 URL
    private String githubUrl;//깃허브 링크
    private List<String> memberPart;//팀원
    private String teamMember;//팀원
    private String category;//프로젝트 카테고리
    private String content;// 내용
    private Integer boardLike;// 좋아요
    private String writer;

    public static BoardResponseDto toDTO(Board board) throws JsonProcessingException {
        BoardResponseDto boardResponseDto = new BoardResponseDto();
        boardResponseDto.setBno(board.getBno());
        boardResponseDto.setTitle(board.getTitle());
        boardResponseDto.setDescription(board.getDescription());
        boardResponseDto.setProjBackground(board.getProjBackground());
        boardResponseDto.setMainFeature(board.getMainFeature());
        boardResponseDto.setImageUrl(board.getImageUrl());// 이미지 URL
        boardResponseDto.setProjUrl(board.getProjUrl());
        boardResponseDto.setGithubUrl(board.getGithubUrl());
        boardResponseDto.setMemberPart(toList(board.getMemberPart()));
        boardResponseDto.setTeamMember(board.getTeamMembers());
        boardResponseDto.setCategory(board.getCategory());
        boardResponseDto.setContent(board.getContent());
        boardResponseDto.setBoardLike(board.getBoardLike());
        boardResponseDto.setWriter(board.getWriter());
        return boardResponseDto;
    }

    public static List<String> toList(String imageUrl) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(imageUrl, new TypeReference<List<String>>() {});
    }
}
