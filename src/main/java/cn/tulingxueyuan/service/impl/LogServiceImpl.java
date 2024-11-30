package cn.tulingxueyuan.service.impl;

import cn.tulingxueyuan.dao.IUserDao;
import cn.tulingxueyuan.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@Service
public class LogServiceImpl implements ILogService {

    @Autowired
    IUserDao userDao;

    @Override
    // 如果事务传播行为是挂起事务  需要将父事务方法和子事务方法写在不同的类里面
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void log() {
        userDao.sub();
    }
}
