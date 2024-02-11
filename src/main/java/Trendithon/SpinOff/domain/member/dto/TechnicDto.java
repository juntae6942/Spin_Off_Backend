package Trendithon.SpinOff.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnicDto {
    String memberId;
    Set<String> technics;
}
