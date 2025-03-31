package org.secretdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.secretdemo.domain.dto.UserDto;
import org.secretdemo.domain.entity.UserRecord;

public interface UserMapper extends BaseMapper<UserRecord> {
    UserDto selectById2(@Param("id") Long id);
}
