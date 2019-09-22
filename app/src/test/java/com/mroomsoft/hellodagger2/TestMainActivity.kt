package com.mroomsoft.hellodagger2

import dagger.Component
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

class TestMainActivity {
    @Inject @field:Named(LOVE) lateinit var infoLove: Info
    @Inject @field:Named(LIKE) lateinit var infoLike: Info


    @Before
    fun setup() {
        DaggerTestMagicBox
            .builder().bag(TestBag()).build().poke(this)
    }

    @Test
    fun simpleTest(){
        assertEquals("Test Love", infoLove.text)
    }
}


class TestBag: Bag() {
    override fun sayLoveDagger2(): Info {
        return Info("Test Love")
    }
}

@Component(modules = [Bag::class])
interface TestMagicBox: MagicBox {
    fun poke(app:TestMainActivity)
}