package com.example.nenguou.youngleague.model;

/**
 * Created by Nenguou on 2017/5/20.
 */

public class Journal_Model {
    public String id;
    public String typeId;
    public String secretaryId;
    public String title;
    public String cover;
    public String images;
    public String level;
    public String status;
    public String createdAt;
    public String updatedAt;
    public String deletedAt;
    public String content;

    public class secretary{
        public String id;
        public String branchId;
        public String universityId;
        public String name;
        public String userId;
        public String idCard;
        public String type;
        public String university;
        public String avator;
        public String email;
        public String tel;
        public String telWork;
        public String province;
        public String city;
        public String status;
        public String rememberToken;
        public String createdAt;
        public String updatedAt;
        public String deletedAt;
        public String roles;
        public String branch;
        public String universityPO;
    }
}
