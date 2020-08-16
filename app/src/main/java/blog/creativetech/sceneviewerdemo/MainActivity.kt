package blog.creativetech.sceneviewerdemo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        simple_btn.setOnClickListener {
            val sceneViewerIntent = Intent(Intent.ACTION_VIEW)
            val intentUri = createIntentUri()

            /* Alternative version */
            /* val intentUri = Uri.parse("https://arvr.google.com/scene-viewer/1.0").buildUpon()
                    .appendQueryParameter("file", "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/Duck/glTF/Duck.gltf")
                    .appendQueryParameter("mode", "ar_only")
                    .appendQueryParameter("title", "Duck")
                    .appendQueryParameter("resizable", "false")
                    .build() */
            sceneViewerIntent.setData(intentUri);
            sceneViewerIntent.setPackage("com.google.android.googlequicksearchbox")
            startActivity(sceneViewerIntent)
        }
    }

    private fun createIntentUri() : Uri {
        val intentUri = Uri.parse("https://arvr.google.com/scene-viewer/1.0").buildUpon()
        val params = getIntentParams()
        params.forEach { (key, value) -> intentUri.appendQueryParameter(key, value) }
        return intentUri.build()
    }

    private fun getIntentParams() : HashMap<String, String> {
        val map = HashMap<String, String> ()
        map["file"] = if (model_duck_rbt.isChecked) {
            "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/Duck/glTF/Duck.gltf"
        } else {
            "https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/BrainStem/glTF/BrainStem.gltf"
        }.toString()
        if (title_switch.isChecked) {
            map["title"] = "Here is your title"
        }
        if (link_switch.isChecked) {
            map["link"] = "https://google.com"
        }

        when {
            ar_only_rbt.isChecked ->  map["mode"] = "ar_only"
            ar_preferred.isChecked -> map["mode"] = "ar_preferred"
        }

        if (resizable_switch.isChecked) {
            map["resizable"] = "true"
        } else {
            map["resizable"] = "false"
        }
        return map
    }
}