package a.lib

import org.junit.Test
import org.robolectric.util.reflector.ForType
import org.robolectric.util.reflector.Reflector

class RobolectricTest {
    @Test
    fun test() {
        Reflector.reflector(_Object_::class.java)
    }

    @ForType(Object::class)
    private interface _Object_
}
