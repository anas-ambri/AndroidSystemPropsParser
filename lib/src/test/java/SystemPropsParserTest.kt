import com.anasambri.lib.SystemPropsParser
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class SystemPropsParserTest {

    @Test
    fun `parses one property`() {
        val input = """
           [dalvik.vm.appimageformat]: [lz4]
           [dalvik.vm.appimage]: [png]
        """

        val output = SystemPropsParser.parse(input)
        assertThat(output["dalvik"]["vm"]["appimageformat"] as String, `is`("lz4"))
        assertThat(output["dalvik"]["vm"]["appimage"] as String, `is`("png"))
    }
}

private operator fun Any?.get(s: String): Any {
    val map = this as Map<String, Any>
    return map[s]!!
}
