package cn.tulingxueyuan.service;

import cn.tulingxueyuan.entity.User;
import org.springframework.transaction.annotation.Transactional;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
public interface IUserService {
    User getUser();

    void trans();

    void trans2();

    @Transactional(readOnly = true)
    void getUsers();

    void tranException() throws Exception;

    void sub();
}
