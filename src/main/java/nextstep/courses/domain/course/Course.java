package nextstep.courses.domain.course;

import java.time.LocalDateTime;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionName;
import nextstep.courses.domain.session.Sessions;
import nextstep.payments.domain.Payment;

public class Course {
    private Long id;

    private String title;

    private Long creatorId;

    private Sessions sessions;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Course() {
    }

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, new Sessions(), LocalDateTime.now(), null);
    }

    public Course(Long id, String title, Long creatorId, Sessions sessions, LocalDateTime createdAt,
        LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.sessions = sessions;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getTitle() {
        return title;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", creatorId=" + creatorId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public boolean registerSession(SessionName sessionName, Payment payment) {
        Session session = sessions.findSession(sessionName);

        if (session.isRegistrationAvailable() && session.isPaymentAmountSameTuitionFee(payment)) {
            session.addRegistrationCount();
            return true;
        }

        return false;
    }

    public void addSession(SessionName sessionName, Session session) {
        sessions.addSession(sessionName, session);
    }
}
