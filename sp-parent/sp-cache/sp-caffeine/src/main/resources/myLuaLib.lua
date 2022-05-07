
-- 封装Http请求
local function read_http(path,params)
    local resp = ngx.location.capture(path,{
        method = ngx.HTTP_GET,
        args = params
    })
    if not resp then
        ngx.log(ngx.ERR,"http查询失败 path: ",path,", params: ",params)
    end
    return resp.body
end

--引入redis
local redis = require("resty.redis")
local redisObj = redis:new()
redisObj:set_timeouts(1000,1000,1000)

--把redis链接释放回连接池
local function close_redis(redis_Obj)
    if not redis_Obj then
        return
    end
    local pool_max_idle_time = 10000 --链接的空闲时间 单位毫秒
    local pool_size = 100 --连接池大小
    local ok,err = redis_Obj:set_keepalive(pool_max_idle_time,pool_size)
    if not ok then
        ngx.log(ngx.ERR,"放入Redis连接池失败",err)
    end
end

-- 查看redis
local function read_redis(ip,port,key)
    local ok,err = redisObj:connect(ip,port)
    if not ok then
        ngx.log(ngx.ERR,"连接redis失败",err)
    end
    --查询redis
    local resp,err = redisObj:get(key)
    if not resp then
        ngx.log(ngx.ERR,"查询redis失败:",err," key= ",key)
    end
    close_redis(redisObj)
    return resp
end



--到处函数
local _MM = {
    read_http = read_http,
    read_redis = read_redis
}
return _MM