package util;

import org.apache.commons.lang.StringUtils;

/**
 * Created by lxy on 27/02/2018.
 */
public class VersionUtils {

    public static final String VERSION_PATTERN_REGEX = "[0-9]+(\\.[0-9]+)*";
    public static final String VERSION_SPLIT_REGEX = "\\.";

    /**
     * 比较版本号大小
     * @param v1
     * @param v2
     * @return 0，表示v1 == v2; -1，表示v1 < v2; 1，表示v1 > v2; -2,表示比较失败
     */
    public static int compareVersion(String v1, String v2) {
        if (StringUtils.isBlank(v1) && StringUtils.isBlank(v2)) {
            return 0;
        }
        if (StringUtils.isBlank(v1)) {
            return -1;
        }
        if (StringUtils.isBlank(v2)) {
            return 1;
        }
        try {
            if (v1.matches(VERSION_PATTERN_REGEX) && v2.matches(VERSION_PATTERN_REGEX)) {
                String[] arrV1 = v1.split(VERSION_SPLIT_REGEX);
                String[] arrV2 = v2.split(VERSION_SPLIT_REGEX);
                int ops = Math.min(arrV1.length, arrV2.length);
                int sign = 0;
                for (int i = 0; i < ops && sign == 0; i++) {
                    sign = Integer.compare(Integer.parseInt(arrV1[i]), Integer.parseInt(arrV2[i]));
                }
                if (sign == 0) {
                    if (arrV1.length > ops) {
                        for (int i = ops; i < arrV1.length && sign == 0; i++) {
                            sign = Integer.compare(Integer.parseInt(arrV1[i]), 0);
                        }
                    }
                    if (arrV2.length > ops) {
                        for (int i = ops; i < arrV2.length && sign == 0; i++) {
                            sign = Integer.compare(0, Integer.parseInt(arrV2[i]));
                        }
                    }
                }
                return sign;
            }
        } catch (Exception e) {
            System.out.println("VersionUtils.compareVersion failed, v1=" + v1 + ", v2=" + v2 + ", " + e.getMessage());
        }
        return -2;
    }
}
