package content.howto.create_a_custom_json_marshaller

import org.http4k.core.Body
import content.howto.create_a_custom_json_marshaller.MyJackson.auto

val lens = Body.auto<Customer>().toLens()
