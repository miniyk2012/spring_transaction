package cn.tulingxueyuan.tests;

import cn.tulingxueyuan.entity.User;
import cn.tulingxueyuan.service.IUserService;
import com.alibaba.druid.pool.DruidDataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
public class JDBCTest {

  ClassPathXmlApplicationContext ioc;

  @Before
  public void before() {
    ioc = new ClassPathXmlApplicationContext("classpath:spring.xml");
  }

  @Test
  public void test01() {
    DruidDataSource bean = ioc.getBean(DruidDataSource.class);
    System.out.println(bean);

  }

  /**
   * JdbcTemplate演示
   */
  @Test
  public void test02() {
    JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);
    Long aLong = jdbcTemplate.queryForObject("select count(*) from t_user", Long.class);
    System.out.println(aLong);
  }

  /**
   * 查询单个值
   */
  @Test
  public void test03() {
    JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);
    // 查询单个值
    Long aLong = jdbcTemplate.queryForObject("select count(*) from t_user", Long.class);
    System.out.println(aLong);
  }

  /**
   * 查询实体
   */
  @Test
  public void test04() {
    JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);
    // 数据库字段名和属性名一样 利用BeanPropertyRowMapper
    User user = jdbcTemplate.queryForObject("select * from t_user where id=?",
        new BeanPropertyRowMapper<>(User.class), 2);

    System.out.println(user);
    user = jdbcTemplate.queryForObject("select * from t_user where id=1",
        (resultSet, i) -> {
          User o = new User();
          o.setId(resultSet.getInt("id"));
          o.setRealname(resultSet.getString("realname"));
          o.setBalance(resultSet.getInt("balance"));
          return o;
        });
    System.out.println(user);
  }


  /**
   * 查询List<实体>
   */
  @Test
  public void test05() {
    JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);
    // 数据库字段名和属性名一样 利用BeanPropertyRowMapper
    List<User> users = jdbcTemplate.query("select * from t_user where id>1",
        new BeanPropertyRowMapper<>(User.class));
    System.out.println(users);

    users = jdbcTemplate.query("select * from t_user where id>1",
            (resultSet, i) -> {
                User o=new User();
                o.setId(resultSet.getInt("id"));
                o.setRealname(resultSet.getString("realname"));
                o.setBalance(resultSet.getInt("balance"));
                return o;
            });
    System.out.println(users);
  }

  /**
   * 新增
   */
  @Test
  public void test06() {
    JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);
    // 数据库字段名和属性名一样 利用BeanPropertyRowMapper
    int result =
        jdbcTemplate.update("insert into t_user(realname,cardno,balance) values (?,?,?)",
            "王麻子", "12345677", 800);

    System.out.println(result);
  }

  /**
   * 修改
   */
  @Test
  public void test07() {
    JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);
    // 数据库字段名和属性名一样 利用BeanPropertyRowMapper
    int result =
        jdbcTemplate.update("update t_user set balance=balance+200 where id=?",
            7);

    System.out.println(result);
  }

  /**
   * 删除
   */
  @Test
  public void test08() {
    JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);
    // 数据库字段名和属性名一样 利用BeanPropertyRowMapper
    int result =
        jdbcTemplate.update("delete from t_user where id=?",
            7);

    System.out.println(result);
  }


  /**
   * 具名参数处理NamedParameterJdbcTemplate
   */
  @Test
  public void test09() {
    NamedParameterJdbcTemplate jdbcTemplate = ioc.getBean(NamedParameterJdbcTemplate.class);

    Map<String, Object> map = new HashMap<>();
    map.put("id", 4);

    // 数据库字段名和属性名一样 利用BeanPropertyRowMapper
    int result =
        jdbcTemplate.update("delete from t_user where id=:id", map);

    System.out.println(result);
  }


  @Test
  public void test10() {
    IUserService service = ioc.getBean(IUserService.class);

    System.out.println(service.getUser());
    JdbcTemplate jdbcTemplate = ioc.getBean(JdbcTemplate.class);
    System.out.println(jdbcTemplate);
  }
}
