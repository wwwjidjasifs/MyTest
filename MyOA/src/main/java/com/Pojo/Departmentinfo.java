package com.Pojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class Departmentinfo implements Serializable {
    private int departmentid;
    private String departmentname;
    private String departmentphone;
    private int departmentnumber;
}
