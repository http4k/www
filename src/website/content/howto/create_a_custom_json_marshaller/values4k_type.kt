package content.howto.create_a_custom_json_marshaller

import dev.forkhandles.values.StringValue
import dev.forkhandles.values.StringValueFactory

class CustomerNameV4k(value: String) : StringValue(value) {
    companion object : StringValueFactory<CustomerNameV4k>(::CustomerNameV4k)
}
