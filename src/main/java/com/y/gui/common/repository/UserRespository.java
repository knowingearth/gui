package com.y.gui.common.repository;

import com.y.gui.dto.UserDTO;
import com.y.gui.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class UserRespository {
    private static final Map<Integer, UserDTO> userDB = new HashMap<>();

    private static Random random = new Random();

    static {
        for (int i = 0; i < 10000; i++) {
            UserDTO dto = new UserDTO();
            Integer id = getId();
            dto.setId(id);
            dto.setName("name" + id);
            dto.setAge(1);
            dto.setGender(i % 2);
            dto.setAddress("地址" + id);
            userDB.put(id, dto);
        }
    }

    private static Integer getId() {
        while (true) {
            Integer id = Math.abs(random.nextInt(10000));
            if (!userDB.containsKey(id)) {
                return id;
            }
        }
    }

    /**
     * 从库里查询数据
     * @param param
     * @return
     */
    public static List<UserDTO> queryUserFromDB(UserVO param) {
        List<UserDTO> result = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            result.add(userDB.get(Math.abs(random.nextInt(10000))));
        }
        return result;
    }
}
