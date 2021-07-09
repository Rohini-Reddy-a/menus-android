package com.example.menus_android

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.widget.ListPopupWindow
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout


class MainActivity : AppCompatActivity() {

    private lateinit var parent: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        parent = findViewById(R.id.parent)
        val menuTextView = findViewById<MaterialCardView>(R.id.popup_menu_card_view)
        val listPopupMenuTextView = findViewById<MaterialCardView>(R.id.list_popup_menu_card_view)
        val contextMenuTextView = findViewById<MaterialCardView>(R.id.context_menu_card_view)

        registerForContextMenu(contextMenuTextView)

        val textInputMenu = findViewById<TextInputLayout>(R.id.text_input_menu)
        val items = listOf("Apparels", "Electronics", "Sports", "Kitchen")
        val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, items)
        (textInputMenu.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        menuTextView.setOnClickListener { view ->
            showMenuOnClick(view)
        }

        listPopupMenuTextView.setOnClickListener { view ->
            showListPopUpMenu(view)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_react_me, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_like -> showSnackbar("You liked it.")
            R.id.menu_dislike -> showSnackbar("You disliked it.")
        }
        return super.onContextItemSelected(item)
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

    private fun showListPopUpMenu(view: View) {
        val listPopupWindow = ListPopupWindow(applicationContext, null, R.attr.listPopupWindowStyle)
        listPopupWindow.anchorView = view

        val items = listOf("One", "Two", "Three", "Four")
        val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, items)
        listPopupWindow.setAdapter(adapter)
        listPopupWindow.setBackgroundDrawable(
            ResourcesCompat.getDrawable(
                resources,
                R.color.list_bg,
                applicationContext.theme
            )
        )
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

    private fun showSnackbar(message: String) {
        Snackbar.make(parent, message, Snackbar.LENGTH_SHORT).show()
    }
}