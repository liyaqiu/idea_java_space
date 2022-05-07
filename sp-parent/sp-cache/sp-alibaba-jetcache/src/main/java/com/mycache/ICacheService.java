package com.mycache;

/**
 * @author lyq
 * @date 2022/3/10 19:11
 */
public interface ICacheService {
    public CacheUser selectById(String id);

    public CacheUser selectById2(String id);

    public void updateUser(CacheUser user);
    public void deleteUser(String id);
}
