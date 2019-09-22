package com.mroomsoft.hellodagger2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Named

const val LOVE = "Love"
const val LIKE = "Like"


class MainActivity : AppCompatActivity() {

    @Inject @field:Named(LOVE) lateinit var infoLove: Info
    @Inject @field:Named(LIKE) lateinit var infoLike: Info
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerMagicBox.create().poke(this)
        tvText.text = "${infoLove.text} ${infoLike.text}"

    }
}

@Component(modules = [Bag::class])
interface MagicBox {
    fun poke(app: MainActivity)
}

@Module
open class Bag {
    @Provides @Named(LOVE)
    open fun sayLoveDagger2(): Info {
        return Info("Love Dagger 2")
    }

    open @Provides @Named(LIKE)
    fun sayLikeDagger2(): Info {
        return Info("Like Dagger 2")
    }
}

class Info constructor(val text: String? = "Hello Dagger2")
