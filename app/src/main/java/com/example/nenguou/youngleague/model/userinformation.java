package com.example.nenguou.youngleague.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Nenguou on 2017/5/14.
 */

public class userinformation implements Serializable {

    private static final long serialVersionUID = 1L;

    public String id;
    public int branchId;
    public int universityId;
    public String name;    //姓名
    public int userId;
    public int idCard;
    public String type;
    public String university;
    public String avator;
    public String email;       //账号
    public String tel;        //手机号
    public String telWork;
    public String province;    //地区
    public String city;
    public String status;
    public String rememberToken;
    public String  createdAt;
    public String updatedAt;
    public String deletedAt;
    public List<role> roles;
        public class role { //数组
            public int id;
            public String name;
            public String description;
            public String createdAt;
            public String updatedAt;
            public String type;
        }

    public class branch {
        public int id;
        public String universityId;
        public String secretaryId;
        public String name;        //所在党支部
        public String avator;
        public String type;
        public String university;
        public int tel;
        public String verification;
        public String address;
        public String summary;
        public String totalMember;
        public String secretarySummary;
        public String createdAt;
        public String updatedAt;
        public String deletedAt;
        public String applyImg;
        public String detail;
    }

    public class universityPO {
        public int id;
        public String provinceId;
        public String name;
        public class province {
            public int id;
            public String name;
        }
    }

}
