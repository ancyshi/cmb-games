import cn.fulugame.core.CoreDaoApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by burgl on 2018/4/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CoreDaoApplication.class)
@Slf4j
public class RedisTest {


    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testObj() throws Exception {
    }
}
