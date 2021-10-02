import android.content.Intent
import android.net.Uri
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.activity.result.contract.ActivityResultContracts.CreateDocument
/* This part has been commented because it could not be used in the project. You can use this template to meet your needs */
//import androidx.activity.result.contract.ActivityResultContracts.GetMultipleContents
//import androidx.activity.result.contract.ActivityResultContracts.OpenDocument
//import androidx.activity.result.contract.ActivityResultContracts.OpenDocumentTree
//import androidx.activity.result.contract.ActivityResultContracts.OpenMultipleDocuments
//import androidx.activity.result.contract.ActivityResultContracts.PickContact
//import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
//import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
//import androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult
//import androidx.activity.result.contract.ActivityResultContracts.TakePicture
//import androidx.activity.result.contract.ActivityResultContracts.TakePicturePreview
//import androidx.activity.result.contract.ActivityResultContracts.TakeVideo
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

object ActivityResultObservers {

    /*
    * Example Usage:
    *
    * requireActivity().getContent { result ->
            put your code
        }.selectImage()
    *
    * requireActivity().startActivityForResult { result ->
            put your code
        }
    *
    * */

    //region StartActivityForResultObservers
    class StartActivityForResultObservers(
        private val registry : ActivityResultRegistry,
        private val resCallback: (res: ActivityResult) -> Unit
        ) : DefaultLifecycleObserver{
        private lateinit var getContent : ActivityResultLauncher<Intent>
        private val key = System.currentTimeMillis().toString()
        fun getKey(): String = key
        override fun onCreate(owner: LifecycleOwner) {
            super.onCreate(owner)
            getContent = registry.register(key, owner, StartActivityForResult()) {
                resCallback.invoke(it)
            }
        }
        fun launch(intent: Intent){
            if (this::getContent.isInitialized){
                getContent.launch(intent)
            }
        }
    }
    fun FragmentActivity.startActivityForResult(resCallback: (res: ActivityResult) -> Unit){
        this.lifecycle.addObserver(StartActivityForResultObservers(this.activityResultRegistry, resCallback))
    }
    //endregion


    //region GetContentObserver
    class GetContentObserver(
        private val registry : ActivityResultRegistry,
        private val resCallback: (res: Uri) -> Unit
    ) : DefaultLifecycleObserver{
        private lateinit var getContent : ActivityResultLauncher<String>
        private val key = System.currentTimeMillis().toString()
        fun getKey(): String = key
        override fun onCreate(owner: LifecycleOwner) {
            super.onCreate(owner)
            getContent = registry.register(key, owner, GetContent()) {
                resCallback.invoke(it)
            }
        }
        fun selectImage(){
            if (this::getContent.isInitialized){
                getContent.launch("*/image")
            }
        }
        fun selectVideo(){
            if (this::getContent.isInitialized){
                getContent.launch("*/video")
            }
        }
    }
    fun FragmentActivity.getContent(resCallback: (res: Uri) -> Unit): GetContentObserver{
        val contentObserver = GetContentObserver(this.activityResultRegistry, resCallback)
        this.lifecycle.addObserver(contentObserver)
        return contentObserver
    }
    //endregion

    //region CreateDocumentObserver
    class CreateDocumentObserver(
        private val registry : ActivityResultRegistry,
        private val resCallback: (res: Uri) -> Unit
    ) : DefaultLifecycleObserver{
        private lateinit var getContent : ActivityResultLauncher<String>
        private val key = System.currentTimeMillis().toString()
        fun getKey(): String = key
        override fun onCreate(owner: LifecycleOwner) {
            super.onCreate(owner)
            getContent = registry.register(key, owner, CreateDocument()) {
                resCallback.invoke(it)
            }
        }
//        fun `putYourAction`(){
//            if (this::getContent.isInitialized){
//                getContent.launch("*/image")
//            }
        }
    }
    fun FragmentActivity.createDocument(resCallback: (res: Uri) -> Unit): ActivityResultObservers.CreateDocumentObserver {
        val contentObserver = ActivityResultObservers.CreateDocumentObserver(this.activityResultRegistry, resCallback)
        this.lifecycle.addObserver(contentObserver)
        return contentObserver
    }
    //endregion




}
