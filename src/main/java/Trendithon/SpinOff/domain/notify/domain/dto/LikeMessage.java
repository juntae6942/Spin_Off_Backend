package Trendithon.SpinOff.domain.notify.domain.dto;

public record LikeMessage(
        String memberId,
        String comment,
        String projectTitle,
        String imageUrl
) {
}
