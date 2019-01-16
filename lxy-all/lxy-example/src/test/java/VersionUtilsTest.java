import org.junit.Assert;
import org.junit.Test;
import util.VersionUtils;

/**
 * Created by lxy on 26/02/2018.
 */
public class VersionUtilsTest {

    @Test
    public void testCompareVersionCase01() {
        Assert.assertTrue(VersionUtils.compareVersion(null, null) == 0);
        Assert.assertTrue(VersionUtils.compareVersion("", "") == 0);
        Assert.assertTrue(VersionUtils.compareVersion("", "0.1") == -1);
        Assert.assertTrue(VersionUtils.compareVersion("0.1", "") == 1);
    }

    @Test
    public void testCompareVersionCase02() {
        Assert.assertTrue(VersionUtils.compareVersion("0", "0") == 0);
        Assert.assertTrue(VersionUtils.compareVersion("0", "0.0") == 0);
        Assert.assertTrue(VersionUtils.compareVersion("0.0", "0") == 0);
        Assert.assertTrue(VersionUtils.compareVersion("0.0", "0.0") == 0);

        Assert.assertTrue(VersionUtils.compareVersion("0", "0.0.0") == 0);
        Assert.assertTrue(VersionUtils.compareVersion("0.0", "0.0.0") == 0);
        Assert.assertTrue(VersionUtils.compareVersion("0.0.0", "0.0.0") == 0);

        Assert.assertTrue(VersionUtils.compareVersion("0.0.0", "0") == 0);
        Assert.assertTrue(VersionUtils.compareVersion("0.0.0", "0.0") == 0);
        Assert.assertTrue(VersionUtils.compareVersion("0.0.0", "0.0.0") == 0);
    }

    @Test
    public void testCompareVersionCase03() {
        Assert.assertTrue(VersionUtils.compareVersion("0", "0.1") == -1);
        Assert.assertTrue(VersionUtils.compareVersion("0.1", "0") == 1);

        Assert.assertTrue(VersionUtils.compareVersion("0", "0.0.1") == -1);
        Assert.assertTrue(VersionUtils.compareVersion("0.0", "0.0.1") == -1);

        Assert.assertTrue(VersionUtils.compareVersion("0.0.1", "0") == 1);
        Assert.assertTrue(VersionUtils.compareVersion("0.0.1", "0.0") == 1);
    }

    @Test
    public void testCompareVersionCase04() {
        Assert.assertTrue(VersionUtils.compareVersion("0", "1") == -1);
        Assert.assertTrue(VersionUtils.compareVersion("1", "0") == 1);

        Assert.assertTrue(VersionUtils.compareVersion("0.0", "0.1") == -1);
        Assert.assertTrue(VersionUtils.compareVersion("0.1", "0.0") == 1);

        Assert.assertTrue(VersionUtils.compareVersion("0.0.0", "0.0.1") == -1);
        Assert.assertTrue(VersionUtils.compareVersion("0.0.1", "0.0.0") == 1);
    }

    @Test
    public void testCompareVersionCase05() {
        Assert.assertTrue(VersionUtils.compareVersion("9", "9.1") == -1);
        Assert.assertTrue(VersionUtils.compareVersion("9", "9.0.1") == -1);

        Assert.assertTrue(VersionUtils.compareVersion("8", "9.0") == -1);
        Assert.assertTrue(VersionUtils.compareVersion("8", "9.0.0") == -1);

        Assert.assertTrue(VersionUtils.compareVersion("8.10", "9") == -1);
        Assert.assertTrue(VersionUtils.compareVersion("8.10", "9.0") == -1);
        Assert.assertTrue(VersionUtils.compareVersion("8.10", "9.0.0") == -1);

        Assert.assertTrue(VersionUtils.compareVersion("8.10.9", "9") == -1);
        Assert.assertTrue(VersionUtils.compareVersion("8.10.9", "9.0") == -1);
        Assert.assertTrue(VersionUtils.compareVersion("8.10.9", "9.0.0") == -1);

        Assert.assertTrue(VersionUtils.compareVersion("8.10.9", "8.9.10") == 1);
    }

    @Test
    public void testCompareVersionCase06() {
        Assert.assertTrue(VersionUtils.compareVersion("9.1", "9") == 1);
        Assert.assertTrue(VersionUtils.compareVersion("9.0.1", "9") == 1);

        Assert.assertTrue(VersionUtils.compareVersion("9.0", "8") == 1);
        Assert.assertTrue(VersionUtils.compareVersion("9.0.0", "8") == 1);

        Assert.assertTrue(VersionUtils.compareVersion("9", "8.10") == 1);
        Assert.assertTrue(VersionUtils.compareVersion("9.0", "8.10") == 1);
        Assert.assertTrue(VersionUtils.compareVersion("9.0.0", "8.10") == 1);

        Assert.assertTrue(VersionUtils.compareVersion("9", "8.10.9") == 1);
        Assert.assertTrue(VersionUtils.compareVersion("9.0", "8.10.9") == 1);
        Assert.assertTrue(VersionUtils.compareVersion("9.0.0", "8.10.9") == 1);

        Assert.assertTrue(VersionUtils.compareVersion("8.9.10", "8.10.9") == -1);
    }

    @Test
    public void testCompareVersionCase07() {
        Assert.assertTrue(VersionUtils.compareVersion("9.1a", "9.0") == -2);
        Assert.assertTrue(VersionUtils.compareVersion("9.1", "9.0.1a") == -2);
    }
}
