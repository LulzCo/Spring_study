package hello.core;

import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoreApplicationTests {

    @Autowired
    MemoryMemberRepository memoryMemberRepository;

    @Test
    void contextLoads() {
    }

}
