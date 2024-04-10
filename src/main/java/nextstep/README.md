# 학습 관리 시스템 (LMS) 1차

## 수강 신청 기능 요구사항
- 과정(Course)은 기수 단위로 운영하며, 여러 개의 강의(Session)를 가질 수 있다.
- 강의는 시작일과 종료일을 가진다.
- 강의는 강의 커버 이미지 정보를 가진다.
  - 이미지 크기는 1MB 이하여야 한다.
  - 이미지 타입은 gif, jpg(jpeg 포함), png, svg만 허용한다.
  - 이미지의 width는 300픽셀, height는 200픽셀 이상이어야 하며, width와 height의 비율은 3:2여야 한다.
- 강의는 무료 강의와 유료 강의로 나뉜다.
  - 무료 강의는 최대 수강 인원 제한이 없다. 
  - 유료 강의는 강의 최대 수강 인원을 초과할 수 없다. 
  - 유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.
- 강의 상태는 준비중, 모집중, 종료 3가지 상태를 가진다. 
  - 강의 수강신청은 강의 상태가 모집중일 때만 가능하다. 
  - 유료 강의의 경우 결제는 이미 완료한 것으로 가정하고 이후 과정을 구현한다.
- 결제를 완료한 결제 정보는 payments 모듈을 통해 관리되며, 결제 정보는 Payment 객체에 담겨 반한된다.

## 프로그래밍 요구사항
- DB 테이블 설계 없이 도메인 모델부터 구현한다.
- 도메인 모델은 TDD로 구현한다.
- 단, Service 클래스는 단위 테스트가 없어도 된다.
- DB 테이블보다 도메인 모델을 먼저 설계하고 구현한다.

### TODO LIST
- 과정 (Course) 
  - [ ] 과정은 기수 단위로 운영한다. -> id
  - [ ] Course : Session = 1 : N
- 이미지 (Image)
  - [x] image.size < 1MB
  - [x] image.type in `gif`, `jpg`, `png`, `svg`
  - [x] image.width >= 300
  - [x] image.height >= 200
  - [x] image.width / image.height == 3 / 2
- 강의 (Session)
  - [ ] 강의는 시작일과 종료일을 가진다. -> startAt, endAt
  - [x] 강의는 강의 커버 이미지 정보를 가진다. -> image
  - [ ] type : FREE, PAID
  - [ ] status : READY, RECRUITING, END
    - [ ] RECRUITING : 수강 신청 가능 
  - [ ] Session : Payment = 1 : 1
    - [ ] payment.amount == session.amount : 수강 신청 가능
  - [ ] maxStudent
    - [ ] type == FREE : maxStudent == null
    - [ ] type == PAID : maxStudent > 0
  - [ ] Fee
    - [ ] type == FREE : Fee == 0
    - [ ] type == PAID : Fee > 0
- 결제 (Payment)
  - [ ] status : PAID, CANCELED
  - [ ] amount : Fee와 일치할 때 수강 신청이 가능하다.
