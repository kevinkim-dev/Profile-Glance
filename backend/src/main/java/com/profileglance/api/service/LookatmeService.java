package com.profileglance.api.service;

import com.profileglance.api.request.LookatmePostReq;
import com.profileglance.api.response.LookatmePostRes;
import com.profileglance.db.entity.Lookatme;

import java.util.List;

public interface LookatmeService {

    List<LookatmePostRes> searchByCategory(String category, Long limit);

    Boolean uploadLookatme(LookatmePostReq lookatmePostReq);

    Boolean updateLookatme(LookatmePostReq lookatmePostReq);

    List<LookatmePostRes> searchByTitle(String title, Long limit);

    List<LookatmePostRes> searchByNickname(String nickname, Long limit);

    LookatmePostRes detailLookatme(Long lookatmeId);
}
