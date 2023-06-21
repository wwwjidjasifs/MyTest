package com.Pojo;

import lombok.Data;

@Data
public class Holidayinfo {
    private int id;
    private int userid;
    private String username;
    private int departmentid;
    private String type;
    private String reason;
    private String starttime;
    private String endtime;
    private String totaltime;
    private int state;
    private int isApply;
}
