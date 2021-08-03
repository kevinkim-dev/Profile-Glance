package com.profileglance.api.service;

import com.profileglance.api.request.LookatmePostReq;
import com.profileglance.api.response.LookatmePostRes;
import com.profileglance.db.entity.Lookatme;

import java.util.List;

public interface LookatmeService {

    List<LookatmePostRes> searchByCategory(String category);

    Boolean uploadLookatme(LookatmePostReq lookatmePostReq, String userEmail);

    Boolean updateLookatme(LookatmePostReq lookatmePostReq);

    List<LookatmePostRes> searchByTitle(String title);

}
