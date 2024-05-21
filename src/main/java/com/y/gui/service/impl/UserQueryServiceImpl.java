package com.y.gui.service.impl;

import com.y.gui.common.annotations.CLog;
import com.y.gui.common.bases.Page;
import com.y.gui.common.extension.RedisExt;
import com.y.gui.common.repository.UserRespository;
import com.y.gui.dto.UserDTO;
import com.y.gui.service.UserQueryService;
import com.y.gui.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserQueryServiceImpl implements UserQueryService {
    private Long expireTime = 60L * 60L;

    @Autowired
    public RedisExt cache;

    /**
     * 分页查询用户
     * @param vo
     * @return
     */
    @Override
    @CLog
    public Page<List<UserDTO>> queryUser(UserVO vo) {
        // 1.获取缓存key
        String cacheKey = vo.toString();

        // 2.判断是否有缓存数据
        if (!cache.hasKey(cacheKey)) {
            // 缓存无数据，去DB查数据写入缓存
            initCacheData(vo);
        }

        // 3.从缓存查数据
        List<UserDTO> resultDtos = new ArrayList<>();
        Set userIdSet = cache.zRange(cacheKey, vo.getStart(), vo.getStop());
        userIdSet.forEach(id -> resultDtos.add(cache.get(id + "")));
        Long count = cache.zCard(cacheKey);

        // 4.返回数据
        return new Page<>(vo.getPage(), vo.getPageSize(), count, resultDtos);
    }

    /**
     * 查询数据写入缓存
     * @param vo
     */
    private void initCacheData(UserVO vo) {
        // 1.获取缓存key
        String cacheKey = vo.toString();
        String cacheScoreKey = cacheKey + "score";

        // 2.从库里查询数据 todo
        List<UserDTO> userDTOS = UserRespository.queryUserFromDB(vo);

        // 3.将数据写入缓存
        for (UserDTO dto : userDTOS) {
            cache.zAdd(cacheKey, dto.getId(), Double.valueOf(cache.incr(cacheScoreKey)));
            cache.set(dto.getId() + "", dto, expireTime);
        }

        // 4.设置缓存1小时过期
        cache.expire(cacheKey, expireTime);
        cache.del(cacheScoreKey);
    }
}
