# 피쳐
- 유저 생성
- 유저가 유저 팔로우
- 유저가 글 작성
- 글 좋아요
- 피드 목록

# 테이블
- 유저
- 글
- 팔로우
- 유저-글 매핑테이블
- 피드테이블
```
| 작성자 | 팔로워 | 글 아이디 |
```

# Task
- 프로젝트 셋팅 (with Docker/DB)
  - RDBMS(H2)
- 도메인 설계
  - 유저
  - 친구
  - 글


---

### 1 주차
- 홍찬 : 프로젝트 셋팅 + 도커
- 준혁 : 패키지 구조 + Layer + 컨벤션화
- 태관 : 러프한 ERD

### 2 주차
- 유저 : 태관
- 친구 : 홍찬
- 글 : 준혁

### 3 주차
- 부족한 부분들
- 진행에 대한 리뷰
- 확장성 고민

---

온라인 : 상시 온라인 코드리뷰
오프라인 : 원래 스터디 시간 줌

스프링 + 자바(코틀린 으로 바뀜)

---

## 패키지 구조

### 1안 : 계층형

```
├── config
├── controller
│   ├── feed
│   └── user
├── domain
│   ├── feed
│   └── user
├── event
├── exception
├── model
│   ├── dto
│   │   ├── feed
│   │   └── user
│   ├── request
│   │   ├── feed
│   │   └── user
│   └── response
│       ├── feed
│       └── user
├── repository
├── service
│   └── user
└── util
```

### 2안 : 도메인형
예시 : https://github.com/njhyuk/product-api
```
├── config
│   └── exception
├── core
│   ├── feed
│   │   ├── command
│   │   ├── domain
│   │   ├── exception
│   │   └── query
│   ├── user
│   │   ├── command
│   │   ├── domain
│   │   ├── exception
│   │   └── query
└── inbound
    └── web
        ├── api
        │   └── v1
        │       ├── feed
        │       └── user
        └── public
            └── v1
                ├── admin
                └── user
```
