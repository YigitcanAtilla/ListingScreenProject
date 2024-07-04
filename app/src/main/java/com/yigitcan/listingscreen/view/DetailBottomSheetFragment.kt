package com.yigitcan.listingscreen.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import coil.load
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yigitcan.listingscreen.R
import com.yigitcan.listingscreen.databinding.FragmentDetailBottomSheetBinding
import com.yigitcan.listingscreen.model.ItemModel
import com.yigitcan.listingscreen.util.Constants.IMAGE_URL
import com.yigitcan.listingscreen.util.parcelable
import com.yigitcan.listingscreen.viewmodel.ListingViewModel

class DetailBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentDetailBottomSheetBinding? = null
    private val binding get() = _binding!!

    private var item: ItemModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            item = it.parcelable("item")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel : ListingViewModel by activityViewModels()

        item?.let {
            binding.apply {
                headerTitleEditText.setText(it.title)
                descriptionEditText.setText(it.description)
                detailUiImage.load(IMAGE_URL.replace("{id}", it.id.toString())) {
                    placeholder(R.drawable.ic_launcher_background)
                    error(R.drawable.ic_launcher_background)
                }
                updateButton.setOnClickListener {
                    val updatedItem = ItemModel(id = item?.id, title = headerTitleEditText.text.toString(), description = descriptionEditText.text.toString())
                    viewModel.updateItem(updatedItem)
                    dismiss()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(item: ItemModel): DetailBottomSheetDialogFragment {
            val fragment = DetailBottomSheetDialogFragment()
            val args = Bundle()
            args.putParcelable("item", item)
            fragment.arguments = args
            return fragment
        }
    }
}