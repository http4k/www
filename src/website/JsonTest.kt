import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.http4k.format.ConfigurableJackson
import org.http4k.format.asConfigurable
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class JsonTest {

    @Test
    fun test_conversions() {
        val data = Data("Bar", 42)
        val json = Json.asFormatString(data)
        Assertions.assertEquals("""{"foo":"Bar","i":42}""", json)
        Assertions.assertEquals(data, Json.asA<Data>(json))
    }

    data class Data(val foo: String, val i: Int)
    object Json : ConfigurableJackson(KotlinModule.Builder().build().asConfigurable().done())
}
