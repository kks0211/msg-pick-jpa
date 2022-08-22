# 마사지픽 파트너 사이트
#### 마사지샵을 소유하고 있는 고객이 대상이며, 입점 신청부터 샵 정보를 관리할 수 있는 사이트

---

### 프로젝트 구성
### - msg-pick-core 
프로젝트에서 공통적으로 사용하는 것을 모아놓은 프로젝트  
ex) enum 값, response 등    

### - msg-pick-partner
샵을 소유하고 있는 고객들을 대상으로하는 프로젝트

--- 
### 개발 환경 구성
```
cd msg-pick-partner && npm install
```

---
### 개발 방법

스크립트 관련 개발 진행 시, 아래 명령어를 통해 번들링 된 자바스크립트 파일을 watch 한 상태로 스프링부트를 실행한다.
```
npm run watch
```

---
### 메소드 명명 규칙

메소드 작성 시 아래와 같은 규칙으로 작성한다.
```
controller, service 의 경우 
- find (null 리턴가능)
- get  (null 익셉션)
- register
- modify
- remove 
```
```
mapper 의 경우
- save
- findBy
- update
- delete
```

