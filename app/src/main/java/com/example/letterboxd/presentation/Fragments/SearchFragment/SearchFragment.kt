package com.example.letterboxd.presentation.Fragments.SearchFragment

//@AndroidEntryPoint
//class SearchFragment: Fragment() {
//
//    @Inject
//    lateinit var popularMoviesRepo:PopularMoviesRepo
//
//    @Inject
//    lateinit var api:PopularMovies
//
//
//
//
//    private var _binding: FragmentSearchBinding?=null
//    private val binding get() = _binding!!
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding= FragmentSearchBinding.inflate(layoutInflater)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val adapter=SearchAdapter()
//        binding.SearchRV.adapter=adapter
//
//        val inputLayout=binding.inputLayout
//        val editText=binding.editText
//
//        editText.requestFocus()
//
//        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
//
//
//        binding.button12.setOnClickListener {
//            val searchText=editText.text.toString()
//
//            Toast.makeText(requireContext(), "yavas basda", Toast.LENGTH_SHORT).show()
//
//
//            lifecycleScope.launch {
//
//                val response=popularMoviesRepo.getSearchResponse(searchText)
//                Log.e("niyecixmire",response.toString())
//                adapter.getAdapterList(response)
//
//            }
//
//
//
//
//
//        }
//
//
//
//
//
//
//
//
//
//
//    }
//
//
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding=null
//    }
//}