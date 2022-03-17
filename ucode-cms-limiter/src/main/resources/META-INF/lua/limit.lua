local key = KEYS[1]
local count = tonumber(ARGV[1]) or 1
local ttl = tonumber(ARGV[2]) or 1

local current = tonumber(redis.call('get', key))

if current == nil then
    redis.call("setex", key , ttl , '1')
    return 0
else
    if current > count then
        return 1
    else
        redis.call("incr", key)
        redis.call("expire", key , ttl)
        return 0
    end
end
