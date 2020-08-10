package a.agent

import java.lang.instrument.ClassFileTransformer
import java.lang.instrument.Instrumentation
import java.security.ProtectionDomain
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.util.CheckClassAdapter

class Main : ClassFileTransformer {
    override fun transform(loader: ClassLoader?, className: String, classBeingRedifined: Class<*>?, protectionDomain: ProtectionDomain?, classfileBuffer: ByteArray): ByteArray? = try {
        val reader = ClassReader(classfileBuffer)
        val writer = ClassWriter(reader, 0)
        reader.accept(CheckClassAdapter(writer), 0)
        writer.toByteArray()
    } catch (e: Exception) {
        e.printStackTrace()
        throw e
    }

    companion object {
        @JvmStatic
        fun agentmain(agentArgs: String?, inst: Instrumentation) {
            inst.addTransformer(Main())
        }

        @JvmStatic
        fun premain(agentArgs: String?, inst: Instrumentation) {
            inst.addTransformer(Main())
        }
    }
}
