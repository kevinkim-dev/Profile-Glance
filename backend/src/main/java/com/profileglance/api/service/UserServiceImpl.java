package com.profileglance.api.service;

import com.profileglance.api.response.LookatmePostRes;
import com.profileglance.db.entity.Interview;
import com.profileglance.db.entity.Lookatme;
import com.profileglance.db.entity.User;
import com.profileglance.db.repository.InterviewRepository;
import com.profileglance.db.repository.LookatmeRepository;
import com.profileglance.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;
    @Autowired
    LookatmeRepository lookatmeRepository;
    @Autowired
    InterviewRepository interviewRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {

        return userRepository.save(User.builder()
                .userName(user.getUserName())
                .userEmail(user.getUserEmail())
                .userNickname(user.getUserNickname())
                .userPassword(passwordEncoder.encode(user.getUserPassword()))
                .major1(user.getMajor1())
                .major2(user.getMajor2())
                .userPhone(user.getUserPhone())
                .companyLike(0l)
                .userImg("")
                .birth(user.getBirth())
                .build());
    }

    @Override
    public User updateUser(User userUpdateInfo, String userEmail) {

        User user = userRepository.findByUserEmail(userEmail).get();

        user.setMajor1(userUpdateInfo.getMajor1());
        user.setMajor2(userUpdateInfo.getMajor2());
        user.setPortfolio1(userUpdateInfo.getPortfolio1());
        user.setPortfolio2(userUpdateInfo.getPortfolio2());
        user.setUserPhone(userUpdateInfo.getUserPhone());

        userRepository.save(user);

        return user;

    }

    @Override
    public boolean deleteUser(String userEmail) {
        System.out.println("in service");

        userRepository.deleteByuserEmail(userEmail);

        return true;
    }

    @Override
    public User myinfo(String userEmail) {
        User user = userRepository.findByUserEmail(userEmail).get();
        return user;
    }

    @Override
    public boolean uploadUserImg(String userEmail, String userImg) {
        Optional<User> user = userRepository.findByUserEmail(userEmail);
        user.ifPresent(uploadUser->{
            uploadUser.setUserImg((userImg));
            userRepository.save(uploadUser);
        });

        return user.isPresent();
    }

    @Override
    public List<LookatmePostRes> myVideoList(String userEmail){

        List<Lookatme> lookatmeList = lookatmeRepository.findAllByUser_UserEmail(userEmail);
        List<LookatmePostRes> lookatmePostResList = new ArrayList<>();
        for (Lookatme l : lookatmeList){
            lookatmePostResList.add(new LookatmePostRes(
                    l.getLookatmeId(),
                    l.getUser().getUserNickname(),
                    l.getTitle(),
                    l.getContent(),
                    l.getVideo(),
                    l.getThumbnail(),
                    l.getCategory().getCategoryName(),
                    l.getView(),
                    l.getVideoLike(),
                    l.getCreatedAt()
            ));
        }
        return lookatmePostResList;
    }

    @Override
    public List<Interview> myInterviewList(String userEmail){
        return interviewRepository.findAllByUser_UserEmail(userEmail);
    }
}
