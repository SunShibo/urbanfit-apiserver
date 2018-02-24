package com.urbanfit.apiserver.dao;


import com.urbanfit.apiserver.entity.UserDO;
import com.urbanfit.apiserver.entity.bo.UserBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户
 * @author zhaojiafu
 *
 * 2016年1月7日 下午12:29:55
 */
public interface UserDAO {

    /**
     * 登录
     * @param account
     * @return 返回该账户的用户信息
     */
    public UserBO login(@Param("account") String account, @Param("password") String password) ;

    /**
     * 通过id登录
     * @param id
     * @return 返回该账户的用户信息
     */
    public UserBO loginById(@Param("id") int id) ;

    /**
     * 修改用户头像
     * @param userDO
     * @return
     */
    public int updateUserHeadPortrait(UserDO userDO) ;

    // FIXME zhaojiafu 使用文档注释，标注时间，作者，参数，返回结果
    int getCount(String test);

    /**
     * 注册
     * 2016年1月7日 下午12:34:12
     * @author zhaojiafu
     * @param userDO
     * @return
     */
    int register(UserDO userDO) ;

    /**
     * 根据【用户名】查询【用户】的数量
     * 2016年1月7日 下午12:32:58
     * @author zhaojiafu
     * @param username 用户名
     * @return 用户数
     */
    int selectCountByUsername(String username);
    
    /**
     * 根据【手机号】查询【用户数】
     * 2016年1月7日 下午3:17:09
     * @author zhaojiafu
     * @param mobileCode
     * @return 【用户数】
     */
    int selectCountByMobileCode(String mobileCode);

    /**
     * 根据【手机号】更新【密码】
     * 2016年1月7日 下午2:58:47
     * @author zhaojiafu
     * @param userDO
     */
    void updatePasswordByMobileCode(UserDO userDO);


    int updateStatusByEmail(UserDO userDO);

    /**
     * 通过邮箱查找用户
     * @param email
     * @return
     */
    List<UserDO> selectUserByEmail(@Param("email") String email) ;

    /**
     * 完成用户信息
     * @param user
     * @return
     */
    public int completeProfile(UserDO user) ;

    /**
     * 通过邮箱修改用户密码
     * @param user
     * @return
     */
    public int updatePasswordByEmail(UserDO user) ;

    /**
     * 通过用户id修改用户信息
     * @param userDO
     * @return
     */
    int updateUserInfo(UserDO userDO) ;

    /**
     * 通过旧密码修改新密码
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return
     */
    int updatePwdByOldPwd(@Param("id") int id, @Param("oldPassword") String oldPassword, @Param("newPassword") String newPassword) ;
}
