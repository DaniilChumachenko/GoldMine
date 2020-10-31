import com.security_space.presentation.aboutus.fileToArray
import com.security_space.presentation.aboutus.goldMine
import org.junit.Assert
import org.junit.Test

class GoldMainTests {
    @Test
    fun testGoldMine() {
        Assert.assertEquals(22, goldMine(fileToArray("tests/testGoldMine1.txt",4,4),4,4))
    }
    @Test
    fun testGoldMineWithArrayOfZeros() {
        Assert.assertEquals(0, goldMine(fileToArray("tests/testGoldMine2.txt",3,3),3,3))
    }
    @Test
    fun testGoldMineWithEmptyArray() {
        Assert.assertEquals(0, goldMine(fileToArray("tests/testGoldMine3.txt",3,3),3,3))
    }
    @Test
    fun testGoldMineWithRowsAndColumnsOfZeros() {
        Assert.assertEquals(0, goldMine(fileToArray("tests/testGoldMine2.txt",0,0),0,0))
    }
}