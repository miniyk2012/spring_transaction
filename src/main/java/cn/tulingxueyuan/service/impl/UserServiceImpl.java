package cn.tulingxueyuan.service.impl;

import cn.tulingxueyuan.dao.IUserDao;
import cn.tulingxueyuan.entity.User;
import cn.tulingxueyuan.service.ILogService;
import cn.tulingxueyuan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@Service
public class UserServiceImpl implements IUserService {

  @Autowired
  IUserDao userDao;

  @Autowired
  ILogService logService;

  /**
   * 转账
   */
//    @Transactional(propagation = Propagation.REQUIRED)
  @Transactional
  @Override
  public void trans() {
    userDao.sub();
    int i = 10 / 0;
    userDao.save();
  }

  @Transactional
  @Override
  public void trans2() {
    logService.log();
    userDao.sub();
    int i = 1 / 0;
    userDao.save();
  }

  /**
   * 扣钱
   */
//  @Transactional(propagation = Propagation.SUPPORTS)   // 只适用于该事务方法是一个查询
  @Override
  @Transactional(timeout = 2)
  public void sub() {

    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    userDao.sub();
  }

  /**
   * 存钱
   *
   * @return
   */
  @Transactional
  public void save() {
    userDao.save();
  }


  @Override
  @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
  //  Propagation.SUPPORTS只适用于该事务方法是一个查询
  public User getUser() {
    userDao.sub();
    return userDao.getUser();
  }

  @Transactional(readOnly = true)
  @Override
  public void getUsers() {
    userDao.sub();
    userDao.getUser();
  }


//  @Transactional(rollbackFor = {Exception.class})  // 扩大异常检测范围
  @Transactional(noRollbackFor = {RuntimeException.class})
  @Override
  public void tranException() throws Exception {
    userDao.sub();
    throw new RuntimeException("测试");
  }
}
