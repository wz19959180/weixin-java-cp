package com.github.binarywang.demo.wx.cp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WechatUserInfo {
    private int errcode;
    private String errmsg;
    private String userid;
    private String name;
    private List<Integer> department;
    private String position;
    private int status;
    private int isleader;
    private ExtAttr extattr;
    private String telephone;
    private int enable;
    private int hideMobile;
    private List<Integer> order;
    private ExternalProfile externalProfile;
    private int mainDepartment;
    private String alias;
    private List<Integer> isLeaderInDept;
    private List<String> directLeader;

    @Data
    public static class ExtAttr {
        private List<Attr> attrs;

        @Data
        public static class Attr {
            private String name;
            private String value;
            private int type;
            private Text text;

            @Data
            public static class Text {
                private String value;
            }
        }
    }

    @Data
    public static class ExternalProfile {
        private List<ExternalAttr> externalAttr;
        private String externalCorpName;

        @Data
        public static class ExternalAttr {
            // Define fields as necessary for external attributes
        }
    }
}
