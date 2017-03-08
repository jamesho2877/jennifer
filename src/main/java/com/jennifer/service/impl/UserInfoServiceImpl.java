package com.jennifer.service.impl;

import com.jennifer.dao.UserInfoDao;
import com.jennifer.dto.CustomerDetails;
import com.jennifer.dto.SignupForm;
import com.jennifer.entity.UserInfo;
import com.jennifer.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserInfo service implementation
 */

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService, UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    private UserInfoDao userInfoDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserInfoServiceImpl(UserInfoDao userInfoDao, PasswordEncoder passwordEncoder) {
        this.userInfoDao = userInfoDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoDao.findByEmail(email);

        if (userInfo == null)
            throw new UsernameNotFoundException(email);

        return new CustomerDetails(userInfo);
    }

    @Override
    @Transactional
    public boolean signup(SignupForm signupForm) {
        UserInfo userInfo = new UserInfo();
        userInfo.setFullname(signupForm.getFullname());
        userInfo.setEmail(signupForm.getEmail());
        userInfo.setPassword(passwordEncoder.encode(signupForm.getPassword()));
        userInfo.setRole(UserInfo.Role.CUSTOMER);

        userInfoDao.save(userInfo);
        return true;
    }

    @Override
    public UserInfo findById(int id) {
        return userInfoDao.findById(id);
    }

    @Override
    public UserInfo updateUser(UserInfo userInfo) {
        return userInfoDao.save(userInfo);
    }

    @Override
    public boolean compareUserPassword(UserInfo userInfo, String newPassword) {
        UserInfo userInfoFound = userInfoDao.findById(userInfo.getId());
        return passwordEncoder.matches(newPassword,userInfoFound.getPassword());
    }

    @Override
    public UserInfo changePassword(UserInfo userInfo, String newPassword) {
        UserInfo userInfoFound = userInfoDao.findById(userInfo.getId());
        userInfoFound.setPassword(passwordEncoder.encode(newPassword));
        return userInfoDao.save(userInfoFound);
    }

//    @Override
//    public List<ProductInfo> addProductToFavorite(ProductInfo productInfo) {
//        UserInfo userInfo = AppUtil.getCustomerFromSession();
//        if(userInfo.getProductInfos() == null){
//            List<ProductInfo> productInfoList = new ArrayList<>();
//            productInfoList.add(productInfo);
//            userInfo.setProductInfos(productInfoList);
//        } else {
//            log.info("add here!");
//            userInfo.getProductInfos().add(productInfo);
//        }
//        //userInfoDao.save(userInfo);
//        return userInfo.getProductInfos();
//    }

//    @Override
//    public void onApplicationEvent(AuthenticationSuccessEvent authenticationSuccessEvent) {
//
//    }
}
