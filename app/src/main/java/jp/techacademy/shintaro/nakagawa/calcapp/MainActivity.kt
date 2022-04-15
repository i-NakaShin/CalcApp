package jp.techacademy.shintaro.nakagawa.calcapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.util.Log
import android.content.Intent
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addition.setOnClickListener(this)
        substraction.setOnClickListener(this)
        multiplication.setOnClickListener(this)
        division.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (!editText1.text.isNullOrBlank() && !editText2.text.isNullOrBlank() && editText1.text.toString() != "." && editText2.text.toString() != ".") {
            val edit1 = editText1.text.toString()
            val edit2 = editText2.text.toString()
            val num1 = edit1.toFloat()
            val num2 = edit2.toFloat()
            var sum: Float = 0.toFloat()

            if (v.id == R.id.addition) {
                sum = num1 + num2
            } else if (v.id == R.id.substraction) {
                sum = num1 - num2
            } else if (v.id == R.id.multiplication) {
                sum = num1 * num2
            } else if (v.id == R.id.division) {
                try {
                    sum = num1 / num2
                } catch (e: Exception) {
                    Log.d("kotlintest", "0で割ろうとしました")
                    Log.d("kotlintest", e.message.toString())
                } finally {
                    Snackbar.make(v, "0での割り算はできません", Snackbar.LENGTH_SHORT)
                        .show()
                    return
                }
            }

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("VALUE1", sum)
            startActivity(intent)

        } else {
            Snackbar.make(v, "何か数値を入力してください", Snackbar.LENGTH_SHORT)
                .show()
        }
    }
}