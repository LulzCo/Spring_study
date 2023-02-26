# Spring in terminal

## SDKMAN  사용법

### sdkman 설치

```
curl -s "https://get.sdkman.io" | bash
```

-----

### sdkman 기본 사용법

    help         [subcommand]
    install      <candidate> [version] [path]
    uninstall    <candidate> <version>
    list         [candidate]
    use          <candidate> <version>
    config       no qualifier
    default      <candidate> [version]
    home         <candidate> <version>
    env          [init|install|clear]
    current      [candidate]
    upgrade      [candidate]
    version      no qualifier
    offline      [enable|disable]
    selfupdate   [force]
    update       no qualifier
    flush        [tmp|metadata|version]

-----

### 자바 설치 방법

```
sdk list java
sdk install java 11.0.18-amzn
sdk use java 11.0.18-amzn
```

-----

### springboot 설치 방법

```
sdk list springboot
sdk install springboot 2.7.9
sdk use springboot 2.7.9
```

-----

### 스프링부트 프로젝트 생성 방법 - springboot init

스프링부트를 설치해야 함



```
spring init -d web, jpa -g tobyspring -n helloboot -j 11 -x helloboot -b 2.7.9
```

-d : 디펜던시, 설치할 라이브러리 세팅

-g : 스프링부트 프로젝트 그룹 이름

-n : 스프링부트 프로젝트 이름

-j : 사용할 자바 버전

-x : 압축하지 않은 파일을 다운받음 -> 사용하지 않으면 압축 파일로 다운받음 -> 해주는게 좋음

-b : 스프링부트 버전 -> 사용하지 않으면 기본으로 설정된 버전으로 생성 -> 해주는게 좋음



```
spring shell
init --list
```

스프링 커멘드 쉘 실행

다운받을 수 있는 디펜던시 리스트



```
cd helloboot
./gradlew bootRun
```

프로젝트가 잘 생성이 되고 실행이 됐는지 확인