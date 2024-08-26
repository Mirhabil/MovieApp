package com.example.letterboxd.presentation.Fragments.ProfileFragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.letterboxd.R
import com.example.letterboxd.data.local.ImageDatabase.ImageDatabase
import com.example.letterboxd.data.local.ReviewsDatabase.ReviewsDatabase
import com.example.letterboxd.databinding.FragmentProfileBinding
import com.example.letterboxd.presentation.Adapters.KyransFavoriteFilmsAdapter
import com.example.letterboxd.presentation.Adapters.KyransRecentReviewedAdapter
import com.example.letterboxd.presentation.Adapters.KyransRecentWatchedAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class ProfileFragment:Fragment() {

    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { galleryUri: Uri? ->
        galleryUri?.let {
            try {
                // Save the image file
                saveFile(it)

                // Save the URI to SharedPreferences
                shprefProfilePicture.edit().putString("Salam", it.toString()).apply()

                // Log the URI
                Log.d("ProfileFragment", "Selected URI: $it")

                // Load the image into ImageView using Glide
                Glide.with(binding.imageViewProfilePr)
                    .load(it)
                    .error(R.drawable.ic_launcher_background) // Handle errors with a placeholder
                    .into(binding.imageViewProfilePr)

            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("ProfileFragment", "Error handling image", e)
            }
        }
    }


    @Inject
    lateinit var imageDatabase: ImageDatabase

    @Inject
    @Named("UserProfileImage")
    lateinit var shprefProfilePicture : SharedPreferences

    @Inject
    lateinit var reviewsDatabase:ReviewsDatabase

    val viewModel:ProfileViewModel by viewModels()

//    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
//        if (uri != null) {
//            Log.d("PhotoPicker", "Selected URI: $uri")
//            saveImageUri(uri)
//            refreshProfilePicture()
//        } else {
//            Log.d("PhotoPicker", "No media selected")
//        }
//    }




    private var _binding: FragmentProfileBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        // Load the previously saved image URI from SharedPreferences
        val uriString = shprefProfilePicture.getString("Salam", null)
        uriString?.let {
            Glide.with(binding.imageViewProfilePr)
                .load(Uri.parse(it))
                .error(R.drawable.ic_launcher_background)
                .into(binding.imageViewProfilePr)
        } ?: Log.d("ProfileFragment", "No URI found in SharedPreferences")

        // Launch the gallery to pick an image
        binding.imageViewSelector.setOnClickListener {
            //galleryLauncher.launch("image/*")
            showAlertDialog()
        }




//        binding.imageViewProfilePr.setOnClickListener{
//
//            showChangeProfileDialog()
//            refreshProfilePicture()
//        }
//
//        val currentUri = getProfilePictureUri()
//        // Check if the current picture is the default one
//        val isDefaultPicture = currentUri.toString() == "android.resource://${requireActivity().packageName}/${R.drawable.userprofilefoto}"
//
//
//
//
//        binding.imageViewBackPoster.setOnClickListener {
//
//
//            val defaultUri = Uri.parse("android.resource://${requireActivity().packageName}/${R.drawable.userprofilefoto}")
//
//            if(defaultUri!=currentUri) {
//
//                Glide.with(binding.imageViewProfilePr)
//                    .load(defaultUri)
//
//                    .placeholder(R.drawable.userprofilefoto)
//                    .into(binding.imageViewBackPoster)
//
//                // Clear the profile image URI in SharedPreferences
//                shprefProfilePicture.edit().remove("profile_image_uri").apply()
//
//
//            }
//
//        }
//
//
//
//
//
//        binding.imageViewProfilePr.setOnClickListener {
//            try {
//                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
//            } catch (e: Exception) {
//                Log.e("PhotoPicker", "PHOTOPICKER FAILED!")
//            }
//        }
//
//        //shprefProfilePicture=requireActivity().getSharedPreferences("mySharePref", Context.MODE_PRIVATE)
//
//
//
//        val profileImageUri = shprefProfilePicture.getString("profile_image_uri", null)
//        if (profileImageUri != null) {
//            binding.imageViewProfilePr.setImageURI(Uri.parse(profileImageUri))
//        }
//


        val adapterKyransRecentReviewed=KyransRecentReviewedAdapter(shprefProfilePicture.getString("Salam",null))
        binding.KyransRecentReviewedRV.adapter=adapterKyransRecentReviewed

        lifecycleScope.launch {
            adapterKyransRecentReviewed.getAdapterList(reviewsDatabase.getReviewsDao().getALLPosters())


        }



        val adapter2=KyransRecentWatchedAdapter(

            onClick = {findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToMoviePageFragment(it))}
        )

        val adapter=KyransFavoriteFilmsAdapter(

            onClick = {findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToMoviePageFragment(it.id))}
        )

//        val imageDatabase= Room.databaseBuilder(
//
//            requireContext(),ImageDatabase::class.java,"image_database"
//        ).build()

        val posterDao=imageDatabase.getPosterDAO()

        binding.KyransFavoriteFilmsRV.adapter=adapter
        binding.kyransRecentWatchedrv.adapter=adapter2

        val scope= CoroutineScope(Dispatchers.IO)


        scope.launch {

            val a=posterDao.getALLPosters()

            requireActivity().runOnUiThread{

                adapter.getAdapterList(a)


            }




        }

        lifecycleScope.launch {

            viewModel.responseFlow.collect{

                adapter2.getAdapterList(it)


            }
        }

        binding.textViewClearall.setOnClickListener {

            scope.launch {

                posterDao.deleteAllRooms()


            }

        }








    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null

    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Alert Dialog")
        builder.setMessage("Do you want to set a new profile foto?")

        // Add the buttons
        builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
            // User clicked OK button
            galleryLauncher.launch("image/*")
        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
            // User cancelled the dialog
        })

        // Create the AlertDialog
        val dialog: AlertDialog = builder.create()

        // Show the AlertDialog
        dialog.show()
    }

    private fun saveFile(sourceUri: Uri) {
        val contentResolver = requireContext().contentResolver
        val destinationFile = File(requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "profile_image_${System.currentTimeMillis()}.jpg")
        var bis: BufferedInputStream? = null
        var bos: BufferedOutputStream? = null

        try {
            val inputStream = contentResolver.openInputStream(sourceUri)
                ?: throw IOException("Unable to open input stream for $sourceUri")

            bis = BufferedInputStream(inputStream)
            bos = BufferedOutputStream(FileOutputStream(destinationFile))
            val buf = ByteArray(1024)
            var bytesRead: Int
            while (bis.read(buf).also { bytesRead = it } != -1) {
                bos.write(buf, 0, bytesRead)
            }

            Log.d("ProfileFragment", "File saved to: ${destinationFile.absolutePath}")

        } catch (e: IOException) {
            e.printStackTrace()
            Log.e("ProfileFragment", "Error saving file", e)
        } finally {
            try {
                bis?.close()
                bos?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }



//    fun showChangeProfileDialog() {
//
//        val currentUri = getProfilePictureUri()
//        // Check if the current picture is the default one
//        val isDefaultPicture = currentUri.toString() == "android.resource://${requireActivity().packageName}/${R.drawable.userprofilefoto}"
//
//
//        val dialogTitle = if (isDefaultPicture) "  Set a profile picture" else "  Change or Delete picture"
//        val dialogMessage = if (isDefaultPicture) "Choose a new profile picture from the gallery." else "You can choose a new profile picture from the gallery, or delete to set default!"
//
//        val dialogBuilder = AlertDialog.Builder(requireContext())
//            .setTitle(dialogTitle)
//            .setMessage(dialogMessage)
//            .setPositiveButton("Change") { _, _ ->
//                chooseGalleryDialog()
//            }
//
//        if (!isDefaultPicture) {
//            dialogBuilder.setNegativeButton("Delete") { _, _ ->
//                // Set default image and clear the URI in SharedPreferences
//                val defaultUri = Uri.parse("android.resource://${requireActivity().packageName}/${R.drawable.userprofilefoto}")
//                Glide.with(binding.imageViewProfilePr)
//                    .load(defaultUri)
//                    .placeholder(R.drawable.userprofilefoto)
//                    .into(binding.imageViewProfilePr)
//
//                // Clear the profile image URI in SharedPreferences
//                shprefProfilePicture.edit().remove("profile_image_uri").apply()
//
//
//            }
//        }
//
//        val dialog = dialogBuilder.create()
//
//
//        dialog.show()
//    }

//    private fun chooseGalleryDialog() {
//
//        val dialog = AlertDialog.Builder(requireContext())
//            .setTitle("Choose")
//            .setMessage("You can choose one from your gallery.")
//            .setPositiveButton("Choose") { _, _ ->
//                try {
//                    pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
//                } catch (e: Exception) {
//                    Log.e("PhotoPicker", "PHOTOPICKER FAILED!")
//                }
//            }.create()
//
//
//        dialog.show()
//    }


//    private fun saveImageUri(uri: Uri) {
//        shprefProfilePicture.edit()
//            .putString("profile_image_uri", uri.toString())
//            .apply()
//    }
//
//    private fun getProfilePictureUri(): Uri {
//        val uriImage = shprefProfilePicture.getString("profile_image_uri", null)
//        return if (uriImage != null) {
//            Uri.parse(uriImage)
//        } else {
//            // Return a default drawable URI as fallback
//            Uri.parse("android.resource://${requireActivity().packageName}/${R.drawable.userprofilefoto}")
//        }
//    }
//
//    private fun refreshProfilePicture() {
//        val uri = getProfilePictureUri()
//        Glide.with(binding.imageViewProfilePr)
//            .load(uri)
//            .placeholder(R.drawable.userprofilefoto)
//            .into(binding.imageViewProfilePr)
//    }

//    private fun saveImageUri(uri: Uri) {
//        shprefProfilePicture.edit()
//            .putString("profile_image_uri", uri.toString())
//            .apply()
//    }
//
//    private fun getProfilePictureUri(): Uri {
//        val uriImage = shprefProfilePicture.getString("profile_image_uri", null)
//        return if (uriImage != null) {
//            Uri.parse(uriImage)
//        } else {
//            // Return a default drawable URI as fallback
//            Uri.parse("android.resource://${requireActivity().packageName}/${R.drawable.userprofilefoto}")
//        }
//    }
//
//    private fun refreshProfilePicture() {
//        val uri = getProfilePictureUri()
//        Glide.with(binding.imageViewProfilePr)
//            .load(uri)
//            .placeholder(R.drawable.userprofilefoto)
//            .into(binding.imageViewProfilePr)
//    }


}