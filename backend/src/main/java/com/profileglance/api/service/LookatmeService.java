package com.profileglance.api.service;

import com.profileglance.api.request.LookatmePostReq;
import com.profileglance.db.entity.Lookatme;

import java.util.List;

public interface LookatmeService {

    List<Lookatme> searchByCategory(String category);

    Boolean uploadLookatme(LookatmePostReq lookatmePostReq, String userEmail);

    Boolean updateLookatme(LookatmePostReq lookatmePostReq);

}
