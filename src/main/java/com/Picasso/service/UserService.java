package com.Picasso.service;

import com.Picasso.dao.AccountDao;
import com.Picasso.dao.UserInfoDao;
import com.Picasso.entity.Account;
import com.Picasso.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private AccountDao accountDao;

    @Autowired
    private UserInfoDao userInfoDao;

    /**
     * 查找
     */
    public Account checkAccount(String username, String password) {
        return accountDao.findAccountByNamePass(username, password);
    }

    public Account findAccountByUserName(String username) {
        return accountDao.findAccountByName(username);
    }

    public List<Account> selectAllAccount() {
        return accountDao.findAllAccount();
    }

    public List<Account> selectAllAccountByWeight(int weight) {
        return accountDao.findAllAccountByWeight(weight);
    }

    public List<UserInfo> selectAllUserInfo() {
        return userInfoDao.findAllUser();
    }

    public UserInfo showUserInfo(String username, String password) {
        Account account = accountDao.findAccountByNamePass(username, password);
        return userInfoDao.findUserById(account.getUid());
    }

    public UserInfo showUserInfo(int uid) {
        return userInfoDao.findUserById(uid);
    }

    /**
     * 插入修改
     */
    public boolean createAccount(String username, String password) {
        if (accountDao.findAccountByName(username) == null) {
            if (username.equals(""))
                return false;
            accountDao.insertAccount(username, password, 0);
            int uid = accountDao.findMaxId();
            userInfoDao.insertUserInfo(uid, null, null, null, null, 0, null);
            return true;
        } else {
            return false;
        }
    }

    public boolean createAccount(String username, String password, int weight) {
        if (accountDao.findAccountByName(username) == null) {
            if (username.equals(""))
                return false;
            accountDao.insertAccount(username, password, weight);
            int uid = accountDao.findMaxId();
            userInfoDao.insertUserInfo(uid, null, null, null, null, 0, null);
            return true;
        } else {
            return false;
        }
    }

    public UserInfo changeUserInfo(int uid, String name, String school, String profession, String phone, int sex,
                                   Date birth) {
        if (userInfoDao.findUserById(uid) == null) {
            userInfoDao.insertUserInfo(uid, name, school, profession, phone, sex, birth);
        } else {
            userInfoDao.updateUserInfo(name, school, profession, phone, sex, birth, uid);
        }
        return userInfoDao.findUserById(uid);
    }

    @Transactional
    public Account changepassword(String username, String password, String newwd) {
        Account account = this.checkAccount(username, password);
        if (account != null) {
            accountDao.updateAccount(username, newwd, account.getWeight(), account.getUid());
            return accountDao.findAccountByNamePass(username, newwd);
        } else {
            return null;
        }
    }

    public Account changPass(int uid, String newPass) {
        Account account = accountDao.findAccountById(uid);
        if (account != null) {
            accountDao.updateAccount(account.getusername(), account.getPassword(), account.getWeight(), account.getUid());
            return accountDao.findAccountById(uid);
        } else {
            return null;
        }
    }

    /**
     * 删除
     */

    @Transactional
    public boolean delAccount(int uid) {
        if (accountDao.findAccountById(uid) != null) {
            if (userInfoDao.findUserById(uid) != null) {
                userInfoDao.deleteUserInfo(uid);
            }
            accountDao.deleteAccount(uid);
            return true;
        } else {
            return false;
        }
    }
}