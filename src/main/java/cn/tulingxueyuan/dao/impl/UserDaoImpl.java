package cn.tulingxueyuan.dao.impl;

import cn.tulingxueyuan.dao.IUserDao;
import cn.tulingxueyuan.entity.User;
import com.alibaba.druid.pool.DruidDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@Repository
public class UserDaoImpl implements IUserDao {
//    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        // 一开始jdbcTemplate是xml里的那个JdbcTemplate或者是null
        System.out.println("before setDataSource(jdbcTemplate): " + this.jdbcTemplate);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        // 然后这里是新的初始化出来的JdbcTemplate, 就能每个Dao一个JdbcTemplate
        System.out.println("after setDataSource(jdbcTemplate): " + this.jdbcTemplate);
    }

    @Override
    public User getUser(){
        System.out.println("getUser(jdbcTemplate): " + jdbcTemplate);
        return jdbcTemplate.queryForObject("select * from t_user where id=1",new BeanPropertyRowMapper<>(User.class));
    }

    /**
     * 张三扣钱
     * 扣钱
     */
    @Override
    public void sub() {
        System.out.println("张三扣钱");
        jdbcTemplate.update("update t_user set balance=balance-200 where id=1");
    }

    /**
     * 李四加钱
     */
    @Override
    public void save() {
        System.out.println("李四加钱");
        jdbcTemplate.update("update t_user set balance=balance+200 where id=2");
    }


}
