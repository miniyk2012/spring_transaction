package cn.tulingxueyuan.dao;


import cn.tulingxueyuan.entity.User;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
public interface IUserDao {
    User getUser();

    void sub();

    void save();

}
