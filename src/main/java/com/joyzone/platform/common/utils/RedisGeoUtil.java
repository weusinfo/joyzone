package com.joyzone.platform.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
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
     * @param point 坐标
     * @param radius 范围
     * @return  List<geoRadius>
     */
   public static List<GeoRadiusDto> geoRadius(StringRedisTemplate redisTemplate,String key,Point point, double radius) {
       logger.debug("-----------------------------根据坐标开始获取附近信息开始---------------");
       GeoOperations<String, String> geoOps = redisTemplate.opsForGeo();
       //设置geo查询参数
       RedisGeoCommands.GeoRadiusCommandArgs geoRadiusArgs = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs();
       geoRadiusArgs = geoRadiusArgs.includeCoordinates().includeDistance();//查询返回结果包括距离和坐标
       geoRadiusArgs.sortAscending();//按查询出的坐标距离中心坐标的距离进行排序
       geoRadiusArgs.limit(RedisColumn.NUM);//限制查询数量

       List<GeoRadiusDto> radiusDtos = new ArrayList<GeoRadiusDto>();
       GeoResults<RedisGeoCommands.GeoLocation<String>> radiusGeo = geoOps.geoRadius(key,
               new Circle(point, new Distance(radius, RedisGeoCommands.DistanceUnit.KILOMETERS)),geoRadiusArgs);

        if(radiusGeo != null ){
            for (GeoResult<RedisGeoCommands.GeoLocation<String>> geoResult : radiusGeo) {
                String name = geoResult.getContent().getName();
                Point pointx = geoResult.getContent().getPoint();
                GeoRadiusDto radiusDto = new GeoRadiusDto();
                radiusDto.setKey(key);
                radiusDto.setMember(name);
                radiusDto.setX(pointx.getX());
                radiusDto.setY(pointx.getY());
                radiusDtos.add(radiusDto);
            }
        }
       logger.debug("-----------------------------根据坐标开始获取附近信息结束---------------");
       return  radiusDtos;
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


    /**
     * 获取用户附近的商家信息
     * @param shopDto
     * @Mr.Gx
     */
//    public R getAppShopList(ShopDto shopDto){
//        Double longitude = shopDto.getLng().doubleValue();
//        Double latitude = shopDto.getLat().doubleValue();
//        Point geoCoordinate=new Point(longitude,latitude);
//        //根据给定地理位置坐标获取指定范围内的地理位置集合
//        List<GeoRadiusDto>  geoRadiusResponseList = RedisGeoUtil.geoRadius(redisService.getStringRedisTemplate(), RedisColumn.SHOP_MODEL,geoCoordinate,radius);
//        String areaCode = null;
//        if(geoRadiusResponseList !=null && geoRadiusResponseList.size()>0){
//            for (GeoRadiusDto geoRadiusResponse:geoRadiusResponseList) {
//                String shopId = new String(geoRadiusResponse.getMember());
//                ShopModel shop = findById(Long.valueOf(shopId));
//                if(shop != null && (shop.getArea()!=null && !shop.getArea().equals("666666"))){
//                    areaCode = shop.getArea();
//                    break;
//                }
//            }
//            if(areaCode==null) {
//                areaCode= "666666";
//            }
//        }else{
//            areaCode= "666666";
//        }
//        //如有区域编号就根据编号查询所区别下的店家信息
//        if(!"666666".equals(areaCode)){
//            shopDto.setAreaCode(areaCode);
//        }
//        shopDto.setStatus(ShopModel.STATUS_SUCCESS); //已签约
//        List<AppShopVO> list = shopMapper.getAppShopList(shopDto);
//        //计算离商家的距离
//        if(list!=null && list.size()>0){
//            for (AppShopVO appShopVO: list) {
//                Double lon = appShopVO.getLng() != null ? appShopVO.getLng() : 0.0;
//                Double lat = appShopVO.getLat() != null ? appShopVO.getLat() : 0.0;
//                appShopVO.setDistance(LocationUtils.getDistance(latitude,longitude,lat,lon));
//            }
//            //距离排序
//            Collections.sort(list, new Comparator<AppShopVO>(){
//                public int compare(AppShopVO arg0, AppShopVO arg1) {
//                    BigDecimal hits0 = BigDecimal.valueOf(arg0.getDistance());
//                    BigDecimal hits1 = BigDecimal.valueOf(arg1.getDistance()) ;
//                    if (hits1.compareTo(hits0) > 0) {
//                        return -1;
//                    } else if (hits1.compareTo(hits0) == 0) {
//                        return 0;
//                    } else {
//                        return 1;
//                    }
//                }
//            });
//            Page page = new Page();
//            page = (Page)list;
//            return R.pageToData(page.getTotal(),page.getResult());
//        }
//        return R.pageToData(0L,list);
//    }

}
