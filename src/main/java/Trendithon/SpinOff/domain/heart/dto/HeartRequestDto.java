package Trendithon.SpinOff.domain.heart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HeartRequestDto {
    private Long memberId;
    private Long boardId;
}
