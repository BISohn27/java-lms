package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {

    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories() {
        this.deleteHistories = new ArrayList<>();
    }

    public void add(final Question question) {
        question.setDeleted(true);
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()));
        for (Answer answer : question.getAnswers()) {
            answer.setDeleted(true);
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }
}
