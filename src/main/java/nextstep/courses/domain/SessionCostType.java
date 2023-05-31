package nextstep.courses.domain;

public enum SessionCostType {
    FREE("FREE"), PAID("PAID");

    private final String code;

    SessionCostType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
