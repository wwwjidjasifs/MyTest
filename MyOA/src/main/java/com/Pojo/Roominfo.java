package com.Pojo;

import lombok.Data;

@Data
public class Roominfo {
    private int roomid;//会议室id
    private String roomname;//会议室名称
    private int peoplenum;//容纳人数
    private String roomaddress;//会议室地址
    private int state;//会议室状态
    private int isapply;//是否被申请
}
