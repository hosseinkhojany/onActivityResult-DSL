# onActivityResult-DSL
A clean way to handle onActivityResult

        private lateinit var selectImage: GetContentObserver

        //launch 
        button.onClickListener {
            startSignInActivity.selectImage()
        }

        //Must be call in the onCreate()
        selectImage = requireActivity().getContent { result ->
            put your code
        }
        
       
