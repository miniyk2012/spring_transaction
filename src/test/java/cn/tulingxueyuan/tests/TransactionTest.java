package cn.tulingxueyuan.tests;

import cn.tulingxueyuan.service.IUserService;
import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.jdbc.Connection;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
public class TransactionTest {
    ClassPathXmlApplicationContext ioc;
    @Before
    public  void before(){
        ioc=new ClassPathXmlApplicationContext("classpath:spring.xml");
    }

    @Test
    public void test01() {
        IUserService service = ioc.getBean(IUserService.class);
        service.trans();
    }

    @Test
    public void test02() {
        IUserService service = ioc.getBean(IUserService.class);
        service.trans2();
    }

    @Test
    public void test04() {
        IUserService service = ioc.getBean(IUserService.class);
        service.sub();
    }


    @Test
    public void test05() {
        IUserService service = ioc.getBean(IUserService.class);
        // org.springframework.dao.TransientDataAccessResourceException 异常
        service.getUsers();
    }

    @Test
    public void test06() throws Exception {
        IUserService service = ioc.getBean(IUserService.class);
        service.tranException();
    }

}
