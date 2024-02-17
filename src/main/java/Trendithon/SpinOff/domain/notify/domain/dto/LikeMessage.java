package Trendithon.SpinOff.domain.notify.domain.dto;

public record LikeMessage(
        String liker,
        Long projectId,
        Long jobPostingId
) {
}
