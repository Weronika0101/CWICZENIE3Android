package com.example.cwiczenie3android

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.view.ActionMode
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import java.util.Calendar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentRight.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentRight : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private var myAM : ActionMode? = null
    lateinit var tvChangeColor: TextView
    lateinit var tvChangedColor: TextView
    lateinit var conBirth: TextView
    lateinit var conBirthDate: TextView

    val myAMCallback: ActionMode.Callback = object: ActionMode.Callback{
        override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
            val inflater = mode.menuInflater
            inflater.inflate(R.menu.cam_view,menu)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
            return false
        }

        override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
           // var color_tv:TextView = binding.textcolor

            return when (item.itemId) {
                R.id.item1c -> {
                    tvChangedColor.setBackgroundColor(Color.RED);
                    mode.finish()
                    true
                }
                R.id.item2c -> {
                    tvChangedColor.setBackgroundColor(Color.GREEN);
                    mode.finish()
                    true
                }
                R.id.item3c -> {
                    tvChangedColor.setBackgroundColor(Color.BLUE);
                    mode.finish()
                    true
                }
                else -> {
                    mode.finish()
                    false
                }
            }
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            myAM=null
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_right, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvChangeColor = (requireActivity().findViewById<View>(R.id.tvColor) as TextView)
        tvChangedColor = (requireActivity().findViewById<View>(R.id.textcolor) as TextView)

        tvChangeColor.setOnLongClickListener(View.OnLongClickListener { view ->
            if (myAM != null) {
                return@OnLongClickListener false
            }
            val toolbar = (requireActivity().findViewById<View>(R.id.toolbar) as androidx.appcompat.widget.Toolbar)
            myAM = toolbar.startActionMode(myAMCallback)
            view.isSelected = true
            true
        })

        conBirth = (requireActivity().findViewById<View>(R.id.birthDate1) as TextView)
        conBirthDate = (requireActivity().findViewById<View>(R.id.birthDate2) as TextView)

        conBirthDate.setOnClickListener { _ ->
            val cal = Calendar.getInstance()

            DatePickerDialog(
                requireActivity(),
                { _: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                    conBirthDate.text = "$dayOfMonth-${monthOfYear + 1}-$year"
                },
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        val backButton: Button = (requireActivity().findViewById<View>(R.id.btnback) as Button)
        backButton.setOnClickListener { _ ->
            val builder: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
            builder
                .setTitle("Go Back Dialog")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes") { dialog, which ->
                    requireActivity().onBackPressed()
                }.setNegativeButton("No") { dialog, which ->
                    dialog.cancel()
                }.create().show()
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentRight.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentRight().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}