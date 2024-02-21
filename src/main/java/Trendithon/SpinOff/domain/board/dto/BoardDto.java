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
    //private List<String> memberPart;//팀원
    private String distribution;//배포 URL
    private String github;//깃허브 링크
    private List<String> projectMembers;//팀원
    private String category;//프로젝트 카테고리
    //private String content;// 내용
    private Integer boardLike;// 좋아요
    private Long writer; // 작성자

}
