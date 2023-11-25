# 학습 관리 시스템(Learning Management System)
## 진행 방법
* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

---
## 🚀 1단계 - 레거시 코드 리팩터링

### 질문 삭제하기 요구사항
- 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
- 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.
- 답변이 없는 경우 삭제가 가능하다.
- 질문자와 답변글의 모든 답변자가 같은 경우 삭제가 가능하다.
- 질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경한다.
- 질문자와 답변자가 다른 경우 답변을 삭제할 수 없다.
- 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.

### 리팩터링 요구사항
- nextstep.qna.service.QnaService의 deleteQuestion()는 앞의 질문 삭제 기능을 구현한 코드이다. 이 메소드는 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드가 섞여 있다.
- QnaService의 deleteQuestion() 메서드에 단위 테스트 가능한 코드(핵심 비지니스 로직)를 도메인 모델 객체에 구현한다.
- QnaService의 비지니스 로직을 도메인 모델로 이동하는 리팩터링을 진행할 때 TDD로 구현한다.
- QnaService의 deleteQuestion() 메서드에 대한 단위 테스트는 src/test/java 폴더 nextstep.qna.service.QnaServiceTest이다. 도메인 모델로 로직을 이동한 후에도 QnaServiceTest의 모든 테스트는 통과해야 한다.

---
## 🚀 2단계 - 수강신청(도메인 모델)

### 수강 신청 기능 요구사항
- [ ] 과정(Course)은 기수 단위로 운영하며, 여러 개의 강의(Session)를 가질 수 있다.
- [ ] 강의는 시작일과 종료일을 가진다.
- [ ] 강의는 강의 커버 이미지 정보를 가진다.
  - [ ] 이미지 크기는 1MB 이하여야 한다.
  - [ ] 이미지 타입은 gif, jpg(jpeg 포함),, png, svg만 허용한다.
  - [ ] 이미지의 width는 300픽셀, height는 200픽셀 이상이어야 하며, width와 height의 비율은 3:2여야 한다.
- [ ] 강의는 무료 강의와 유료 강의로 나뉜다.
  - [ ] 무료 강의는 최대 수강 인원 제한이 없다.
  - [ ] 유료 강의는 강의 최대 수강 인원을 초과할 수 없다.
  - [ ] 유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.
- [ ] 강의 상태는 준비중, 모집중, 종료 3가지 상태를 가진다.
- [ ] 강의 수강신청은 강의 상태가 모집중일 때만 가능하다.
- [ ] 유료 강의의 경우 결제는 이미 완료한 것으로 가정하고 이후 과정을 구현한다.
  - [ ] 결제를 완료한 결제 정보는 payments 모듈을 통해 관리되며, 결제 정보는 Payment 객체에 담겨 반한된다.

### 프로그래밍 요구사항
- DB 테이블 설계 없이 도메인 모델부터 구현한다.
- 도메인 모델은 TDD로 구현한다.
  - 단, Service 클래스는 단위 테스트가 없어도 된다.
- 다음 동영상을 참고해 DB 테이블보다 도메인 모델을 먼저 설계하고 구현한다.
