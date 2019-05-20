package cn.logicalthinking.common.cache;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.*;
import redis.clients.jedis.params.geo.GeoRadiusParam;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * jedis工具，用于操作redis
 *
 * @version 1.0
 * @date 2017年12月21日
 */
public class RedisManager {

    private Logger logger = Logger.getLogger(getClass());

    /**
     * redis服务器地址
     */
    @Value("${redis.host}")
    private String host;

    /**
     * redis服务端口
     */
    @Value("${redis.port}")
    private Integer port;

    /**
     * 过期时间（单位：秒）
     * 0 - 永不过期
     */
    @Value("${redis.app_login_expire}")
    private int app_login_expire;

    /**
     * 过期时间（单位：秒）
     * 0 - 永不过期
     */
    @Value("${redis.expire}")
    private int expire;

    /**
     * 超时多久jedis重新连接redis服务（单位：毫秒）
     */
    @Value("${redis.timeout}")
    private int timeout;

    /**
     * redis服务器密码
     */
    @Value("${redis.password}")
    private String password;

    /**
     * redis数据库索引号（redis默认有16个数据库，索引号为0-15）
     */
    @Value("${redis.database}")
    private int database;

    private JedisPoolConfig jedisPoolConfig;

    /**
     * jedis连接池
     */
    private static JedisPool jedisPool;

    public RedisManager() {
        /*System.out.println("host:"+host);*/
    }

    /**
     * 初始化方法
     */
    public void init() {
        if (jedisPool == null) {
            if (StringUtils.isNotBlank(password)) {
                jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password, database);
            } else if (timeout != 0) {
                jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, null, database);
            } else {
                jedisPool = new JedisPool(jedisPoolConfig, host, port, Protocol.DEFAULT_TIMEOUT, null, database, null);
            }
        }
    }

    /**
     * 根据Key得到Value
     *
     * @param key
     * @return
     */
    public byte[] get(byte[] key) {
        init();
        byte[] value = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
        return value;
    }

    /**
     * 根据Key得到Value
     *
     * @param key
     * @return
     */
    public String get(String key) {
        init();
        String result = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.get(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @return byte[]
     */
    public byte[] set(byte[] key, byte[] value) {
        init();
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
            if (this.expire != 0) {
                jedis.expire(key, this.expire);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
        return value;
    }

    /**
     * app登录存入
     *
     * @param key
     * @param value
     * @return String
     */
    public byte[] app_loginSet(byte[] key, byte[] value) {
        init();
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
            if (this.app_login_expire != 0) {
                jedis.expire(key, this.app_login_expire);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
        return value;
    }

    /**
     * app登录设置key-value
     *
     * @param key
     * @param value
     * @return String
     */
    public String set(String key, String value) {
        init();
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
            if (this.app_login_expire != 0) {
                jedis.expire(key, this.app_login_expire);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
        return value;
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public byte[] set(byte[] key, byte[] value, int expire) {
        init();
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
            if (expire != 0) {
                jedis.expire(key, expire);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
        return value;
    }

    /**
     * 设置key-value，并设置其过期时间expire
     *
     * @param key
     * @param value
     * @param expire
     * @return String
     */
    public String set(String key, String value, int expire) {
        init();
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
            if (expire != 0) {
                jedis.expire(key, expire);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
        return value;
    }

    /**
     * 设置key-value，并设置其过期时间expire
     *
     * @param key
     * @param field
     * @param value
     * @param expire
     * @return String
     */
    public String hset(String key, String field, String value, int expire) {
        init();
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hset(key, field, value);
            if (expire != 0) {
                jedis.expire(key, expire);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
        return value;
    }

    /**
     * 设置key-value，并设置其过期时间expire
     *
     * @param key
     * @param field
     * @param value
     * @param expire
     * @return String
     */
    public String[] hset(String key, String[] field, String[] value, int expire) {
        if (field.length != value.length) {
            throw new RuntimeException("field,value 长度不一致");
        }
        init();
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            for (int i = 0; i < field.length; i++) {

                jedis.hset(key, field[i], value[i]);
            }
            if (expire != 0) {
                jedis.expire(key, expire);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
        return value;
    }

    /**
     * 设置key-value，并设置其过期时间expire
     *
     * @param key
     * @param field
     * @return String
     */
    public String hget(String key, String field) {
        init();
        String result = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.hget(key, field);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
        return result;
    }

    /**
     * 设置key-value，并设置其过期时间expire
     *
     * @param key
     * @param field
     * @return String
     */
    public String[] hget(String key, String[] field) {
        init();
        Jedis jedis = null;
        String[] result = new String[field.length];
        try {
            jedis = jedisPool.getResource();
            for (int i = 0; i < field.length; i++) {
                result[i] = jedis.hget(key, field[i]);
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
        return result;
    }

    public boolean isExists(String key) {
        init();
        Jedis jedis = null;
        boolean isExists = false;
        try {
            jedis = jedisPool.getResource();
            isExists = jedis.exists(key);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
        return isExists;
    }

    /**
     * del
     *
     * @param key
     */
    public void del(byte[] key) {
        init();
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
    }

    /**
     * 删除指定键对应的值
     *
     * @param key
     */
    public void del(String key) {
        init();
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
    }

    /**
     * 为key设置过期时间
     */
    public void expire(byte[] key, int seconds) {
        init();
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.expire(key, seconds);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
    }


    /**
     * flush
     */
    public void flushDB() {
        init();
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.flushDB();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
    }

    /**
     * size
     */
    public Long dbSize() {
        init();
        Long dbSize = 0L;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            dbSize = jedis.dbSize();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
        return dbSize;
    }

    /**
     * keys
     *
     * @param pattern
     * @return
     */
    public Set<byte[]> keys(String pattern) {
        init();
        Set<byte[]> keys = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            keys = jedis.keys(pattern.getBytes());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            close(jedis);
        }
        return keys;
    }

    /**
     * 增加地理位置的坐标
     *
     * @param key
     * @param coordinate
     * @param memberName
     * @return
     */
    public Long geoadd(byte[] key, GeoCoordinate coordinate, byte[] memberName) {
        init();
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.geoadd(key, coordinate.getLongitude(), coordinate.getLatitude(), memberName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (null != jedis)
                jedis.close();
        }
        return null;
    }

    /**
     * 增加地理位置的坐标
     *
     * @param key
     * @param coordinate
     * @param memberName
     * @return
     */
    public Long geoadd(String key, GeoCoordinate coordinate, String memberName) {
        init();
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.geoadd(key, coordinate.getLongitude(), coordinate.getLatitude(), memberName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (null != jedis)
                jedis.close();
        }
        return null;
    }

    /**
     * 批量添加地理位置
     *
     * @param key
     * @param memberCoordinateMap
     * @return
     */
    public Long geoadd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
        init();
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.geoadd(key, memberCoordinateMap);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (null != jedis)
                jedis.close();
        }
        return null;
    }

    /**
     * 根据给定地理位置坐标获取指定范围内的地理位置集合（返回匹配位置的经纬度 + 匹配位置与给定地理位置的距离 + 从近到远排序，）
     *
     * @param key
     * @param coordinate
     * @param radius
     * @return List<GeoRadiusResponse>
     */
    public List<GeoRadiusResponse> geoRadius(byte[] key, GeoCoordinate coordinate, double radius) {
        init();
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.georadius(key, coordinate.getLongitude(), coordinate.getLatitude(), radius, GeoUnit.KM, GeoRadiusParam.geoRadiusParam().withDist().withCoord().sortAscending());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (null != jedis)
                jedis.close();
        }
        return null;
    }

    /**
     * 根据给定地理位置坐标获取指定范围内的地理位置集合（返回匹配位置的经纬度 + 匹配位置与给定地理位置的距离 + 从近到远排序，）
     *
     * @param key
     * @param coordinate
     * @param radius
     * @return List<GeoRadiusResponse>
     */
    public List<GeoRadiusResponse> geoRadius(String key, GeoCoordinate coordinate, double radius) {
        init();
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.georadius(key, coordinate.getLongitude(), coordinate.getLatitude(), radius, GeoUnit.KM, GeoRadiusParam.geoRadiusParam().withDist().withCoord().sortAscending());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (null != jedis)
                jedis.close();
        }
        return null;
    }

    /**
     * 根据给定地理位置获取指定范围内的地理位置集合（返回匹配位置的经纬度 + 匹配位置与给定地理位置的距离 + 从近到远排序，）
     *
     * @param key
     * @param member
     * @param radius
     * @return List<GeoRadiusResponse>
     */
    public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius) {
        init();
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.georadiusByMember(key, member, radius, GeoUnit.KM, GeoRadiusParam.geoRadiusParam().withDist().withCoord().sortAscending());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (null != jedis)
                jedis.close();
        }
        return null;
    }


    /**
     * 查询两位置距离
     *
     * @param key
     * @param member1
     * @param member2
     * @param unit
     * @return
     */
    public Double geoDist(String key, String member1, String member2, GeoUnit unit) {
        init();
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.geodist(key, member1, member2, unit);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (null != jedis)
                jedis.close();
        }
        return null;
    }

    /**
     * 可以获取某个地理位置的geohash值
     *
     * @param key
     * @param members
     * @return
     */
    public List<String> geohash(String key, String... members) {
        init();
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.geohash(key, members);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (null != jedis)
                jedis.close();
        }
        return null;
    }

    /**
     * 获取地理位置的坐标
     *
     * @param key
     * @param members
     * @return
     */
    public List<GeoCoordinate> geopos(String key, String... members) {
        init();
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.geopos(key, members);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (null != jedis)
                jedis.close();
        }
        return null;
    }

    private void close(Jedis jedis) {
        init();
        if (null != jedis) {
            jedis.close();
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public JedisPoolConfig getJedisPoolConfig() {
        return jedisPoolConfig;
    }

    public void setJedisPoolConfig(JedisPoolConfig jedisPoolConfig) {
        this.jedisPoolConfig = jedisPoolConfig;
    }

    public static JedisPool getJedisPool() {
        return jedisPool;
    }

    public static void setJedisPool(JedisPool jedisPool) {
        RedisManager.jedisPool = jedisPool;
    }

}
