package com.joyzone.platform.common.utils;

/**
 * 商家评论自动回复处理
 */
public class ShopCommentUtil {


    // 很好的回答
    private final static String FINE = "感谢您的评价,欢迎您再次光临!";
    // 一般的回答
    private final static String MIDDLING  = "感谢您的评价,我们将努力做到好为止!";
    // 环境很差的回答
    private final static String ENVIRONMENT_LOWER = "感谢您的评价,我们必将重视起来并加以改善环境!";
    // 服务很差的回答
    private final static String SERVICE_LOWER = "感谢您的评价,我们必将重视起来并提高服务质量!";
    // 体验很差的回答
    private final static String PLAY_LOWER = "感谢您的评价,我们必将重视起来并提高用户体验!";



    public static String adminContent(Byte type){
        if(null == type){
            return null;
        }
        String content = null;
        switch (type) {
            case 1:
                content =  FINE;
                break;
            case 2:
                content =  MIDDLING;
                break;
            case 3:
                content =  ENVIRONMENT_LOWER;
                break;
            case 4:
                content =  FINE;
                break;
            case 5:
                content =  MIDDLING;
                break;
            case 6:
                content =  SERVICE_LOWER;
                break;
            case 7:
                content =  FINE;
                break;
            case 8:
                content =  MIDDLING;
                break;
            case 9:
                content =  PLAY_LOWER;
                break;
            default:
                break;
        }
        return content;
    }
}
