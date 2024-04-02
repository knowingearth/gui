package com.y.gui.service;

import com.y.gui.common.bases.Page;
import com.y.gui.dto.UserDTO;
import com.y.gui.vo.UserVO;

import java.util.List;

public interface UserQueryService {
    /**
     * 分页查询用户
     * @param vo
     * @return
     */
    Page<List<UserDTO>> queryUser(UserVO vo);
}
