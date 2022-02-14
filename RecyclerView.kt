package com.example.amcr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.EditText
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.amcr.data.ActivityEdit
import com.example.amcr.data.WorksRepository
import com.example.amcr.databinding.ActivityMainBinding
import com.example.amcr.databinding.ActivityRecyclerViewBinding
import org.w3c.dom.Text


class RecyclerView : AppCompatActivity() {
    lateinit var binding: ActivityRecyclerViewBinding
    var adapter = com.example.amcr.data.Adapter()
    var editWork: ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var worksCount = 1

        supportActionBar?.title = intent.getStringExtra("input_main")


        editWork = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                adapter.addWork(it.data?.getSerializableExtra("save_work") as WorksRepository)
                worksCount++
            }
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this@RecyclerView)
        binding.recyclerView.adapter = adapter
        binding.buttonAdd.setOnClickListener {
            val intent = Intent(this, ActivityEdit::class.java )
            intent.putExtra("list_count", worksCount)
            editWork?.launch(intent) }
    }


}

