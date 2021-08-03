package com.profileglance.api.service;

import com.profileglance.api.request.LookatmePostReq;
import com.profileglance.api.response.LookatmePostRes;
import com.profileglance.db.entity.Category;
import com.profileglance.db.entity.Lookatme;
import com.profileglance.db.entity.User;
import com.profileglance.db.repository.CategoryRepository;
import com.profileglance.db.repository.LookatmeRepository;
import com.profileglance.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LookatmeServiceImpl implements LookatmeService{

    @Autowired
    LookatmeRepository lookatmeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<LookatmePostRes> searchByCategory(String category) {

        List<Lookatme> lookatmeList = lookatmeRepository.findAllByCategory_CategoryName(category);

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
    public Boolean uploadLookatme(LookatmePostReq lookatmePostReq, String userEmail) {

        System.out.println("서비스 등록 들어왔어요~~~");
        User user = userRepository.findByUserEmail(userEmail).get();

        String categoryName = lookatmePostReq.getCategory();

        Category category = categoryRepository.findByCategoryName(categoryName).get();

        Lookatme lookatme = lookatmeRepository.save(Lookatme.builder().title(lookatmePostReq.getTitle())
        .content(lookatmePostReq.getContent()).thumbnail(lookatmePostReq.getThumbnail()).video(lookatmePostReq.getVideo())
        .user(user).category(category).build());

        user.getLookatmes().add(lookatme);

        userRepository.save(user);

        return true;
    }

    @Override
    public Boolean updateLookatme(LookatmePostReq lookatmePostReq) {

        System.out.println("룩앳미 업데이트 서비스 입니다.");

        Lookatme lookatme = lookatmeRepository.findById(lookatmePostReq.getLookatmeId()).get();

        lookatme.setTitle(lookatmePostReq.getTitle());
        lookatme.setContent(lookatmePostReq.getContent());
        lookatme.setVideo(lookatmePostReq.getVideo());
        lookatme.setThumbnail(lookatmePostReq.getThumbnail());
        lookatme.setCategory(categoryRepository.findByCategoryName(lookatmePostReq.getCategory()).get());

        lookatmeRepository.save(lookatme);

        return true;
    }

    @Override
    public List<LookatmePostRes> searchByTitle(String title) {

        List<Lookatme> lookatmeList = lookatmeRepository.findAllByTitleContaining(title);

        System.out.println(lookatmeList.size());

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


}
