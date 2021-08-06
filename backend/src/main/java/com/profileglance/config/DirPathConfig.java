package com.profileglance.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DirPathConfig {

    //로컬용
//    String baseDir = "C:\\ssafy\\profile_glance\\frontend\\public\\ServerFiles";  //재우
//    String baseDir = "C:\\Users\\multicampus\\Documents\\S05P13A402\\frontend\\public\\ServerFiles";

    //서버용
    public static String baseDir = "/home/ubuntu/profile_glance/dist/ServerFiles";

}
