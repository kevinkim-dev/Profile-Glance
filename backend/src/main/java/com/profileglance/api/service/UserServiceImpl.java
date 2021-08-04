package com.profileglance.api.service;

import com.profileglance.api.request.UserPostReq;
import com.profileglance.api.response.InterviewListGetRes;
import com.profileglance.api.response.LookatmePostRes;
import com.profileglance.api.response.MypageGetRes;
import com.profileglance.db.entity.Interview;
import com.profileglance.db.entity.Lookatme;
import com.profileglance.db.entity.User;
import com.profileglance.db.repository.InterviewRepository;
import com.profileglance.db.repository.LookatmeRepository;
import com.profileglance.db.repository.UserLikeRepository;
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
    UserLikeRepository userLikeRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserPostReq userPostReq) {

        return userRepository.save(User.builder()
                .userName(userPostReq.getUserName())
                .userEmail(userPostReq.getUserEmail())
                .userNickname(userPostReq.getUserNickname())
                .userPassword(passwordEncoder.encode(userPostReq.getUserPassword()))
                .major1(userPostReq.getMajor1())
                .major2(userPostReq.getMajor2())
                .userPhone(userPostReq.getUserPhone())
                .companyLike(0l)
                .userImg("")
                .birth(userPostReq.getBirth())
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

//    @Override
//    public User myinfo(String userEmail) {
//        User user = userRepository.findByUserEmail(userEmail).get();
//        return user;
//    }

    @Override
    public MypageGetRes myInfo(String userEmail){
        User user = userRepository.findByUserEmail(userEmail).get();
        MypageGetRes mypageGetRes = new MypageGetRes(
                user.getUserName()
                ,user.getUserEmail()
                ,user.getBirth()
                ,user.getMajor1()
                ,user.getMajor2()
                ,userLikeRepository.countByUser_UserEmail(userEmail)
                ,lookatmeRepository.countByUser_UserEmail(userEmail)
                ,user.getPortfolio1()
                ,user.getPortfolio2()
        );
        return mypageGetRes;
    }

    @Override
    public MypageGetRes myInfoByNickname(String userNickname){
        User user = userRepository.findByUserNickname(userNickname).get();
        MypageGetRes mypageGetRes = new MypageGetRes(
                user.getUserName()
                ,user.getUserEmail()
                ,user.getBirth()
                ,user.getMajor1()
                ,user.getMajor2()
                ,userLikeRepository.countByUser_UserEmail(user.getUserEmail())
                ,lookatmeRepository.countByUser_UserEmail(user.getUserEmail())
                ,user.getPortfolio1()
                ,user.getPortfolio2()
        );
        return mypageGetRes;
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
    public List<InterviewListGetRes> myInterviewList(String userEmail){
        List<InterviewListGetRes> interviewListGetRes = new ArrayList<>();
        List<Interview> interviewList = interviewRepository.findAllByUser_UserEmail(userEmail);
        for(Interview i : interviewList){
            interviewListGetRes.add(new InterviewListGetRes(
                    i.getUser().getUserName()
                    ,i.getCompany().getCompanyId()
                    ,i.getInterviewDate()
                    ,i.getInterviewTime()
                    ,i.getRoom().getRoomUrl()
            ));
        }
        return interviewListGetRes;
    }

    @Override
    public Long likeCount(String userEmail){
        return userLikeRepository.countByUser_UserEmail(userEmail);
    }
}
