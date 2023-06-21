package com.Pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Menuinfo implements Serializable {
    private String menuid;
    private String title;
    private Integer state;
    private String url;
    private String pid;
    private String iconCls;
}
