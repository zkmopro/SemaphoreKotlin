package uniffi.mopro

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import uniffi.mopro.*
import java.io.File


@RunWith(AndroidJUnit4::class)
class SemaphoreTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.github.zkmopro.test", appContext.packageName)
    }

    @Test
    fun testSemaphoreIdentity() {
        val privateKey = "secret".toByteArray()
        // generate an identity from privateKey
        val identity = Identity(privateKey)
        val commitment = identity.commitment()
        assertEquals(commitment, "21756852044673293804725356853298692762259855200429755225624171532449447776732")
        val identityPrivateKey = identity.privateKey()
        assertArrayEquals(identityPrivateKey, privateKey)
        val secretScalar = identity.secretScalar()
        assertEquals(secretScalar, "1072931509665125050858164614503996272893941281138625620671594663472720926391")
        val toElement = identity.toElement()
        val byteArray = byteArrayOf(
            220.toByte(), 145.toByte(), 41.toByte(), 230.toByte(), 37.toByte(), 163.toByte(), 179.toByte(), 162.toByte(),
            235.toByte(), 150.toByte(), 78.toByte(), 165.toByte(), 42.toByte(), 67.toByte(), 48.toByte(), 31.toByte(),
            188.toByte(), 116.toByte(), 185.toByte(), 247.toByte(), 87.toByte(), 191.toByte(), 177.toByte(), 59.toByte(),
            167.toByte(), 248.toByte(), 170.toByte(), 60.toByte(), 30.toByte(), 241.toByte(), 25.toByte(), 48.toByte()
        )
        assertArrayEquals(toElement, byteArray)
    }

    @Test
    fun testSemaphoreGroup() {
        val privateKey = "secret".toByteArray()
        val privateKey2 = "secret2".toByteArray()
        // generate an identity from privateKey
        val identity = Identity(privateKey)
        val identity2 = Identity(privateKey2)
        val group = Group(listOf(
            identity.toElement(),
            identity2.toElement()
        ))
        val groupRoot = group.root()
        val byteArray = byteArrayOf(
            252.toByte(), 10.toByte(), 239.toByte(), 67.toByte(), 190.toByte(), 43.toByte(), 60.toByte(), 182.toByte(), 30.toByte(), 147.toByte(), 1.toByte(), 30.toByte(), 7.toByte(), 23.toByte(), 232.toByte(), 198.toByte(), 106.toByte(), 240.toByte(), 50.toByte(), 0.toByte(), 192.toByte(), 125.toByte(), 228.toByte(), 215.toByte(), 83.toByte(), 223.toByte(), 100.toByte(), 83.toByte(), 108.toByte(), 15.toByte(), 135.toByte(), 31.toByte(),
        )
        assertArrayEquals(groupRoot, byteArray)
    }

    fun testSemaphoreProof() {
        val message = "message"
        val scope = "scope"
        val privateKey = "secret".toByteArray()
        val privateKey2 = "secret2".toByteArray()
        val identity = Identity(privateKey)
        val identity2 = Identity(privateKey2)
        val group = Group(listOf(
            identity.toElement(),
            identity2.toElement()
        ))
        val proof = generateSemaphoreProof(
            identity,
            group,
            message,
            scope,
            16.toUShort()
        )
        val valid = verifySemaphoreProof(
            proof
        )
        assertEquals(valid, true)
    }

    
} 