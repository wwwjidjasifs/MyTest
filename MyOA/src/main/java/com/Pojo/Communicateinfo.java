package com.Pojo;

import lombok.Data;

@Data
//会议实体类
public class Communicateinfo {
    private int comid;//会议id
    private int roomid;//会议室id
    private int userid;//用户id
    private String title;//会议名称
    private String starttime;//开始时间
    private String endtime;//结束时间
    private int state;//会议审核状态
    private String roomname;//会议室名称
    private String username;//用户名
}
