package com.matthew.carvalhodagenais.modernaconverter.listeners

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.matthew.carvalhodagenais.modernaconverter.R
import com.matthew.carvalhodagenais.modernaconverter.data.entities.Currency
import com.matthew.carvalhodagenais.modernaconverter.utils.ImageViewSwapperUtil
import com.matthew.carvalhodagenais.modernaconverter.utils.NetworkConnectivityUtil
import com.matthew.carvalhodagenais.modernaconverter.viewmodels.ConvertViewModel

class ButtonListenerGenerator {

    /**
     * OnClickListener for the "calculate currency" button
     * Takes the base currency and the amount and converts it to the selected "to" currency
     *
     * @param context Context
     * @param parentLayout MotionLayout
     * @param editText EditText
     * @param button Button
     * @param baseTextView TextView
     * @param conversionTextView TextView
     * @param swapImageView ImageView
     * @param viewModel ConvertViewModel
     * @param lco LifecycleOwner
     * @param fromCurrency Currency?
     * @param toCurrency Currency?
     * @return View.OnClickListener
     */
    fun generateConvertListener(
        context: Context,
        parentLayout: MotionLayout,
        editText: EditText,
        button: Button,
        baseTextView: TextView,
        conversionTextView: TextView,
        swapImageView: ImageView,
        viewModel: ConvertViewModel,
        lco: LifecycleOwner,
        fromCurrency: Currency?,
        toCurrency: Currency?
    ): View.OnClickListener {
        return object: View.OnClickListener {
            override fun onClick(p0: View?) {
                if (editText.text.toString().trim() != "") {
                    if (fromCurrency != toCurrency) { // Check if both currencies are the same
                        button.isClickable = false

                        ImageViewSwapperUtil(context).swapToLoading(swapImageView)

                        // Close the keyboard
                        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(p0?.windowToken, 0)

                        // Convert the currency and set the TextViews with the proper strings
                        viewModel.getRates(fromCurrency!!.code).observe(lco, Observer {
                            if (it != null) {
                                baseTextView.text =
                                    String.format(context.resources.getString(R.string.base_convertfragment),
                                        it.base,
                                        editText.text.toString().toBigDecimal())
                                conversionTextView.text =
                                    String.format(context.resources.getString(R.string.conversion_convertfragment),
                                        toCurrency!!.code,
                                        (editText.text.toString().toBigDecimal())
                                                * (it.rates[toCurrency.code].toString().toBigDecimal()))
                                parentLayout.setTransition(R.id.start_to_end)
                                parentLayout.transitionToEnd()
                            } else { // 1) no internet 2) no response
                                if (NetworkConnectivityUtil().hasNetworkConnection(context)) {
                                    Snackbar.make(
                                        parentLayout,
                                        context.resources.getString(R.string.no_connection_snackbar),
                                        Snackbar.LENGTH_LONG
                                    ).show()
                                } else {
                                    Snackbar.make(
                                        parentLayout,
                                        context.resources.getString(R.string.no_api_snackbar),
                                        Snackbar.LENGTH_LONG
                                    ).show()
                                }
                            }
                            button.isClickable = true
                            ImageViewSwapperUtil(context).swapToStatic(swapImageView)
                        })
                    } else {
                        Snackbar.make(
                            parentLayout,
                            "Pick 2 different currencies",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                } else { // No amount put in the EditText
                    Snackbar.make(
                        parentLayout,
                        context.resources.getString(R.string.no_amount_snackbar),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

}