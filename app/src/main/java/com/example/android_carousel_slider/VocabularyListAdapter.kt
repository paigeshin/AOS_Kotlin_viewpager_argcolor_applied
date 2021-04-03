package com.example.android_carousel_slider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.android_carousel_slider.databinding.ItemVocabularyListBinding

class VocabularyListAdapter(
    private val context: Context,
    private val vocabularyCardList: ArrayList<VocabularyCard>
): PagerAdapter() {
    override fun getCount(): Int = vocabularyCardList.size
    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) = container.removeView(`object` as View)
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = LayoutInflater.from(context)
        val binding = ItemVocabularyListBinding.inflate(layoutInflater, container, false)
        val card = vocabularyCardList[position]
        card.imageResource?.let {
            binding.imageviewVocabularyList.setImageResource(it)
        }
//        card.imageURL?.let {
//            Glide
//                .with(context)
//                .load(it)
//                .centerCrop()
//                .into(binding.imageviewVocabularyList);
//        }
        binding.textviewTitle.text = card.title
        binding.textviewDescription.text = card.description
        container.addView(binding.root)
        return binding.root
    }

}