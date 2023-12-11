package nextstep.courses.domain.session.enrollment2;

import nextstep.courses.type.ProgressState;
import nextstep.courses.type.RecruitState;
import nextstep.courses.type.SessionType;

public class EnrollmentInfo {

    private ProgressState progressState;
    private RecruitState recruitState;

    private final Long amount;
    private final Long enrollmentMax;

    private final Enroll enroll;

    public EnrollmentInfo(ProgressState progressState, RecruitState recruitState, Long amount, Long enrollmentMax, Enroll enroll) {
        this.progressState = progressState;
        this.recruitState = recruitState;
        this.amount = amount;
        this.enrollmentMax = enrollmentMax;
        this.enroll = enroll;
    }

    public static EnrollmentInfo of(String progressState, String recruitState, Long amount, Long enrollmentMax, String sessionType) {
        return of(ProgressState.valueOf(progressState), RecruitState.valueOf(recruitState), amount, enrollmentMax, SessionType.valueOf(sessionType));
    }

    public static EnrollmentInfo of(ProgressState progressState, RecruitState recruitState, Long amount, Long enrollmentMax, SessionType sessionType) {
        return new EnrollmentInfo(progressState, recruitState, amount, enrollmentMax, Enroll.from(sessionType));
    }


    public ProgressState progressState() {
        return progressState;
    }

    public String progressStateValue() {
        return progressState.name();
    }

    public RecruitState recruitState() {
        return recruitState;
    }

    public Long amount() {
        return amount;
    }

    public Long enrollmentMax() {
        return enrollmentMax;
    }

    public Enroll enroll() {
        return enroll;
    }

    public void preparing() {
        progressState = ProgressState.PREPARING;
    }

    public void ongoing() {
        progressState = ProgressState.ONGOING;
    }

    public void end() {
        progressState = ProgressState.END;
    }

    public void recruiting() {
        recruitState = RecruitState.RECRUITING;
    }

    public String recruitStateValue() {
        return recruitState.name();
    }
}
