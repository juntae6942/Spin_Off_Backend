package Trendithon.SpinOff.domain.board.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bno;// id
    @Column(length = 50,nullable = false)
    private String title;// 제목
    @Column(length =20,nullable= false)
    private String description;// 한줄 소개
    @Column(length= 1000,nullable = false)
    private String projBackground;//배경
    @Column(length=100,nullable = false)
    private String mainFeature;//주요기능
    
    private String imageUrl;// 이미지 URL
    
    private String projUrl;//배포 URL
    
    private String githubUrl;//깃허브 링크
    private String memberPart;//팀원
    private String teamMembers;//팀원
    private String category;//프로젝트 카테고리
    @Column(length = 500,nullable = false)
    private String content;// 내용
    private Integer boardLike;// 좋아요
    private String writer;

//    package Trendithon.SpinOff.domain.board.entity;
//
//import jakarta.persistence.*;
//
//import lombok.Getter;
//import lombok.Setter;
//
//    @Entity
//    @Getter
//    @Setter
//    public class Board {
//        @Id
//        @GeneratedValue(strategy = GenerationType.AUTO)
//        private Long bno;// id
//        @Column(length = 50,nullable = false)
//        private String projectName;// 제목
//        @Column(length =20,nullable= false)
//        private String projectDescription;// 한줄 소개
//        @Column(length= 1000,nullable = false)
//        private String projectBackground;//배경
//        @Column(length=100,nullable = false)
//        private String projectFeatures;//주요기능
//
//        private String projectImage;// 이미지 URL
//
//        private String distribution;//배포 URL
//
//        private String github;//깃허브 링크
//        private String projectMembers;//팀원
//
//        private String projectParts;
//        // private String teamMembers;//팀원
//        private String category;//프로젝트 카테고리
//        private Integer boardLike;// 좋아요
//        private String writer;
//    }

}
