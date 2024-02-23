package Trendithon.SpinOff.domain.board.dto;

import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
public class BoardDto {
    private Long bno;// id
    private String projectName;// 제목
    private String projectDescription;// 한줄 소개
    private String projectBackground;//배경
    private String projectFeatures;//주요기능
    private String projectImage;// 이미지 URL
    private String distribution;//배포 URL
    private String github;//깃허브 링크
    private List<String> selectedParts;
    private List<String> projectMembers;
    private String category;//프로젝트 카테고리
    private Integer boardLike;// 좋아요
    private String writer; // 작성자
}
