package com.example.proje

import android.graphics.Rect
import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import coil.compose.AsyncImage

@Composable
fun MainScreen(navController: NavController) {
    val items = remember {
        mutableStateListOf(
            Item(
                "Kapadokya - Nevşehir",
                "Aslen Nevşehir’e bağlı olsa da, Kırşehir, Niğde, Aksaray ve Kayseri şehirlerini de içine alan Kapadokya Bölgesi, yapılan hesaplamalara göre 60 milyon yıl önce Erciyes",
                "https://cdn.prod.website-files.com/60423ce08bd7d3ce169059e4/625d1b5127854772cb94668a_uption-kapadokya.jpg"
            ),
            Item(
                "Ölüdeniz - Muğla (Fethiye)",
                "Muğla’nın Fethiye ilçesine bağlı bir belde olan Ölüdeniz, öncesinde eşsiz plajıyla ve muhteşem denizi",
                "https://cdn.prod.website-files.com/60423ce08bd7d3ce169059e4/625d1cbdd6bb00045333e1cc_uption-oludeniz.jpeg"
            ),
            Item(
                "Salda Gölü",
                "Yüzölçümü yaklaşık 44 kilometrekare olan ve Burdur'un Yeşilova ilçesine yaklaşık 4 kilometre uzaklıkta bulunan bu karstik göl",
                "https://cdn.prod.website-files.com/60423ce08bd7d3ce169059e4/625d1d9d8a25db64454342aa_uption-salda-golu-lake-salda.jpg"
            )
        )
    }

    var selectedTab by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        when (selectedTab) {
            0 -> {
                AndroidView(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    factory = { context ->
                        RecyclerView(context).apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = ItemAdapter(items)
                            addItemDecoration(object : RecyclerView.ItemDecoration() {
                                override fun getItemOffsets(
                                    outRect: Rect,
                                    view: View,
                                    parent: RecyclerView,
                                    state: RecyclerView.State
                                ) {
                                    outRect.bottom = 16
                                }
                            })
                            val itemTouchHelper =
                                ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
                                    ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0
                                ) {
                                    override fun onMove(
                                        recyclerView: RecyclerView,
                                        viewHolder: RecyclerView.ViewHolder,
                                        target: RecyclerView.ViewHolder
                                    ): Boolean {
                                        val fromPos = viewHolder.adapterPosition
                                        val toPos = target.adapterPosition
                                        items.apply {
                                            add(toPos, removeAt(fromPos))
                                        }
                                        adapter?.notifyItemMoved(fromPos, toPos)
                                        return true
                                    }

                                    override fun onSwiped(
                                        viewHolder: RecyclerView.ViewHolder,
                                        direction: Int
                                    ) {
                                    }
                                })
                            itemTouchHelper.attachToRecyclerView(this)
                        }
                    }
                )
            }

            1 -> {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "", style = MaterialTheme.typography.headlineMedium)
                    AsyncImage(
                        model= "https://static.ticimax.cloud/11141/uploads/urunresimleri/buyuk/kbk-market-kirmizi-hosgeldiniz-strafor-s-3be0.jpg",
                        contentDescription= "Hoşgeldin"
                    )

                }
            }
        }

        NavigationBar {
            NavigationBarItem(
                icon = { Icon(Icons.Filled.List, contentDescription = "Liste") },
                label = { Text("Liste") },
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 }
            )
            NavigationBarItem(
                icon = { Icon(Icons.Filled.Person, contentDescription = "Profil") },
                label = { Text("Profil") },
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 }
            )
        }
    }
}