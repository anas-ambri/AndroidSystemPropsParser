import com.anasambri.lib.SystemPropsParser
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class SystemPropsParserTest {

    @Test
    fun `parses alRl properties`() {
        val input = """
           [dalvik.vm.appimageformat]: [lz4]
           [dalvik.vm.appimage]: []
           [dalvik.vm.appimageformat.extra]: [lz5]
        """

        val output = SystemPropsParser.parse(input)
        assertThat(output["dalvik"]["vm"]["appimageformat"][""] as String, `is`("lz4"))
        assertThat(output["dalvik"]["vm"]["appimage"] as String, `is`(""))
        assertThat(output["dalvik"]["vm"]["appimageformat"]["extra"] as String, `is`("lz5"))
    }
}

private operator fun Any?.get(s: String): Any {
    val map = this as Map<String, Any>
    return map[s]!!
}
