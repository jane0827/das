package com.jane.das.entity;

import com.jane.das.model.Sex;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "das_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;                //唯一性标识


    private String username;        // 姓名

    private String password;        //密码

    private String phoneNumber;     // 电话

    private String email;          //邮箱

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;       // 创建时间

    private String prompt;          //密码提示

    @Enumerated(EnumType.STRING)
    private Sex sex;                //性别

}
