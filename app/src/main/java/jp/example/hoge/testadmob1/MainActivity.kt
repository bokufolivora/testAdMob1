package jp.example.hoge.testadmob1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.AdSize
import kotlinx.android.synthetic.main.activity_main.*

// sdkでのAdMob広告表示
class MainActivity : AppCompatActivity() {
    private val adTextDeviceId = "テストする端末のデバイスID"
    private val adTestId="ca-app-pub-3940256099942544/6300978111"
    private var debugMode = true
    private var adUnitId="本番のID"
    private var blAdView : Boolean = false
    private lateinit var adMobView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 広告
        if(debugMode) {
            adUnitId = adTestId
        }
        adMobView = AdView(this)
        adMobView.adUnitId = adUnitId
        adMobView.adSize = AdSize.BANNER
        val adRequest : AdRequest = AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                    .addTestDevice(adTextDeviceId)
                    .build()
        adMobView.loadAd(adRequest)

        button.setOnClickListener { _ ->
            redisplayAdMob()
        }
    }
    private fun redisplayAdMob() {
        if (blAdView) {
            // 表示時 -> 消す
            adMobView.visibility = AdView.GONE
            admob_layout.removeView(adMobView)
            Log.d("AdMob", "OFF")
            blAdView = false
        } else {
            // 非表示 -> 表示
            admob_layout.addView(adMobView)
            admob_layout.visibility = LinearLayout.VISIBLE
            adMobView.visibility = AdView.VISIBLE
            Log.d("AdMob", "ON")
            blAdView = true
        }
    }

}
