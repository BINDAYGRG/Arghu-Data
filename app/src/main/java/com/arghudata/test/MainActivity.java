package com.arghudata.test;

import android.app.SearchManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Contact> contacts;
    private Adapter adapter, adb;
    private ApiInterface apiInterface;
    private SQLiteHandler db;
    ProgressBar progressBar;
    TextView search;
    String[] item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.prograss);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        db = new SQLiteHandler(getApplicationContext());
        fetchContact();

    }

    public void fetchContact() {

        if(db.getcount()==0){
               syncdata("data_arghu", "");
        }
           else {
               List<ContactsSQLite> allContacts = db.listContacts();
               adapter = new Adapter(allContacts, MainActivity.this);
               recyclerView.setAdapter(adapter);
               adapter.notifyDataSetChanged();
           }

    }
//for search function with menu and searchview
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchContact(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchContact(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void searchContact(String keyword) {
        progressBar.setVisibility(View.GONE);
        List<ContactsSQLite> allContacts = db.search(keyword);
        adapter = new Adapter(allContacts, MainActivity.this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.syncdata:
                syncdata("data_arghu", "");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void syncdata(String type, String key) {

        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Contact>> call = apiInterface.getContact(type, key);
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                List<Contact> contacts = response.body();
                db.deleteUsers();
                for (int i = 0; i<contacts.size(); i++) {
                    Contact name22 = contacts.get(i); //To get first item
                    String nameeng1 = name22.getNameeng();
                    String namenep1 = name22.getNamenep();
                    String oldaddresseng1 = name22.getOldaddresseng();
                    String oldaddressnep1 = name22.getOldaddressnep();
                    String currentaddresseng1 = name22.getCurrentaddresseng();
                    String currentaddressnep1 = name22.getCurrentaddressnep();
                    String vetieng1 = name22.getVetieng();
                    String vetinep1 = name22.getVetinep();
                    String syaieng1 = name22.getSyaieng();
                    String syainep1 = name22.getSyainep();
                    String reference1 = name22.getReference();
                    db.addUser(nameeng1, namenep1, oldaddresseng1, oldaddressnep1, currentaddresseng1, currentaddressnep1, vetieng1, vetinep1, syaieng1, syainep1, reference1);
                }
                progressBar.setVisibility(View.GONE);
                List<ContactsSQLite> allContacts = db.listContacts();
                adapter = new Adapter(allContacts, MainActivity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Error\n" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}