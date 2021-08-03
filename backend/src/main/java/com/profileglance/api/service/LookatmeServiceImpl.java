package com.profileglance.api.service;

import com.profileglance.api.request.LookatmePostReq;
import com.profileglance.db.entity.Category;
import com.profileglance.db.entity.Lookatme;
import com.profileglance.db.entity.User;
import com.profileglance.db.repository.CategoryRepository;
import com.profileglance.db.repository.LookatmeRepository;
import com.profileglance.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LookatmeServiceImpl implements LookatmeService{

    @Autowired
    LookatmeRepository lookatmeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Lookatme> searchByCategory(String category) {

        return null;
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


}
