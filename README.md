# onActivityResult-DSL
A clean way to handle onActivityResult


        requireActivity().getContent { result ->
            put your code
        }.selectImage()
        
        
        requireActivity().startActivityForResult { result ->
            put your code
        }
