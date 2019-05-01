package com.joyzone.platform.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class RedisGeoUtil {
    private static Logger logger= LoggerFactory.getLogger(RedisGeoUtil.class);


    /**
     * 增加地理位置的坐标
     * @param key
     * @param memberName
     * @return
     */
    public static Long geoadd(StringRedisTemplate stringRedisTemplate, String key, Point point, String memberName) {
        return stringRedisTemplate.opsForGeo().geoAdd(key, point,memberName);
    }

    /**
     * 判断经纬度是否有效
     * @param longitude
     * @param latitude
     * @return
     */
    public static boolean isValidCoordinate(StringRedisTemplate stringRedisTemplate, double longitude, double latitude) {
        if (0.0 > longitude || 180.0 < longitude) {
            logger.error("isValidCoordinate  error   longitude 0.0 > {}  或 180.0 < {} ",longitude,longitude);
            return false;
        }

        //纬度最大是90° 最小是0°
        // CGFloat latitude = coordinate.latitude;
        if (0.0 > latitude || 90.0 < latitude) {
            logger.error("isValidCoordinate  error  latitude 0.0 > {}  或 180.0 < {} ",latitude,latitude);
            return false;
        }
        return true;
    }



    /**
     * 批量添加地理位置
     * @param key
     * @param memberCoordinateMap
     * @return
     */
    public static Long geoadd(StringRedisTemplate stringRedisTemplate, String key, Map<String, Point> memberCoordinateMap){
        return stringRedisTemplate.opsForGeo().geoAdd(key, memberCoordinateMap);
    }

    /**
     * 根据给定地理位置坐标获取指定范围内的地理位置集合（返回匹配位置的经纬度 + 匹配位置与给定地理位置的距离 + 从近到远排序，）
     * @param key
     * @param coordinate
     * @param radius
     * @return  List<GeoRadiusResponse>
     */
   /* public static List<GeoRadiusResponse> geoRadius(String key, GeoCoordinate coordinate, double radius) {
        Jedis jedis = null;
        try {
            jedis = Redis.use().getJedis();
            return jedis.georadius(key, coordinate.getLongitude(), coordinate.getLatitude(), radius, GeoUnit.KM, GeoRadiusParam.geoRadiusParam().withDist().withCoord().sortAscending());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (null != jedis)
                jedis.close();
        }
        return stringRedisTemplate.opsForGeo().geoRadius(geoRadius)
        return null;
    }*/

    /**
     * 根据给定地理位置获取指定范围内的地理位置集合（返回匹配位置的经纬度 + 匹配位置与给定地理位置的距离 + 从近到远排序，）
     * @param key
     * @param radius
     * @return  List<GeoRadiusResponse>
     */
    /*List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius){
        Jedis jedis = null;
        try {
            jedis = Redis.use().getJedis();
            return jedis.georadiusByMember(key, member, radius, GeoUnit.KM, GeoRadiusParam.geoRadiusParam().withDist().withCoord().sortAscending());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (null != jedis)
                jedis.close();
        }
        return null;
    }*/

    public static List<GeoRadiusDto> geoRadius(StringRedisTemplate stringRedisTemplate, String key, Point center, Double radius, Metric metric, Sort.Direction direction) {
        System.out.println("stringRedisTemplate============="+stringRedisTemplate);
        List<GeoRadiusDto> radiusDtos = new ArrayList<GeoRadiusDto>();
        Distance distance = new Distance(radius, metric);
        Circle within = new Circle(center, distance);
        RedisGeoCommands.GeoRadiusCommandArgs args = null;
        if(direction.isAscending()){
            args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().sortAscending().includeCoordinates();
        }
        if(direction.isDescending()){
            args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().sortDescending().includeCoordinates();
        }
        GeoResults<RedisGeoCommands.GeoLocation<String>> geoResults = stringRedisTemplate.opsForGeo().geoRadius(key, within, args);
        List<GeoResult<RedisGeoCommands.GeoLocation<String>>> geoResultList = geoResults.getContent();
        for(GeoResult<RedisGeoCommands.GeoLocation<String>> geoResult:geoResultList){
            String name = geoResult.getContent().getName();
            Point point = geoResult.getContent().getPoint();
            GeoRadiusDto radiusDto = new GeoRadiusDto();
            radiusDto.setKey(key);
            radiusDto.setMember(name);
            radiusDto.setX(point.getX());
            radiusDto.setY(point.getY());
            radiusDtos.add(radiusDto);
        }
        return radiusDtos;
    }

    /**
     * @Title: geoRadiusByMember
     * @Description: TODO(根据成员查询附近点)
     * @param key
     * @param member 成员
     * @param radius 半径
     * @param metric 半径单位
     * @param direction 排序
     * @return List<GeoRadiusDto>
     */
    public static List<GeoRadiusDto> geoRadiusByMember(StringRedisTemplate stringRedisTemplate, String key, String member, Double radius, Metric metric, Sort.Direction direction) {
        List<GeoRadiusDto> radiusDtos = new ArrayList<GeoRadiusDto>();
        Distance distance = new Distance(radius, metric);
        RedisGeoCommands.GeoRadiusCommandArgs args = null;
        if(direction.isAscending()){
            args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().sortAscending().includeCoordinates();;
        }
        if(direction.isDescending()){
            args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().sortDescending().includeCoordinates();;
        }
        GeoResults<RedisGeoCommands.GeoLocation<String>> geoResults = stringRedisTemplate.opsForGeo().geoRadiusByMember(key, member, distance, args);
        List<GeoResult<RedisGeoCommands.GeoLocation<String>>> geoResultList = geoResults.getContent();
        for(GeoResult<RedisGeoCommands.GeoLocation<String>> geoResult:geoResultList){
            String name = geoResult.getContent().getName();
            Point point = geoResult.getContent().getPoint();
            GeoRadiusDto radiusDto = new GeoRadiusDto();
            radiusDto.setKey(key);
            radiusDto.setMember(name);
            radiusDto.setX(point.getX());
            radiusDto.setY(point.getY());
            radiusDtos.add(radiusDto);
        }
        return radiusDtos;
    }

    /**
     * 查询两位置距离
     * @param key
     * @param member1
     * @param member2
     * @return
     */
    public static Double geoDist(StringRedisTemplate stringRedisTemplate, String key, String member1, String member2, Metric metric){
        return stringRedisTemplate.opsForGeo().geoDist(key, member1, member2, metric).getValue();
    }

    /**
     * 可以获取某个地理位置的geohash值
     * @param key
     * @param members
     * @return
     */
    public static List<String> geohash(StringRedisTemplate stringRedisTemplate, String key, String... members){
        return stringRedisTemplate.opsForGeo().geoHash(key, members);
    }

    /**
     * 获取地理位置的坐标
     * @param key
     * @param members
     * @return
     */
    public static List<Point> geopos(StringRedisTemplate stringRedisTemplate, String key, String... members){
        return stringRedisTemplate.opsForGeo().geoPos(key, members);
    }

}
