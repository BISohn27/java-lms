package nextstep.courses.domain.session;

import net.bytebuddy.asm.Advice;
import nextstep.courses.type.SessionType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;


public class SessionBuilderTest {

    @Test
    void create_free_session() {
        Session freeSession = freeSessionBuilder().build();

        assertThat(freeSession).isInstanceOf(FreeSession.class);
    }

    public static SessionBuilder freeSessionBuilder() {
        return SessionBuilder.builder()
                .name("무료강의")
                .coverImage("src/test/java/nextstep/courses/domain/session/file/image.png")
                .sessionType(SessionType.FREE)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(90));
    }

    @Test
    void create_paid_session() {
        Session paidSession = paidSessionBuilder().build();

        assertThat(paidSession).isInstanceOf(PaidSession.class);
    }

    public static SessionBuilder paidSessionBuilder() {
        return SessionBuilder.builder()
                .name("유료강의")
                .enrollment(30)
                .sessionFee(10000)
                .coverImage("src/test/java/nextstep/courses/domain/session/file/image.png")
                .sessionType(SessionType.PAID)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(90));
    }

    @Test
    void throw_exception_if_name_null_or_empty() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                freeSessionBuilder().name(null).build()).withMessageContaining("강의명");
        assertThatIllegalArgumentException().isThrownBy(() ->
                freeSessionBuilder().name(" ").build()).withMessageContaining("강의명");
    }

    @Test
    void throw_exception_if_cover_image_null() {
        SessionBuilder builder = SessionBuilder.builder()
                .name("무료강의")
                .sessionType(SessionType.FREE)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(90));

        assertThatIllegalArgumentException().isThrownBy(builder::build).withMessageContaining("강의 커버");
    }

    @Test
    void throw_exception_if_start_or_end_date_not_assign() {
        SessionBuilder endDate = SessionBuilder.builder()
                .coverImage("src/test/java/nextstep/courses/domain/session/file/image.png")
                .name("무료강의")
                .sessionType(SessionType.FREE)
                .endDate(LocalDateTime.now().plusDays(90));

        SessionBuilder startDate = SessionBuilder.builder()
                .coverImage("src/test/java/nextstep/courses/domain/session/file/image.png")
                .name("무료강의")
                .sessionType(SessionType.FREE)
                .startDate(LocalDateTime.now());

        assertThatIllegalArgumentException().isThrownBy(endDate::build).withMessageContaining("시작일");
        assertThatIllegalArgumentException().isThrownBy(startDate::build).withMessageContaining("종료일");
    }

    @Test
    void throw_exception_if_session_type_not_assign() {
        SessionBuilder builder = SessionBuilder.builder()
                .coverImage("src/test/java/nextstep/courses/domain/session/file/image.png")
                .name("무료강의")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(90));

        assertThatIllegalArgumentException().isThrownBy(builder::build).withMessageContaining("강의 타입");
    }

    @Test
    void throw_exception_if_enrollment_not_assign() {
        SessionBuilder builder = SessionBuilder.builder()
                .name("유료강의")
                .sessionFee(10000)
                .coverImage("src/test/java/nextstep/courses/domain/session/file/image.png")
                .sessionType(SessionType.PAID)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(90));

        assertThatIllegalArgumentException().isThrownBy(builder::build).withMessageContaining("수강 인원");
    }

    @Test
    void throw_exception_if_sessionFee_not_assign() {
        SessionBuilder builder = SessionBuilder.builder()
                .name("유료강의")
                .enrollment(30)
                .coverImage("src/test/java/nextstep/courses/domain/session/file/image.png")
                .sessionType(SessionType.PAID)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(90));

        assertThatIllegalArgumentException().isThrownBy(builder::build).withMessageContaining("수강료");
    }
}
