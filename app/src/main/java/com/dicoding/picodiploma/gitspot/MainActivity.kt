package com.dicoding.picodiploma.gitspot

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import com.dicoding.picodiploma.gitspot.databinding.ActivityMainBinding
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.gitspot.adapter.UserAdapter
import com.dicoding.picodiploma.gitspot.data.UserData
import com.dicoding.picodiploma.gitspot.ui.MenuFragment
import com.dicoding.picodiploma.gitspot.viewmodel.UserViewModel

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val userVm : UserViewModel by viewModels()
    private var adapter: UserAdapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val layoutManager = LinearLayoutManager(this)
        binding.rvAccount.layoutManager = layoutManager
        binding.rvAccount.adapter = adapter
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvAccount.addItemDecoration(itemDecoration)

        userVm.users.observe(this){
            it.items?.let { list ->
                adapter.submitList(list)
                binding.rvAccount.adapter = adapter
            }
        }

        adapter.setOnClickListener(object : UserAdapter.OnItemClickListener{
            override fun onClick(user: UserData) {
                val bundle = Bundle()
                bundle.putParcelable(DetailActivity.EXTRA_USERNAME, user)
                val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
                    putExtras(bundle)
                }
                startActivity(intent)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            /*
            Gunakan method ini ketika search selesai atau OK
             */
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                userVm.searchUser(query)
                searchView.clearFocus()
                return true
            }

            /*
            Gunakan method ini untuk merespon tiap perubahan huruf pada searchView
             */
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu1 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, MenuFragment())
                    .addToBackStack(null)
                    .commit()
                true
            }
            R.id.menu2 -> {
                val i = Intent(this, MenuActivity::class.java)
                startActivity(i)
                true
            }
            else -> true
        }
    }
}