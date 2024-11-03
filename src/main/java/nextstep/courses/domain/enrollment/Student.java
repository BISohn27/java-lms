package nextstep.courses.domain.enrollment;

import nextstep.courses.type.EnrollmentState;
import nextstep.users.domain.NsUser;

import java.util.Objects;

public class Student {

    private final NsUser student;
    private EnrollmentState enrollmentState;

    public Student(NsUser student) {
        this(student, EnrollmentState.APPLY);
    }

    public Student(NsUser student, EnrollmentState enrollmentState) {
        this.student = student;
        this.enrollmentState = enrollmentState;
    }

    public void approve() {
        enrollmentState = enrollmentState.approve();
    }

    public void reject() {
        enrollmentState = enrollmentState.reject();
    }

    public boolean isRegistered() {
        return enrollmentState.isRegistered();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student1 = (Student) o;
        return Objects.equals(student, student1.student);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(student);
    }
}
