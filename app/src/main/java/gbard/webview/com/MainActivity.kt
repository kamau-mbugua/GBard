package gbard.webview.com

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import gbard.webview.com.databinding.ActivityMainBinding
import gbard.webview.com.loaders.TransparentProgressDialog
import im.delight.android.webview.AdvancedWebView

class MainActivity : AppCompatActivity(), AdvancedWebView.Listener {
    private var _binding: ActivityMainBinding ?= null
    private val binding get() = _binding!!

    private var transparentProgressDialog: TransparentProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpWebView()
        swipeRefresh()
    }

    private fun swipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.webview.reload()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setUpWebView() {
        transparentProgressDialog = TransparentProgressDialog(this, "Loading...")

        binding.webview.setListener(this, this)
        binding.webview.setMixedContentAllowed(false)
        binding.webview.loadUrl("https://bard.google.com/")
    }

    @SuppressLint("NewApi")
    override fun onResume() {
        super.onResume()
        binding.webview.onResume()
    }

    @SuppressLint("NewApi")
    override fun onPause() {
        super.onPause()
        binding.webview.onPause()
    }

    override fun onStop() {
        super.onStop()
//        binding.webview.onDestroy()
        Log.d("MainActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.webview.onDestroy()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (!binding.webview.onBackPressed()) {
            return
        }else{
            super.onBackPressed()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        binding.webview.onActivityResult(requestCode, resultCode, data)
    }

    override fun onPageStarted(url: String?, favicon: Bitmap?) {
        transparentProgressDialog!!.show()
    }

    override fun onPageFinished(url: String?) {
        transparentProgressDialog!!.dismiss()

    }

    override fun onPageError(errorCode: Int, description: String?, failingUrl: String?) {
        transparentProgressDialog!!.dismiss()

    }

    override fun onDownloadRequested(
        url: String?,
        suggestedFilename: String?,
        mimeType: String?,
        contentLength: Long,
        contentDisposition: String?,
        userAgent: String?
    ) {

    }

    override fun onExternalPageRequest(url: String?) {

    }
}