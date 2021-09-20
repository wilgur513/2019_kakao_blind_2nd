# 2019 카카오 블라인드 2차 코딩 테스트 풀이

### [서버](https://github.com/kakao-recruit/2019-blind-2nd-elevator)

## 의존성

```
<dependencies>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.20</version>
    </dependency>
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.2.5</version>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.9.8</version>
    </dependency>
    <dependency>
        <groupId>org.modelmapper</groupId>
        <artifactId>modelmapper</artifactId>
        <version>2.3.1</version>
    </dependency>
</dependencies>
```

## 결과
* 어피치 맨션 (problem_id = 0)
  * 문제 조건
    * 엘리베이터의 최대 수용인원(=Call) : 8명
    * 건물의 최고층 : 5층
    * Call 수 : 6개
  * 결과(timestamp) : 17
* 제이지 빌딩 (problem_id = 1)
  * 문제 조건
    * 엘리베이터의 최대 수용인원(=Call) : 8명
    * 건물의 최고층 : 25층
    * Call 수 : 200개
  * 결과(timestamp) : 587
* 라이언 타워 (problem_id = 2)
  * 문제 조건
    * 엘리베이터의 최대 수용인원(=Call) : 8명
    * 건물의 최고층 : 25층
    * Call 수 : 500개
    * 출입구 : 1층
  * 결과(timestamp) : 1876

