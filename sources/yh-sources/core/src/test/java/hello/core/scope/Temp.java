package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.*;

public class Temp {

    @Test
    void tempTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingleBean.class);
        SingleBean singleBean1 = ac.getBean(SingleBean.class);
        int count1 = singleBean1.logic();
        assertThat(count1).isEqualTo(1);

        SingleBean singleBean2 = ac.getBean(SingleBean.class);
        int count2 = singleBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

//    @Scope("prototype")
    static class ProtoBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return this.count;
        }
    }

    @Scope("singleton")
    static class SingleBean {

        int logic() {
            ProtoBean protoBean = new ProtoBean();
            System.out.println(protoBean);
            System.out.println(protoBean.getCount());
            protoBean.addCount();
            System.out.println(protoBean.getCount());
            return protoBean.getCount();
        }
    }
}
