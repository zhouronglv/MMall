package xin.keepmoving.mmall.util;

import java.math.BigDecimal;

/**
 * <p>@author: zhourl(zhouronglv@gmail.com)
 * <p>@description: mmall
 * <p>@version: v1.0
 * <p>@date: 2018/6/29
 **/
public class BigDecimalUtil  {
    private BigDecimalUtil() {
        super();
    }

    /**
     * 加
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.add(b2);
    }

    /**
     * 减
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal subtract(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.subtract(b2);
    }

    /**
     * 乘
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal multiply(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.multiply(b2);
    }

    /**
     * 除
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal divide(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        // 四舍五入，保留两位小数
        return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP);
    }
}
