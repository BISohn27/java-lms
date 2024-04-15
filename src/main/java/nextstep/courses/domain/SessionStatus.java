package nextstep.courses.domain;

public enum SessionStatus {
    PREPARING,
    ENROLLING,
    FINISH;

    public boolean isEnrolling(){
        return this == ENROLLING;
    }
}
