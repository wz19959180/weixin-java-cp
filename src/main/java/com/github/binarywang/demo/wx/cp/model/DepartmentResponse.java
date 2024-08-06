package com.github.binarywang.demo.wx.cp.model;

import lombok.Data;
import java.util.List;

@Data
public class DepartmentResponse {
    private int errcode;
    private String errmsg;
    private List<Department> department;

    @Data
    public static class Department {
        private int id;
        private String name;
        private String name_en;
        private List<String> department_leader;
        private int parentid;
        private int order;
    }
}
