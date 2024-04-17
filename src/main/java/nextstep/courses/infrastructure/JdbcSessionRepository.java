package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.courses.domain.enums.SessionStatus;
import nextstep.courses.domain.enums.SessionType;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Repository("SessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private static final String SESSION_MUST_HAVE_COVER_IMAGE_MESSAGE = "세션에 하나 이상의 커버 이미지가 등록되어야 합니다.";

    private static final String FIND_SESSION_BY_ID_SQL = "select " +
            "s.id as id" +
            ", s.start_date as startDate" +
            ", s.end_date as endDate" +
            ", s.status as status" +
            ", s.number_of_students as numberOfStudents" +
            ", s.max_number_of_students as maxNumberOfStudents" +
            ", s.price as price" +
            ", s.type as type" +
            ", s.is_recruiting as isRecruiting" +
            ", c.id as courseId " +
            ", c.title as courseTitle " +
            ", c.creator_id as courseCreatorId " +
            ", c.created_at as courseCreatedAt " +
            ", c.updated_at as courseUpdatedAt " +
            "from " +
            "session s " +
            "left join course c " +
            "on c.id = s.course_id " +
            "where s.id = ?";

    private final JdbcOperations jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("session")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Long saveSessionAndGetId(Session session) {
        Map<String, Object> sessionParameter = getSessionParameter(session);
        Number numberKey = simpleJdbcInsert.executeAndReturnKey(sessionParameter);

        return numberKey.longValue();
    }

    private Map<String, Object> getSessionParameter(Session session) {
        Map<String, Object> insertParam = new HashMap<>(getCommonSessionInsertParam(session));

        if (session instanceof PaySession) {
            PaySession paySession = (PaySession) session;
            insertParam.put("price", paySession.getPrice());
            insertParam.put("max_number_of_students", paySession.getMaxNumberOfStudents());
        }

        return insertParam;
    }

    private Map<String, Object> getCommonSessionInsertParam(Session session) {
        SessionDate sessionDate = session.getSessionDate();
        SessionStatus sessionStatus = session.getSessionStatus();
        SessionType sessionType = session.getType();
        return Map.of(
                "start_date", sessionDate.getStartDate(),
                "end_date", sessionDate.getEndDate(),
                "status", sessionStatus.name(),
                "number_of_students", session.getNumberOfStudents(),
                "type", sessionType.getType(),
                "is_recruiting", session.isRecruiting()
        );
    }

    @Override
    public Session findById(Long id) {
        RowMapper<Session> rowMapper = (rs, rowNum) -> {
            Course course = makeCourse(rs);

            String type = rs.getString("type");

            SessionInfos sessionInfos = makeSessionInfo(rs, type);

            if (SessionType.isPaySession(type)) {
                return PaySession.createFromData(
                        rs.getLong("id"),
                        course,
                        sessionInfos,
                        rs.getInt("numberOfStudents"),
                        rs.getInt("maxNumberOfStudents"),
                        rs.getLong("price")
                );
            }

            return FreeSession.createFromData(
                    rs.getLong("id"),
                    course,
                    sessionInfos,
                    rs.getInt("numberOfStudents")
            );
        };

        return jdbcTemplate.queryForObject(FIND_SESSION_BY_ID_SQL, rowMapper, id);
    }

    private SessionInfos makeSessionInfo(ResultSet rs, String type) throws SQLException {
        return SessionInfos.createFromData(
                SessionDate.of(toLocalDateTime(rs.getTimestamp("startDate")), toLocalDateTime(rs.getTimestamp("endDate"))),
                SessionStatus.findByStatusStr(rs.getString("status")).orElseThrow(),
                rs.getBoolean("is_recruiting")
        );
    }

    private Course makeCourse(ResultSet rs) throws SQLException {
        return new Course(
                rs.getLong("courseId"),
                rs.getString("courseTitle"),
                rs.getLong("courseCreatorId"),
                toLocalDateTime(rs.getTimestamp("courseCreatedAt")),
                toLocalDateTime(rs.getTimestamp("courseUpdatedAt"))
        );
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
