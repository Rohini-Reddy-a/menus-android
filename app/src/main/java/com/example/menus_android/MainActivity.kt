package com.example.menus_android

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.widget.ListPopupWindow
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout


class MainActivity : AppCompatActivity() {

    private lateinit var parent: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        parent = findViewById(R.id.parent)
        val showMenuTextView = findViewById<MaterialCardView>(R.id.popup_menu_card_view)
        val showListPopupMenuTextView = findViewById<MaterialCardView>(R.id.list_popup_menu_card_view)
        val showContextMenuTextView = findViewById<MaterialCardView>(R.id.context_menu_card_view)

        showContextMenuTextView.setOnLongClickListener {
            showSnackbar("Add Context Options and remove this")
            true
        }

        val textInputMenu = findViewById<TextInputLayout>(R.id.text_input_menu)
        val items = listOf("Option 1", "Option 2", "Option 3", "Option 4")
        val adapter = ArrayAdapter(applicationContext, R.layout.list_popup_window_item, items)
        (textInputMenu.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        showMenuTextView.setOnClickListener { view ->
            showMenuOnClick(view)
        }

        showListPopupMenuTextView.setOnClickListener { view ->
            showListPopUpMenu(view)
        }
    }

    @SuppressLint("RestrictedApi")
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.overflow_menu, menu)

        if (menu is MenuBuilder) {
            menu.setOptionalIconsVisible(true)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_email -> showSnackbar("Received Email")
            R.id.menu_alert -> showSnackbar("Received Notification")
            R.id.menu_delete -> showSnackbar("Deleted Everything")
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(parent, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun showListPopUpMenu(view: View) {
        val listPopupWindow = ListPopupWindow(applicationContext, null, R.attr.listPopupWindowStyle)
        listPopupWindow.anchorView = view

        val items = listOf("One", "Two", "Three", "Four")
        val adapter = ArrayAdapter(applicationContext, R.layout.list_popup_window_item, items)
        listPopupWindow.setAdapter(adapter)
        listPopupWindow.show()
    }

    private fun showMenuOnClick(view: View) {
        val popupMenu = PopupMenu(applicationContext, view)
        popupMenu.menuInflater.inflate(R.menu.android_menu, popupMenu.menu)
        popupMenu.show()

        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.option1 -> {
                    Toast.makeText(applicationContext, "One", Toast.LENGTH_SHORT).show()
                }
                R.id.option2 -> {
                    Toast.makeText(applicationContext, "Two", Toast.LENGTH_SHORT).show()
                }
                R.id.sub_1 -> {
                    Toast.makeText(applicationContext, "Sub 1", Toast.LENGTH_SHORT).show()
                }
                R.id.sub_2 -> {
                    Toast.makeText(applicationContext, "Sub 2", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(applicationContext, "Three", Toast.LENGTH_SHORT).show()
                }
            }

            true
        }
    }
}