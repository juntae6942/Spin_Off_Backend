package Trendithon.SpinOff.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Information {
    private String memberId;
    private String introduce;
    private String job;
    private String specificDuty;
    private String link;
}
