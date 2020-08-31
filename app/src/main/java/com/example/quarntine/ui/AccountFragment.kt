@file:Suppress("DEPRECATION")

package com.example.quarntine.ui


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide

import com.example.quarntine.R
import com.example.quarntine.databinding.FragmentAccountBinding
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_account.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.ByteArrayOutputStream


@Suppress("DEPRECATION")
class AccountFragment : Fragment() {
    private val rCSELECTIMAGE = 2
    private lateinit var selectedimageBytes: ByteArray
    private var pictureJustChanged = false
    private val accountFragmentViewModel by viewModel<AccountFragmentViewModel>()


    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAccountBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)
        val view = binding.root

        view.apply {
            binding.authviewmodel = accountFragmentViewModel

               btn_save.setOnClickListener {
                   accountFragmentViewModel.savedata(
                       editText_name.toString(),
                       editText_bio.toString(),
                       imageView_profile_picture.toString()
                   )
               }




        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == rCSELECTIMAGE && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val selectedImage = data.data
            val selectedImageBmp = MediaStore.Images.Media
                .getBitmap(activity?.contentResolver, selectedImage)
            val outputStream = ByteArrayOutputStream()
            selectedImageBmp.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
            selectedimageBytes = outputStream.toByteArray()
            Glide.with(this)
                .load(selectedimageBytes)
                .into(imageView_profile_picture)
            pictureJustChanged = true

        }

    }


}
