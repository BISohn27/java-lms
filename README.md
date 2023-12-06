# 학습 관리 시스템(Learning Management System)
## 진행 방법
* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

### TODO


### DONE
- [X] Question.delete()에서 질문자와 loginUser가 같은지 체크
- [X] Question.delete()에서 답변이 없는지 체크
- [X] Question.delete()에서 답변이 있다면 Answer.delete() 호출
- [X] Question.delete()에서 위 체크로직이 이상 없을 경우 deleted 필드를 true로 변경
- [X] Answer 내부로 delete() 로직 이동
- [X] Answer.delete()에서 답변자와 loginUser가 같은지 체크
- [X] Question 내부로 delete() 로직 이동
- [X] QnAService 에서 delete() 로직 제거

- [X] Session 구현. Session은 시작일/종료일을 가진다.
- [X] Session은 강의 커버 이미지를 가진다.
  - [X] 커버 이미지의 크기는 1MB 이하여야 한다.
  - [X] 커버 이미지의 확장자는 gif, jpg(jpeg 포함),, png, svg만 허용한다.
  - [X] 커버 이미지의 width는 300픽셀, height는 200픽셀 이하여야 하며, width와 height의 비율은 3:2여야 한다.
- [X] Session은 상태가 존재한다. (준비중 /  모집중 / 종료)
- [X] Session은 무료 강의와 유료 강의로 나뉜다.
  - [X] 무료 Session은 최대 수강 인원 제한이 없다.
  - [X] 유료 Session은 강의 최대 수강 인원을 초과할 수 없다.
  - [X] 유료 Session은 수강료가 있으며, 
  - [X] 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.
- [X] 강의 수강신청은 강의 상태가 모집중일 때만 가능하다.
- [X] 유료 강의의 경우 결제는 이미 완료한 것으로 가정하고 이후 과정을 구현한다.
  - [X] 결제를 완료한 결제 정보는 payments 모듈을 통해 관리되며, 결제 정보는 Payment 객체에 담겨 반환된다.
- [X] Course 구현. Course 는 N개의 Session을 가진다.
- [X] Session의 시작일/종료일은 기간(SessionPeriod)객체로 관리한다.