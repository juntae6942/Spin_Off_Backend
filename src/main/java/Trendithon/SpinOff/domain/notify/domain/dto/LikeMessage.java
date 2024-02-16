package Trendithon.SpinOff.domain.notify.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeMessage {
    private String liker;
    private String memberId;
}
