package org.secretdemo.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("`sys_user`")
public class UserRecord implements Serializable {
    @TableId(value = "id", type = IdType.ID_WORKER)
    Long id;

    String username;

    String password;

    @TableField("real_name")
    String realname;
}
