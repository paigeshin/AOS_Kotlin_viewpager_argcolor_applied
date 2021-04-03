# AOS_Kotlin_viewpager_argcolor_applied

```kotlin

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mVocabularyCardList = ArrayList<VocabularyCard>()
    private lateinit var mAdapter: VocabularyListAdapter
    private lateinit var mColorArray: Array<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeVocabularyCardList()
        val tempColorArray = arrayOf(
                ContextCompat.getColor(this, R.color.slide_color_first),
                ContextCompat.getColor(this, R.color.slide_color_second),
                ContextCompat.getColor(this, R.color.slide_color_third),
                ContextCompat.getColor(this, R.color.slide_color_fourth)
        )
        mColorArray = tempColorArray
        setViewPager()
    }

    private fun initializeVocabularyCardList() {
        mVocabularyCardList.add(VocabularyCard("sku", null, R.drawable.image1, "Item1", "Hello World"))
        mVocabularyCardList.add(VocabularyCard("sku", null, R.drawable.image2, "Item2", "Hello World"))
        mVocabularyCardList.add(VocabularyCard("sku", null, R.drawable.image3, "Item3", "Hello World"))
    }

    private fun setViewPager() {
        val argbEvaluator = ArgbEvaluator()
        mAdapter = VocabularyListAdapter(this, mVocabularyCardList)
        binding.viewPager.adapter = mAdapter
        binding.viewPager.setPadding(130, 0, 130, 0)
        binding.viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if(position < mAdapter.count - 1 && position < (mColorArray.size - 1)) {
                    val argbValue = argbEvaluator.evaluate(positionOffset, mColorArray[position], mColorArray[position + 1]) as Int
                    binding.viewPager.setBackgroundColor(argbValue)
                } else {
                    val backgroundColor = mColorArray[mColorArray.size - 1]
                    binding.viewPager.setBackgroundColor(backgroundColor)
                }
            }
            override fun onPageSelected(position: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

}


```

```xml

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <androidx.viewpager.widget.ViewPager
        android:background="@color/black"
        android:foregroundGravity="center"
        android:id="@+id/viewPager"
        android:layout_centerInParent="true"
        android:overScrollMode="never"
        android:clipToPadding="false"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
    
    <com.google.android.material.button.MaterialButton
        android:id="@+id/button_select"
        android:textColor="#fff"
        android:paddingHorizontal="30dp"
        android:layout_marginBottom="60dp"
        android:layout_alignParentBottom="true"
        android:layout_centerHorizontal="true"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>

</RelativeLayout>

```