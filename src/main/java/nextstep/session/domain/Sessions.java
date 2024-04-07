package nextstep.session.domain;

import java.util.ArrayList;
import java.util.List;

public class Sessions {

    private final List<Session> sessions;

    public Sessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public Sessions() {
        this.sessions = new ArrayList<>();
    }

    public void add(Session session) {
        this.sessions.add(session);
    }

    public int countSessions() {
        return this.sessions.size();
    }
}
