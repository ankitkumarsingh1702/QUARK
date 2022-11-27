package com.example.quark

import android.content.Intent
import android.graphics.Color
import android.graphics.Color.RED
import android.hardware.camera2.params.RggbChannelVector.RED
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.quark.databinding.FragmentMessageBinding
import com.example.quark.databinding.FragmentVerificationBinding
import java.util.Objects


class VerificationFragment : Fragment() {

    private lateinit var binding: FragmentVerificationBinding

    val longTextWithLinks = "android.com"


    override fun onCreateView(



        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        binding = FragmentVerificationBinding.inflate(layoutInflater)


        binding.usersaveDatanew.setOnClickListener {
            startActivity(Intent(requireContext(), verificationadmin::class.java))
        }

        clickableLink(longTextWithLinks)


        return binding.root
    }

    private fun clickableLink(longText: String) {
       try {
           val spanned = SpannableString(longText)
           val matcher = Patterns.WEB_URL.matcher(longText)
           var matchStart: Int
           var matchEnd: Int

           while (matcher.find()) {
               matchStart = matcher.start()
               matchEnd = matcher.end()

               var url = longText.substring(matchStart, matchEnd)
               if (!url.startsWith("http://") && !url.startsWith("https://"))
                   url = "https://$url"
               val clickableSpan: ClickableSpan = object : ClickableSpan() {
                   override fun onClick(widget: View) {
                       val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                   }

                   override fun updateDrawState(ds: TextPaint) {
                       super.updateDrawState(ds)
                       ds.color = Color.RED
                       ds.isUnderlineText = false

                   }
               }
               spanned.setSpan(
                   clickableSpan, matchStart, matchEnd,
                   Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
               )
           }
           binding.adminsaveData.text = spanned
           binding.adminsaveData.movementMethod = LinkMovementMethod.getInstance()
       } catch (e: Exception){
          e.printStackTrace()
       }
    }



}