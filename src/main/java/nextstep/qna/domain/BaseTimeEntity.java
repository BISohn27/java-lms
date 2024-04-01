package nextstep.qna.domain;

import java.time.LocalDateTime;

public abstract class BaseTimeEntity {
    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime updatedDate;

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }
}
