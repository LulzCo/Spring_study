package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)           // 단 하나의 요청 내에서만 동작한다!! 단!!! 하!나!의 요청!!       // 주석처리해서 실행을 하면 모든 요청이 같은 uuid를 가지며 실행되고 있다!!!!!
/*ScopedProxyMode.TARGET_CLASS
* -> 프로바이더처럼 동작하게 만들기 위해 가짜 프록시 객체를 만들어 사용, 스프링 컨테이너에 MyLogger를 빈으로 등록하지 않고 가짜 프록시 객체를 만들어 등록
* -> 스프링 컨테이너에 껍데기인 프록시 객체를 등록하고 나서 실제로 호출되고 사용될 때 진짜 객체를 등록한다. -> 마치 싱글톤 처럼 사용한다!!(실제 싱글톤이라 생각하면 안된다!!) -> 무분별하게 사용해서는 안된다!!
* 프록시 객체 덕분에 다형성과 DI 컨테이너 가능*/
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create: " + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close: " + this);
    }
}
